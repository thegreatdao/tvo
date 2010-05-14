package com.tvo.dao;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tvo.databases.DbTelescope;
import com.tvo.entity.AssetProgram;
import com.tvo.entity.AssetVideo;
import com.tvo.telescope.TelescopeConnection;
import com.tvo.telescope.TelescopeQuery;
import com.tvo.telescope.TelescopeResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class TestTelescope
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TestTelescope.class);
	
	@Autowired
	private AssetVideoDao assetVideoDao;
	
	@Autowired
	private AssetProgramDao assetProgramDao;
	
	@Test
	public void testTelescopeVideoAsset() throws Throwable
	{
		
    	System.out.println("Starting...");
    	 
    	TelescopeConnection telescopeConnection = new TelescopeConnection();
    	
    	try {
    		telescopeConnection.connect();
    		System.out.println("- Connected to telescope");
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	try {
	    	TelescopeQuery telescopeQuery = new TelescopeQuery(telescopeConnection);
	    	Calendar startDate = Calendar.getInstance();
	    	startDate.set(2010, 04 -1 , 23, 16, 0);
	    	
	    	Calendar endDate = Calendar.getInstance();
	    	endDate.set(2010, 04 - 1, 23, 20, 0);
	    	
	    	//int[] assets = TelescopeQueryCustom.getFullVideoAssets(startDate.getTime(), endDate.getTime());
	    	int[] assets = telescopeQuery.getAssetsByDate(startDate.getTime(), endDate.getTime());
	    	
	    	if(assets.length > 0) {
	    		System.out.println("- Found " + assets.length + " assets, writing XML");
	    		TelescopeResult[] results = telescopeQuery.getResultSet(assets, TelescopeQuery.getDefaultFieldNames()); //TelescopeQuery.getDefaultFieldNames());
	    	
		    	for(TelescopeResult result : results) {
		    		
		    		switch(result.getAssetType())
					{
						case VIDEO: 
							
							try {
								AssetVideo assetVideo = result.getAssetVideo();

								LOGGER.debug("> Found Video");
								LOGGER.debug("Record ID:" + assetVideo.getAssetRoot().getTelescopeRecordId());
								
								System.out.println("> Found Video");
								System.out.println("Record ID:" + assetVideo.getAssetRoot().getTelescopeRecordId());
								
								assetVideoDao.saveAssetVideo(assetVideo);
								
							} catch(Error error) {
								LOGGER.error("Error: " + error.getMessage());
							}
							
						break;
						
						
						case PROGRAM:
							try {
								
								AssetProgram assetProgram = result.getAssetProgram();
								System.out.println("> Found Program");
								System.out.println("Record ID:" + assetProgram.getAssetRoot().getTelescopeRecordId());
								
								assetProgramDao.save(assetProgram);
								
							} catch(Error error) {
								LOGGER.error("Error: " + error.getMessage());
							}
								
							break;
							
						
						default:
							// System.out.println("Nothing to do.");
							break;
					}
		    	}
	    	} else {
	    		LOGGER.debug("No assets found.");
	    	}
    	} catch(Exception ex) {
    		ex.printStackTrace();
	    	telescopeConnection.disconnect();
	    }
		 
    	try {
    		telescopeConnection.disconnect();
    		DbTelescope.close();
    		System.out.println("- Disconnected from telescope");
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	DbTelescope.close();
    	System.out.println("Finished...");
    }
    
}
