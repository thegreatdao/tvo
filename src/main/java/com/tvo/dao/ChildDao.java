package com.tvo.dao;

import com.tvo.entity.Child;

public interface ChildDao
{
	public void save(Child child, long parentId);
}
