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

public class TextValidatorTest extends ValidatorTest {

	@Test
	public void testNone() {
		testNone(() -> Validator.text(), () -> Validator.text(CUSTOM_FIELD_NAME), "");
	}
	
	@Test
	public void testNotNull() {
		testNotNull(() -> Validator.text(), () -> Validator.text(CUSTOM_FIELD_NAME), "");
	}
	
	@Test
	public void testMinLength() {
		TextValidator v;
		
		v = Validator.text().minLength(2);
		assertValid(v, "xxx");
		assertValid(v, "xx");
		assertInvalid(v, "x", "Field cannot be less than 2 characters long.");
		
		v = Validator.text(CUSTOM_FIELD_NAME).minLength(2);
		assertValid(v, "xxx");
		assertValid(v, "xx");
		assertInvalid(v, "x", CUSTOM_FIELD_NAME + " cannot be less than 2 characters long.");
		
		v = Validator.text().minLength(2, CUSTOM_MESSAGE);
		assertValid(v, "xxx");
		assertValid(v, "xx");
		assertInvalid(v, "x", CUSTOM_MESSAGE);
	}
	
	@Test
	public void testMaxLength() {
		TextValidator v;
		
		v = Validator.text().maxLength(2);
		assertValid(v, "x");
		assertValid(v, "xx");
		assertInvalid(v, "xxx", "Field cannot be more than 2 characters long.");
		
		v = Validator.text(CUSTOM_FIELD_NAME).maxLength(2);
		assertValid(v, "x");
		assertValid(v, "xx");
		assertInvalid(v, "xxx", CUSTOM_FIELD_NAME + " cannot be more than 2 characters long.");
		
		v = Validator.text().maxLength(2, CUSTOM_MESSAGE);
		assertValid(v, "x");
		assertValid(v, "xx");
		assertInvalid(v, "xxx", CUSTOM_MESSAGE);
	}
	
	@Test
	public void testRegex() {
		TextValidator v;
		
		v = Validator.text().regex("[a-z]+");
		assertValid(v, "abc");
		assertInvalid(v, "123", "Field is invalid.");
		
		v = Validator.text(CUSTOM_FIELD_NAME).regex("[a-z]+");
		assertValid(v, "abc");
		assertInvalid(v, "123", CUSTOM_FIELD_NAME + " is invalid.");
		
		v = Validator.text(CUSTOM_FIELD_NAME).regex("[a-z]+", CUSTOM_MESSAGE);
		assertValid(v, "abc");
		assertInvalid(v, "123", CUSTOM_MESSAGE);
	}
}
