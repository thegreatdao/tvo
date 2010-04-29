package com.tvo.entity;

import java.util.List;

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
public class AssetVideo extends TvoEntity {
	
	private static final long serialVersionUID = 6049312423966660328L;
	
	@PersistentId
	private Integer assetVideoId;
	@XmlTransient
	private Integer assetRootId;
	private Integer length;
	@XmlElement(name="link_url")
	private String linkUrl;
	@XmlElement(name="link_title")
	private String linkTitle;
	@XmlElement(name="msn")
	private String masterSeriesNumber;
	@XmlElement(name="iec")
	private Boolean isEmbedCode;
	@XmlElement(name="thumbnail_url")
	private String thumbnailUrl;
	@XmlElement(name="videostill_url")
	private String videoStillUrl;
	@XmlElement(name="video_url")
	private String videoUrl;
	@XmlElement(name="bc_ref_id")
	private String bcRefId;
	@PersistentTransient
	@XmlElement(name="root")
	private AssetRoot assetRoot;
	@PersistentTransient
	private List<AssetArticle> assetArticles;
}
