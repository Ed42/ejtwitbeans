/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.japp.guice;
import com.google.inject.AbstractModule;
import com.japp.server.AdminServer;
import com.japp.server.service.AdminService;
import com.japp.server.serviceimpl.AdminServiceImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JAppModule extends AbstractModule  {
    
     private static Logger sLogger = LoggerFactory.getLogger(JAppModule.class);
     
         @Override
	 protected void configure() {
         sLogger.info("JAppModule configure()");    
          bind(AdminServer.class);
          bind(AdminService.class).to(AdminServiceImpl.class);

         }
    
}
