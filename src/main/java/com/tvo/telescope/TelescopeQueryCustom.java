package com.tvo.telescope;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.tvo.databases.DbTelescope;

public class TelescopeQueryCustom {
	
	public static int[] getFullVideoAssets(Date startDate, Date endDate) {
		
		DateFormat oracleDateFormat = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
		
		String sqlQuery = "SELECT DIGITAL_RECORD_ID FROM tsp.web_encode_info_all_v a, tsp.asset_last_updated_v b " +
	 	  "WHERE a.digital_record_id = b.record_id " +
	 	  "AND b.last_update BETWEEN TO_DATE('" + oracleDateFormat.format(startDate) + "', 'yyyy/mm/dd:HH24:mi:ss') AND TO_DATE('" + oracleDateFormat.format(endDate) + "', 'yyyy/mm/dd:HH24:mi:ss')";
    	
		return _getVideoAssets(sqlQuery);
    }
	
	public static int[] getFullVideoAssetsWithin24Hours() {
		
		String sqlQuery = "SELECT DIGITAL_RECORD_ID FROM tsp.web_encode_info_all_v a, tsp.asset_last_updated_v b " +
					 	  "WHERE a.digital_record_id = b.record_id " +
					 	  "AND b.last_update BETWEEN sysdate - 1 and sysdate";
		
		return _getVideoAssets(sqlQuery);
    }
	
	private static int[] _getVideoAssets(String sqlQuery) {
		
		JdbcTemplate select = new JdbcTemplate();
		select.setDataSource(DbTelescope.getDataSource());
	
	  	System.out.println(sqlQuery);
	  	
	  	SqlRowSet rowSet = select.queryForRowSet(sqlQuery);
	  	
	  	ArrayList<Integer> resultIdList = new ArrayList<Integer>();
	  	
	  	while(rowSet.next()) {
	  		resultIdList.add(rowSet.getInt(1));
	  	}
	  	
	  	int returnResultIds[] = new int[resultIdList.size()];
	  	
	  	for(int i = 0; i < resultIdList.size(); i++) {
    		returnResultIds[i] = resultIdList.get(i);
    	}
    		
    	return returnResultIds;
	}
}
