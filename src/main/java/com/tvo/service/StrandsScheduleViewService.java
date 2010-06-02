package com.tvo.service;

import com.tvo.entity.StrandsScheduleView;

public interface StrandsScheduleViewService {
	public StrandsScheduleView[] getSchedule();
	public void update(StrandsScheduleView[] view);
}
