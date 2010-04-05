package com.tvo.asset;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.tvo.databases.DbTelescope;


public class TestMe1 {
	
	public static void testCase1() {
		JdbcTemplate select = new JdbcTemplate();
    	select.setDataSource(DbTelescope.getDataSource());
    	SqlRowSet rowSet = select.queryForRowSet("select tsp.get_container_ids(1318919, 'content_digital','C') from dual");
    	
    	while(rowSet.next()) {
    		
    		System.out.println("RECORD!");
    		System.out.println("Data: " + rowSet.getString(1));
    	}
	}
}
