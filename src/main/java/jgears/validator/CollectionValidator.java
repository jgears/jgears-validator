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

import java.util.Collection;
import java.util.List;

public class CollectionValidator<T> extends BasicValidator<Collection<T>> {
	
	private boolean notEmpty;
	private String notEmptyMessage;
	
	private Integer minSize;
	private String minSizeMessage;
	
	private Integer maxSize;
	private String maxSizeMessage;
	
	private Validator<T> itemValidator;

	public CollectionValidator(String fieldName) {
		super(fieldName);
	}
	
	public CollectionValidator<T> notNull() { return (CollectionValidator<T>) super.notNull(); }
	
	public CollectionValidator<T> notNull(String message) { return (CollectionValidator<T>) super.notNull(message); }
	
	public CollectionValidator<T> notEmpty() { return notEmpty(null); }
			
	public CollectionValidator<T> notEmpty(String message) {
		this.notEmpty = true;
		this.notEmptyMessage = msg(message, Messages.MSG_COLLECTION_NOT_EMPTY, fieldName);
		return this;
	}
	
	public CollectionValidator<T> notNullOrEmpty() { return notNullOrEmpty(null); }
	
	public CollectionValidator<T> notNullOrEmpty(String message) {
		this.notNull(message);
		this.notEmpty(message);
		return this;
	}
	
	public CollectionValidator<T> minSize(int minSize) {
		return minSize(minSize, null);
	}
	
	public CollectionValidator<T> minSize(int minSize, String message) {
		this.minSize = minSize;
		this.minSizeMessage = msg(message, Messages.MSG_COLLECTION_MIN_SIZE, fieldName, minSize);
		return this;
	}
	
	public CollectionValidator<T> maxSize(int maxSize) {
		return maxSize(maxSize, null);
	}
	
	public CollectionValidator<T> maxSize(int maxSize, String message) {
		this.maxSize = maxSize;
		this.maxSizeMessage = msg(message, Messages.MSG_COLLECTION_MAX_SIZE, fieldName, maxSize);
		return this;
	}
	
	public CollectionValidator<T> item(Validator<T> itemValidator) {
		this.itemValidator = itemValidator;
		return this;
	}
	
	@Override
	protected void doValidate(Collection<T> value, List<String> errors) {
		if (notEmpty && value.isEmpty()) {
			errors.add(notEmptyMessage);
		}
		
		if (minSize != null && value.size() < minSize) {
			errors.add(minSizeMessage);
		}
		
		if (maxSize != null && value.size() > maxSize) {
			errors.add(maxSizeMessage);
		}
		
		if (itemValidator != null) {
			for (T item : value) {
				itemValidator.validate(item, errors);
			}
		}
	}
}
