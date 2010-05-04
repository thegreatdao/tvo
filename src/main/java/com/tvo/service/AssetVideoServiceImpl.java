package com.tvo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.dao.AssetVideoDao;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainPublish;

@Service
@Transactional
public class AssetVideoServiceImpl implements AssetVideoService
{
	@Autowired
	private AssetVideoDao assetVideoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<AssetVideo> findAssetVideosByDates(String startDate, String endDate)
	{
		return assetVideoDao.findAssetVideosBetweenDates(startDate, endDate);
	}

	@Override
	public void saveAssetVideo(AssetVideo assetVideo, AssetRoot assetRoot, String[] domain)
	{
		assetVideoDao.saveAssetVideo(assetVideo, assetRoot, domain);
	}
	
	@Override
	public void saveAssetVideo(AssetVideo assetVideo)
	{
		assetVideoDao.saveAssetVideo(assetVideo);
	}
}
