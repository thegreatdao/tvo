package com.tvo.entity;

import java.util.Date;
import lombok.Data;

@Data
public class AirTime implements java.io.Serializable
{

	private static final long serialVersionUID = 5177393003578736534L;
	
	private int airTimeId;
	private String strandId;
	private Date airTime;
	private Boolean isRepeat;
	private Date createdOn;
	private Date updatedOn;
	private String createdBy;
	private String updatedBy;

	public AirTime()
	{
		
	}

}
