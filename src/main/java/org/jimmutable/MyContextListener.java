package org.jimmutable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener
{

	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		DigitalPandaTypeNameRegister.registerAllTypes();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
