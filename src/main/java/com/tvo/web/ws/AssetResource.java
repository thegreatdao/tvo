package com.tvo.web.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.entity.wrapper.AssetVideos;
import com.tvo.service.AssetVideoService;
import com.tvo.service.TvoAssetsService;

@Controller
@Path(AssetResource.ASSET_ROOT_URL)
public class AssetResource
{
	public static final String ASSET_ROOT_URL ="/ws/assets";
	
	@Autowired
	private TvoAssetsService tvoAssetsService;
	@Autowired
	private AssetVideoService assetVideoService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("videos")
	public AssetVideo getAssetVideo()
	{
		AssetRoot assetRoot = tvoAssetsService.findAll(AssetRoot.class).get(0);
		AssetVideo assetVideo = tvoAssetsService.findAll(AssetVideo.class).get(0);
		assetVideo.setAssetRoot(assetRoot);
		return assetVideo;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("videos/{startDate}/{endDate}")
	public AssetVideos getAssetVideosByDates(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate)
	{
		List<AssetVideo> assetVideos = assetVideoService.findAssetVideosByDates(startDate, endDate);
		return new AssetVideos(assetVideos);
	}
}
