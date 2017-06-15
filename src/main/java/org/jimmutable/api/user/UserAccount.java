package org.jimmutable.api.user;

import org.jimmutable.core.objects.StandardImmutableObject;
import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.jimmutable.core.serialization.writer.ObjectWriter;
import org.jimmutable.core.utils.Comparison;
import org.jimmutable.core.utils.Normalizer;
import org.jimmutable.core.utils.Optional;
import org.jimmutable.core.utils.Validator;
import org.jimmutable.datastore.DatastoreReader;
import org.jimmutable.datastore.ObjectParseTreeReader;
import org.jimmutable.datastore.ObjectReader;

import com.google.api.client.util.Objects;
import com.google.cloud.datastore.Entity;

public class UserAccount extends StandardImmutableObject<UserAccount>
{
	static public final long ID_NOT_ASSIGNED_YET = -1;
	
	
	static public final TypeName TYPE_NAME = new TypeName("digitalpanada.user.account"); public TypeName getTypeName() { return TYPE_NAME; }
	 
	static public final FieldDefinition.Long FIELD_ID = new FieldDefinition.Long("id",ID_NOT_ASSIGNED_YET); 
	
	static public final FieldDefinition.String FIELD_FIRST_NAME = new FieldDefinition.String("first_name",null); 
	static public final FieldDefinition.String FIELD_LAST_NAME = new FieldDefinition.String("last_name",null); 
	
	static public final FieldDefinition.String FIELD_EMAIL_ADDRESS = new FieldDefinition.String("email_address",null); 
	
	static public final FieldDefinition.String FIELD_TITLE = new FieldDefinition.String("title",null); 
	
	static public final FieldDefinition.String FIELD_MOBILE_PHONE_NUMBER = new FieldDefinition.String("mobile_phone_number",null); 
	static public final FieldDefinition.String FIELD_WORK_PHONE_NUMBER = new FieldDefinition.String("work_phone_number",null); 
	
	static public final FieldDefinition.String FIELD_TIMEZONE = new FieldDefinition.String("timezone",null); 
	
	static public final FieldDefinition.Boolean FIELD_ACCOUNT_SUSPENDED = new FieldDefinition.Boolean("suspended",false);
	static public final FieldDefinition.Boolean FIELD_IS_SYSTEM_ADMINISTRATOR = new FieldDefinition.Boolean("is_system_administrator",false); 
	static public final FieldDefinition.Boolean FIELD_IS_SYSTEM_SUPPORT = new FieldDefinition.Boolean("is_system_support",false); 
	
	private long id; // Required
	
	private String first_name; // Required
	private String last_name; // Required
	
	private String email_address; // Required
	
	private String title;  // Optional
	
	private String phone_number_mobile; // optional
	private String phone_number_work; // optional
	
	private String timezone; // required
	
	private boolean is_account_suspended; // required 
	private boolean is_system_administrator; // required
	private boolean is_system_support; // required
	
	public UserAccount(ObjectParseTree t)
	{
		id = t.getLong(FIELD_ID);
		
		first_name = t.getString(FIELD_FIRST_NAME);
		last_name = t.getString(FIELD_LAST_NAME);
		
		email_address = t.getString(FIELD_EMAIL_ADDRESS);
		
		title = t.getString(FIELD_TITLE);
		
		phone_number_mobile = t.getString(FIELD_MOBILE_PHONE_NUMBER);
		phone_number_work = t.getString(FIELD_WORK_PHONE_NUMBER);
		
		timezone = t.getString(FIELD_TIMEZONE);
		
		is_account_suspended = t.getBoolean(FIELD_ACCOUNT_SUSPENDED);
		is_system_administrator = t.getBoolean(FIELD_IS_SYSTEM_ADMINISTRATOR);
		
		is_system_support = t.getBoolean(FIELD_IS_SYSTEM_SUPPORT);
	}
	
	public long getSimpleID() { return id; }
	
	public String getSimpleFirstName() { return first_name; }
	public String getSimpleLastName() { return last_name; }
	
	public String getSimpleEmailAddress() { return email_address; }
	
	public String getSimpleTimezone() { return timezone; }
	
	public boolean getSimpleIsAccountSuspended() { return is_account_suspended; }
	public boolean getSimpleIsSystemAdministrator() { return is_system_administrator; }
	public boolean getSimpleIsSystemSupport() { return is_system_support; }
	
	public boolean hasMobilePhoneNumber() { return Optional.has(phone_number_mobile, null); }
	public boolean hasWorkPhoneNumber() { return Optional.has(phone_number_work, null); }
	public boolean hasTitle() { return Optional.has(title, null); }
	
	public String getOptionalMobilePhoneNumber(String default_value) { return Optional.getOptional(phone_number_mobile, null, default_value); }
	public String getOptionalWorkPhoneNumber(String default_value) { return Optional.getOptional(phone_number_work, null, default_value); }
	public String getOptionalTitle(String default_value) { return Optional.getOptional(title, null, default_value); }


	public void write(ObjectWriter writer) 
	{
		writer.writeLong(FIELD_ID, id);
		
		writer.writeString(FIELD_FIRST_NAME, getSimpleFirstName());
		writer.writeString(FIELD_LAST_NAME, getSimpleLastName());
		
		writer.writeString(FIELD_EMAIL_ADDRESS, getSimpleEmailAddress());
		
		writer.writeString(FIELD_TITLE, getOptionalTitle(null));
		
		writer.writeString(FIELD_MOBILE_PHONE_NUMBER, getOptionalMobilePhoneNumber(null));
		writer.writeString(FIELD_WORK_PHONE_NUMBER, getOptionalWorkPhoneNumber(null));
		
		writer.writeString(FIELD_TIMEZONE, getSimpleTimezone());
		
		writer.writeBoolean(FIELD_ACCOUNT_SUSPENDED, getSimpleIsAccountSuspended());
		writer.writeBoolean(FIELD_IS_SYSTEM_ADMINISTRATOR, getSimpleIsSystemAdministrator());
		writer.writeBoolean(FIELD_IS_SYSTEM_SUPPORT, getSimpleIsSystemSupport());
	}

	public int compareTo(UserAccount o) 
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
	}

	@Override
	public void validate() 
	{
		Validator.notNull(first_name, last_name, email_address, timezone);
	}


	public int hashCode() 
	{
		return Long.hashCode(id);
	}


	public boolean equals(Object obj) 
	{
		if ( !(obj instanceof UserAccount) )  return false;
		
		
		UserAccount other = (UserAccount)obj;
		
		if ( this.getSimpleID() != other.getSimpleID() ) return false;
		
		if ( !Objects.equal(this.getSimpleFirstName(), other.getSimpleFirstName()) ) return false;
		if ( !Objects.equal(this.getSimpleLastName(), other.getSimpleLastName()) ) return false;
		
		if ( !Objects.equal(this.getSimpleEmailAddress(), other.getSimpleEmailAddress()) ) return false;
		
		if ( !Objects.equal(this.getOptionalTitle(null), other.getOptionalTitle(null)) ) return false;
		
		if ( !Objects.equal(this.getOptionalMobilePhoneNumber(null), other.getOptionalMobilePhoneNumber(null)) ) return false;
		if ( !Objects.equal(this.getOptionalWorkPhoneNumber(null), other.getOptionalWorkPhoneNumber(null)) ) return false;
		
		if ( !Objects.equal(this.getSimpleTimezone(), other.getSimpleTimezone()) ) return false;
		
		if ( this.getSimpleIsAccountSuspended() != other.getSimpleIsAccountSuspended() ) return false;
		if ( this.getSimpleIsSystemAdministrator() != other.getSimpleIsSystemAdministrator() ) return false;
		if ( this.getSimpleIsSystemSupport() != other.getSimpleIsSystemSupport() ) return false;
		
		return true;
	}
	
	
}


