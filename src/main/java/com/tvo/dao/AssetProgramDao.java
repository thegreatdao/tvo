package com.tvo.dao;

import com.tvo.entity.AssetProgram;;

public interface AssetProgramDao
{
	//public List<AssetProgram> findAssetVideosBetweenDates(String startDate, String endDate);
	public int save(AssetProgram assetProgram);
	
}