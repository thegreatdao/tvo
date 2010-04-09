package com.tvo.entity;

import lombok.Data;

@Data
public class AssetVideo implements java.io.Serializable
{

	private static final long serialVersionUID = 6049312423966660328L;
	
	private Integer assetVideoId;
	private AssetRoot assetRoot;
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
