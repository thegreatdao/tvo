package com.tvo.entity.wrapper;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import com.tvo.entity.AssetVideo;

@Data
@XmlRootElement(name="asset_vidoes")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssetVideos implements Serializable
{
	private static final long serialVersionUID = 442673989914286968L;
	@XmlElement(name="asset_video")
	private Collection<AssetVideo> assetVideos;
	
	public AssetVideos()
	{
		
	}
	
	public AssetVideos(Collection<AssetVideo> assetVideos)
	{
		this.assetVideos = assetVideos;
	}
	
}
