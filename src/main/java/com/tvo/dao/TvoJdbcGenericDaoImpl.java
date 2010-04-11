package com.tvo.dao;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.sakaiproject.genericdao.springjdbc.JdbcGeneralGenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvo.entity.TvoEntity;

public class TvoJdbcGenericDaoImpl extends JdbcGeneralGenericDao
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TvoJdbcGenericDaoImpl.class);

	public <T extends TvoEntity> void saveParentWithChild(T parent, T child)
	{
		saveOrUpdate(parent);

		String key = getIdColumn(parent.getClass());
		key = mapFieldName(key);
		Object value = getIdValue(parent);
		try
		{
			PropertyUtils.setSimpleProperty(child, key, value);
		}
		catch (Exception e)
		{
			TvoReflectionException tvoReflectionException = new TvoReflectionException(e);
			LOGGER.error(e.getMessage(), tvoReflectionException);
			throw tvoReflectionException;
		}
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
	
	private class TvoReflectionException extends RuntimeException
	{
		private static final long serialVersionUID = 1776111410006763987L;

		public TvoReflectionException(Throwable cause)
		{
			super(cause);
		}

	}
}
