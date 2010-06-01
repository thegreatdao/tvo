package com.tvo.asset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.tvo.entity.StrandsScheduleView;

@SuppressWarnings("unchecked")
public class AssetRootRowMapper implements RowMapper
{
	private StrandsScheduleView	strandsScheduleView;

	public Object mapRow(ResultSet rs, int line) throws SQLException
	{
		strandsScheduleView = new StrandsScheduleView();
		/*
		
	private String programTitle;
	private String programDescription;
	private String longDescription;
	private Integer durationSeconds;
	// private String durationHHMMSS (should not be required)
	private Date airingTime;
	private String network;
	private String vRating;
	private String weekday;
	private String isWeekend;
	private String isRepeat;
	private String isFirstAir;
	private String isCaptioned;
	private String isDescVideo;
	private String audienceDescretion;
	private String yearProduced;
	private Integer episodeNumber;
	private String episodeOrder;
	private Date previousAir;
	
	private String defSeriesTitle;
	private String defSeriesDescription;
	private String defProgramTitle;
	private String defProgramDescription;
	private String defLongDescription;
		 */
		strandsScheduleView.setIdSeries(rs.getString("id_series"));
		strandsScheduleView.setSeriesTitle(rs.getString("series_title"));
		strandsScheduleView.setSeriesDescription(rs.getString("series_description"));
		strandsScheduleView.setBpn(rs.getString("bpn"));
		strandsScheduleView.setProgramTitle(rs.getString("series_description"));
		strandsScheduleView.setProgramDescription(rs.getString("programDescription"));
		strandsScheduleView.setLongDescription(rs.getString("longDescription"));
		strandsScheduleView.setDurationSeconds(rs.getInt("duration_seconds"));
		strandsScheduleView.setAiringTime(rs.getDate("airing_time"));
		
		/*
		assetRoot.setId(rs.getInt("asset_root_id"));
		assetRoot.setTelescopeAssetId(rs.getString("r_telescope_asset_id"));
		assetRoot.setTelescopeRecordId(rs.getInt("r_telescope_record_id"));
		assetRoot.setTitle(rs.getString("r_title"));
		assetRoot.setSource(rs.getString("r_source"));
		assetRoot.setTypeCategory(AssetRoot.AssetType.valueOf(rs
				.getString("r_type")));
		assetRoot
				.setDescriptionInternet(rs.getString("r_description_internet"));
		assetRoot.setDescriptionShort(rs.getString("r_description_short"));

		assetRoot.setAgeRating(rs.getString("r_age_rating"));
		assetRoot.setUserTimeStart(rs.getDate("r_user_time_start"));
		assetRoot.setUserTimeEnd(rs.getDate("r_user_time_end"));
		assetRoot.setDuration(rs.getTime("r_duration"));

		assetRoot.setReleaseDate(rs.getDate("r_release_date"));
		assetRoot.setCreatedOn(rs.getTimestamp("r_created_on"));
		assetRoot.setUpdatedOn(rs.getTimestamp("r_updated_on"));
		assetRoot.setCreatedby(rs.getString("r_created_by"));
		assetRoot.setUpdatedBy(rs.getString("r_updated_by"));

		return assetRoot;
		*/
		return strandsScheduleView;
	}
}