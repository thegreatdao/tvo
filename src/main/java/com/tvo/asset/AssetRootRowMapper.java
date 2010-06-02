package com.tvo.asset;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("unchecked")
public class AssetRootRowMapper implements RowMapper
{

	protected AssetRoot	assetRoot;

	public Object mapRow(ResultSet rs, int line) throws SQLException
	{
		assetRoot.setId(rs.getInt("asset_root_id"));
		assetRoot.setTelescopeAssetId(rs.getString("r_telescope_asset_id"));
		assetRoot.setTelescopeRecordId(rs.getInt("r_telescope_record_id"));
		assetRoot.setTitle(rs.getString("r_title"));
		assetRoot.setSource(rs.getString("r_source"));
		assetRoot.setTypeCategory(AssetRoot.AssetType.valueOf(rs.getString("r_type")));
		assetRoot.setDescriptionInternet(rs.getString("r_description_internet"));
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
	}
}