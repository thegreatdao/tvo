package com.tvo.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.sakaiproject.genericdao.api.annotations.PersistentId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DomainName extends TvoEntity
{
	private static final long serialVersionUID = 4859796326760602519L;

	@PersistentId
	@XmlTransient
	private Integer domainNameId;
	
	private String domainName;
	
	@XmlTransient
	private Date createdOn;
	
	@XmlTransient
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;
}
