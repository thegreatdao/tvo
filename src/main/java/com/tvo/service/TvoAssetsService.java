package com.tvo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.TvoEntity;

public interface TvoAssetsService
{
	public <T extends TvoEntity> void saveParentWithChild(T parent, T child);
	
	public <T extends TvoEntity> void saveOrUpdate(T entity);

	public <T extends TvoEntity> boolean delete(Class<T> entityType, Serializable id);

	public <T extends TvoEntity> T findById(Class<T> entityType, Serializable id);

	public <T extends TvoEntity> List<T> findAll(Class<T> entityType);
	
	public <T extends TvoEntity> void fetchOneAssociation(T entity, Class<? extends TvoEntity> entityType);
	
	public <T extends TvoEntity> void fetchOneAssociation(Class<? extends TvoEntity> child, Class<? extends TvoEntity> parent, Serializable childId);
	
	public <T extends TvoEntity> List<T> findAllBySearch(Class<T> entityType, Map<String, Object> parameters);
	
	public <T extends TvoEntity> T findOneBySearch(Class<T> entityType, Map<String, Object> parameters);

	public <T extends TvoEntity> T findAssetByAssetRootId(Class<T> entityType, int assetRootId);
	
	public AssetRoot findAssetByTelescopeAssetId(String assetId);
	
}
