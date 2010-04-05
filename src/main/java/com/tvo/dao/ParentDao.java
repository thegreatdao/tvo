package com.tvo.dao;

import java.util.List;

import com.tvo.entity.Parent;

public interface ParentDao
{
	public long saveOrUpdate(Parent parent);
	public Parent getParentById(long id);
	public List<Parent> getAllParents();
	public String getName(long id);
}
