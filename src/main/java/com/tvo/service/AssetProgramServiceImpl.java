package com.tvo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.dao.AssetProgramDao;
import com.tvo.entity.AssetProgram;
import com.tvo.entity.AssetRoot;

@Service
@Transactional
public class AssetProgramServiceImpl implements AssetProgramService
{
	@Autowired
	private AssetProgramDao assetProgramDao;
	
	@Autowired
	private TvoAssetsService assetsService;
	
	@Override
	public int save(AssetProgram assetProgram) {
		return assetProgramDao.save(assetProgram);
	}
	
	public AssetProgram getByTelescopeAssetId(String assetId) {
		
		AssetProgram assetProgram = null;
		
		/* Map<String, Object> searchMapTelescopeAssetId = new HashMap<String, Object>();
		searchMapTelescopeAssetId.put("telescopeAssetId", "telescopeAssetId");
		AssetRoot existingAssetRootRecord = assetsService.findOneBySearch(AssetRoot.class, searchMapTelescopeAssetId); */
		
		AssetRoot existingAssetRootRecord = assetsService.findAssetByTelescopeAssetId(assetId);
		
		if(existingAssetRootRecord != null) {
			
			AssetProgram existingAssetProgramRecord = assetsService.findAssetByAssetRootId(AssetProgram.class, existingAssetRootRecord.getAssetRootId());
			
			existingAssetProgramRecord.setAssetRoot(existingAssetRootRecord);
			assetProgram = existingAssetProgramRecord;
		}
		
		return assetProgram;
	}
}
