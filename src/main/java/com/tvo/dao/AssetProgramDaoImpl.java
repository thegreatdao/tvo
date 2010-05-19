package com.tvo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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