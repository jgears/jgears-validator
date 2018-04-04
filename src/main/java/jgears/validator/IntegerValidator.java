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

public class IntegerValidator extends BasicValidator<Integer> {

	private Integer minValue;
	private String minValueMessage;
	
	private Integer maxValue;
	private String maxValueMessage;
	
	public IntegerValidator(String fieldName) {
		super(fieldName);
	}
	
	public IntegerValidator notNull() { return (IntegerValidator) super.notNull(); }
	
	public IntegerValidator notNull(String message) { return (IntegerValidator) super.notNull(message); }
	
	public IntegerValidator minValue(int minValue) {
		return minValue(minValue, null);
	}
	
	public IntegerValidator minValue(int minValue, String message) {
		this.minValue = minValue;
		this.minValueMessage = msg(message, Messages.MSG_INTEGER_MIN_VALUE, fieldName, minValue);
		return this;
	}
	
	public IntegerValidator maxValue(int maxValue) {
		return maxValue(maxValue, null);
	}
	
	public IntegerValidator maxValue(int maxValue, String message) {
		this.maxValue = maxValue;
		this.maxValueMessage = msg(message, Messages.MSG_INTEGER_MAX_VALUE, fieldName, maxValue);
		return this;
	}

	@Override
	protected void doValidate(Integer value, List<String> errors) {
		if (minValue != null && value < minValue) {
			errors.add(minValueMessage);
		}
		
		if (maxValue != null && value > maxValue) {
			errors.add(maxValueMessage);
		}
	}
}
