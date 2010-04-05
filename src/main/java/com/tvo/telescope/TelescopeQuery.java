package com.tvo.telescope;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.northplains.soapclient.TSWebSOAPUtils;
import com.northplains.soapclient.stubs.*;
import com.tvo.asset.AssetRoot;
import com.tvo.asset.AssetRoot.AssetType;
import com.tvo.databases.DbTelescope;
import com.tvo.telescope.TelescopeConnection;

public class TelescopeQuery {
	
	public enum Containers { CONTENT_DIGITAL, REQUESTED_MEDIA };
	public enum RelationshipType { CHILD_TO_PARENT, PARENT_TO_CHILD }
	
	private static String[] fieldNames = { "editorial.record_id",
										   "editorial.id_source",
										   "editorial.asset_type",
										   "editorial.desc_tagline",
										   "editorial.aud_rating",
										   "FRM.publish_start",
										   "FRM.publish_end",
										   "FRM.publish_point",
										   "FRM.embed",
										   "first_air_v.first_air",
										   "editorial.homepage",
										   "editorial.related_link_txt",
										   "editorial.id_series",
										   "editorial.id_web",
										   "editorial.id_elmnt",
										   "editorial.id_prg",
										   "editorial.id_seg",
										   "editorial.id_elmnt",
										   "editorial.type_elmnt",
										   "editorial.ttl_tv_listing",
										   "editorial.ttl_series",
										   "editorial.ttl_web_dist",
										   "editorial.ttl_dist",
										   "editorial.ttl_working",
										   "editorial.desc_tv_listing_short",
										   "editorial.desc_tv_listing_long",
										   "editorial.desc_web_dist",
										   "editorial.desc_series",
										   "editorial.desc_awards",
										   "editorial.content_digital",
										   "editorial.program_cuts",
										   "asset_last_updated_v.last_update" };
	
	private TelescopeConnection telescopeConnection;
	private TSWebServiceService service;
	private TSWebService stub;
	
	public TelescopeQuery(TelescopeConnection telescopeConnection) throws Exception {
		this.telescopeConnection = telescopeConnection;
		this.service = new TSWebServiceServiceLocator();
		this.stub = service.getsoapservice(new java.net.URL(this.telescopeConnection.getEndPointUrl()));
	}
	
	public static String[] getDefaultFieldNames() {
		return fieldNames;
	}
	
	public static AssetType createAsset(int assetId) {
		
		AssetType assetType;
		assetType = AssetType.VIDEO;
		
		return assetType;
	}
	
	@SuppressWarnings("unchecked") 
	public String[] enumerateFields() throws Exception {
		
		String[] fieldsAvailable = null;
		 
        NPSMapArray tResults = this.stub.enumerateFields();
        if (tResults != null) {
        	NPSMap[] arMaps = tResults.getMaps(); 
        	HashMap[] arHashMaps = TSWebSOAPUtils.NPSMapsToJavaMaps(arMaps);
        	
        	fieldsAvailable = new String[arHashMaps.length];
        	
        	if (arHashMaps != null) {
        		for (int iCount = 0; iCount < arHashMaps.length; iCount++) {
        			fieldsAvailable[iCount] = arHashMaps[iCount].get("TABLE_NAME").toString() + "." + arHashMaps[iCount].get("COLUMN_NAME").toString();
        		}
        	}
        }
        
        return fieldsAvailable;
	}
	
	@SuppressWarnings("unchecked")
	private int[] _getAssetsByProgram(int[] programId) throws Throwable {
		
		HashMap[] telescopeQuery = new HashMap[programId.length];
		
		for(int i = 0; i < programId.length; i++ ) {
			int singleProgramId = programId[i];
			HashMap<String, String> criteria = new HashMap<String, String>();
			criteria.put("TABLE_NAME", "editorial");
			criteria.put("COLUMN_NAME", "id_prg");
			criteria.put("CONJUNCTION", "");
			criteria.put("OPERATOR", "IS");
			criteria.put("VALUE", Integer.toString(singleProgramId));
			telescopeQuery[i] = criteria;
		}
		
		NPSMap[] tNPSMap = TSWebSOAPUtils.JavaMapsToNPSMaps(telescopeQuery);
		int[]arRecord = this.stub.search(tNPSMap);
		
		return arRecord;
	}
	
	
	private int[] _getAssetsByDate(Date startDate, Date endDate, String assetType) throws Throwable {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		HashMap[] telescopeQuery;
		
		if(assetType == null) {
			telescopeQuery = new HashMap[2];
		} else {
			telescopeQuery = new HashMap[3];
		}
		
		
		// -- select * from tsp.editorial where record_id = 1807182
		
		/*
		criteria.put("TABLE_NAME", "frm");
		criteria.put("COLUMN_NAME", "req_type");
		criteria.put("CONJUNCTION", "");
		criteria.put("OPERATOR", "IS");
		criteria.put("VALUE", "Web Distribution");
		telescopeQuery[0] = criteria;
		
		criteria.clear();
		criteria.put("TABLE_NAME", "frm");
		criteria.put("COLUMN_NAME", "emma_stat");
		criteria.put("CONJUNCTION", "");
		criteria.put("OPERATOR", "IS");
		criteria.put("VALUE", "Order Complete");
		telescopeQuery[1] = criteria;
		
		criteria.clear();
		criteria.put("TABLE_NAME", "frm");
		criteria.put("COLUMN_NAME", "publish_point");
		criteria.put("CONJUNCTION", "");
		criteria.put("OPERATOR", "CONTAINS");
		criteria.put("VALUE", "Brightcove"); //"2010/03/02 00:00:00");  //dateFormat.format(endDate));
		telescopeQuery[3] = criteria;
		*/
		
		HashMap<String, String> criteriaA = new HashMap<String, String>();
		criteriaA.put("TABLE_NAME", "asset_last_updated_v");
		criteriaA.put("COLUMN_NAME", "last_update");
		criteriaA.put("CONJUNCTION", "AND");
		criteriaA.put("OPERATOR", "GREATER THAN");
		criteriaA.put("VALUE", dateFormat.format(startDate)); //"2010/03/01 00:00:00");  //dateFormat.format(startDate));
		telescopeQuery[0] = criteriaA;
		
		HashMap<String, String> criteriaB = new HashMap<String, String>();
		criteriaB.put("TABLE_NAME", "asset_last_updated_v");
		criteriaB.put("COLUMN_NAME", "last_update");
		criteriaB.put("CONJUNCTION", "AND");
		criteriaB.put("OPERATOR", "LESS THAN");
		criteriaB.put("VALUE", dateFormat.format(endDate)); //"2010/03/02 00:00:00");  //dateFormat.format(endDate));
		telescopeQuery[1] = criteriaB;
		
		if(assetType != null) {
			
			String assetTypeQueryValue = "";
			
			if(assetType.equals(AssetRoot.AssetType.SERIES)) {
				assetTypeQueryValue = "Series";
			}
			
			if(assetType.equals(AssetRoot.AssetType.PROGRAM)) {
				assetTypeQueryValue = "Program";
			}
			
			if(assetType.equals(AssetRoot.AssetType.VIDEO.toString())) {
				assetTypeQueryValue = "Element";
			}
			
			HashMap<String, String> assetCriteria = new HashMap<String, String>();
			assetCriteria.put("TABLE_NAME", "editorial");
			assetCriteria.put("COLUMN_NAME", "asset_type");
			assetCriteria.put("CONJUNCTION", "AND");
			assetCriteria.put("OPERATOR", "IS");
			assetCriteria.put("VALUE", assetTypeQueryValue);
			telescopeQuery[2] = assetCriteria;
		}
		
		NPSMap[] tNPSMap = TSWebSOAPUtils.JavaMapsToNPSMaps(telescopeQuery);
		int[]arRecord = this.stub.search(tNPSMap);
		
		return arRecord;
	}
	
	public int[] getAssetsByDate(Date startDate, Date endDate) throws Throwable {
		int[] arRecord = _getAssetsByDate(startDate, endDate, null);
		return arRecord;
	}
	
	public int[] getAssetsByDate(Date startDate, Date endDate, AssetRoot.AssetType type) throws Throwable {
		int[] arRecord = _getAssetsByDate(startDate, endDate, type.toString());
		return arRecord;
	}
	
	public int[] getAssetsByProgram(int programId) throws Throwable {
		int[] singleProgramId = new int[1];
		singleProgramId[0] = programId;
		return _getAssetsByProgram(singleProgramId);
	}
	
	public int[] getAssetsByProgram(int[] programId) throws Throwable {
		return _getAssetsByProgram(programId);
	}
	
	public TelescopeResult[] getResultSet(int[] arRecord, String[] columns) throws Exception {
		
		String xmlOutput = this.stub.getDataMultiple(arRecord, columns);
		
		InputSource xmlInputSource = new InputSource();
		xmlInputSource.setCharacterStream(new StringReader(xmlOutput));
		
		TelescopeResult[] telescopeResults = new TelescopeResult[arRecord.length];
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(xmlInputSource);
		
		document.getDocumentElement().normalize();
		
		NodeList nodeList = document.getElementsByTagName("ASSET");
		
		for(int s = 0; s < nodeList.getLength(); s++) {
			Node assetNode = nodeList.item(s);
			telescopeResults[s] = new TelescopeResult(assetNode);
			telescopeResults[s].setTelescopeQuery(this);
		}
		
		return telescopeResults;
	}
	
	public TelescopeResult getResult(int arRecord, String[] columns) throws Exception {
		
		int[] arRecords = new int[1];
		arRecords[0] = arRecord;
		String xmlOutput = this.stub.getDataMultiple(arRecords, columns);
		
		InputSource xmlInputSource = new InputSource();
		xmlInputSource.setCharacterStream(new StringReader(xmlOutput));
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(xmlInputSource);
		
		document.getDocumentElement().normalize();
		
		NodeList nodeList = document.getElementsByTagName("ASSET");
		
		Node assetNode = nodeList.item(0);
		
		TelescopeResult telescopeResult = new TelescopeResult(assetNode);
		telescopeResult.setTelescopeQuery(this);
		
		return telescopeResult;
	}
	
	public int[] getRelated(int recordId, TelescopeRelationship.Containers containerName, TelescopeRelationship.RelationshipType relationship) {
		
		char telescopeRelationship = 0;
		String telescopeContainer = null;
		
		switch(relationship) {
			case CHILD_TO_PARENT :
				telescopeRelationship  = 'P';
			break;
			
			case PARENT_TO_CHILD :
				telescopeRelationship  = 'C';
			break;
			
			default :
				
			break;
		}
		
		switch(containerName) {
		
			case CONTENT_DIGITAL :
				telescopeContainer = "content_digital";
				break;
				
			case REQUESTED_MEDIA :
				telescopeContainer = "fr_requested_media";
		}
		
		JdbcTemplate select = new JdbcTemplate();
    	select.setDataSource(DbTelescope.getDataSource());
    	
    	String sqlQuery = "SELECT tsp.get_container_ids(" + recordId + ", '" + telescopeContainer + "','" + telescopeRelationship + "') FROM dual";
    	SqlRowSet rowSet = select.queryForRowSet(sqlQuery);
    	
    	int returnResultIds[] = null;
    	
    	if(rowSet.next()) { // We are only interested in one row.
    		String resultLine = rowSet.getString(1);
    		
    		if(resultLine != null) {
    			
	    		if(resultLine.startsWith("|")) {
	    			resultLine = resultLine.substring(1);
	    		}
	    		
	    		if(resultLine.endsWith("|")) {
	    			resultLine = resultLine.substring(0, resultLine.length() - 1);
	    		}
	    		
	    		String[] resultIds = resultLine.split("\\|");
	    		
	    		returnResultIds = new int[resultIds.length];
	    		
	    		for(int i = 0; i < resultIds.length; i ++) {
	    			returnResultIds[i] = Integer.parseInt(resultIds[i]);
	    		}
    		}
    	}
    	
    	return returnResultIds;
	}
}
