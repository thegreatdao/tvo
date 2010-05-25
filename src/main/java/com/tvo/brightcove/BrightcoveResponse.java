package com.tvo.brightcove;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.codehaus.jettison.json.JSONObject;

public class BrightcoveResponse {
	
	private static String tokenSecret = "MP_rS39dA8YulIl2n8RCXx1evpJ5GUDlzQypNYRBpTU.";
	// private static String tokenSecret = "6vie5IAaEOFagDJOKUy4qDSpU3DWjNqh13vd8cM7IZvYapep4Upfzw."; // KIDS

	private int brightCoveVideoId;
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

	public void setAssetVideoId(int assetVideoId) {
		this.assetVideoId = assetVideoId;
	}
	
	public String getTokenSecret() {
		return tokenSecret;
	}

	@SuppressWarnings("static-access")
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

	public int getBrightCoveVideoId() {
		return brightCoveVideoId;
	}
	
	public static BrightcoveResponse getResponseByTelescopeAssetId(String tvoMainId) throws Exception {
		
			BrightcoveResponse response = new BrightcoveResponse();
					
			String brightcoveRequestUrl = "http://api.brightcove.com/services/library?command=find_video_by_reference_id&reference_id=" + tvoMainId + "&token=" + tokenSecret;
			HttpURLConnection connection = (HttpURLConnection) new URL(brightcoveRequestUrl).openConnection();
		
			connection.setRequestMethod("GET");
			connection.getInputStream();
			
			BufferedReader rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder jsonResponseSb = new StringBuilder();
			String line;
			
	        while ((line = rd.readLine()) != null) {
	        	jsonResponseSb.append(line + '\n');
	        }
	        
	        String brightcoveResponseJsonText = jsonResponseSb.toString();
	        
	        JSONObject jsonObj = new JSONObject(brightcoveResponseJsonText);
	        //String errorTest = "{ \"result\": null, \"error\": { \"code\": 103, \"name\": \"CallTimeoutError\", \"message\": \"The request you made is taking longer than expected to return. If requesting a large amount of data please try again with a smaller page_size.\" } }";
	        //JSONObject jsonObj = new JSONObject(errorTest);
	        
	        try {
		        response.brightCoveVideoId = jsonObj.getString("id");
		        response.length = Integer.parseInt(jsonObj.getString("length"));
		        response.videoStillUrl = jsonObj.getString("videoStillURL");
		        response.thumbnailUrl = jsonObj.getString("thumbnailURL");
		        response.referenceId = jsonObj.getString("referenceId");
	        } catch(Exception ex) {
	        	
	        	ex.printStackTrace();
	        	
	        	String result = jsonObj.getString("result");
	        	
	        	@SuppressWarnings("unused")
				int foo = 1;
	        	
	        	if(result == null) {
	        		
	        	}
	        }
	        
	        return response;
	}
	
	public static String getUrlToken(String domainName) {
		
		String secretToken = null;
		
		if(domainName.equals("tvomain.org")) {
			return "MP_rS39dA8YulIl2n8RCXx1evpJ5GUDlzQypNYRBpTU.";
		}
		
		if(domainName.equals("tvo.org")) {
			return "0fmmXNVgKkPeT7msJNLHr4qmSgYRFIga5IgfQVUv0f_yzKvs2-dSWQ..";
		}
		
		if(domainName.equals("tvokids.org")) {
			return "coe-nT4ylJHlpfeOLCm1udUEHHBRLa8w60zjaP3tl9mzepn-NBp4Ug..";
		}

		if(domainName.equals("tvoparents.org")) {
			return "8Kc4N2hA_vHv1v6DlZ-mnxFFz6BpWynpHqEncpA-UnwX8oIyi9xc4g..";
		}
		
		return secretToken;
	}
	
	public static void main(String[] args) throws Throwable {
	    
		 BrightcoveResponse brightcoveResponse = BrightcoveResponse.getResponseByTelescopeAssetId("1837282190");
		 System.out.println(brightcoveResponse.videoStillUrl);
	 }
	
}