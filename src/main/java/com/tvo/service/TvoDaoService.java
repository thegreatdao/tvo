package com.tvo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.dao.TvoJdbcGenericDaoImpl;
import com.tvo.entity.AssetProgram;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.TvoEntity;

@Service
@Transactional
public class TvoDaoService
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
	
	/*
	 * Asset Specific Methods
	 */
	
	public <T extends TvoEntity> void assetVideoSave(AssetVideo assetVideo)
	{
		tvoJdbcGenericDaoImpl.assetVideoSave(assetVideo);
	}
	
	
	
	/*
	public <T extends TvoEntity> void assetProgramSave(AssetProgram assetProgram)
	{
		tvoJdbcGenericDaoImpl.assetProgramSave(assetProgram);
	}
	*/
	
/*	@Override
	public void addInterceptor(DaoOperationInterceptor interceptor)
	{
		
		super.addInterceptor(interceptor);
	}
	
	@Override
	public <T> int countAll(Class<T> entityType)
	{
		
		return super.countAll(entityType);
	}
	
	@Override
	public int countByProperties(Class entityClass, String[] entityProperties,
			Object[] values, int[] comparisons)
	{
		
		return super.countByProperties(entityClass, entityProperties, values,
				comparisons);
	}
	
	@Override
	public int countByProperties(Class entityClass, String[] entityProperties,
			Object[] values)
	{
		
		return super.countByProperties(entityClass, entityProperties, values);
	}
	
	@Override
	public <T> long countBySearch(Class<T> entityType, Search search)
	{
		
		return super.countBySearch(entityType, search);
	}
	
	@Override
	public <T> boolean delete(Class<T> entityType, Serializable id)
	{
		
		return super.delete(entityType, id);
	}
	
	@Override
	public void delete(Object entity)
	{
		
		super.delete(entity);
	}
	
	@Override
	public void deleteMixedSet(Set[] entitySets)
	{
		
		super.deleteMixedSet(entitySets);
	}
	
	@Override
	public <T> void deleteSet(Class<T> entityType, Serializable[] ids)
	{
		
		super.deleteSet(entityType, ids);
	}
	
	@Override
	public <T> void deleteSet(Set<T> entities)
	{
		
		super.deleteSet(entities);
	}
	
	@Override
	public <T> List<T> findAll(Class<T> entityType, int firstResult, int maxResults)
	{
		
		return super.findAll(entityType, firstResult, maxResults);
	}
	
	@Override
	public <T> T findById(Class<T> entityType, Serializable id)
	{
		
		return super.findById(entityType, id);
	}
	
	@Override
	public List findByProperties(Class entityClass, String[] entityProperties,
			Object[] values, int[] comparisons, int firstResult, int maxResults)
	{
		
		return super.findByProperties(entityClass, entityProperties, values,
				comparisons, firstResult, maxResults);
	}
	
	@Override
	public List findByProperties(Class entityClass, String[] entityProperties,
			Object[] values, int[] comparisons, String[] sortProperties,
			int firstResult, int maxResults)
	{
		
		return super.findByProperties(entityClass, entityProperties, values,
				comparisons, sortProperties, firstResult, maxResults);
	}
	
	@Override
	public List findByProperties(Class entityClass, String[] entityProperties,
			Object[] values, int[] comparisons, String[] sortProperties)
	{
		
		return super.findByProperties(entityClass, entityProperties, values,
				comparisons, sortProperties);
	}
	
	@Override
	public List findByProperties(Class entityClass, String[] entityProperties,
			Object[] values, int[] comparisons)
	{
		
		return super.findByProperties(entityClass, entityProperties, values,
				comparisons);
	}
	
	@Override
	public List findByProperties(Class entityClass, String[] entityProperties,
			Object[] values)
	{
		
		return super.findByProperties(entityClass, entityProperties, values);
	}
	
	@Override
	public <T> List<T> findBySearch(Class<T> entityType, Search search)
	{
		
		return super.findBySearch(entityType, search);
	}
	
	@Override
	public <T> T findOneBySearch(Class<T> entityType, Search search)
	{
		
		return super.findOneBySearch(entityType, search);
	}
	
	@Override
	public String getDeleteTemplate(Class<?> entityType)
	{
		
		return super.getDeleteTemplate(entityType);
	}
	
	@Override
	public String getIdColumn(Class<?> entityType)
	{
		
		return super.getIdColumn(entityType);
	}
	
	@Override
	public String getIdProperty(Class<?> entityType)
	{
		
		return super.getIdProperty(entityType);
	}
	
	@Override
	public void save(Object entity)
	{
		
		super.save(entity);
	}
	
	@Override
	public void saveMixedSet(Set[] entitySets)
	{
		
		super.saveMixedSet(entitySets);
	}
	
	@Override
	public <T> void saveSet(Set<T> entities)
	{
		
		super.saveSet(entities);
	}
	
	@Override
	public void setDataMappers(List<DataMapper> dataMappers)
	{
		
		super.setDataMappers(dataMappers);
	}
	
	@Override
	public void setInterceptor(DaoOperationInterceptor interceptor)
	{
		
		super.setInterceptor(interceptor);
	}
	
	@Override
	public void update(Object entity)
	{
		
		super.update(entity);
	}
*/}
