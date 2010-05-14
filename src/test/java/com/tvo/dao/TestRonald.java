package com.tvo.dao;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainPublish;
import com.tvo.entity.AssetRoot.AssetType;
import com.tvo.service.AssetVideoService;
import com.tvo.service.TvoAssetsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TestRonald
{
	@Autowired
	private TvoAssetsService assetsService;
	@Autowired
	private AssetVideoService assetVideoService;
	
	private AssetRoot assetRoot;
	private AssetVideo assetVideo;
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
		assetVideo.setAssetRoot(assetRoot);
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
		
		String[] domainList = new String[2];
		domainList[0] = "tvo.org";
		domainList[1] = "tvokids.org";
		assetVideo.setDomains(domainList);
	}


	public void justInsert()
	{
		assetVideoService.saveAssetVideo(assetVideo);
	}
	
	@Test
	public void justLoad()
	{
		List<AssetVideo> assetVideos = assetVideoService.findAssetVideosByDates("2010-02-05", "2010-05-06");
		
		for(AssetVideo assetVideo : assetVideos) 
		{
			System.out.println("ID: " + assetVideo.getAssetVideoId());
		}
	}
}