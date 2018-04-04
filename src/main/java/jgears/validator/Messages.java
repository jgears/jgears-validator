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

class Messages {
	
	public static final String MSG_NOT_NULL = "%s cannot be null.";
	
	public static final String MSG_INTEGER_MIN_VALUE = "%s cannot be less than %d.";
	public static final String MSG_INTEGER_MAX_VALUE = "%s cannot exceed %d.";
	
	public static final String MSG_DECIMAL_MIN_VALUE = "%s cannot be less than %.2f.";
	public static final String MSG_DECIMAL_MAX_VALUE = "%s cannot exceed %.2f.";
	
	public static final String MSG_TEXT_NOT_EMPTY = "%s cannot be empty.";
	public static final String MSG_TEXT_MIN_LENGTH = "%s cannot be less than %d characters long.";
	public static final String MSG_TEXT_MAX_LENGTH = "%s cannot be more than %d characters long.";
	public static final String MSG_TEXT_REGEX = "%s is invalid.";
	
	public static final String MSG_COLLECTION_NOT_EMPTY = "%s cannot be empty.";
	public static final String MSG_COLLECTION_MIN_SIZE = "%s cannot contain less than %d items.";
	public static final String MSG_COLLECTION_MAX_SIZE = "%s cannot contain more than %d items.";
}
