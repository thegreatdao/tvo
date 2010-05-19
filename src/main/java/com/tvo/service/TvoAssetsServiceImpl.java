package com.tvo.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.dao.TvoJdbcGenericDaoImpl;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.TvoEntity;

@Service
@Transactional
public class TvoAssetsServiceImpl implements TvoAssetsService
{
	@Autowired
	private TvoJdbcGenericDaoImpl tvoJdbcGenericDaoImpl;
	
	public <T extends TvoEntity> void saveParentWithChild(T parent, T child)
	{
		tvoJdbcGenericDaoImpl.saveParentWithChild(parent, child);
	}
	
	public <T extends TvoEntity>void saveOrUpdate(T entity)
	{
		tvoJdbcGenericDaoImpl.saveOrUpdate(entity);
	}

	public <T extends TvoEntity> boolean delete(Class<T> entityType, Serializable id)
	{
		return tvoJdbcGenericDaoImpl.delete(entityType, id);
	}

	@Transactional(readOnly=true)
	public <T extends TvoEntity> T findById(Class<T> entityType, Serializable id)
	{
		return tvoJdbcGenericDaoImpl.findById(entityType, id);
	}

	@Transactional(readOnly=true)
	public <T extends TvoEntity> List<T> findAll(Class<T> entityType)
	{
		return tvoJdbcGenericDaoImpl.findAll(entityType);
	}
	
	@Transactional(readOnly=true)
	public <T extends TvoEntity> void fetchOneAssociation(T entity, Class<? extends TvoEntity> entityType)
	{
		tvoJdbcGenericDaoImpl.fetchOneAssociation(entity, entityType);
	}
	
	@Transactional(readOnly=true)
	public <T extends TvoEntity> void fetchOneAssociation(Class<? extends TvoEntity> child, Class<? extends TvoEntity> parent, Serializable childId)
	{
		tvoJdbcGenericDaoImpl.fetchOneAssociation(child, parent, childId);
	}
	
	@Transactional(readOnly=true)
	public <T extends TvoEntity> List<T> findAllBySearch(Class<T> entityType, Map<String, Object> parameters)
	{
		return tvoJdbcGenericDaoImpl.findAllByQuery(entityType, parameters);
	}
	
	@Transactional(readOnly=true)
	public <T extends TvoEntity> T findOneBySearch(Class<T> entityType, Map<String, Object> parameters)
	{
		return tvoJdbcGenericDaoImpl.findOneByQuery(entityType, parameters);
	}
	
	@Transactional(readOnly=true)
	public <T extends TvoEntity> T findAssetByAssetRootId(Class<T> entityType, int assetRootId)
	{
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("assetRootId", assetRootId);
		return tvoJdbcGenericDaoImpl.findOneByQuery(entityType, searchMap);
	}
	
	@Transactional(readOnly=true)
	public AssetRoot findAssetByTelescopeAssetId(String telescopeRecordId)
	{
		Map<String, Object> searchMapTelescopeAssetId = new HashMap<String, Object>();
		searchMapTelescopeAssetId.put("telescopeAssetId", telescopeRecordId);
	
		AssetRoot existingAssetRootRecord = this.findOneBySearch(AssetRoot.class, searchMapTelescopeAssetId);
		return existingAssetRootRecord;
	}
}
