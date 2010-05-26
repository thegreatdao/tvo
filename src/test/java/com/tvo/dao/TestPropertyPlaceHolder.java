package com.tvo.dao;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
public class TestPropertyPlaceHolder
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TestPropertyPlaceHolder.class);
	
	@Autowired
	private PropertyPlaceHolder propertyPlaceHolder;
	
	@Test
	public void testProperty()
	{
		LOGGER.info(propertyPlaceHolder.getMessage());
		assertNull(null);
	}
}
