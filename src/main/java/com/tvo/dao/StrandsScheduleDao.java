package com.tvo.dao;

import com.tvo.entity.StrandsScheduleView;

public interface StrandsScheduleDao {
	void update(StrandsScheduleView[] strandsScheduleView);
	public StrandsScheduleView[] getStrandsScheduleView();
}
