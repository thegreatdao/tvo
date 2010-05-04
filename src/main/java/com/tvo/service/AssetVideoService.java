package com.tvo.service;

import java.util.List;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainPublish;

public interface AssetVideoService
{
	public List<AssetVideo> findAssetVideosByDates(String startDate, String endDate);
	public void saveAssetVideo(AssetVideo assetVideo, AssetRoot assetRoot, DomainPublish domainPublish);
}
