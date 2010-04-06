package com.tvo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.entity.Parent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestParentDao
{
	@Autowired
	private ParentDao parentDao;
	
	@Test
    @Transactional
	public void testParent()
	{
		Parent parent = new Parent();
		parent.setName("test parent");
		int initialSize = parentDao.getAllParents().size();
		parentDao.saveOrUpdate(parent);
		int endSize = parentDao.getAllParents().size();
		assertTrue(initialSize == endSize - 1);
	}
}
