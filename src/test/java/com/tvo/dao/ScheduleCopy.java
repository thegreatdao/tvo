package com.tvo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.tvo.entity.*;
import com.tvo.service.StrandsScheduleViewServiceImpl;
import com.tvo.telescope.TelescopeStrandsSchedule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-transaction-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ScheduleCopy {
	
	@Autowired
	private StrandsScheduleViewServiceImpl strandsScheduleService;
	
	@Test
	public void testAssetRootWithAssetVideo()
	{
		TelescopeStrandsSchedule strandsSchedule = new TelescopeStrandsSchedule();
		StrandsScheduleView[] ssList = strandsSchedule.getSchedule(4);
		strandsScheduleService.update(ssList);	
	}
}
