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

public class DecimalValidatorTest extends ValidatorTest {

	@Test
	public void testNone() {
		testNone(() -> Validator.decimal(), () -> Validator.decimal(CUSTOM_FIELD_NAME), 0.0);
	}
	
	@Test
	public void testNotNull() {
		testNotNull(() -> Validator.decimal(), () -> Validator.decimal(CUSTOM_FIELD_NAME), 0.0);
	}
	
	@Test
	public void testMinValue() {
		DecimalValidator v;
		
		v = Validator.decimal().minValue(-10.1);
		assertValid(v, -10.0);
		assertValid(v, -10.1);
		assertInvalid(v, -10.2, "Field cannot be less than -10.10.");
		
		v = Validator.decimal(CUSTOM_FIELD_NAME).minValue(-10.1);
		assertValid(v, -10.0);
		assertValid(v, -10.1);
		assertInvalid(v, -10.2, CUSTOM_FIELD_NAME + " cannot be less than -10.10.");
		
		v = Validator.decimal().minValue(-10.1, CUSTOM_MESSAGE);
		assertValid(v, -10.0);
		assertValid(v, -10.1);
		assertInvalid(v, -10.2, CUSTOM_MESSAGE);
	}
	
	@Test
	public void testMaxValue() {
		DecimalValidator v;
		
		v = Validator.decimal().maxValue(10.1);
		assertValid(v, 10.0);
		assertValid(v, 10.1);
		assertInvalid(v, 10.2, "Field cannot exceed 10.10.");
		
		v = Validator.decimal(CUSTOM_FIELD_NAME).maxValue(10.1);
		assertValid(v, 10.0);
		assertValid(v, 10.1);
		assertInvalid(v, 10.2, CUSTOM_FIELD_NAME + " cannot exceed 10.10.");
		
		v = Validator.decimal().maxValue(10.1, CUSTOM_MESSAGE);
		assertValid(v, 10.0);
		assertValid(v, 10.1);
		assertInvalid(v, 10.2, CUSTOM_MESSAGE);
	}
}
