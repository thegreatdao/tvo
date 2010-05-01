package com.tvo.asset;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("unchecked")
public class AssetVideoRowMapper extends AssetRootRowMapper implements RowMapper
{

	protected AssetVideo	assetVideo;

	public Object mapRow(ResultSet rs, int line) throws SQLException
	{

		assetVideo = new AssetVideo();
		assetRoot = assetVideo.getAssetRootInstance();

		super.mapRow(rs, line);

		assetVideo.setAssetRootId(rs.getInt("asset_root_id"));
		assetVideo.setBrightCoveReferenceId(rs.getString("bc_ref_id"));
		assetVideo.setLength(rs.getInt("length"));

		return assetVideo;
	}
}