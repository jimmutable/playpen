package org.jimmutable.api.user;

import org.jimmutable.core.objects.StandardImmutableObject;
import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.jimmutable.core.serialization.writer.ObjectWriter;
import org.jimmutable.core.utils.Comparison;
import org.jimmutable.core.utils.Normalizer;
import org.jimmutable.core.utils.Optional;
import org.jimmutable.exception.FormErrorDetail;
import org.jimmutable.exception.FormValidationException;
import org.jimmutable.exception.FormValidator;

import com.google.api.client.util.Objects;

public class RequestNewUserAccount extends StandardImmutableObject<RequestNewUserAccount>
{
	static public final TypeName TYPE_NAME = new TypeName("digitalpanada.api.v1_0.users.request_new_user_account"); public TypeName getTypeName() { return TYPE_NAME; }
	
	static public final FieldDefinition.String FIELD_FIRST_NAME = new FieldDefinition.String("first_name",null); 
	static public final FieldDefinition.String FIELD_LAST_NAME = new FieldDefinition.String("last_name",null); 
	
	static public final FieldDefinition.String FIELD_EMAIL_ADDRESS = new FieldDefinition.String("email_address",null); 
	
	static public final FieldDefinition.String FIELD_TITLE = new FieldDefinition.String("title",null); 
	
	static public final FieldDefinition.String FIELD_MOBILE_PHONE_NUMBER = new FieldDefinition.String("mobile_phone_number",null); 
	static public final FieldDefinition.String FIELD_WORK_PHONE_NUMBER = new FieldDefinition.String("work_phone_number",null); 
	
	static public final FieldDefinition.String FIELD_TIMEZONE = new FieldDefinition.String("timezone",null); 
	
	static public final FieldDefinition.String FIELD_PASSWORD = new FieldDefinition.String("password",null); 
	
	
	private String first_name; // Required
	private String last_name; // Required
	
	private String email_address; // Required
	
	private String title;  // Optional
	
	private String phone_number_mobile; // optional
	private String phone_number_work; // optional
	
	private String timezone; // required
	
	private String password; // required
	
	public RequestNewUserAccount(ObjectParseTree t)
	{
		
		first_name = t.getString(FIELD_FIRST_NAME);
		last_name = t.getString(FIELD_LAST_NAME);
		
		email_address = t.getString(FIELD_EMAIL_ADDRESS);
		
		title = t.getString(FIELD_TITLE);
		
		phone_number_mobile = t.getString(FIELD_MOBILE_PHONE_NUMBER);
		phone_number_work = t.getString(FIELD_WORK_PHONE_NUMBER);
		
		timezone = t.getString(FIELD_TIMEZONE);
		
		password = t.getString(FIELD_PASSWORD);
	}
	
	public String getSimpleFirstName() { return first_name; }
	public String getSimpleLastName() { return last_name; }
	
	public String getSimpleEmailAddress() { return email_address; }
	
	public String getSimpleTimezone() { return timezone; }
	
	public String getSimplePassword() { return password; }
	
	
	public boolean hasMobilePhoneNumber() { return Optional.has(phone_number_mobile, null); }
	public boolean hasWorkPhoneNumber() { return Optional.has(phone_number_work, null); }
	public boolean hasTitle() { return Optional.has(title, null); }
	
	public String getOptionalMobilePhoneNumber(String default_value) { return Optional.getOptional(phone_number_mobile, null, default_value); }
	public String getOptionalWorkPhoneNumber(String default_value) { return Optional.getOptional(phone_number_work, null, default_value); }
	public String getOptionalTitle(String default_value) { return Optional.getOptional(title, null, default_value); }


	public void write(ObjectWriter writer) 
	{
		writer.writeString(FIELD_FIRST_NAME, getSimpleFirstName());
		writer.writeString(FIELD_LAST_NAME, getSimpleLastName());
		
		writer.writeString(FIELD_EMAIL_ADDRESS, getSimpleEmailAddress());
		
		writer.writeString(FIELD_TITLE, getOptionalTitle(null));
		
		writer.writeString(FIELD_MOBILE_PHONE_NUMBER, getOptionalMobilePhoneNumber(null));
		writer.writeString(FIELD_WORK_PHONE_NUMBER, getOptionalWorkPhoneNumber(null));
		
		writer.writeString(FIELD_TIMEZONE, getSimpleTimezone());
	}

	public int compareTo(RequestNewUserAccount o) 
	{
		int ret = Comparison.startCompare();
		
		ret = Comparison.continueCompare(ret, getSimpleEmailAddress(), o.getSimpleEmailAddress());
		
		return ret;
	}

	public void freeze() 
	{
	}

	@Override
	public void normalize() 
	{
		email_address = Normalizer.lowerCase(email_address);
		
		first_name = Normalizer.trim(first_name);
		last_name = Normalizer.trim(last_name);
		
		email_address = Normalizer.trim(email_address);
		
		title = Normalizer.trim(title);
		
		phone_number_mobile = Normalizer.trim(phone_number_mobile);
		phone_number_work = Normalizer.trim(phone_number_work);
		
		timezone = Normalizer.trim(timezone);
		
		password = Normalizer.trim(password);
	}

	@Override
	public void validate() 
	{
		FormValidator.nonBlank(FIELD_FIRST_NAME, first_name, "A first name is required");
		FormValidator.nonBlank(FIELD_LAST_NAME, last_name, "A last name is required");
		FormValidator.nonBlank(FIELD_EMAIL_ADDRESS, email_address, "A valid email address is required");
		FormValidator.nonBlank(FIELD_TIMEZONE, timezone, "A timezone is requried");
		
		FormValidator.nonBlank(FIELD_PASSWORD, password, "A password (case sensative, minimum of 5 characters) is requried");
		
		if ( password.length() < 5 )
		{
			throw new FormValidationException(new FormErrorDetail(FIELD_PASSWORD.getSimpleFieldName(), "A password (case sensative, minimum of 5 characters) is requried"));
		}
	}


	public int hashCode() 
	{
		return getSimpleEmailAddress().hashCode();
	}


	public boolean equals(Object obj) 
	{
		if ( !(obj instanceof RequestNewUserAccount) )  return false;
	
		RequestNewUserAccount other = (RequestNewUserAccount)obj;
		
		if ( !Objects.equal(this.getSimpleFirstName(), other.getSimpleFirstName()) ) return false;
		if ( !Objects.equal(this.getSimpleLastName(), other.getSimpleLastName()) ) return false;
		
		if ( !Objects.equal(this.getSimpleEmailAddress(), other.getSimpleEmailAddress()) ) return false;
		
		if ( !Objects.equal(this.getOptionalTitle(null), other.getOptionalTitle(null)) ) return false;
		
		if ( !Objects.equal(this.getOptionalMobilePhoneNumber(null), other.getOptionalMobilePhoneNumber(null)) ) return false;
		if ( !Objects.equal(this.getOptionalWorkPhoneNumber(null), other.getOptionalWorkPhoneNumber(null)) ) return false;
		
		if ( !Objects.equal(this.getSimpleTimezone(), other.getSimpleTimezone()) ) return false;
		
		if ( !Objects.equal(this.getSimplePassword(), other.getSimplePassword()) ) return false;
		
		return true;
	}
}
