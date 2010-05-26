package com.tvo.entity;

import java.util.Date;

import org.sakaiproject.genericdao.api.annotations.PersistentId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BrightcoveId extends TvoEntity
{
	private static final long serialVersionUID = 4859796326760602519L;

	@PersistentId
	private Integer brightcoveId;
	private String brightcoveVideoId;
	private Date createdOn;
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;
}