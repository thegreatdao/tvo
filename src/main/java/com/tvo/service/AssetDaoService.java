package com.tvo.service;

import org.springframework.stereotype.Service;

import com.tvo.entity.AssetVideo;

@Service
public class AssetDaoService extends TvoDaoService
{
	
	public void saveAssetVideo(AssetVideo assetVideo)
	{
		saveParentWithChild(assetVideo, assetVideo.getAssetRoot());
	}
}