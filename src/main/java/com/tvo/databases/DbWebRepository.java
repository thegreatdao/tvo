package com.tvo.databases;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbWebRepository {
	
	private static final DbWebRepository dbWebRepositoryInstance = new DbWebRepository();
    private static DriverManagerDataSource dataSource;
    
    private DbWebRepository() {
    	// The source attachment does not contain the source for the file JdbcTemplate.class You can change the source attachment by clicking Change Attached Source Below:
    	DbWebRepository.dataSource = new DriverManagerDataSource();
    	DbWebRepository.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	DbWebRepository.dataSource.setUrl("jdbc:mysql://localhost:3306/tvo_web_repository_rev9");
    	DbWebRepository.dataSource.setUsername("root");
    	DbWebRepository.dataSource.setPassword("foobarz");
    }
    
    @SuppressWarnings("static-access") // This is a singleton!
	public static DriverManagerDataSource getDataSource() {
    	return dbWebRepositoryInstance.dataSource;
    }
    
    //  ws/lift
}
