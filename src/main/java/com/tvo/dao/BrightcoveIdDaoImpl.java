package com.tvo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tvo.entity.BrightcoveId;
import com.tvo.entity.DomainName;

@Repository
public class BrightcoveIdDaoImpl implements BrightcoveIdDao
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	@Autowired
	private TvoJdbcGenericDaoImpl genericDao;
	
	public BrightcoveId[] getBrightcoveIdsByAssetVideoId(int assetVideoId) {
		
		String sql;
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("assetVideoId", Integer.toString(assetVideoId));
		RowMapper<BrightcoveId> domainNameRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(BrightcoveId.class);
		sql = "SELECT * FROM brightcove_id WHERE asset_video_id=:assetVideoId";
		List<BrightcoveId> brightcoveIdList = namedParameterJdbcTemplate.query(sql, paramMap, domainNameRowMapper);
		
		BrightcoveId[] brightcoveIds = new BrightcoveId[brightcoveIdList.size()];
		
		for(int i = 0; i < brightcoveIdList.size(); i++) {
			brightcoveIds[i] = brightcoveIdList.get(i);
		}
	
		return brightcoveIds;
	}
	
	public void save(BrightcoveId brightcoveId) {
		genericDao.save(brightcoveId);
	}
	
	public void deleteBrightcoveIdsByAssetVideoId(int assetVideoId) {
		String sql;
		
		sql = "DELETE FROM brightcove_id WHERE asset_video_id=:assetVideoId";
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("assetVideoId", Integer.toString(assetVideoId));
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
}
