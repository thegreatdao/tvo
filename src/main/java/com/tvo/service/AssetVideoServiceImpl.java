package com.tvo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.dao.AssetVideoDao;
import com.tvo.dao.DomainPublishDao;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainPublish;

@Service
@Transactional
public class AssetVideoServiceImpl implements AssetVideoService
{
	@Autowired
	private AssetVideoDao assetVideoDao;
	
	@Autowired
	private TvoAssetsService assetsService;
	
	@Autowired
	private DomainPublishDao domainPublishDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<AssetVideo> findAssetVideosByDates(String startDate, String endDate)
	{
		return assetVideoDao.findAssetVideosBetweenDates(startDate, endDate);
	}

	@Override
	public void saveAssetVideo(AssetVideo assetVideo)
	{
		assetVideoDao.saveAssetVideo(assetVideo);
	}
	
	public AssetVideo getAssetVideoByTelescopeAssetId(String assetId)
	{
		AssetVideo assetVideo = null;
		AssetRoot existingAssetRootRecord = assetsService.findAssetByTelescopeAssetId(assetId);
		
		if(existingAssetRootRecord != null) {

			AssetVideo existingAssetVideoRecord = assetsService.findAssetByAssetRootId(AssetVideo.class, existingAssetRootRecord.getAssetRootId());
			existingAssetVideoRecord.setAssetRoot(existingAssetRootRecord);
			existingAssetVideoRecord.setDomains(domainPublishDao.getDomainsByAssetId(existingAssetRootRecord.getAssetRootId()));
			assetVideo = existingAssetVideoRecord;
		}
		
		return assetVideo;
	}
}
