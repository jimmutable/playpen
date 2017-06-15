package org.jimmutable.api.user;

import org.jimmutable.core.objects.StandardImmutableObject;
import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.jimmutable.core.serialization.writer.ObjectWriter;
import org.jimmutable.core.utils.Validator;
import org.jimmutable.data.UserAccount;

public class ResponseNewUserAccount extends StandardImmutableObject<ResponseNewUserAccount>
{
	static public final TypeName TYPE_NAME = new TypeName("digitalpanada.api.v1_0.users.response_new_user_account"); public TypeName getTypeName() { return TYPE_NAME; }
	
	static public final FieldDefinition.StandardObject FIELD_USER_ACCOUNT = new FieldDefinition.StandardObject("user_account",null); 
	static public final FieldDefinition.String FIELD_TOKEN = new FieldDefinition.String("token",null); 
	
	private UserAccount user_account; // required
	private String token; // required
	
	
	public ResponseNewUserAccount(ObjectParseTree t)
	{
		user_account = (UserAccount)t.getObject(FIELD_USER_ACCOUNT);
		token = t.getString(FIELD_TOKEN);
	}
	
	public UserAccount getSimpleUserAccount() { return user_account; }
	public String getSimpleToken() { return token; }
	
	public ResponseNewUserAccount(UserAccount user_account, String token)
	{
		this.user_account = user_account;
		this.token = token;
		
		complete();
	}

	
	public int compareTo(ResponseNewUserAccount o)
	{
		return user_account.compareTo(o.getSimpleUserAccount());
	}
	
	
	public void write(ObjectWriter writer) 
	{
		writer.writeObject(FIELD_USER_ACCOUNT, getSimpleUserAccount());
		writer.writeString(FIELD_TOKEN, getSimpleToken());
	}
	
	public void freeze() 
	{	
	}

	public void normalize() 
	{	
	}
	
	public void validate() 
	{
		Validator.notNull(user_account, token);
	}

	public int hashCode()
	{
		return getSimpleUserAccount().hashCode();
	}
	
	public boolean equals(Object obj) 
	{
		if ( !(obj instanceof ResponseNewUserAccount) ) return false;
		
		ResponseNewUserAccount other = (ResponseNewUserAccount)obj;
		
		if ( !getSimpleUserAccount().equals(other.getSimpleUserAccount()) ) return false;
		if ( !getSimpleToken().equals(other.getSimpleToken()) ) return false;
		
		return true;
	}
}
