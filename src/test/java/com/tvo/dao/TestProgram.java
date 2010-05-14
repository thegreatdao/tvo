package com.tvo.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.entity.AssetProgram;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetRoot.AssetType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TestProgram
{	
	@Autowired
	private AssetProgramDao assetProgramDao;
	
	private AssetRoot assetRoot;
	private AssetProgram assetProgram;
	
	@Before
	public void setUp()
	{
		assetProgram = new AssetProgram();
		assetRoot = new AssetRoot();
		assetProgram.setAssetRoot(assetRoot);
		
		assetRoot.setAgeRating("12");
		assetRoot.setAssetType(AssetType.PROGRAM);
		assetRoot.setCreatedBy("createdBy");
		assetRoot.setCreatedOn(new Date());
		assetRoot.setDescriptionInternet("descriptionInternet");
		assetRoot.setDescriptionShort("descriptionShort");
		assetRoot.setDuration(new Date());
		assetRoot.setGeoFilterId(1);
		assetRoot.setReleaseDate(new Date());
		assetRoot.setSource("source");
		assetRoot.setTelescopeAssetId("telescopeAssetId");
		assetRoot.setTelescopeRecordId(1);
		assetRoot.setTitle("title");
		assetRoot.setUpdatedBy("updatedBy");
		assetRoot.setUpdatedOn(new Date());
		assetRoot.setUserTimeEnd(new Date());
		assetRoot.setUserTimeStart(new Date());
	}

	@Test
	public void testassetProgramWithAssetVideo()
	{
		assetProgramDao.save(assetProgram);
	}
}
