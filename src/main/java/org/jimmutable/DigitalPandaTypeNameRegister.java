package org.jimmutable;

import org.jimmutable.api.auth.AuthGetTokenResponse;
import org.jimmutable.api.user.RequestNewUserAccount;
import org.jimmutable.api.user.ResponseNewUserAccount;
import org.jimmutable.core.serialization.JimmutableTypeNameRegister;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.jimmutable.data.UserAccount;
import org.jimmutable.exception.FormErrorDetail;

public class DigitalPandaTypeNameRegister 
{
	static public void registerAllTypes()
	{
		JimmutableTypeNameRegister.registerAllTypes();
		ObjectParseTree.registerTypeName(AuthGetTokenResponse.class);
		
		ObjectParseTree.registerTypeName(FormErrorDetail.class);
		
		
		ObjectParseTree.registerTypeName(UserAccount.class);
		
		ObjectParseTree.registerTypeName(RequestNewUserAccount.class);
		ObjectParseTree.registerTypeName(ResponseNewUserAccount.class);
	}
}
