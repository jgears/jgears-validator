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
import java.util.function.Function;

public class PojoValidator<T> extends Validator<T> {

	private List<PojoValidatorEntry<Object>> entries = new ArrayList<>();

	public PojoValidator(String fieldName) {
		super(fieldName);
	}
	
	public PojoValidator<T> notNull() { return (PojoValidator<T>) super.notNull(); }
	
	public PojoValidator<T> notNull(String message) { return (PojoValidator<T>) super.notNull(message); }
	
	@SuppressWarnings("unchecked")
	public <F> PojoValidator<T> field(Function<T, F> accessor, Validator<F> validator) {
		if (accessor != null && validator != null) {
			entries.add((PojoValidatorEntry<Object>) new PojoValidatorEntry<F>(accessor, validator));
		}
		return this;
	}
	
	@Override
	protected void doValidate(T value, List<String> errors) {
		for (PojoValidatorEntry<Object> entry : entries) {
			entry.getValidator().validate(entry.getAccessor().apply(value), errors);
		}
	}

	private class PojoValidatorEntry<F> {
		
		private final Function<T, F> accessor;
		private final Validator<F> validator;

		public PojoValidatorEntry(Function<T, F> accessor, Validator<F> validator) {
			this.accessor = accessor;
			this.validator = validator;
		}
		
		public Function<T, F> getAccessor() {
			return accessor;
		}
		
		public Validator<F> getValidator() {
			return validator;
		}
	}
}
