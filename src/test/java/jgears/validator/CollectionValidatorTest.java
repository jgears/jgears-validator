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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class CollectionValidatorTest extends BasicValidatorTest {

	@Test
	public void testNone() {
		testNone(() -> Validator.collection(), () -> Validator.collection(CUSTOM_FIELD_NAME), new ArrayList<Object>());
	}
	
	@Test
	public void testNotNull() {
		testNotNull(() -> Validator.collection(), () -> Validator.collection(CUSTOM_FIELD_NAME), new ArrayList<Object>());
	}
	
	@Test
	public void testNotEmpty() {
		CollectionValidator<Object> v;
		
		v = Validator.collection().notEmpty();
		assertValid(v, Arrays.asList(new Object()));
		assertInvalid(v, Collections.emptyList(), "Field cannot be empty.");
		
		v = Validator.collection(CUSTOM_FIELD_NAME).notEmpty();
		assertValid(v, Arrays.asList(new Object()));
		assertInvalid(v, Collections.emptyList(), CUSTOM_FIELD_NAME + " cannot be empty.");
		
		v = Validator.collection().notEmpty(CUSTOM_MESSAGE);
		assertValid(v, Arrays.asList(new Object()));
		assertInvalid(v, Collections.emptyList(), CUSTOM_MESSAGE);
	}
	
	@Test
	public void testMinSize() {
		CollectionValidator<Object> v;
		
		v = Validator.collection().minSize(2);
		assertValid(v, Arrays.asList(new Object(), new Object(), new Object()));
		assertValid(v, Arrays.asList(new Object(), new Object()));
		assertInvalid(v, Arrays.asList(new Object()), "Field cannot contain less than 2 items.");
		
		v = Validator.collection(CUSTOM_FIELD_NAME).minSize(2);
		assertValid(v, Arrays.asList(new Object(), new Object(), new Object()));
		assertValid(v, Arrays.asList(new Object(), new Object()));
		assertInvalid(v, Arrays.asList(new Object()), CUSTOM_FIELD_NAME + " cannot contain less than 2 items.");
		
		v = Validator.collection().minSize(2, CUSTOM_MESSAGE);
		assertValid(v, Arrays.asList(new Object(), new Object(), new Object()));
		assertValid(v, Arrays.asList(new Object(), new Object()));
		assertInvalid(v, Arrays.asList(new Object()), CUSTOM_MESSAGE);
	}
	
	@Test
	public void testMaxSize() {
		CollectionValidator<Object> v;
		
		v = Validator.collection().maxSize(2);
		assertValid(v, Arrays.asList(new Object()));
		assertValid(v, Arrays.asList(new Object(), new Object()));
		assertInvalid(v, Arrays.asList(new Object(), new Object(), new Object()), "Field cannot contain more than 2 items.");
		
		v = Validator.collection(CUSTOM_FIELD_NAME).maxSize(2);
		assertValid(v, Arrays.asList(new Object()));
		assertValid(v, Arrays.asList(new Object(), new Object()));
		assertInvalid(v, Arrays.asList(new Object(), new Object(), new Object()), CUSTOM_FIELD_NAME + " cannot contain more than 2 items.");
		
		v = Validator.collection().maxSize(2, CUSTOM_MESSAGE);
		assertValid(v, Arrays.asList(new Object()));
		assertValid(v, Arrays.asList(new Object(), new Object()));
		assertInvalid(v, Arrays.asList(new Object(), new Object(), new Object()), CUSTOM_MESSAGE);
	}
	
	@Test
	public void testItems() {
		CollectionValidator<Integer> v;
		
		v = Validator.collection(Integer.class).item(Validator.integer().minValue(0).maxValue(10));
		assertValid(v, Arrays.asList());
		assertValid(v, Arrays.asList(0, 10));
		assertInvalid(v, Arrays.asList(0, 10, 20), "Field cannot exceed 10.");
		
		v = Validator.collection(Integer.class).item(Validator.integer(CUSTOM_FIELD_NAME).minValue(0).maxValue(10));
		assertValid(v, Arrays.asList());
		assertValid(v, Arrays.asList(0, 10));
		assertInvalid(v, Arrays.asList(0, 10, 20), CUSTOM_FIELD_NAME + " cannot exceed 10.");
		
		v = Validator.collection(Integer.class).item(Validator.integer().minValue(0).maxValue(10, CUSTOM_MESSAGE));
		assertValid(v, Arrays.asList());
		assertValid(v, Arrays.asList(0, 10));
		assertInvalid(v, Arrays.asList(0, 10, 20), CUSTOM_MESSAGE);
	}
}
