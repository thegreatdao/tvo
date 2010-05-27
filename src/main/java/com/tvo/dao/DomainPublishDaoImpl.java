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
	
	@Autowired
	private TvoJdbcGenericDaoImpl tvoJdbcGenericDao;
	
	public List<DomainName> getDomainsByAssetId(int assetRootId) {
		
		String sql;
		
		sql = "SELECT domain_name.* FROM domain_name " +
		      "INNER JOIN domain_publish ON(domain_name.domain_name_id=domain_publish.domain_name_id) " +
		      "WHERE domain_publish.asset_root_id=:assetRootId";

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("assetRootId", Integer.toString(assetRootId));
		
		return innerQuery(sql, paramMap);
	}
	
	public List<DomainName> getAll() {
		
		String sql = "SELECT domain_name.* FROM domain_name";
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		return innerQuery(sql, paramMap);
	}
	
	private List<DomainName> innerQuery(String sql, HashMap<String, String> paramMap) {
		
		RowMapper<DomainName> domainNameRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(DomainName.class);
		List<DomainName> domainNameList = namedParameterJdbcTemplate.query(sql, paramMap, domainNameRowMapper);
		return domainNameList;
	}
	
	public void deleteDomainsByAssetId(int assetRootId) {
		String sql;
		
		sql = "DELETE FROM domain_publish WHERE asset_root_id=:assetRootId";
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("assetRootId", Integer.toString(assetRootId));
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	public static DomainName getByDomainName(List<DomainName> domainNameList, String domainNameSearch) {
		
		DomainName domainNameObj = null;
		
		for(DomainName domainName : domainNameList) {
			if(domainName.getDomainName().equals(domainNameSearch)) {
				return domainName;
			}
		}
		
		return domainNameObj;
	}
	
	public static DomainName getByDomainId(List<DomainName> domainNameList, int domainNameIdSearch) {
		
		DomainName domainNameObj = null;
		
		for(DomainName domainName : domainNameList) {
			if(domainName.getDomainNameId().equals(domainNameIdSearch)) {
				return domainName;
			}
		}
		
		return domainNameObj;
	}
	
	public static DomainName[] convertGenericToArray(List<DomainName> domainNameList) {
		
		DomainName[] domainNames = new DomainName[domainNameList.size()];
		
		for(int i = 0; i < domainNameList.size(); i++) {
			domainNames[i] = domainNameList.get(i);
		}
		
		return domainNames;
	}
	
	public static String[] convertGenericToString(List<DomainName> domainNameList) {
		
		String[] domainNames = new String[domainNameList.size()];
		
		for(int i = 0; i < domainNameList.size(); i++) {
			domainNames[i] = domainNameList.get(i).getDomainName();
		}
		
		return domainNames;
	}
}
