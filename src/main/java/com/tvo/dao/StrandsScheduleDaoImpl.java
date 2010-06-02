package com.tvo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tvo.entity.StrandsScheduleView;

@Repository
public class StrandsScheduleDaoImpl implements StrandsScheduleDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	@Autowired
	private TvoJdbcGenericDaoImpl tvoJdbcGenericDao;
	
	public void update(StrandsScheduleView[] strandsScheduleView) {
		String sql = "DELETE FROM strands_schedule";
		HashMap<String, String> paramMap = new HashMap<String, String>();
		namedParameterJdbcTemplate.update(sql, paramMap);
		
		for(StrandsScheduleView view : strandsScheduleView){
			tvoJdbcGenericDao.saveOrUpdate(view);
		}
	}
	
	public StrandsScheduleView[] getStrandsScheduleView() {
		List<StrandsScheduleView> view = tvoJdbcGenericDao.findAll(StrandsScheduleView.class);
		
		StrandsScheduleView[] viewList = new StrandsScheduleView[view.size()];
		
		for(int i = 0; i < view.size(); i++) {
			viewList[i] = view.get(i);
		}
		
		return viewList;
	}
}
