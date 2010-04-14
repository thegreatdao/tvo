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

@Data
@EqualsAndHashCode(callSuper=false)
@XmlType(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssetRoot extends TvoEntity
{
	
	private static final long serialVersionUID = 3386949100542034153L;

	@PersistentId
	@XmlTransient
	private Integer assetRootId;
	@XmlElement(name="geo_id")
	private Integer geoFilterId;
	@XmlElement(name="taid")
	private String telescopeAssetId;
	@XmlElement(name="trId")
	private Integer telescopeRecordId;
	private String title;
	private String source;
	@XmlElement(name="type")
	private String assetType;
	@XmlElement(name="DI")
	private String descriptionInternet;
	@XmlElement(name="DESC")
	private String descriptionShort;
	@XmlElement(name="AGE_RATE")
	private String ageRating;
	@XmlElement(name="user_time_start")
	private Date userTimeStart;
	@XmlElement(name="user_time_end")
	private Date userTimeEnd;
	private Date duration;
	@XmlElement(name="release_date")
	private Date releaseDate;
	@XmlElement(name="created_on")
	private Date createdOn;
	@XmlElement(name="updated_on")
	private Date updatedOn;
	@XmlElement(name="created_by")
	private String createdBy;
	@XmlElement(name="updated_by")
	private String updatedBy;

}
