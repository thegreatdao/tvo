package com.tvo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tvo.entity.DomainName;

@Repository
public class DomainPublishDaoImpl implements DomainPublishDao
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	public String[] getDomainsByAssetId(int assetRootId) {
		
		String sql;
		
		sql = "SELECT domain_name.* FROM domain_name " +
		      "INNER JOIN domain_publish ON(domain_name.domain_name_id=domain_publish.domain_name_id) " +
		      "WHERE domain_publish.asset_root_id=:assetRootId";

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("assetRootId", Integer.toString(assetRootId));
		RowMapper<DomainName> domainNameRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(DomainName.class);
		List<DomainName> domainNameList = namedParameterJdbcTemplate.query(sql, paramMap, domainNameRowMapper);
		
		String[] domainNames = new String[domainNameList.size()];
		
		for(int i = 0; i < domainNameList.size(); i++) {
			domainNames[i] = domainNameList.get(i).getDomainName();
		}
	
		return domainNames;
	}
	
	public void deleteDomainsByAssetId(int assetRootId) {
		String sql;
		
		sql = "DELETE FROM domain_publish WHERE asset_root_id=:assetRootId";
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("assetRootId", Integer.toString(assetRootId));
		namedParameterJdbcTemplate.queryForInt(sql, paramMap);
	}
}
