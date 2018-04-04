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

public class AllValidatorTest extends ValidatorTest {

	@Test
	public void testAll() {
		
		String msg1 = "First Message";
		String msg2 = "Second Message";
		String msg3 = "Third Message";
		
		Validator<String> v;
		
		v = Validator.all(String.class)
				.add(Validator.text().minLength(2, msg1))
				.add(Validator.text().maxLength(5, msg2))
				.add(Validator.text().regex("[a-z]+", msg3));
		
		assertValid(v, "abc");
		
		assertInvalid(v, "x", msg1);
		assertInvalid(v, "xxxxxx", msg2);
		assertInvalid(v, "123", msg3);
		
		assertInvalid(v, "1", msg1, msg3);
		assertInvalid(v, "123456", msg2, msg3);
		
		
		v = Validator.all(String.class, msg1)
				.add(Validator.text().minLength(2))
				.add(Validator.text().maxLength(5))
				.add(Validator.text().regex("[a-z]+"));
		
		assertValid(v, "abc");
		
		assertInvalid(v, "x", msg1);
		assertInvalid(v, "xxxxxx", msg1);
		assertInvalid(v, "123", msg1);
		
		assertInvalid(v, "1", msg1);
		assertInvalid(v, "123456", msg1);
	}
}
