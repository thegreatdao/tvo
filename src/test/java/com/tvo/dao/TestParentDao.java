package com.tvo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sakaiproject.genericdao.springjdbc.JdbcGeneralGenericDao;
import org.sakaiproject.genericdao.springjdbc.JdbcGenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tvo.entity.Generic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestParentDao
{
	/*@Autowired
	private ParentDao parentDao;*/
	
	@Autowired
	private JdbcGeneralGenericDao jdbcGenericDao;
	
	@Test
	public void testParent()
	{
		Generic generic = new Generic();
		generic.setName("save");
		jdbcGenericDao.create(generic);
		System.out.println(generic.getGenericKey());
		System.out.println(jdbcGenericDao.getIdColumn(Generic.class));
		/*Parent parent = new Parent();
		parent.setName("test parent");
		int initialSize = parentDao.getAllParents().size();
		parentDao.saveOrUpdate(parent);
		int endSize = parentDao.getAllParents().size();
		assertTrue(initialSize == endSize - 1);*/
	}
}
