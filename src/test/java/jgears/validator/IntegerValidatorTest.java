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

import org.junit.Test;

public class IntegerValidatorTest extends BasicValidatorTest {

	@Test
	public void testNone() {
		testNone(() -> Validator.integer(), () -> Validator.integer(CUSTOM_FIELD_NAME), 0);
	}
	
	@Test
	public void testNotNull() {
		testNotNull(() -> Validator.integer(), () -> Validator.integer(CUSTOM_FIELD_NAME), 0);
	}
	
	@Test
	public void testMinValue() {
		IntegerValidator v;
		
		v = Validator.integer().minValue(-10);
		assertValid(v, -9);
		assertValid(v, -10);
		assertInvalid(v, -11, "Field cannot be less than -10.");
		
		v = Validator.integer(CUSTOM_FIELD_NAME).minValue(-10);
		assertValid(v, -9);
		assertValid(v, -10);
		assertInvalid(v, -11, CUSTOM_FIELD_NAME + " cannot be less than -10.");
		
		v = Validator.integer().minValue(-10, CUSTOM_MESSAGE);
		assertValid(v, -9);
		assertValid(v, -10);
		assertInvalid(v, -11, CUSTOM_MESSAGE);
	}
	
	@Test
	public void testMaxValue() {
		IntegerValidator v;
		
		v = Validator.integer().maxValue(10);
		assertValid(v, 9);
		assertValid(v, 10);
		assertInvalid(v, 11, "Field cannot exceed 10.");
		
		v = Validator.integer(CUSTOM_FIELD_NAME).maxValue(10);
		assertValid(v, 9);
		assertValid(v, 10);
		assertInvalid(v, 11, CUSTOM_FIELD_NAME + " cannot exceed 10.");
		
		v = Validator.integer().maxValue(10, CUSTOM_MESSAGE);
		assertValid(v, 9);
		assertValid(v, 10);
		assertInvalid(v, 11, CUSTOM_MESSAGE);
	}
}
