package com.emasagae.config;

import com.emasagae.entity.Emasa;
import com.emasagae.entity.Operation;
import com.emasagae.entity.Report;
import com.emasagae.entity.ReportUser;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class OfyHelper implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Emasa.class);
		ObjectifyService.register(Report.class);		
		ObjectifyService.register(Operation.class);	
		ObjectifyService.register(ReportUser.class);	
	}

}
