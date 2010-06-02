package com.tvo.telescope;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.tvo.databases.DbWebRepository;
import com.tvo.entity.StrandsScheduleView;

public class TelescopeStrandsSchedule {
	@SuppressWarnings("unchecked")
	public StrandsScheduleView[] getSchedule(int daysAhead) {
		
		JdbcTemplate select = new JdbcTemplate();
		select.setDataSource(DbWebRepository.getDataSource());
		List<StrandsScheduleView> strandsScheduleList = select.query(
		"SELECT * FROM tsp.strand_listings_v " +
        "WHERE airing_time BETWEEN TRUNC(sysdate) AND TRUNC(sysdate + ?) " +
        "order by airing_time",
		new Object[] { daysAhead }, new TelescopeStrandsRowMapper());
		
		StrandsScheduleView[] strandsScheduleArray = new StrandsScheduleView[strandsScheduleList.size()];
		
		return strandsScheduleArray;
	}
}
