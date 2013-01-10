package com.japp.server;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.inject.Inject;
import com.japp.server.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminServer extends AbstractIdleService {
    
    private static Logger sLogger = LoggerFactory.getLogger(AdminServer.class);
    
    private AdminService adminService;

    
        @Inject
        AdminServer(AdminService adminService) {
        this.adminService  = adminService;    
        }
        
        @Override
	protected void startUp() throws Exception {           
        adminService.startAndWait();
        sLogger.info("AdminServer: adminService has started.");  
        }
        
      
        @Override
	public void shutDown() throws Exception {                           
        sLogger.info("AdminServer: Shutting down adminService...");
	adminService.stopAndWait();                               
                }
      
}
