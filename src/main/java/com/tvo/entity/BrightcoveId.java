package com.tvo.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.sakaiproject.genericdao.api.annotations.PersistentId;
import org.sakaiproject.genericdao.api.annotations.PersistentTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BrightcoveId extends TvoEntity
{
	private static final long serialVersionUID = 4859796326760602519L;

	@PersistentId
	@XmlTransient
	private Integer brightcoveId;
	
	@XmlTransient
	private Integer assetVideoId;
	
	@XmlTransient
	private Integer domainNameId;
	
	@PersistentTransient
	private String domainName;
	
	private String bcId;
	
	@XmlTransient
	private Date createdOn;
	
	@XmlTransient
	private Date updatedOn;
	
	@XmlTransient
	private String createdBy;
	
	@XmlTransient
	private String updatedBy;
}