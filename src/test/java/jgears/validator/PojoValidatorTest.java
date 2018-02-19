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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PojoValidatorTest extends ValidatorTest {

	@Test
	public void testNone() {
		testNone(() -> Validator.pojo(), () -> Validator.pojo(CUSTOM_FIELD_NAME), new Object());
	}
	
	@Test
	public void testNotNull() {
		testNotNull(() -> Validator.pojo(), () -> Validator.pojo(CUSTOM_FIELD_NAME), new Object());
	}
	
	@Test
	public void validateFields() {
		PojoValidator<TestClass> v;
		
		v = Validator.pojo(TestClass.class)
				.field(TestClass::getValue1, Validator.integer().minValue(0))
				.field(TestClass::getValue2, Validator.text(CUSTOM_FIELD_NAME).regex("[a-z]+"))
				.field(TestClass::getValue3, Validator.collection().maxSize(2, CUSTOM_MESSAGE));
		
		assertValid(v, new TestClass(10, "abc", new Object()));
		assertValid(v, new TestClass(0, "xyz", new Object(), new Object()));
		assertInvalid(v, new TestClass(-1, "xyz", new Object(), new Object()), "Field cannot be less than 0.");
		assertInvalid(v, new TestClass(0, "123", new Object(), new Object()), CUSTOM_FIELD_NAME + " is invalid.");
		assertInvalid(v, new TestClass(0, "xyz", new Object(), new Object(), new Object()), CUSTOM_MESSAGE);
	}
	
	public static class TestClass {
		
		private final int value1;
		private final String value2;
		private final List<Object> value3;
		
		public TestClass(int value1, String value2, Object ... value3) {
			this.value1 = value1;
			this.value2 = value2;
			this.value3 = Arrays.asList(value3);
		}

		public int getValue1() {
			return value1;
		}
		
		public String getValue2() {
			return value2;
		}
		
		public List<Object> getValue3() {
			return value3;
		}
	}
}
