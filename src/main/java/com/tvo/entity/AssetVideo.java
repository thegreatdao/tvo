package com.tvo.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.sakaiproject.genericdao.api.annotations.PersistentId;
import org.sakaiproject.genericdao.api.annotations.PersistentTransient;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="video")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssetVideo extends TvoEntity
{

	private static final long serialVersionUID = 6049312423966660328L;
	
	@PersistentId
	@XmlElement(name="video_id")
	private Integer assetVideoId;
	@XmlTransient
	private Integer assetRootId;
	private Integer length;
	private String linkUrl;
	private String linkTitle;
	@XmlElement(name="msn")
	private String masterSeriesNumber;
	@XmlElement(name="iec")
	private Boolean isEmbedCode;
	@XmlElement(name="thumbnail_url")
	private String thumbnailUrl;
	@XmlElement(name="videostill_url")
	private String videoStillUrl;
	private String videoUrl;
	private String bcRefId;
	@PersistentTransient
	@XmlElement(name="root")
	private AssetRoot assetRoot;

}
