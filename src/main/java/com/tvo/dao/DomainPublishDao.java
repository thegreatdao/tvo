package com.tvo.dao;

import java.util.List;

import com.tvo.entity.DomainName;

public interface DomainPublishDao
{
	public List<DomainName> getDomainsByAssetId(int assetRootId);
	public List<DomainName> getAll();
}
