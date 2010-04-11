package com.tvo.entity;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AirTime extends TvoEntity
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

}
