package com.tvo.dao;

import static com.tvo.dao.util.TvoJdbcGenericDaoHelper.getFiledNameAndType;
import static com.tvo.dao.util.TvoJdbcGenericDaoHelper.getSearchFromMap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.azeckoski.reflectutils.ReflectUtils;
import org.sakaiproject.genericdao.api.search.Search;
import org.sakaiproject.genericdao.springjdbc.JdbcGeneralGenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvo.dao.util.bean.TvoEntityFieldNameAndTypePair;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.TvoEntity;

public class TvoJdbcGenericDaoImpl extends JdbcGeneralGenericDao
{	
	protected static final Logger LOGGER = LoggerFactory.getLogger(TvoJdbcGenericDaoImpl.class);
	
	public <T extends TvoEntity> void saveParentWithChild(T parent, T child)
	{
		saveOrUpdate(parent);
		String key = getIdColumn(parent.getClass());
		key = mapFieldName(key);
		Object value = getIdValue(parent);
		ReflectUtils.getInstance().setFieldValue(child, key, value);
		saveOrUpdate(child);
	}
	
	public <T extends TvoEntity> void saveOrUpdate(T entity)
	{
		save(entity);
	}
	
	public void fetchOneAssociation(Class<? extends TvoEntity> child, Class<? extends TvoEntity> parent, Serializable childId)
	{
		TvoEntity entity = findById(child, childId);
		fetchOneAssociation(entity, parent);
	}
	
	public <T extends TvoEntity> void fetchOneAssociation(T entity, Class<? extends TvoEntity> entityType)
	{
		TvoEntityFieldNameAndTypePair fieldNameAndTypePair = getFiledNameAndType(entity, entityType);
		if(fieldNameAndTypePair != null)
		{
			String property = mapFieldName(getIdColumn(entityType));
			Search search = new Search(property, ReflectUtils.getInstance().getFieldValue(entity, property));
			if(fieldNameAndTypePair.isCollectionType())
			{
				LOGGER.info("loading childen not child you see");
			}
			else
			{
				TvoEntity findOneBySearch = findOneBySearch(entityType, search);
				ReflectUtils.getInstance().setFieldValue(entity, fieldNameAndTypePair.getKey(), findOneBySearch);
			}
		}
	}
	
	public <T extends TvoEntity> T findOneByQuery(Class<T> entityType, Map<String, Object> parameters)
	{
		return findOneBySearch(entityType, getSearchFromMap(parameters));
	}
	
	public <T extends TvoEntity> List<T> findAllByQuery(Class<T> entityType, Map<String, Object> parameters)
	{
		return findBySearch(entityType, getSearchFromMap(parameters));
	}
	
	public <T extends TvoEntity> void fetchAllAssociations(T entity)
	{
		
	}
	
	public <T extends TvoEntity> void fetchSelectiveAssociations(T entity, List<Class<? extends TvoEntity>> entityTypes)
	{
		for(Class<? extends TvoEntity> entityType : entityTypes)
		{
			fetchOneAssociation(entity, entityType);
		}
	}
	
	public <T extends TvoEntity> T eagerFetchAll(T entity)
	{
		return null;
	}
	
	public <T extends TvoEntity> T eagerSelectiveFetch(T entity)
	{
		return null;
	}
	
	private String mapFieldName(String fieldName)
	{
		fieldName = StringUtils.lowerCase(fieldName);
		String[] name = StringUtils.split(fieldName, "_");
		StringBuffer result = new StringBuffer();
		int counter = 0;
		for (String part : name)
		{
			if (counter != 0)
			{
				result.append(StringUtils.capitalize(part));
			}
			else
			{
				result.append(part);
			}
			counter++;
		}
		return result.toString();
	}
	
	/*
	 * Asset Specific methods
	 */
	
	public void assetVideoSave(AssetVideo assetVideo)
	{
		AssetRoot assetRoot = assetVideo.getAssetRoot();
		
		saveOrUpdate(assetRoot);
		String key = getIdColumn(assetRoot.getClass());
		key = mapFieldName(key);
		Object value = getIdValue(assetRoot);
		ReflectUtils.getInstance().setFieldValue(assetVideo, key, value);
		saveOrUpdate(assetVideo);
		
		// tvoDaoService.saveParentWithChild(assetVideo.getAssetRoot(), assetVideo);
	}
}
