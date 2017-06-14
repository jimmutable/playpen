package org.jimmutable;

import org.jimmutable.core.serialization.FieldName;
import org.jimmutable.core.serialization.JimmutableTypeNameRegister;
import org.jimmutable.core.serialization.reader.ObjectParseTree;

public class DigitalPandaTypeNameRegister 
{
	static public void registerAllTypes()
	{
		JimmutableTypeNameRegister.registerAllTypes();
		ObjectParseTree.registerTypeName(AuthGetTokenResponse.class);
	}
}
