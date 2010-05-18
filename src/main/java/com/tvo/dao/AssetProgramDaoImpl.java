package com.tvo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetProgram;

@Repository
public class AssetProgramDaoImpl implements AssetProgramDao
{
	@Autowired 
	private TvoJdbcGenericDaoImpl tvoJdbcGenericDaoImpl;

	@Override
	public int save(AssetProgram assetProgram)
	{		
		int[] generatedIds = tvoJdbcGenericDaoImpl.saveParentWithChildAndReturnGeneratedIds(assetProgram.getAssetRoot(), assetProgram);
		return generatedIds[1];
	}
}