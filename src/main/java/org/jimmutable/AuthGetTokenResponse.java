package org.jimmutable;

import java.util.Objects;

import org.jimmutable.core.examples.book.Book;
import org.jimmutable.core.objects.Builder;
import org.jimmutable.core.objects.StandardImmutableObject;
import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.TypeName;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.jimmutable.core.serialization.writer.ObjectWriter;
import org.jimmutable.core.utils.Comparison;
import org.jimmutable.core.utils.Normalizer;
import org.jimmutable.core.utils.Optional;
import org.jimmutable.core.utils.Validator;

public class AuthGetTokenResponse extends StandardImmutableObject<AuthGetTokenResponse>
{
	static public final TypeName TYPE_NAME = new TypeName("digitalpanada.auth.response"); public TypeName getTypeName() { return TYPE_NAME; }
	
	static public final FieldDefinition.Boolean FIELD_SUCCESS = new FieldDefinition.Boolean("success",Boolean.FALSE);
	static public final FieldDefinition.String FIELD_FAILURE_MESSAGE = new FieldDefinition.String("failure_message",null);
	static public final FieldDefinition.String FIELD_TOKEN = new FieldDefinition.String("token",null);
	
	private boolean success;
	private String failure_message;
	
	private String token;
	
	public AuthGetTokenResponse(ObjectParseTree t)
	{
		success = t.getBoolean(FIELD_SUCCESS);
		failure_message = t.getString(FIELD_FAILURE_MESSAGE);
		token = t.getString(FIELD_TOKEN);
	}
	
	static public AuthGetTokenResponse createSuccess(String token)
	{
		Validator.notNull(token);
		
		Builder b = new Builder(TYPE_NAME);
		
		b.set(FIELD_SUCCESS, true);
		b.set(FIELD_TOKEN, token);
		
		return (AuthGetTokenResponse)b.create(null);
	}
	
	static public AuthGetTokenResponse createFailure(String msg)
	{
		Validator.notNull(msg);
		
		Builder b = new Builder(TYPE_NAME);
		
		b.set(FIELD_SUCCESS, false);
		b.set(FIELD_FAILURE_MESSAGE, msg);
		
		return (AuthGetTokenResponse)b.create(null);
	}
	
	public boolean getSimpleSuccess() { return success; }
	
	public String getOptionalFailureMessage(String default_value) { return Optional.getOptional(failure_message, null, default_value); }
	public boolean hasOptinoalFailureMessage() { return Optional.has(failure_message, null); }
	
	public String getOptionalToken(String default_value) { return Optional.getOptional(token, null, default_value); }
	public boolean hasOptionalToken() { return Optional.has(token, null); }

	public int compareTo(AuthGetTokenResponse o) 
	{
		int ret = Comparison.startCompare();
		
		ret = Comparison.continueCompare(ret, getSimpleSuccess(), o.getSimpleSuccess());
		ret = Comparison.continueCompare(ret, getOptionalFailureMessage(null), o.getOptionalFailureMessage(null));
		ret = Comparison.continueCompare(ret, getOptionalToken(null), o.getOptionalToken(null));
		
		return ret;
	}

	
	public void write(ObjectWriter writer) 
	{
		writer.writeBoolean(FIELD_SUCCESS, success);
		writer.writeString(FIELD_FAILURE_MESSAGE, failure_message);
		writer.writeString(FIELD_TOKEN, token);
	}


	public void freeze() 
	{	
	}

	@Override
	public void normalize() 
	{
		token = Normalizer.lowerCase(token);
	}

	@Override
	public void validate()
	{
		if ( success ) 
			Validator.notNull(token);
		else
			Validator.notNull(failure_message);
	}

	@Override
	public int hashCode() 
	{
		return Objects.hash(success, failure_message, token);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if ( !(obj instanceof AuthGetTokenResponse) ) return false;
		
		AuthGetTokenResponse other = (AuthGetTokenResponse)obj;
		
		if ( getSimpleSuccess() != other.getSimpleSuccess() ) return false;
		
		if ( !Objects.equals(getOptionalFailureMessage(null), other.getOptionalFailureMessage(null)) ) return false;
		if ( !Objects.equals(getOptionalToken(null), other.getOptionalToken(null)) ) return false;
		
		return true;
	}
	
	
}
