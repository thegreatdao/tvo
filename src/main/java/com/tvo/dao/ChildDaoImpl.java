package com.tvo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tvo.entity.Child;

@Repository
public class ChildDaoImpl implements ChildDao
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void save(Child child, long parentId)
	{
		String sql = "insert into child (name, pid) values (:name, :pid)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", child.getName());
		paramMap.put("pid", parentId);		
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}
