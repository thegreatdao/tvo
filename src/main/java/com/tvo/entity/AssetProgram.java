package com.tvo.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.sakaiproject.genericdao.api.annotations.PersistentId;
import org.sakaiproject.genericdao.api.annotations.PersistentTransient;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="program")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssetProgram extends TvoEntity {
	
	private static final long serialVersionUID = 6049312423966660328L;
	
	@PersistentId
	private Integer assetProgramId;
	
	private Integer assetRootId;
	
	@PersistentTransient
	@XmlElement(name="root")
	private AssetRoot assetRoot;
}