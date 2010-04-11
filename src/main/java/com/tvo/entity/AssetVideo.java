package com.tvo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.sakaiproject.genericdao.api.annotations.PersistentId;

@Data
@EqualsAndHashCode(callSuper=false)
public class AssetVideo extends TvoEntity
{

	private static final long serialVersionUID = 6049312423966660328L;
	
	@PersistentId
	private Integer assetVideoId;
	private Integer assetRootId;
	private Integer length;
	private String linkUrl;
	private String linkTitle;
	private String masterSeriesNumber;
	private Boolean isEmbedCode;
	private String thumbnailUrl;
	private String videoStillUrl;
	private String videoUrl;
	private String bcRefId;

}
