package com.tvo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvo.dao.AssetVideoDao;
import com.tvo.entity.AssetVideo;

@Service
public class AssetVideoServiceImpl implements AssetVideoService
{
	@Autowired
	private AssetVideoDao assetVideoDao;
	
	@Override
	public List<AssetVideo> findAssetVideosByDates(String startDate, String endDate)
	{
		return assetVideoDao.findAssetVideosBetweenDates(startDate, endDate);
	}

}
