package com.tvo.service;

import com.tvo.entity.AssetVideo;

public class AssetDaoService extends TvoDaoService
{
	public void saveAssetVideo(AssetVideo assetVideo)
	{
		saveParentWithChild(assetVideo, assetVideo.getAssetRoot());
	}
}