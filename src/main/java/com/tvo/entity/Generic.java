package com.tvo.entity;

import org.sakaiproject.genericdao.api.annotations.PersistentId;

import lombok.Data;

@Data
public class Generic
{
	@PersistentId
	private Integer genericKey;
	private String name;
}
