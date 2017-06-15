package org.jimmutable;

import javax.servlet.http.HttpServletRequest;

import org.jimmutable.core.objects.StandardObject;
import org.jimmutable.core.serialization.FieldName;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.exception.FormErrorDetail;
import org.jimmutable.exception.FormValidationException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONPostReader 
{ 
	private boolean json_valid;
	private JSONObject json;
	
	private TypeName set_type;
	 
	
	public JSONPostReader(HttpServletRequest req)
	{
		this(req,null);
	}
	
	public JSONPostReader(HttpServletRequest req, TypeName type)
	{
		try
		{ 
			this.set_type = type;
			json = new JSONObject(new JSONTokener(req.getReader()));
			
			if ( type != null && !json.has(FieldName.FIELD_NAME_TYPE_HINT.getSimpleName()) )
			{
				json.put(FieldName.FIELD_NAME_TYPE_HINT.getSimpleName(), type.getSimpleName().toString());
			}
			
			json_valid = true;
		}
		catch(Exception e)
		{
			json = new JSONObject("{}");
			json_valid = false;
		}
	}
	
	public String toString()
	{
		return json.toString();
	}
	
	public boolean getSimpleJsonValid() { return json_valid; }
	
	public boolean has(String key_name)
	{
		return json.has(key_name);
	}
	
	public String getOptionalString(String key_name, String default_value)
	{
		if ( key_name == null ) return default_value;
		
		if ( !json.has(key_name) ) return default_value;
		
		return json.getString(key_name);
	}
	
	public StandardObject getStandardImmutableObject() throws FormValidationException
	{
		try
		{
			StandardObject ret = StandardObject.deserialize(json.toString());
			
			if ( set_type != null && !set_type.equals(ret.getTypeName()) )
			{
				FormErrorDetail detail = new FormErrorDetail(FormErrorDetail.FIELD_ERROR_GENERAL, "Posted data is not of type "+set_type);
				throw new FormValidationException(detail);
			}
			
			return ret;
		}
		catch(FormValidationException fve) 
		{ 
			fve.printStackTrace();
			throw fve; 
		}
		catch(Exception e)
		{
			Throwable cause = e.getCause();
			if ( cause != null && cause instanceof FormValidationException )
				throw (FormValidationException)cause;
			
			
			FormErrorDetail detail = new FormErrorDetail(FormErrorDetail.FIELD_ERROR_GENERAL, e.getMessage());
			throw new FormValidationException(detail);
		}
	}
}
