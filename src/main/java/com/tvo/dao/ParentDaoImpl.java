package com.tvo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tvo.entity.Parent;

@Repository("parentDao")
public class ParentDaoImpl implements ParentDao
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	public long saveOrUpdate(Parent parent)
	{
		String sql = null;
		String inserSql = "insert into parent (name) values (:name)";
		String updateSql = "update parent set name=:name where id=:id";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", parent.getName());
		
		if(parent.getId() != 0)
		{
			sql = updateSql;
			paramMap.put("id", parent.getId());
			namedParameterJdbcTemplate.update(sql, paramMap);
			return 0;
		}
		else
		{
			sql = inserSql;
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramMap);
			namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
			return keyHolder.getKey().longValue();
		}
	}

	public Parent getParentById(long id)
	{
		String sql = "select * from parent where id = :id";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, ParameterizedBeanPropertyRowMapper.newInstance(Parent.class));
	}

	public List<Parent> getAllParents()
	{
		String sql = "select * from parent";
		RowMapper<Parent> rm = ParameterizedBeanPropertyRowMapper.newInstance(Parent.class);
		return simpleJdbcTemplate.query(sql, rm);
	}
	
	public String getName(long id)
	{
		String sql = "select name from parent where id = ?";
		return simpleJdbcTemplate.queryForObject(sql, String.class, id);
	}
	
}
