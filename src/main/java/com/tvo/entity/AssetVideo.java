package com.tvo.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.sakaiproject.genericdao.api.annotations.PersistentColumnName;
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
	private Integer assetProgramId;
	private Integer length;
	private String linkUrl;
	private String linkTitle;
	private String masterSeriesNumber;
	
	@PersistentColumnName("is_embed_code")
	private boolean embedCode;
	private String thumbnailUrl;
	private String videoStillUrl;
	private String videoUrl;
	private String bcRefId;
	
	@PersistentTransient 
	private AssetRoot assetRoot;
	
	@PersistentTransient
	private List<AssetArticle> assetArticles;
	
	@PersistentTransient
	private String[] domains;
	
	@PersistentTransient
	private BrightcoveId[] brightcoveIds;
}
