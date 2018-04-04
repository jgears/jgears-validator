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

public class DecimalValidator extends BasicValidator<Double> {

	private Double minValue;
	private String minValueMessage;
	
	private Double maxValue;
	private String maxValueMessage;
	
	public DecimalValidator(String fieldName) {
		super(fieldName);
	}
	
	public DecimalValidator notNull() { return (DecimalValidator) super.notNull(); }
	
	public DecimalValidator notNull(String message) { return (DecimalValidator) super.notNull(message); }
	
	public DecimalValidator minValue(double minValue) {
		return minValue(minValue, null);
	}
	
	public DecimalValidator minValue(double minValue, String message) {
		this.minValue = minValue;
		this.minValueMessage = msg(message, Messages.MSG_DECIMAL_MIN_VALUE, fieldName, minValue);
		return this;
	}
	
	public DecimalValidator maxValue(double maxValue) {
		return maxValue(maxValue, null);
	}
	
	public DecimalValidator maxValue(double maxValue, String message) {
		this.maxValue = maxValue;
		this.maxValueMessage = msg(message, Messages.MSG_DECIMAL_MAX_VALUE, fieldName, maxValue);
		return this;
	}

	@Override
	protected void doValidate(Double value, List<String> errors) {
		if (minValue != null && value < minValue) {
			errors.add(minValueMessage);
		}
		
		if (maxValue != null && value > maxValue) {
			errors.add(maxValueMessage);
		}
	}
}
