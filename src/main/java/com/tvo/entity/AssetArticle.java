package com.tvo.entity;

import lombok.Data;

@Data
public class AssetArticle implements java.io.Serializable
{

	private static final long serialVersionUID = -9066260942514094862L;
	
	private int assetArticleId;
	private String teaser;
	private String body;

}
