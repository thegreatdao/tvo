package com.tvo.asset;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.sql.Time;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.tvo.databases.DbWebRepository;

public abstract class AssetRoot
{

	/*
	 * asset_type = 'element' and element_type = video, you have a VIDEO asset
	 * asset_type = 'segment' is the meta data for the video (possible, very
	 * similar to program) asset_type = 'series' is treated the same as a
	 * program Series->Program->Segment->ElementVideo
	 */

	// NA is not applicable and will be skipped.
	public enum AssetType
	{
		PROMO, SERIES, PROGRAM, VIDEO, ARTICLE, BLOG, GAME, CONTEST, NA
	}

	private int id;
	private String telescopeAssetId;
	private int telescopeRecordId;

	private String title;
	private String category;
	private String source;
	private String descriptionShort;
	private String descriptionInternet;
	private String ageRating;
	private AssetType typeCategory;

	private Time duration;
	private Date releaseDate;
	private Date userTimeStart;
	private Date userTimeEnd;

	private Date createdOn;
	private Date updatedOn;
	private String createdby;
	private String updatedBy;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setTelescopeAssetId(String telescopeAssetId)
	{
		this.telescopeAssetId = telescopeAssetId;
	}

	public String getTelescopeAssetId()
	{
		return telescopeAssetId;
	}

	public void setTelescopeRecordId(int telescopeRecordId)
	{
		this.telescopeRecordId = telescopeRecordId;
	}

	public int getTelescopeRecordId()
	{
		return telescopeRecordId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getDescriptionShort()
	{
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort)
	{
		this.descriptionShort = descriptionShort;
	}

	public String getAgeRating()
	{
		return ageRating;
	}

	public void setAgeRating(String ageRating)
	{
		this.ageRating = ageRating;
	}

	public String getDescriptionInternet()
	{
		return descriptionInternet;
	}

	public void setDescriptionInternet(String descriptionInternet)
	{
		this.descriptionInternet = descriptionInternet;
	}

	public void setDuration(Time duration)
	{
		this.duration = duration;
	}

	public Time getDuration()
	{
		return duration;
	}

	public Date getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public Date getUserTimeStart()
	{
		return userTimeStart;
	}

	public void setUserTimeStart(Date userTimeStart)
	{
		this.userTimeStart = userTimeStart;
	}

	public Date getUserTimeEnd()
	{
		return userTimeEnd;
	}

	public void setUserTimeEnd(Date userTimeEnd)
	{
		this.userTimeEnd = userTimeEnd;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn)
	{
		this.updatedOn = updatedOn;
	}

	public String getCreatedby()
	{
		return createdby;
	}

	public void setCreatedby(String createdby)
	{
		this.createdby = createdby;
	}

	public String getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public AssetType getTypeCategory()
	{
		return typeCategory;
	}

	public void setTypeCategory(AssetType typeCategory)
	{
		this.typeCategory = typeCategory;
	}

	public AssetRoot()
	{

	}

	protected static String assetRootSqlColumnSelect = ""
			+ "asset_root.asset_root_id, "
			+ "asset_root.telescope_asset_id AS r_telescope_asset_id, "
			+ "asset_root.telescope_record_id AS r_telescope_record_id, "
			+ "asset_root.title AS r_title, "
			+ "asset_root.asset_type AS r_type, "
			+ "asset_root.source AS r_source, "
			+ "asset_root.description_internet AS r_description_internet, "
			+ "asset_root.description_short AS r_description_short, "
			+ "asset_root.age_rating AS r_age_rating, "
			+ "asset_root.user_time_start AS r_user_time_start, "
			+ "asset_root.user_time_end AS r_user_time_end, "
			+ "asset_root.duration AS r_duration, "
			+ "asset_root.geo_filter_id AS r_geo_filter_id, "
			+ "asset_root.release_date AS r_release_date, "
			+ "asset_root.created_on AS r_created_on, "
			+ "asset_root.updated_on AS r_updated_on, "
			+ "asset_root.created_by AS r_created_by, "
			+ "asset_root.updated_by AS r_updated_by, ";

	protected int saveRoot() throws SQLException
	{

		int lastInsertedId = 0;

		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DbWebRepository
				.getDataSource()).withTableName("asset_root")
				.usingGeneratedKeyColumns("asset_root_id");
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		Map<String, Object> arParams = new HashMap<String, Object>(2);
		arParams.put("telescope_asset_id", this.telescopeAssetId);
		arParams.put("telescope_record_id", this.telescopeRecordId);
		arParams.put("title", this.title);
		arParams.put("source", this.source);
		arParams.put("asset_type", this.typeCategory);
		arParams.put("description_internet", this.descriptionInternet);
		arParams.put("description_short", this.descriptionShort);
		arParams.put("age_rating", this.ageRating);
		arParams.put("user_time_start", this.userTimeStart);
		arParams.put("user_time_end", this.userTimeEnd);
		arParams.put("duration", this.duration);
		arParams.put("geo_filter_id", 1);
		arParams.put("release_date", this.releaseDate);
		arParams.put("created_by", "ron");
		arParams.put("updated_by", "ron");
		arParams.put("created_on", 0);
		arParams.put("updated_on", 0);

		parameters.addValues(arParams);
		lastInsertedId = jdbcInsert.execute(arParams);

		return lastInsertedId;
	}

	public AssetRoot getAssetRootInstance()
	{
		return this;
	}
}
