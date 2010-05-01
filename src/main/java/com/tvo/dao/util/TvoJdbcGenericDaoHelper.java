package com.tvo.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.sakaiproject.genericdao.api.search.Search;

import com.tvo.dao.util.bean.TvoEntityFieldNameAndTypePair;
import com.tvo.entity.TvoEntity;

public class TvoJdbcGenericDaoHelper
{

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
				Class<?> inferredType = (Class<?>)fieldType;
				if(inferredType.getSuperclass() == TvoEntity.class)
				{
					associations.add(inferredType);
				}
			}
			Class<?> actualType = field.getType();
			if(actualType.getSuperclass() == TvoEntity.class)
			{
				associations.add(actualType);
			}
		}
		return associations;
	}		
	
	public static Search getSearchFromMap(Map<String, Object> parameters)
	{
		int size = parameters.size();
		String[] properties = new String[size];
		Object[] values = new Object[size];
		int index = 0;
		for(Map.Entry<String, Object> pair : parameters.entrySet())
		{
			properties[index] = pair.getKey();
			values[index] = pair.getValue();
			index++;
		}		
		return new Search(properties, values);
	}
}
