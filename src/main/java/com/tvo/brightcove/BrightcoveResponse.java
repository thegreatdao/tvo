package com.tvo.brightcove;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.codehaus.jettison.json.JSONObject;

public class BrightcoveResponse {
	
	private static String tokenSecret = "MP_rS39dA8YulIl2n8RCXx1evpJ5GUDlzQypNYRBpTU.";
	
	private int assetVideoId;
	private String tvoMainId;
	private int length;
	
	private String videoStillUrl;
	private String thumbnailUrl;
	private String referenceId;
	
	private String domainName;
	private Date createdOn;
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;
	
	public String getReferenceId() {
		return referenceId;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getVideoStillUrl() {
		return videoStillUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public String getTvoMainId() {
		return tvoMainId;
	}
	
	public void setAssetVideoId(int assetVideoId) {
		this.assetVideoId = assetVideoId;
	}
	
	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getAssetVideoId() {
		return assetVideoId;
	}

	public static BrightcoveResponse getResponse(String tvoMainId) throws Exception {
		
			BrightcoveResponse response = new BrightcoveResponse();
					
			String brightCoveRequestUrl = "http://api.brightcove.com/services/library?command=find_video_by_id&video_id=" + tvoMainId + "&token=" + BrightcoveResponse.tokenSecret;
			HttpURLConnection connection = (HttpURLConnection) new URL(brightCoveRequestUrl).openConnection();
		
			connection.setRequestMethod("GET");
			connection.getInputStream();
			
			BufferedReader rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder jsonResponseSb = new StringBuilder();
			String line;
			
	        while ((line = rd.readLine()) != null) {
	        	jsonResponseSb.append(line + '\n');
	        }
	        
	        String errorTest = "{ \"result\": null, \"error\": { \"code\": 103, \"name\": \"CallTimeoutError\", \"message\": \"The request you made is taking longer than expected to return. If requesting a large amount of data please try again with a smaller page_size.\" } }";
	        // jsonResponseSb.toString()
	        JSONObject jsonObj = new JSONObject(errorTest);
	        
	        try {
		        response.tvoMainId = jsonObj.getString("id");
		        response.length = Integer.parseInt(jsonObj.getString("length"));
		        response.videoStillUrl = jsonObj.getString("videoStillURL");
		        response.thumbnailUrl = jsonObj.getString("thumbnailURL");
		        response.referenceId = jsonObj.getString("referenceId");
	        } catch(Exception ex) {
	        	
	        	String result = jsonObj.getString("result");
	        	
	        	if(result == null) {
	        		
	        	}
	        }
	        
	        return response;
	}
	
	
	public static void main(String[] args) throws Throwable {
	    
		 BrightcoveResponse brightcoveResponse = BrightcoveResponse.getResponse("1837282190");
		 System.out.println(brightcoveResponse.videoStillUrl);
	 }
	
}