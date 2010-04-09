package com.tvo.entity;

import java.util.Date;

import lombok.Data;

@Data
public class AssetRoot implements java.io.Serializable
{
	
	private static final long serialVersionUID = 3386949100542034153L;

	private Integer assetRootId;
	private Integer geoFilterId;
	private String telescopeAssetId;
	private Integer telescopeRecordId;
	private String title;
	private String source;
	private String assetType;
	private String descriptionInternet;
	private String descriptionShort;
	private String ageRating;
	private Date userTimeStart;
	private Date userTimeEnd;
	private Date duration;
	private Date releaseDate;
	private Date createdOn;
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;

}
