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

import java.util.List;

public class TextValidator extends Validator<String> {

	private Integer minLength;
	private String minLengthMessage;
	
	private Integer maxLength;
	private String maxLengthMessage;
	
	private String regex;
	private String regexMessage;
	
	public TextValidator(String fieldName) {
		super(fieldName);
	}
	
	public TextValidator notNull() { return (TextValidator) super.notNull(); }
	
	public TextValidator notNull(String message) { return (TextValidator) super.notNull(message); }
	
	public TextValidator minLength(int minLength) {
		return minLength(minLength, null);
	}
	
	public TextValidator minLength(int minLength, String message) {
		this.minLength = minLength;
		this.minLengthMessage = msg(message, Messages.MSG_TEXT_MIN_LENGTH, fieldName, minLength);
		return this;
	}
	
	public TextValidator maxLength(int maxLength) {
		return maxLength(maxLength, null);
	}
	
	public TextValidator maxLength(int maxLength, String message) {
		this.maxLength = maxLength;
		this.maxLengthMessage = msg(message, Messages.MSG_TEXT_MAX_LENGTH, fieldName, maxLength);
		return this;
	}
	
	public TextValidator regex(String regex) {
		return regex(regex, null);
	}
	
	public TextValidator regex(String regex, String message) {
		this.regex = regex;
		this.regexMessage = msg(message, Messages.MSG_TEXT_REGEX, fieldName);
		return this;
	}

	@Override
	protected void doValidate(String value, List<String> errors) {
		if (minLength != null && value.length() < minLength) {
			errors.add(minLengthMessage);
		}
		
		if (maxLength != null && value.length() > maxLength) {
			errors.add(maxLengthMessage);
		}
		
		if (regex != null && !value.matches(regex)) {
			errors.add(regexMessage);
		}
	}
}
