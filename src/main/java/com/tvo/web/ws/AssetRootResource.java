package com.tvo.web.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tvo.entity.Parents;
import com.tvo.service.ParentService;

@Controller
@Path(AssetRootResource.ASSET_ROOT_URL)
public class AssetRootResource
{
	public static final String ASSET_ROOT_URL ="/ws/assetRoot";
	
	@Autowired
	private ParentService parentService;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("data")
	public Parents getAll()
	{
		return parentService.getParents();
	}
}
