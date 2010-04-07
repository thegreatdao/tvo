package com.tvo.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parents
{
	private List<Parent> parents;

	public Parents()
	{
		
	}
	
	public Parents(List<Parent> parents)
	{
		this.parents = parents;
	}

	@XmlElement(name="parent")
	public List<Parent> getParents()
	{
		return parents;
	}

	public void setParents(List<Parent> parents)
	{
		this.parents = parents;
	}
	
}
