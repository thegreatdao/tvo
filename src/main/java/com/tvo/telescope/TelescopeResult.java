package com.tvo.telescope;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
import com.tvo.asset.AssetProgram;
import com.tvo.asset.AssetRoot;
import com.tvo.asset.AssetSeries;
import com.tvo.asset.AssetVideo;
import com.tvo.asset.AssetRoot.AssetType;
*/

import com.tvo.brightcove.BrightcoveResponse;
import com.tvo.entity.AssetProgram;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetRoot.*;
import com.tvo.entity.AssetVideo;

import com.tvo.telescope.TelescopeRelationship.Containers;
import com.tvo.telescope.TelescopeRelationship.RelationshipType;

public class TelescopeResult {
	
	private AssetType assetType;
	private NodeList childNodeList;
	private int telescopeRecordId;
	@SuppressWarnings("unused")
	private int[] boundContainers;
	private TelescopeQuery telescopeQuery;
	
	private String getTelescopeFieldValue(String fieldName) {
		
		String fieldNameFmt = fieldName.toUpperCase();
		
		if(fieldNameFmt.startsWith("EDITORIAL.")) {
			fieldNameFmt = fieldNameFmt.substring(10);
		}
		
		String fieldValue = "";
		
		for(int s = 0; s < childNodeList.getLength(); s++) {
			
			if(this.childNodeList.item(s).getNodeName().equals("FIELD")) {
				
				NamedNodeMap nodeMap = this.childNodeList.item(s).getAttributes();
		
				if(nodeMap.getNamedItem("NAME").getNodeValue().equals(fieldNameFmt)) {
					fieldValue = childNodeList.item(s).getTextContent();
				}
			}
		}
		
		return fieldValue;
	}
	
	@SuppressWarnings("unused")
	private String chaseTitle() {
		
		String programTitle = getTelescopeFieldValue("editorial.ttl_tv_listing");
		
		if(programTitle.isEmpty() || programTitle.equals("na") || programTitle.equals("n/a")) {
			programTitle = getTelescopeFieldValue("editorial.ttl_prg");
			
			if(programTitle.isEmpty()) {
				programTitle = getTelescopeFieldValue("editorial.ttl_web_dist");
				
				if(programTitle.isEmpty()) {
					programTitle = getTelescopeFieldValue("editorial.ttl_dist");
					
					if(programTitle.isEmpty()) {
						programTitle = getTelescopeFieldValue("editorial.ttl_working");
					}
				}
			}
		}
		
		return programTitle;
	}
	
	@SuppressWarnings("unused")
	private String chaseDescription() {
		
		String programDescription = getTelescopeFieldValue("editorial.desc_tv_listing_short");
		
		if(programDescription.isEmpty() || programDescription.equals("na") || programDescription.equals("n/a")) {
			programDescription = getTelescopeFieldValue("editorial.desc_tv_listing_long");
			
			if(programDescription.isEmpty()) {
				programDescription = getTelescopeFieldValue("editorial.desc_web_dist");
				
				if(programDescription.isEmpty()) {
					programDescription = getTelescopeFieldValue("editorial.desc_prg");
					
					if(programDescription.isEmpty()) {
						programDescription = getTelescopeFieldValue("editorial.desc_awards");
					}
				}
			}
		}
		
		return programDescription;
	}
	
	private void populateAssetRoot(AssetRoot assetRootInstance) throws Exception {
		assetRootInstance.setAssetType(this.getAssetType());
		assetRootInstance.setCreatedOn(new Date());
		assetRootInstance.setTelescopeRecordId(this.telescopeRecordId);
		assetRootInstance.setSource(getTelescopeFieldValue("editorial.id_source"));
		 
		String firstAirDate = getTelescopeFieldValue("first_air_v.first_air");
		
		String userTimeStart = getTelescopeFieldValue("frm.publish_start");
		String userTimeEnd = getTelescopeFieldValue("frm.publish_end");
		
		if(!userTimeStart.isEmpty()) {
			 
		}
		
		if(!userTimeEnd.isEmpty()) {
			
		}
		
		if(!firstAirDate.isEmpty()) {
			SimpleDateFormat df_releaseDate = new SimpleDateFormat("yyyy-MM-dd");
			assetRootInstance.setReleaseDate(df_releaseDate.parse(firstAirDate));
		}
		
		assetRootInstance.setCreatedOn(new Date());
		assetRootInstance.setCreatedBy("SYSTEM");
	}

	@SuppressWarnings("unused")
	private String getProgramOrSeriesTitle() {
		
		String title = null;
		
		title = getTelescopeFieldValue("editorial.ttl_tv_listing");
		
		return title;
	}

	public void setTelescopeQuery(TelescopeQuery telescopeQuery) {
		this.telescopeQuery = telescopeQuery; 
	}
	
	public String[] getPublishPointDomains(String publishPoint) {
		
		List<String> possibleDomains = new ArrayList<String>();
		
		if(publishPoint.contains("tvo.org")) {
			possibleDomains.add("tvo.org");
		}
		
		if(publishPoint.contains("tvokids")) {
			possibleDomains.add("tvokids.org");
		}
		
		if(publishPoint.contains("tvoparents")) {
			possibleDomains.add("tvoparents.org");
		}
		
		String[] domainsForAsset = new String[possibleDomains.size()];
		
		for(int i = 0; i < possibleDomains.size(); i++) {
			domainsForAsset[i] = possibleDomains.get(i);
		}
		
		return domainsForAsset;
	}

	public TelescopeResult(Node assetNode) {

		NamedNodeMap nodeMap = assetNode.getAttributes();
		Node assetAttributes = nodeMap.getNamedItem("ID");
		
		this.childNodeList = assetNode.getChildNodes();
		this.telescopeRecordId = Integer.parseInt(assetAttributes.getNodeValue());
		
		String assetType = getTelescopeFieldValue("editorial.asset_type");
		
		if(assetType.equals("Program")) {
			this.assetType = AssetType.PROGRAM;
		}
		
		if(assetType.equals("Element")) {
			String elementType = getTelescopeFieldValue("editorial.type_elmnt");
			
			if(elementType.equals("Video")) {
				this.assetType = AssetType.VIDEO;
			}
		}
		
		if(assetType.equals("Series")) {
			this.assetType = AssetType.SERIES;
		}
		
		if(this.assetType == null)
			this.assetType = AssetType.NA;
	}
	
	public AssetType getAssetType() {
		return assetType;
	}
	
	public AssetProgram getAssetProgram() throws Exception {
		AssetProgram assetProgram = new AssetProgram();
		AssetRoot assetRoot = new AssetRoot();
		assetProgram.setAssetRoot(assetRoot);
		return assetProgram;
	}
	
	public AssetVideo getAssetVideo() throws Exception {
		AssetVideo assetVideo = new AssetVideo();
		AssetRoot assetRoot = new AssetRoot();
		assetVideo.setAssetRoot(assetRoot);
		
		assetVideo.getAssetRoot().setTelescopeAssetId(getTelescopeFieldValue("editorial.id_elmnt"));
		assetVideo.getAssetRoot().setDescriptionInternet(getTelescopeFieldValue("editorial.desc_elmnt"));
		assetVideo.setMasterSeriesNumber(getTelescopeFieldValue("editorial.id_series"));

		populateAssetRoot(assetVideo.getAssetRoot());
		
		int[] relatedProgramIds = this.telescopeQuery.getRelated(assetVideo.getAssetRoot().getTelescopeRecordId(), Containers.CONTENT_DIGITAL, RelationshipType.CHILD_TO_PARENT);
		
		if(relatedProgramIds != null) {
			
			int assetContentDigitalId = relatedProgramIds[0];
			
			TelescopeResult contentDigitalResult = this.telescopeQuery.getResult(assetContentDigitalId, TelescopeQuery.getDefaultFieldNames());
			
			assetRoot.setTitle(contentDigitalResult.getTelescopeFieldValue("editorial.ttl_web_dist"));
			assetRoot.setDescriptionInternet(contentDigitalResult.getTelescopeFieldValue("editorial.desc_web_dist"));
			assetRoot.setDescriptionShort(contentDigitalResult.getTelescopeFieldValue("editorial.desc_tagline"));
		}
		
		int[] relatedFormAssetIds = this.telescopeQuery.getRelated(assetVideo.getAssetRoot().getTelescopeRecordId(), Containers.REQUESTED_MEDIA, RelationshipType.CHILD_TO_PARENT);
		
		if(relatedFormAssetIds != null) {
			
			boolean isBrightcove = false;
			
			for(int assetFormId : relatedFormAssetIds) {
				
				TelescopeResult requestMediaFormResult = this.telescopeQuery.getResult(assetFormId, TelescopeQuery.getDefaultFieldNames());
				
				String publishPoint = requestMediaFormResult.getTelescopeFieldValue("frm.publish_point").toLowerCase();
				System.out.println(publishPoint);

				if(publishPoint.contains("brightcove")) {
					
					isBrightcove = true;
					
					/*
					 * Publish points. This tells us what website this asset is available to.
					 */
					
					assetVideo.setDomains(getPublishPointDomains(publishPoint));
					
					SimpleDateFormat df_releaseDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
					
					String frmPublishStart = requestMediaFormResult.getTelescopeFieldValue("frm.publish_start");
					String frmPublishEnd = requestMediaFormResult.getTelescopeFieldValue("frm.publish_end");
					
					if(frmPublishStart.length() == 0 || frmPublishEnd.length() == 0) {
						return null;
					}
					
					assetRoot.setUserTimeStart(df_releaseDate.parse(frmPublishStart));
					assetRoot.setUserTimeEnd(df_releaseDate.parse(frmPublishEnd));
					
					// WE MAY NEED TO CHANGE THIS
					assetRoot.setReleaseDate(df_releaseDate.parse(requestMediaFormResult.getTelescopeFieldValue("frm.publish_start")));
					
					BrightcoveResponse response = BrightcoveResponse.getResponseByTelescopeAssetId(assetRoot.getTelescopeAssetId());
					
					assetVideo.setLength(response.getLength());
					assetVideo.setVideoStillUrl(response.getVideoStillUrl());
					assetVideo.setThumbnailUrl(response.getThumbnailUrl());
					//assetVideo.setGeoFilter(GeoFilter.TEST_FILTER);
					assetVideo.getAssetRoot().setGeoFilterId(1);
				}
			}
			
			if(isBrightcove == false) {
				throw new Error("Unable to acquire brightcove information for record id: " + this.telescopeRecordId);
			}
			
		} else {
			throw new Error("Unable to obtain complete information to create a video asset from record id: " + this.telescopeRecordId);
		}
		
		/*
		 * We need to get the form information 
		 */
		
		return assetVideo;
	}
	
	/*
	public AssetProgram getAssetProgram() throws Exception {
		AssetProgram assetProgram = new AssetProgram();
		assetProgram.setTelescopeAssetId(getTelescopeFieldValue("editorial.id_prg"));
		
		assetProgram.setTitle(chaseTitle());
		assetProgram.setDescriptionShort(chaseDescription());
		
		populateAssetRoot(assetProgram.getAssetRootInstance());
	
		return assetProgram;
	}
	
	public AssetSeries getAssetSeries() throws Exception {
		
		AssetSeries assetSeries = new AssetSeries();
		assetSeries.setTelescopeAssetId(getTelescopeFieldValue("editorial.id_series"));
		
		assetSeries.setTitle(chaseTitle());
		assetSeries.setDescriptionShort(chaseDescription());

		populateAssetRoot(assetSeries.getAssetRootInstance());
		
		return assetSeries;
	}
	*/
}
