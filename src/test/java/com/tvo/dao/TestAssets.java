package com.tvo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainPublish;
import com.tvo.entity.AssetRoot.AssetType;
import com.tvo.service.AssetVideoService;
import com.tvo.service.TvoAssetsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestAssets
{
	@Autowired
	private TvoAssetsService assetsService;
	@Autowired
	private AssetVideoService assetVideoService;
	
	private AssetRoot assetRoot;
	private AssetVideo assetVideo;
	private AssetProgram assetProgram;
	private DomainPublish domainPublish;
	
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
		assetVideo.setEmbedCode(true);
		assetVideo.setLength(68);
		assetVideo.setLinkTitle("linkTitle");
		assetVideo.setLinkUrl("linkUrl");
		assetVideo.setMasterSeriesNumber("masSerNum");
		assetVideo.setThumbnailUrl("thumbnailUrl");
		assetVideo.setVideoStillUrl("videoStillUrl");
		assetVideo.setVideoStillUrl("videoStillUrl");
		assetVideo.setVideoUrl("videoUrl");
		
		domainPublish = new DomainPublish();
		domainPublish.setCreatedBy("createdBy");
		domainPublish.setCreatedOn(new Date());
		domainPublish.setDomainNameId(3);
		domainPublish.setPublished(true);
		domainPublish.setUpdatedBy("updatedBy");
		domainPublish.setUpdatedOn(new Date());
		
		
		/*
		 * Asset program is now required for asset video to work.
		 */
		
		assetProgram = new AssetProgram();
		AssetRoot assetRootProgram = new AssetRoot();
		assetProgram.setAssetRoot(assetRootProgram);
		
		assetRootProgram.setAgeRating("12");
		assetRootProgram.setAssetType(AssetType.PROGRAM);
		assetRootProgram.setCreatedBy("createdBy");
		assetRootProgram.setCreatedOn(new Date());
		assetRootProgram.setDescriptionInternet("descriptionInternet");
		assetRootProgram.setDescriptionShort("descriptionShort");
		assetRootProgram.setDuration(new Date());
		assetRootProgram.setGeoFilterId(1);
		assetRootProgram.setReleaseDate(new Date());
		assetRootProgram.setSource("source");
		assetRootProgram.setTelescopeAssetId("telescopeAssetId");
		assetRootProgram.setTelescopeRecordId(1);
		assetRootProgram.setTitle("title");
		assetRootProgram.setUpdatedBy("updatedBy");
		assetRootProgram.setUpdatedOn(new Date());
		assetRootProgram.setUserTimeEnd(new Date());
		assetRootProgram.setUserTimeStart(new Date());
	}

	@Test
	public void testAssetRootWithAssetVideo()
	{
		int initSizeOfAssetVideo = assetsService.findAll(AssetVideo.class).size();
		
		String[] domainList = new String[2];
		domainList[0] = "tvo.org";
		domainList[1] = "tvokids.org";
		assetVideo.setDomains(domainList);
		assetVideoService.saveAssetVideo(assetVideo);
		
		assetsService.fetchOneAssociation(assetVideo, AssetRoot.class);
		assertNotNull(assetRoot.getAssetRootId());
		assertNotNull(assetVideo.getAssetVideoId());
		assetRoot.setAgeRating("AR");
		assetVideo.setEmbedCode(false);
		assetsService.saveParentWithChild(assetRoot, assetVideo);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("bcRefId", "bcRefId");
		parameters.put("linkUrl", "linkUrl");
		parameters.put("embedCode", false);
		AssetVideo findByIdAssetVideo = assetsService.findById(AssetVideo.class, assetVideo.getAssetVideoId());
		assertFalse(findByIdAssetVideo.isEmbedCode());
		AssetRoot findByIdAssetRoot = assetsService.findById(AssetRoot.class, assetRoot.getAssetRootId());
		assertTrue(findByIdAssetRoot.getAgeRating().equals("AR"));
		AssetVideo findAssetVideoBySearch = assetsService.findOneBySearch(AssetVideo.class, parameters);
		assertNotNull(findAssetVideoBySearch);
		assertTrue(findAssetVideoBySearch.getAssetVideoId().equals(assetVideo.getAssetVideoId()));
		List<AssetVideo> findAllAssetVideosBySearch = assetsService.findAllBySearch(AssetVideo.class, parameters);
		assertTrue(findAllAssetVideosBySearch.size()==1);
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
