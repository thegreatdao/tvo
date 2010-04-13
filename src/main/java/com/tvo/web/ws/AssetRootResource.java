package com.tvo.web.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;
import com.tvo.service.TvoAssetsService;

@Controller
@Path(AssetRootResource.ASSET_ROOT_URL)
public class AssetRootResource
{
	public static final String ASSET_ROOT_URL ="/ws/assetRoot";
	
	@Autowired
	private TvoAssetsService tvoAssetsService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("data")
	public AssetVideo getAssetVideo()
	{
		AssetRoot assetRoot = tvoAssetsService.findAll(AssetRoot.class).get(0);
		AssetVideo assetVideo = tvoAssetsService.findAll(AssetVideo.class).get(0);
		assetVideo.setAssetRoot(assetRoot);
		return assetVideo;
	}
}
