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

public class AllValidator<T> extends Validator<T> {
	
	private String message;
	private List<Validator<T>> validators = new ArrayList<>();
	
	public AllValidator(String message) {
		this.message = message;
	}

	public AllValidator<T> add(Validator<T> validator) {
		if (validator != null) {
			validators.add(validator);
		}
		
		return this;
	}
	
	@Override
	public void validate(T value, List<String> errors) {
		List<String> tempErrors = new ArrayList<>();
		
		for (Validator<T> v : validators) {
			v.validate(value, tempErrors);
		}
		
		if (!tempErrors.isEmpty()) {
			if (message != null) { errors.add(message); }
			else { errors.addAll(tempErrors); }
		}
	}

}
