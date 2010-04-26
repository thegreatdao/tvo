package com.tvo.databases;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbTelescope {
	
	private static final DbTelescope dbTelescope = new DbTelescope();
    private static DriverManagerDataSource dataSource;
    
    private DbTelescope() {
    	DbTelescope.dataSource = new DriverManagerDataSource();
    	DbTelescope.dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    	DbTelescope.dataSource.setUrl("jdbc:oracle:thin:@lando.tvo.org:1521:damdb");
    	DbTelescope.dataSource.setUsername("ws");
    	DbTelescope.dataSource.setPassword("ws");
    }
    
    @SuppressWarnings("static-access")
	public static DriverManagerDataSource getDataSource() {
    	return dbTelescope.dataSource;
    }
}