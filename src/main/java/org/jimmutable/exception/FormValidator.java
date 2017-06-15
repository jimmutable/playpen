package org.jimmutable.exception;

import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.FieldName;

public class FormValidator 
{
	static public void nonBlank(FieldName field, String str, String error_message)
	{
		if ( str != null && str.trim().length() != 0 ) return;
		
		FormErrorDetail detail = new FormErrorDetail(field, error_message);
		
		throw new FormValidationException(detail);
	}
	
	static public void nonBlank(FieldDefinition def, String str, String error_message)
	{
		nonBlank(def.getSimpleFieldName(), str, error_message);
	}
}
