package com.tvo.dao;

import java.util.List;

import com.tvo.entity.AssetVideo;

public interface AssetVideoDao
{
	public List<AssetVideo> findAssetVideosBetweenDates(String startDate, String endDate);
}
