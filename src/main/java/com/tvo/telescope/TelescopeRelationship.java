package com.tvo.telescope;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.tvo.databases.DbTelescope;

public class TelescopeRelationship {
	
	public enum Containers { CONTENT_DIGITAL, REQUESTED_MEDIA };
	public enum RelationshipType { CHILD_TO_PARENT, PARENT_TO_CHILD }
	
	public static int[] getRelated(int recordId, TelescopeRelationship.Containers containerName, TelescopeRelationship.RelationshipType relationship) {
		
		char telescopeRelationship = 0;
		String telescopeContainer = null;
		
		switch(relationship) {
			case CHILD_TO_PARENT :
				telescopeRelationship  = 'C';
			break;
			
			case PARENT_TO_CHILD :
				telescopeRelationship  = 'P';
			break;
			
			default :
				
			break;
		}
		
		switch(containerName) {
		
			case CONTENT_DIGITAL :
				telescopeContainer = "content_digital";
				break;
				
			case REQUESTED_MEDIA :
				telescopeContainer = "requested_media";
				break;
		}
		
		JdbcTemplate select = new JdbcTemplate();
    	select.setDataSource(DbTelescope.getDataSource());
    	SqlRowSet rowSet = select.queryForRowSet("SELECT tsp.get_container_ids(" + recordId + ", '" + telescopeContainer + "','" + telescopeRelationship + "') FROM dual");
    	
    	// We are only interested in one row.
    	
    	int returnResultIds[] = null;
    	
    	if(rowSet.next()) {
    		String resultLine = rowSet.getString(1);
    		
    		if(resultLine.startsWith("|")) {
    			resultLine = resultLine.substring(1);
    		}
    		
    		if(resultLine.endsWith("|")) {
    			resultLine = resultLine.substring(0, resultLine.length() - 1);
    		}
    		
    		String[] resultIds = resultLine.split("|");
    		
    		returnResultIds = new int[resultIds.length];
    		
    		for(int i = 0; i < resultIds.length; i ++) {
    			returnResultIds[i] = Integer.parseInt(resultIds[i]);
    		}
    	}
    	
    	return returnResultIds;
	}
}
