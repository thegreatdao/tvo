package com.tvo.client;

import java.util.Calendar;

//import com.tvo.entity.AssetProgram;
//import com.tvo.asset.AssetSeries;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.telescope.TelescopeConnection;
import com.tvo.telescope.TelescopeQuery;
import com.tvo.telescope.TelescopeQueryCustom;
import com.tvo.telescope.TelescopeResult;

public class Application {
    
    public static void main(String[] args) throws Throwable {
    	
    	System.out.println("Starting...");
    	
    	
    	
    	
    	//int[] numbers = telescopeQuery.getRelated(1804965, Containers.REQUESTED_MEDIA, RelationshipType.CHILD_TO_PARENT);
    	
    	// 1596837
    	
    	//System.out.println("Loading Fields.");
    	//String[] fields = telescopeQuery.enumerateFields();
    	//int[] programIds = {991353}; //{580878, 1793147, 1793391};
    	
    	//int[] assets;
    	//System.out.println(numbers);  	
    	// int[] assets = telescopeQuery.getAssetsByProgram(programIds);
    	//int[] assets = { 1787147 };
    	
    	
    	// assets = telescopeQuery.getAssetsByProgram(883846);
    	
    	//String[] enumeratedFields = telescopeQuery.enumerateFields();
    	
    	//int[] assets = TelescopeQueryCustom.getFullVideoAssets(startDate.getTime(), endDate.getTime());
    	 
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
	    	startDate.set(2010, 04 -1 , 22, 12, 0);
	    	
	    	Calendar endDate = Calendar.getInstance();
	    	endDate.set(2010, 04 - 1, 22, 16, 0);
	    	
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
								
								//System.out.println(assetVideo.getTelescopeRecordId());
								//System.out.println(assetVideo.getReleaseDate());
								System.out.println("> Found Video");
								System.out.println("Record ID:" + assetVideo.getAssetRoot().getTelescopeRecordId());
							} catch(Error error) {
								System.out.println("Error: " + error.getMessage());
							}
							
						break;
						
						/*
						case PROGRAM:
							System.out.println("Found Program...");
							
							AssetProgram assetProgram = result.getAssetProgram();
							System.out.println(assetProgram.getTitle());
							System.out.println(assetProgram.getDescriptionShort());
							System.out.println(assetProgram.getReleaseDate());
							System.out.println(assetProgram.getCreatedOn());
							
							break;
						
						case SERIES:
							System.out.println("Found Series...");
							
							AssetSeries assetSeries = result.getAssetSeries();
							System.out.println(assetSeries.getTitle());
							System.out.println(assetSeries.getDescriptionShort());
							System.out.println(assetSeries.getReleaseDate());
							System.out.println(assetSeries.getCreatedOn());
							break;
						*/
						
						default:
							// System.out.println("Nothing to do.");
							break;
					}
		    	}
	    	} else {
	    		System.out.println("No assets found.");
	    	}
    	} catch(Exception ex) {
    		ex.printStackTrace();
	    	telescopeConnection.disconnect();
	    }
		 
    	try {
    		telescopeConnection.disconnect();
    		System.out.println("- Disconnected from telescope");
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	System.out.println("Finished...");
    }
    
    	
    /*
    System.out.println("Hello there");
    	
    	List<AssetVideo> assetVideoList = AssetVideo.GetAll();
    	
    	for(AssetVideo assetVideo : assetVideoList) {
    		
    		XStream xstream = new XStream();
    		xstream.alias("AssetVideo", AssetVideo.class);
    		String assetVideoXml = xstream.toXML(assetVideo);
    		
    		System.out.println(assetVideoXml);
    		System.out.println("\n");
    	} 
     */
}
