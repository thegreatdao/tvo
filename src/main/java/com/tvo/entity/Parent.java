package com.tvo.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
public class Parent
{
	private long id;
	private String name;
}
