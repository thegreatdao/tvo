 package com.tvo.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.sakaiproject.genericdao.api.annotations.PersistentId;
import org.sakaiproject.genericdao.api.annotations.PersistentTransient;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlType(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssetRoot extends TvoEntity
{
	private static final long serialVersionUID = 3386949100542034153L;
	
	public enum AssetType
	{
		PROMO, SERIES, PROGRAM, VIDEO, ARTICLE, BLOG, GAME, CONTEST, NA
	}

	@PersistentTransient
	public AssetType getAssetType()
	{
		return AssetType.valueOf(this.asset_Type);
	}

	public void setAssetType(AssetType typeCategory)
	{
		this.asset_Type = typeCategory.toString();
	}
	
	@PersistentId
	@XmlTransient
	private Integer assetRootId;
	private Integer geoFilterId;
	private String telescopeAssetId;
	private Integer telescopeRecordId;
	private String title;
	private String source;
	
	@XmlElement(name="type")
	private String asset_Type;
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
	@PersistentTransient
	@XmlElement(name="domain_name")
	private String domainName;
}