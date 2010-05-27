package com.tvo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.DomainName;
import com.tvo.entity.DomainPublish;

@Repository
public class AssetVideoDaoImpl implements AssetVideoDao
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private TvoJdbcGenericDaoImpl tvoJdbcGenericDaoImpl;
	
	@Autowired
	private DomainPublishDaoImpl domainPublishImpl;
	
	/*
	 * start date and end date formats are as 2010-11-30
	 * @see com.tvo.dao.AssetVideoDao#findAssetVideosBetweenDates(java.lang.String, java.lang.String)
	 */
	
	@Override
	public List<AssetVideo> findAssetVideosBetweenDates(String startDate, String endDate)
	{
		String sql;
		Map<String, String> paramMap;
		
		sql = "select * from asset_video video join asset_root root on video.asset_root_id = root.asset_root_id " +
			  "where root.created_on between :startDate and :endDate";
		
		paramMap = new HashMap<String, String>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		RowMapper<AssetVideo> rm = ParameterizedBeanPropertyRowMapper.newInstance(AssetVideo.class);
		List<AssetVideo> result = namedParameterJdbcTemplate.query(sql, paramMap, rm);
		
		for(AssetVideo assetVideo : result)
		{
			tvoJdbcGenericDaoImpl.fetchOneAssociation(assetVideo, AssetRoot.class);
			assetVideo.setDomains(domainPublishImpl.getDomainsByAssetId(assetVideo.getAssetRootId()));
		}
		
		return result;
	}

	@Override
	public void save(AssetVideo assetVideo)
	{
		AssetRoot assetRoot = assetVideo.getAssetRoot();
		tvoJdbcGenericDaoImpl.saveParentWithChild(assetRoot, assetVideo);
		
		List<DomainName> domainNames = tvoJdbcGenericDaoImpl.findAll(DomainName.class); 
		
		boolean domainsMatch = false;
		
		if(assetRoot.getAssetRootId() != 0) {
			/*
			 * we need to purge domains from this record to make sure we do not create duplicates
			 */
			 
			domainPublishImpl.deleteDomainsByAssetId(assetRoot.getAssetRootId());
		}
		
		for(String domainNameStr : assetVideo.getDomains())
		{
			domainsMatch = false;
			
			for(DomainName domainName : domainNames) 
			{
				if(domainName.getDomainName().equals(domainNameStr))
				{
					domainsMatch = true;
					DomainPublish domainPublish = new DomainPublish();
					domainPublish.setDomainNameId(domainName.getDomainNameId());
					domainPublish.setAssetRootId(assetRoot.getAssetRootId());
					domainPublish.setPublished(true);
					tvoJdbcGenericDaoImpl.save(domainPublish);
				}
			}
			
			if(domainsMatch == false)
				throw new Error("Domain " + domainNameStr + " does not exist.");
		}
	}
}
