/*
 * Copyright (C) 2018 jGears
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package jgears.validator;

import java.util.List;
import java.util.function.Supplier;

public abstract class ValidatorTest extends org.junit.Assert {
	
	protected static final String CUSTOM_FIELD_NAME = "xyz";
	protected static final String CUSTOM_MESSAGE = "custom message";

	protected <T> void testNone(Supplier<Validator<T>> withoutFieldName, Supplier<Validator<T>> withFieldName, T value) {
		assertValid(withoutFieldName.get(), null);
		assertValid(withoutFieldName.get(), value);
		assertValid(withFieldName.get(), null);
		assertValid(withFieldName.get(), value);
	}
	
	protected <T> void testNotNull(Supplier<Validator<T>> withoutFieldName, Supplier<Validator<T>> withFieldName, T value) {
		assertInvalid(withoutFieldName.get().notNull(), null, "Field cannot be null.");
		assertValid(withoutFieldName.get().notNull(), value);
		
		assertInvalid(withoutFieldName.get().notNull(CUSTOM_MESSAGE), null, CUSTOM_MESSAGE);
		assertValid(withoutFieldName.get().notNull(CUSTOM_MESSAGE), value);
		
		assertInvalid(withFieldName.get().notNull(), null, CUSTOM_FIELD_NAME + " cannot be null.");
		assertValid(withFieldName.get().notNull(), value);
	}
	
	protected <T> void assertValid(Validator<T> v, T value) {
		List<String> errors = v.validate(value);
		assertEquals(0, errors.size());
	}
	
	protected <T> void assertInvalid(Validator<T> v, T value, String message) {
		List<String> errors = v.validate(value);
		assertEquals(1, errors.size());
		assertEquals(message, errors.get(0));
	}
}
