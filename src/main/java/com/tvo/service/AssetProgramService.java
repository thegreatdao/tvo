package com.tvo.service;

import com.tvo.entity.AssetProgram;

public interface AssetProgramService
{
	public int save(AssetProgram assetProgram);
	public AssetProgram getByTelescopeAssetId(String assetId);
}