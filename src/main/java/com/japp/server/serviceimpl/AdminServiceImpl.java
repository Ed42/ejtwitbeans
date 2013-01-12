package com.japp.server.serviceimpl;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.inject.Inject;
import com.japp.server.service.AdminService;
import java.util.logging.Level;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminServiceImpl extends AbstractIdleService implements AdminService{
    
      private Logger sLogger = LoggerFactory.getLogger(AdminServiceImpl.class);
    
        private Integer adminPort = 8080;
        private String ip = "localhost";
        private Server serverAdmin;
        private String path;
        
        @Inject
        public AdminServiceImpl() {
        String ports = System.getenv("OPENSHIFT_INTERNAL_PORT");
        if(ports == null) {
            ports = "8080";
        }
        ip = System.getenv("OPENSHIFT_INTERNAL_IP");             
        if(ip == null)  ip = "localhost";
        sLogger.warn("AdminServiceImpl on sys port: "+ip);
        adminPort = new Integer(ports);
        if(adminPort == null)  adminPort = 8080;
        sLogger.warn("AdminServiceImpl on sys port: "+adminPort.intValue());
        serverAdmin = new Server();
    }
      
        @Override
        protected void startUp() {
        WebAppContext rest = setUpAdminServer();  //new ServletContextHandler(ServletContextHandler.SESSIONS);  
        rest.setContextPath("/");
        // need a connector for to set a a host only
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setHost(ip);
        connector.setPort(adminPort);
        connector.setThreadPool(new QueuedThreadPool(20));
        connector.setName("adminService");
        serverAdmin.setConnectors(new Connector[]{ connector });
        ContextHandlerCollection contextAdmin = new ContextHandlerCollection();             
        contextAdmin.setHandlers(new Handler[]{rest});
        serverAdmin.setHandler(contextAdmin);
        
                                 
        try {            
            serverAdmin.start();  
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
        
        
   }
      
         @Override
        protected void shutDown() throws Exception {
          serverAdmin.stop();
         
    }
         
       WebAppContext setUpAdminServer() {
           
        WebAppContext restServer = new WebAppContext();
        SessionHandler handle = new SessionHandler();
        restServer.setSessionHandler(new SessionHandler());
        restServer.setDescriptor(restServer+"/WEB-INF/web.xml");       
        restServer.setResourceBase(".");
        restServer.setParentLoaderPriority(true);
   //     sLogger.warn("restServer: "+ restServer.getWar());
        return restServer;

    }
          
}
