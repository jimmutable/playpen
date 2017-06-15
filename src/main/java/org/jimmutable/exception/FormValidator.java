package org.jimmutable.exception;

import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.FieldName;

public class FormValidator 
{
	static public void nonBlank(FieldName field, String str)
	{
		if ( str != null && str.trim().length() != 0 ) return;
		
		FormErrorDetail detail = new FormErrorDetail(field, "This field is required");
		
		throw new FormValidationException(detail);
	}
	
	static public void nonBlank(FieldDefinition def, String str)
	{
		nonBlank(def.getSimpleFieldName(), str);
	}
}
