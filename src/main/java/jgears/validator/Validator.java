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
import java.util.List;

public abstract class Validator<T> {

	public final List<String> validate(T value) {
		List<String> errors = new ArrayList<>();
		validate(value, errors);
		return errors;
	}
	
	public abstract void validate(T value, List<String> errors);
	
	public static IntegerValidator integer() { return new IntegerValidator(null); }
	public static IntegerValidator integer(String fieldName) { return new IntegerValidator(fieldName); }
	
	public static DecimalValidator decimal() { return new DecimalValidator(null); }
	public static DecimalValidator decimal(String fieldName) { return new DecimalValidator(fieldName); }
	
	public static TextValidator text() { return new TextValidator(null); }
	public static TextValidator text(String fieldName) { return new TextValidator(fieldName); }
	
	public static <T> PojoValidator<T> pojo() { return new PojoValidator<T>(null); }
	public static <T> PojoValidator<T> pojo(Class<T> cls) { return new PojoValidator<T>(null); }
	public static <T> PojoValidator<T> pojo(String fieldName) { return new PojoValidator<T>(fieldName); }
	public static <T> PojoValidator<T> pojo(String fieldName, Class<T> cls) { return new PojoValidator<T>(fieldName); }
	
	public static <T> CollectionValidator<T> collection() { return new CollectionValidator<T>(null); }
	public static <T> CollectionValidator<T> collection(Class<T> cls) { return new CollectionValidator<T>(null); }
	public static <T> CollectionValidator<T> collection(String fieldName) { return new CollectionValidator<T>(fieldName); }
	public static <T> CollectionValidator<T> collection(String fieldName, Class<T> cls) { return new CollectionValidator<T>(fieldName); }
	
	public static <T> AllValidator<T> all(Class<T> cls) { return new AllValidator<>(null); }
	public static <T> AllValidator<T> all(Class<T> cls, String message) { return new AllValidator<>(message); }
	
	@SafeVarargs
	public static <T> AllValidator<T> all(Validator<T> ... validators) { return all(null, validators); }
	
	@SafeVarargs
	public static <T> AllValidator<T> all(String message, Validator<T> ... validators) {
		AllValidator<T> all = new AllValidator<>(message);
		for (Validator<T> v : validators) { all.add(v); }
		return all;
	}
	
	public static <T> AnyValidator<T> any(Class<T> cls) { return new AnyValidator<>(null); }
	public static <T> AnyValidator<T> any(Class<T> cls, String message) { return new AnyValidator<>(message); }
	
	@SafeVarargs
	public static <T> AnyValidator<T> any(Validator<T> ... validators) { return any(null, validators); }
	
	@SafeVarargs
	public static <T> AnyValidator<T> any(String message, Validator<T> ... validators) {
		AnyValidator<T> any = new AnyValidator<>(message);
		for (Validator<T> v : validators) { any.add(v); }
		return any;
	}
}
