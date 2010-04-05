package com.tvo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tvo.dao.ChildDao;
import com.tvo.dao.ParentDao;
import com.tvo.entity.Child;

@Component
public class TVOMainScheduler
{
	//example logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TVOMainScheduler.class);
	@Autowired
	private ParentDao parentDao;
	@Autowired
	private ChildDao childDao;
	
	@Scheduled(fixedDelay=5000)
	public void print()
	{
		try
		{
			/*
			Parent parent = new Parent();
			parent.setName("hello");
			parent.setId(15);
			parentDao.saveOrUpdate(parent);
			String parentName = parentDao.getName(15);
			LOGGER.info(parentName);
			List<Parent> parents = parentDao.getAllParents();
			LOGGER.info(parents.size() + "");
			Parent parent = parentDao.getParentById(15);
			LOGGER.info(parent.toString());
			Parent parent = new Parent();
			parent.setName("last");
			long id = parentDao.saveOrUpdate(parent);
			LOGGER.info(id + " --------------");
			 */
			Child child = new Child();
			child.setName("first Child");
			long parentId = 16;
			childDao.save(child, parentId);
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
}
