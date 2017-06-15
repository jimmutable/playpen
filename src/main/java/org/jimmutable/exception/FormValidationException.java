package org.jimmutable.exception;

import org.jimmutable.core.exceptions.ValidationException;
import org.jimmutable.core.serialization.FieldName;

public class FormValidationException extends ValidationException 
{
	private FormErrorDetail details;
	
	public FormValidationException(FormErrorDetail details)
	{
		super(details.toString());
		
		this.details = details;
	}
	
	public FormErrorDetail getSimpleErrorDetail() { return details; }
}
