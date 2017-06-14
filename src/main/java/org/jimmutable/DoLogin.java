package org.jimmutable;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.jimmutable.core.serialization.Format;
import org.jimmutable.core.serialization.reader.ObjectParseTree;
import org.json.JSONObject;
import org.json.JSONTokener;

@WebServlet(name="DoLogin", value="/api/1.0/auth/get-token.html")
public class DoLogin extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		QueryStringReader reader = new QueryStringReader(request);
		
		String username = reader.getOptionalString("username", "");
		String password = reader.getOptionalString("password", "");
		
		
		if ( username.equalsIgnoreCase("jim.kane@gmail.com") && password.equals("skippy") ) 
		{
			AuthGetTokenResponse get_auth_response = AuthGetTokenResponse.createSuccess("asdfasfioADFx");
			
			
			//response.setContentType(MimeTypes.JSON);
			response.setStatus(HttpStatus.SC_OK);
			response.getWriter().print(get_auth_response.serialize(Format.JSON));
			return;
		}
		
		// Anything else is a failure...
		AuthGetTokenResponse get_auth_response = AuthGetTokenResponse.createFailure("Invalid username/password");

		//response.setContentType(MimeTypes.JSON);
		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.getWriter().print(get_auth_response.serialize(Format.JSON));
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		JSONPostReader reader = new JSONPostReader(req);
		
		System.out.println(reader.getOptionalString("username", null));
		System.out.println(reader.getOptionalString("password", null));
	}
	
	private JSONObject readBody(HttpServletRequest request)
	{
		try 
		{
		    return new JSONObject(new JSONTokener(request.getReader()));

		} 
		catch (Exception e) 
		{ 
			/*report an error*/ 
		}
		
		return null;
	}
}
