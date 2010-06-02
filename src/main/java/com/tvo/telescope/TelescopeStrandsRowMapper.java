package com.tvo.telescope;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.tvo.entity.StrandsScheduleView;

public class TelescopeStrandsRowMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int line) throws SQLException
	{
		StrandsScheduleView ssv = new StrandsScheduleView();

		ssv.setIdSeries(rs.getString("id_series"));
		ssv.setSeriesTitle(rs.getString("series_title"));
		ssv.setSeriesDescription(rs.getString("series_description"));
		ssv.setBpn(rs.getString("bpn"));
		ssv.setProgramTitle(rs.getString("program_title"));
		ssv.setProgramDescription(rs.getString("program_description"));
		ssv.setLongDescription(rs.getString("long_description"));
		ssv.setDurationSeconds(rs.getInt("duration_seconds"));
		ssv.setAiringTime(rs.getDate("airing_time"));
		ssv.setNetwork(rs.getString("network"));
		ssv.setVRating(rs.getString("v_rating"));
		ssv.setWeekday(rs.getString("weekday"));
		ssv.setIsWeekend(rs.getString("is_weekend"));
		ssv.setIsRepeat(rs.getString("is_repeat"));
		ssv.setIsFirstAir(rs.getString("is_first_air"));
		ssv.setIsCaptioned(rs.getString("is_captioned"));
		ssv.setIsDescVideo(rs.getString("is_desc_video"));
		ssv.setAudienceDescretion(rs.getString("audience_descretion"));
		ssv.setYearProduced(rs.getString("year_produced"));
		ssv.setEpisodeNumber(rs.getInt("episode_number"));
		ssv.setEpisodeOrder(rs.getString("episoder_order"));
		ssv.setPreviousAir(rs.getDate("previous_air"));	
		ssv.setDefSeriesTitle(rs.getString("def_series_title"));
		ssv.setDefSeriesDescription(rs.getString("def_series_description"));
		ssv.setDefProgramTitle(rs.getString("def_program_title"));
		ssv.setDefProgramDescription(rs.getString("def_program_description"));
		ssv.setDefLongDescription(rs.getString("def_long_description"));
		
		return ssv;
	}
}
