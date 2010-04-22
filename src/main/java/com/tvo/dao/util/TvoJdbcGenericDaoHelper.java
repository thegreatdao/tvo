package com.tvo.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvo.dao.TvoJdbcGenericDaoImpl;
import com.tvo.entity.TvoEntity;

public class TvoJdbcGenericDaoHelper
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TvoJdbcGenericDaoImpl.class);

	public static <T extends TvoEntity> TvoEntityFieldNameAndTypePair getFiledNameAndType(T entity, Class<?> type)
	{
		TvoEntityFieldNameAndTypePair fieldNameAndTypePair = new TvoEntityFieldNameAndTypePair();
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		for (Field field : declaredFields)
		{
			Type genericFieldType = field.getGenericType();
			if (genericFieldType instanceof ParameterizedType)
			{
				ParameterizedType tvoEnitityType = (ParameterizedType) genericFieldType;
				Type fieldArgType = tvoEnitityType.getActualTypeArguments()[0];
				if (fieldArgType == type)
				{
					fieldNameAndTypePair.setCollectionType(true);
					fieldNameAndTypePair.setKey(field.getName());
					fieldNameAndTypePair.setType(type);
				}
			}
			if (field.getType() == type)
			{
				fieldNameAndTypePair.setKey(field.getName());
				fieldNameAndTypePair.setType(field.getType());
			}
		}
		return fieldNameAndTypePair;
	}

	public static void setSimpleProperty(Object bean, String name, Object value)
	{
		try
		{
			PropertyUtils.setSimpleProperty(bean, name, value);
		} 
		catch (Exception e)
		{
			TvoReflectionException tvoReflectionException = new TvoReflectionException(e);
			LOGGER.error(e.getMessage(), tvoReflectionException);
			throw tvoReflectionException;
		}
	}

	public static Object getSimpleProperty(Object bean, String name)
	{
		try
		{
			return PropertyUtils.getSimpleProperty(bean, name);
		}
		catch (Exception e)
		{
			TvoReflectionException tvoReflectionException = new TvoReflectionException(e);
			LOGGER.error(e.getMessage(), tvoReflectionException);
			throw tvoReflectionException;
		}
	}

	public static <T extends TvoEntity> List<Class<?>> getAllAssocaitions(Class<T> entityType)
	{
		LinkedList<Class<?>> associations = new LinkedList<Class<?>>();
		Field[] declaredFields = entityType.getDeclaredFields();
		for (Field field : declaredFields)
		{
			Type genericFieldType = field.getGenericType();
			if (genericFieldType instanceof ParameterizedType)
			{
				ParameterizedType tvoEnitityType = (ParameterizedType) genericFieldType;
				Type fieldType = tvoEnitityType.getActualTypeArguments()[0];
				associations.add((Class<?>)fieldType);
			}
			if(field.getType().getSuperclass() == TvoEntity.class)
			{
				associations.add(field.getType());
			}
		}
		return associations;
	}
	
	private static class TvoReflectionException extends RuntimeException
	{
		private static final long serialVersionUID = 1776111410006763987L;

		public TvoReflectionException(Throwable cause)
		{
			super(cause);
		}

	}

}
