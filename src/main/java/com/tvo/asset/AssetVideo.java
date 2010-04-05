package com.tvo.asset;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;

import com.tvo.databases.DbWebRepository;

public class AssetVideo extends AssetRoot
{

	private TransactionTemplate	transactionTemplate;

	private int					assetRootId;
	private int					length;

	private String				linkTitle;
	private String				masterSeriesNumber;
	private boolean				isEmbedCode;
	private String				linkUrl;
	private String				thumbnailUrl;
	private String				videoStillUrl;
	private String				videoUrl;
	private String				brightCoveReferenceId;

	public String getLinkTitle()
	{
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle)
	{
		this.linkTitle = linkTitle;
	}

	public String getMasterSeriesNumber()
	{
		return masterSeriesNumber;
	}

	public void setMasterSeriesNumber(String masterSeriesNumber)
	{
		this.masterSeriesNumber = masterSeriesNumber;
	}

	public boolean isEmbedCode()
	{
		return isEmbedCode;
	}

	public void setEmbedCode(boolean isEmbedCode)
	{
		this.isEmbedCode = isEmbedCode;
	}

	public String getLinkUrl()
	{
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl)
	{
		this.linkUrl = linkUrl;
	}

	public String getThumbnailUrl()
	{
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl)
	{
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getVideoStillUrl()
	{
		return videoStillUrl;
	}

	public void setVideoStillUrl(String videoStillUrl)
	{
		this.videoStillUrl = videoStillUrl;
	}

	public String getVideoUrl()
	{
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl)
	{
		this.videoUrl = videoUrl;
	}

	public int getAssetRootId()
	{
		return assetRootId;
	}

	public void setAssetRootId(int assetRootId)
	{
		this.assetRootId = assetRootId;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getBrightCoveReferenceId()
	{
		return brightCoveReferenceId;
	}

	public void setBrightCoveReferenceId(String brightCoveReferenceId)
	{
		this.brightCoveReferenceId = brightCoveReferenceId;
	}

	@SuppressWarnings("unchecked")
	public static List<AssetVideo> getAll()
	{
		JdbcTemplate select = new JdbcTemplate();
		select.setDataSource(DbWebRepository.getDataSource());
		return select
				.query(
						"SELECT "
								+ AssetRoot.assetRootSqlColumnSelect
								+ " length, bc_ref_id FROM asset_root INNER JOIN asset_video ON (asset_root.asset_root_id=asset_video.asset_root_id) ",
						new AssetVideoRowMapper());
	}

	@SuppressWarnings("unchecked")
	public static AssetVideo getById(int id)
	{
		JdbcTemplate select = new JdbcTemplate();
		select.setDataSource(DbWebRepository.getDataSource());
		List<AssetVideo> assetVideoList = select
				.query(
						"SELECT "
								+ AssetRoot.assetRootSqlColumnSelect
								+ " length, bc_ref_id FROM asset_root INNER JOIN asset_video ON (asset_root.asset_root_id=asset_video.asset_root_id) WHERE asset_root.asset_root_id = ?",
						new Object[] { id }, new AssetVideoRowMapper());
		if (!assetVideoList.isEmpty())
		{
			return assetVideoList.get(0);
		} else
		{
			return null;
		}
	}

	public int save()
	{
		final AssetVideo assetVideoInstance = this;
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
				DbWebRepository.getDataSource());

		this.transactionTemplate = new TransactionTemplate();
		this.transactionTemplate.setTransactionManager(transactionManager);
		this.transactionTemplate
				.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);

		this.transactionTemplate.execute(new TransactionCallback()
		{
			public Object doInTransaction(TransactionStatus status)
			{

				int generatedId = 0;

				try
				{
					generatedId = assetVideoInstance.saveRoot();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("Generated id: " + generatedId);
				return null;
			}
		});
		// transactionTemplate.PROPAGATION_REQUIRED

		// int createdAssetRootId;
		/*
		 * try {
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return 0;
	}
}