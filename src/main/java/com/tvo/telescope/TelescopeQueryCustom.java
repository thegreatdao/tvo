package com.tvo.telescope;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.tvo.databases.DbTelescope;

public class TelescopeQueryCustom {
	
	public static int[] getFullVideoAssets() {
		
		String sqlQuery = "SELECT DIGITAL_RECORD_ID FROM tsp.web_encode_info_all_v a, tsp.asset_last_updated_v b " +
					 	  "WHERE a.digital_record_id = b.record_id " +
					 	  "AND b.last_update BETWEEN sysdate - 1 and sysdate";
		
		JdbcTemplate select = new JdbcTemplate();
    	select.setDataSource(DbTelescope.getDataSource());
 	
    	System.out.println(sqlQuery);
    	
    	SqlRowSet rowSet = select.queryForRowSet(sqlQuery);
    	
    	ArrayList<Integer> resultIdList = new ArrayList<Integer>();
    	
    	while(rowSet.next()) { // We are only interested in one row.
    		resultIdList.add(rowSet.getInt(1));
    	}
    	
    	int returnResultIds[] = new int[resultIdList.size()];
    	
    	for(int i = 0; i < resultIdList.size(); i++) {
    		returnResultIds[i] = resultIdList.get(i);
    	}
    		
    	return returnResultIds;
    }
}
