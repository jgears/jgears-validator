package jgears.validator;

import java.util.List;

abstract class BasicValidator<T> extends Validator<T> {
	
	protected final String fieldName;
	
	protected boolean notNull;
	protected String notNullMessage;
	
	public BasicValidator(String fieldName) {
		this.fieldName = (fieldName != null && !fieldName.isEmpty()) ? fieldName : "Field";
	}
	
	public Validator<T> notNull() { return notNull(null); }
	
	public Validator<T> notNull(String message) {
		this.notNull = true;
		this.notNullMessage = msg(message, Messages.MSG_NOT_NULL, fieldName);
		return this;
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
}
