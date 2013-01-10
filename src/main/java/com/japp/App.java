package com.japp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.japp.guice.JAppModule;
import com.japp.server.AdminServer;
import com.japp.util.Logging;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    
    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) {
             
    Logging.configureBasicConsoleLogging(); 
    
    sLogger.warn("Main app entry");
    sLogger.warn("JApp: Starting adminServer..");
    

    
       try {
                sLogger.warn("JettyApp: inject servers");
        	Injector injector = Guice.createInjector(new JAppModule());
    
                sLogger.warn("JettyApp: injecting AdminServer");
                AdminServer serverAdmin = injector.getInstance(AdminServer.class); 
                serverAdmin.startAndWait();
      
                }
                catch( Exception e ) {
        	sLogger.error("Unable to initialise WebServer..", e);
                }
        
        
    }
}
