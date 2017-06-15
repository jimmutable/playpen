package org.jimmutable.exception;

import java.util.Objects;

import org.jimmutable.core.objects.StandardImmutableObject;
import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.FieldName;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.core.serialization.writer.ObjectWriter;
import org.jimmutable.core.utils.Comparison;
import org.jimmutable.core.utils.Validator;

public class FormValidationErrorDetail extends StandardImmutableObject<FormValidationErrorDetail> 
{
	static public FieldName FIELD_ERROR_GENERAL	= new FieldName("general_error");
	
	static public final TypeName TYPE_NAME = new TypeName("form_validation_error_detail"); public TypeName getTypeName() { return TYPE_NAME; }
	
	static public final FieldDefinition.String FIELD_ERROR_FIELD = new FieldDefinition.String("field",null);
	static public final FieldDefinition.String FIELD_ERROR_MESSAGE = new FieldDefinition.String("message",null);
	
	private String error_field;
	private String message;
	
	public FormValidationErrorDetail(FieldName error_field, String message)
	{
		if ( error_field == null ) error_field = FIELD_ERROR_GENERAL;
		if ( message == null ) message = "Unknown error";
		
		this.error_field = error_field.getSimpleName();
		this.message = message;
	}
	
	public String getSimpleErrorField() { return error_field; }
	public String getSimpleMessage() { return message; }


	public void write(ObjectWriter writer) 
	{
		writer.writeString(FIELD_ERROR_FIELD, error_field);
		writer.writeString(FIELD_ERROR_MESSAGE, message);
	}

	@Override
	public int compareTo(FormValidationErrorDetail o) 
	{
		int ret = Comparison.startCompare();
		
		ret = Comparison.continueCompare(ret, getSimpleErrorField(), o.getSimpleErrorField());
		ret = Comparison.continueCompare(ret, getSimpleMessage(), o.getSimpleMessage());
		
		return ret;
	}

	@Override
	public void freeze() 
	{	
	}

	@Override
	public void normalize() 
	{	
	}

	@Override
	public void validate() 
	{
		Validator.notNull(error_field, message);
	}

	
	public int hashCode() 
	{
		return Objects.hash(error_field, message);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if ( !(obj instanceof FormValidationErrorDetail) ) return false;
		
		FormValidationErrorDetail other = (FormValidationErrorDetail)obj;
		
		if ( !this.getSimpleErrorField().equals(other.getSimpleErrorField()) ) return false;
		if ( !this.getSimpleMessage().equals(other.getSimpleMessage()) ) return false;
		
		return true;
	}
	
	public String toString() { return message; }
}
