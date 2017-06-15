package org.jimmutable.exception;

import org.jimmutable.core.exceptions.ValidationException;
import org.jimmutable.core.serialization.FieldName;

public class FormValidationException extends ValidationException 
{
	private FormValidationErrorDetail details;
	
	public FormValidationException(FormValidationErrorDetail details)
	{
		super(details.toString());
		
		this.details = details;
	}
	
	public FormValidationErrorDetail getSimpleErrorDetail() { return details; }
}
