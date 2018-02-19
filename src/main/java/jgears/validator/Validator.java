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
	
	protected final String fieldName;
	
	protected boolean notNull;
	protected String notNullMessage;
	
	public Validator(String fieldName) {
		this.fieldName = (fieldName != null && !fieldName.isEmpty()) ? fieldName : "Field";
	}
	
	public Validator<T> notNull() { return notNull(null); }
	
	public Validator<T> notNull(String message) {
		this.notNull = true;
		this.notNullMessage = msg(message, Messages.MSG_NOT_NULL, fieldName);
		return this;
	}

	public final List<String> validate(T value) {
		List<String> errors = new ArrayList<>();
		validate(value, errors);
		return errors;
	}
	
	public final void validate(T value, List<String> errors) {
		if (value == null) {
			if (notNull) {
				errors.add(notNullMessage);
			}
			
		} else {
			doValidate(value, errors);
		}
	}
	
	protected abstract void doValidate(T value, List<String> errors);
	
	protected String msg(String message, String defaultMessageFormat, Object ... args) {
		if (message != null && !message.trim().isEmpty()) { return message; }
		else { return String.format(defaultMessageFormat, args); }
	}
	
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
}
