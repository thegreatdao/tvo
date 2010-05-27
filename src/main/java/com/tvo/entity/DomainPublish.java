package com.tvo.entity;

import java.util.Date;

import org.sakaiproject.genericdao.api.annotations.PersistentColumnName;
import org.sakaiproject.genericdao.api.annotations.PersistentId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DomainPublish extends TvoEntity
{
	private static final long serialVersionUID = 7384512528267342643L;
	
	@PersistentId
	private Integer domainPublishId;
	private Integer assetRootId;
	private Integer domainNameId;
	@PersistentColumnName("is_published")
	private boolean published;
	private Date createdOn;
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;
}
