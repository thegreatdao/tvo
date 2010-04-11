package com.tvo.web.ws;

import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

@Controller
@Path(AssetRootResource.ASSET_ROOT_URL)
public class AssetRootResource
{
	public static final String ASSET_ROOT_URL ="/ws/assetRoot";
		
}
