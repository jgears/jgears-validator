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

public class AnyValidatorTest extends ValidatorTest {

	@Test
	public void testAny() {
		
		String msg1 = "First Message";
		String msg2 = "Second Message";
		String msg3 = "Third Message";
		
		Validator<String> v;
		
		v = Validator.any(String.class)
				.add(Validator.text().minLength(5, msg1))
				.add(Validator.text().maxLength(2, msg2))
				.add(Validator.text().regex("[a-z]{3}", msg3));
		
		assertValid(v, "12");
		assertValid(v, "ab");
		assertValid(v, "abc");
		assertValid(v, "12345");
		assertValid(v, "abcde");
		
		assertInvalid(v, "xxxx", msg1);
		assertInvalid(v, "1234", msg1);
		assertInvalid(v, "123", msg1);
		
		
		v = Validator.any(String.class, msg2)
				.add(Validator.text().minLength(5))
				.add(Validator.text().maxLength(2))
				.add(Validator.text().regex("[a-z]{3}"));
		
		assertValid(v, "12");
		assertValid(v, "ab");
		assertValid(v, "abc");
		assertValid(v, "12345");
		assertValid(v, "abcde");
		
		assertInvalid(v, "xxxx", msg2);
		assertInvalid(v, "1234", msg2);
		assertInvalid(v, "123", msg2);
	}
}
