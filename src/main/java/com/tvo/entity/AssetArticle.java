package com.tvo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AssetArticle extends TvoEntity
{

	private static final long serialVersionUID = -9066260942514094862L;
	
	private int assetArticleId;
	private String teaser;
	private String body;
	private AssetRoot assetRoot;

}
