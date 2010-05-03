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
		}
		return result;
	}

	@Override
	public void saveAssetVideo(AssetVideo assetVideo, AssetRoot assetRoot, DomainPublish domainPublish)
	{
		
	}

}
