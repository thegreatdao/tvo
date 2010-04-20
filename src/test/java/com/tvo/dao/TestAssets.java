package com.tvo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.AssetRoot.AssetType;
import com.tvo.service.TvoAssetsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestAssets
{
	@Autowired
	private TvoAssetsService assetsService;
	
	private AssetRoot assetRoot;
	private AssetVideo assetVideo;
	
	@Before
	public void setUp()
	{
		assetRoot = new AssetRoot();
		assetRoot.setAgeRating("12");
		assetRoot.setAssetType(AssetType.ARTICLE);
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

		assetVideo = new AssetVideo();
		assetVideo.setBcRefId("bcRefId");
		assetVideo.setIsEmbedCode(true);
		assetVideo.setLength(68);
		assetVideo.setLinkTitle("linkTitle");
		assetVideo.setLinkUrl("linkUrl");
		assetVideo.setMasterSeriesNumber("masSerNum");
		assetVideo.setThumbnailUrl("thumbnailUrl");
		assetVideo.setVideoStillUrl("videoStillUrl");
		assetVideo.setVideoStillUrl("videoStillUrl");
		assetVideo.setVideoUrl("videoUrl");

	}

	@Test
	public void testAssetRootWithAssetVideo()
	{
		int initSizeOfAssetVideo = assetsService.findAll(AssetVideo.class).size();
		assetsService.saveParentWithChild(assetRoot, assetVideo);
		assetsService.fetchOneAssociation(assetVideo, AssetRoot.class);
		assertNotNull(assetRoot.getAssetRootId());
		assertNotNull(assetVideo.getAssetVideoId());
		AssetRoot loadedAssetRoot = assetsService.findById(AssetRoot.class, assetRoot.getAssetRootId());
		assertNotNull(loadedAssetRoot);
		List<AssetVideo> assetVideos = assetsService.findAll(AssetVideo.class);
		int initSizeAssetVideoPlus1 = assetVideos.size();
		assertTrue(initSizeOfAssetVideo == initSizeAssetVideoPlus1 -1);
		assetsService.fetchOneAssociation(assetVideo, AssetRoot.class);
		AssetVideo loadedAssetVideo = assetsService.findById(AssetVideo.class, assetVideo.getAssetVideoId());
		assertNull(loadedAssetVideo.getAssetRoot());
		assetsService.fetchOneAssociation(loadedAssetVideo, AssetRoot.class);
		assertNotNull(loadedAssetVideo.getAssetRoot());
		assetsService.delete(AssetVideo.class, assetVideo.getAssetVideoId());
		loadedAssetVideo = assetsService.findById(AssetVideo.class, assetVideo.getAssetVideoId());
		assertNull(loadedAssetVideo);
		int afterRemovalAssetVideoSize = assetsService.findAll(AssetVideo.class).size();
		assertTrue(initSizeOfAssetVideo == afterRemovalAssetVideoSize);
		assertEquals(assetRoot.getAssetRootId(), assetVideo.getAssetRootId());
	}
}
