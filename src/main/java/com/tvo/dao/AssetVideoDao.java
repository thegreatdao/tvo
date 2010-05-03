package com.tvo.dao;

import java.util.List;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainPublish;

public interface AssetVideoDao
{
	public List<AssetVideo> findAssetVideosBetweenDates(String startDate, String endDate);
	public void saveAssetVideo(AssetVideo assetVideo, AssetRoot assetRoot, DomainPublish domainPublish);
}
