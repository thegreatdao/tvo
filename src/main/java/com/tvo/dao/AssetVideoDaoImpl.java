package com.tvo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sakaiproject.genericdao.api.search.Search;
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
	
	/*
	 * start date and end date formats are as 2010-11-30
	 * @see com.tvo.dao.AssetVideoDao#findAssetVideosBetweenDates(java.lang.String, java.lang.String)
	 */
	@Override
	public List<AssetVideo> findAssetVideosBetweenDates(String startDate, String endDate)
	{
		String sql = "select * from asset_video video join asset_root root on video.asset_root_id = root.asset_root_id " +
				"where root.created_on between :startDate and :endDate";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		RowMapper<AssetVideo> rm = ParameterizedBeanPropertyRowMapper.newInstance(AssetVideo.class);
		List<AssetVideo> result = namedParameterJdbcTemplate.query(sql, paramMap, rm);
		for(AssetVideo assetVideo : result)
		{
			tvoJdbcGenericDaoImpl.fetchOneAssociation(assetVideo, AssetRoot.class);
			DomainPublish domainPublish = tvoJdbcGenericDaoImpl.findOneBySearch(DomainPublish.class, new Search("assetRootId", assetVideo.getAssetRootId()));
			DomainName domainName = tvoJdbcGenericDaoImpl.findById(DomainName.class, domainPublish.getDomainNameId());
			assetVideo.getAssetRoot().setDomainName(domainName.getDomainName());
		}
		return result;
	}


	@Override
	public void saveAssetVideo(AssetVideo assetVideo, AssetRoot assetRoot, String[] domain)
	{
		tvoJdbcGenericDaoImpl.saveParentWithChild(assetRoot, assetVideo);
		
		List<DomainName> domainNames = tvoJdbcGenericDaoImpl.findAll(DomainName.class); 
		
		boolean domainsMatch = false;
		
		for(String domainNameStr : domain)
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
