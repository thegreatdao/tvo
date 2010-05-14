package com.tvo.dao;

import java.util.List;

import com.tvo.entity.AssetProgram;;

public interface AssetProgramDao
{
	//public List<AssetProgram> findAssetVideosBetweenDates(String startDate, String endDate);
	
	public void save(AssetProgram assetProgram);
}