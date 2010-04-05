package com.tvo.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.thoughtworks.xstream.XStream;
import com.tvo.asset.AssetVideo;

@Path("asset_video")
public class AssetVideoRequest {
	
	private String messageNotFound;
	// @Context ServletConfig servletConfig;
	
	public AssetVideoRequest() {
		this.messageNotFound = "No results found";
	}
	
	@GET
	@Path("/all")
	@Produces("text/xml")
	public String getAllVideoAssets1() {
		
		List<AssetVideo> assetVideoList = AssetVideo.getAll();
    	
		XStream xstream = new XStream();
		xstream.alias("AssetVideo", AssetVideo.class);
		
		String outputBuffer = "<query>";
		
    	for(AssetVideo assetVideo : assetVideoList) {
    		outputBuffer += xstream.toXML(assetVideo);
    		outputBuffer += "\n";
    	}
    	
    	outputBuffer += "</query>";
    	
    	return outputBuffer;
	}
	
	@GET
	@Path("/id/{assetVideoId}")
	@Produces("text/xml")
	public String getVideoAssetById(@PathParam("assetVideoId") int assetVideoId) {
		
		XStream xstream = new XStream();
		xstream.alias("AssetVideo", AssetVideo.class);
		AssetVideo assetVideo = AssetVideo.getById(assetVideoId);
		
		String outputBuffer = "<query>";
		
		if(assetVideo != null) {
			outputBuffer += xstream.toXML(assetVideo);
		} else {
			outputBuffer += this.messageNotFound;
		}
		
		outputBuffer += "</query>";
    	return outputBuffer;
	}
}
