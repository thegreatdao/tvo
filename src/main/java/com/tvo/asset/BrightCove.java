package com.tvo.asset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

// import com.google.gson.*;

public class BrightCove {
	
	private String tokenSecret = "MP_rS39dA8YulIl2n8RCXx1evpJ5GUDlzQypNYRBpTU.";
	
	private int assetVideoId;
	private String domainName;
	private Date createdOn;
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;
	
	
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

	public void doSomething() throws Exception {
		
		int videoId = 1837282190;
		String brightCoveRequestUrl = "http://api.brightcove.com/services/library?command=find_video_by_id&video_id=" + videoId + "&token=" + this.tokenSecret;
		HttpURLConnection connection = (HttpURLConnection) new URL(brightCoveRequestUrl).openConnection();

		System.out.println("Bright Cove URL:" + brightCoveRequestUrl);
		
		connection.setRequestMethod("GET");
		connection.getInputStream();
		
		BufferedReader rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		
        while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }
      
        System.out.println(sb.toString());
	}
	
	public static void main(String[] args) throws Throwable {
	    
		 BrightCove brightCove = new BrightCove();
		 brightCove.doSomething();
		 
		 //BrightCove.doSomething();
	 }
}