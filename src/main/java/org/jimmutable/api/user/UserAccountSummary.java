package org.jimmutable.api.user;

import org.jimmutable.EntityReader;
import org.jimmutable.core.objects.StandardImmutableObject;
import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.jimmutable.core.serialization.writer.ObjectWriter;
import org.jimmutable.core.utils.Optional;
import org.jimmutable.datastore.ObjectParseTreeReader;
import org.jimmutable.datastore.ObjectReader;

import com.google.cloud.datastore.Entity;

public class UserAccountSummary extends StandardImmutableObject<UserAccountSummary>
{
	static public final TypeName TYPE_NAME = new TypeName("digitalpanada.user.account_summary"); public TypeName getTypeName() { return TYPE_NAME; }
	 
	static public final FieldDefinition.Long FIELD_ID = new FieldDefinition.Long("id",-1l); // Required
	
	static public final FieldDefinition.String FIELD_FIRST_NAME = new FieldDefinition.String("first_name",null); // Required
	static public final FieldDefinition.String FIELD_LAST_NAME = new FieldDefinition.String("last_name",null); // Required
	
	static public final FieldDefinition.String FIELD_EMAIL_ADDRESS = new FieldDefinition.String("email_address",null); // Required
	
	static public final FieldDefinition.String FIELD_TITLE = new FieldDefinition.String("title",null); // Optional
	
	static public final FieldDefinition.String FIELD_PASSWORD_HASH = new FieldDefinition.String("password_hash",null); // required
	static public final FieldDefinition.String FIELD_AUTH_TOKEN = new FieldDefinition.String("auth_token",null); // required
	
	static public final FieldDefinition.String FIELD_MOBILE_PHONE_NUMBER = new FieldDefinition.String("mobile_phone_number",null); // required
	static public final FieldDefinition.String FIELD_WORK_PHONE_NUMBER = new FieldDefinition.String("work_phone_number",null); // optional
	
	static public final FieldDefinition.String FIELD_TIMEZONE = new FieldDefinition.String("timezone",null); // required
	
	static public final FieldDefinition.Boolean FIELD_ACCOUNT_SUSPENDED = new FieldDefinition.Boolean("suspended",false); // required 
	static public final FieldDefinition.Boolean FIELD_IS_SYSTEM_ADMINISTRATOR = new FieldDefinition.Boolean("is_system_administrator",false); // required
	static public final FieldDefinition.Boolean FIELD_IS_SYSTEM_SUPPORT = new FieldDefinition.Boolean("is_system_support",false); // required
	
	private long id;
	
	private String first_name;
	private String last_name;
	
	private String email_address;
	
	private String title;
	
	private String password_hash;
	private String auth_token;
	
	private String phone_number_mobile;
	private String phone_number_work;
	
	private String timezone;
	
	private boolean is_account_suspended;
	private boolean is_system_administrator;
	private boolean is_system_support;
	
	public UserAccountSummary(ObjectParseTree t)
	{
		this(new ObjectParseTreeReader(t));
	}
	
	public UserAccountSummary(ObjectReader reader)
	{
		id = reader.getLong(FIELD_ID);
		
		first_name = reader.getString(FIELD_FIRST_NAME);
		last_name = reader.getString(FIELD_LAST_NAME);
		
		email_address = reader.getString(FIELD_EMAIL_ADDRESS);
		
		title = reader.getString(FIELD_TITLE);
		
		password_hash = reader.getString(FIELD_PASSWORD_HASH);
		auth_token = reader.getString(FIELD_AUTH_TOKEN);
		
		phone_number_mobile = reader.getString(FIELD_MOBILE_PHONE_NUMBER);
		phone_number_work = reader.getString(FIELD_WORK_PHONE_NUMBER);
		
		timezone = reader.getString(FIELD_TIMEZONE);
		
		is_account_suspended = reader.getBoolean(FIELD_ACCOUNT_SUSPENDED);
		is_system_administrator = reader.getBoolean(FIELD_IS_SYSTEM_ADMINISTRATOR);
		
		is_system_support = reader.getBoolean(FIELD_IS_SYSTEM_SUPPORT);
	}
	
	public UserAccountSummary(Entity entity)
	{
		this(new DatastoreReader(entity));
	}
	
	public long getSimpleID() { return id; }
	
	public String getSimpleFirstName() { return first_name; }
	public String getSimpleLastName() { return last_name; }
	
	public String getSimpleEmailAddress() { return email_address; }
	
	public String getSimplePasswordHash() { return password_hash; }
	public String getSimpleAuthToken() { return auth_token; }
	
	public String getSimpleMobilePhoneNumber() { return phone_number_mobile; }
	public String getSimpleTimezone() { return timezone; }
	
	public boolean getSimpleIsAccountSuspended() { return is_account_suspended; }
	public boolean getSimpleIsSystemAdministrator() { return is_system_administrator; }
	public boolean getSimpleIsSystemSupport() { return is_system_support; }
	
	public boolean hasWorkPhoneNumber() { return Optional.has(phone_number_work, null); }
	public boolean hasTitle() { return Optional.has(title, null); }
	
	public String getOptionalWorkPhoneNumber(String default_value) { return Optional.getOptional(phone_number_work, null, default_value); }
	public String getOptionalTitle(String default_value) { return Optional.getOptional(title, null, default_value); }


	public void write(ObjectWriter writer) 
	{
		writer.writeLong(FIELD_ID, id);
		
		writer.writeString(FIELD_FIRST_NAME, getSimpleFirstName());
		writer.writeString(FIELD_LAST_NAME, getSimpleLastName());
		
		writer.writeString(FIELD_EMAIL_ADDRESS, getSimpleEmailAddress());
		
		writer.writeString(FIELD_TITLE, getOptionalTitle(null));
		
		writer.writeString(FIELD_PASSWORD_HASH, getSimplePasswordHash());
		writer.writeString(FIELD_AUTH_TOKEN, getSimpleAuthToken());
		
		writer.writeString(FIELD_MOBILE_PHONE_NUMBER, getSimpleMobilePhoneNumber());
		writer.writeString(FIELD_WORK_PHONE_NUMBER, getOptionalWorkPhoneNumber(null));
		
		writer.writeString(FIELD_TIMEZONE, getSimpleTimezone());
		
		writer.writeBoolean(FIELD_ACCOUNT_SUSPENDED, getSimpleIsAccountSuspended());
		writer.writeBoolean(FIELD_IS_SYSTEM_ADMINISTRATOR, getSimpleIsSystemAdministrator());
		writer.writeBoolean(FIELD_IS_SYSTEM_SUPPORT, getSimpleIsSystemSupport());
	}

	public int compareTo(UserAccountSummary o) 
	{
		
	}

	@Override
	public void freeze() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void normalize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
