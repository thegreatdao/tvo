package com.tvo.service;

import java.util.List;

import com.tvo.entity.AssetVideo;

public interface AssetVideoService
{
	public List<AssetVideo> findAssetVideosByDates(String startDate, String endDate);
}
