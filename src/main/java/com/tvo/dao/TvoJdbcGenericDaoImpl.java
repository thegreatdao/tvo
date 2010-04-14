package com.tvo.dao;

import static com.tvo.dao.util.TvoJdbcGenericDaoHelper.*;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.sakaiproject.genericdao.api.search.Search;
import org.sakaiproject.genericdao.springjdbc.JdbcGeneralGenericDao;

import com.tvo.dao.util.TvoEntityFieldNameAndTypePair;
import com.tvo.entity.TvoEntity;

public class TvoJdbcGenericDaoImpl extends JdbcGeneralGenericDao
{	

	public <T extends TvoEntity> void saveParentWithChild(T parent, T child)
	{
		saveOrUpdate(parent);
		String key = getIdColumn(parent.getClass());
		key = mapFieldName(key);
		Object value = getIdValue(parent);
		setSimpleProperty(child, key, value);
		saveOrUpdate(child);
	}

	public <T extends TvoEntity> void saveOrUpdate(T entity)
	{
		if(getIdValue(entity)==null)
		{
			create(entity);
		}
		else
		{
			save(entity);
		}
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
			Search search = new Search(property, getSimpleProperty(entity, property));
			TvoEntity findOneBySearch = findOneBySearch(entityType, search);
			setSimpleProperty(entity, fieldNameAndTypePair.getKey(), findOneBySearch);
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
	
}