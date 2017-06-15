package org.jimmutable;

import org.jimmutable.api.auth.AuthGetTokenResponse;
import org.jimmutable.api.user.UserAccountSummary;
import org.jimmutable.core.serialization.JimmutableTypeNameRegister;
import org.jimmutable.core.serialization.reader.ObjectParseTree;

public class DigitalPandaTypeNameRegister 
{
	static public void registerAllTypes()
	{
		JimmutableTypeNameRegister.registerAllTypes();
		ObjectParseTree.registerTypeName(AuthGetTokenResponse.class);
		
		ObjectParseTree.registerTypeName(UserAccountSummary.class);
	}
}
