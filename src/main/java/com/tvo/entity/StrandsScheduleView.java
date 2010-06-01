package com.tvo.entity;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlType(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class StrandsScheduleView {
	private String idSeries;
	private String seriesTitle;
	private String seriesDescription;
	private String bpn;
	private String programTitle;
	private String programDescription;
	private String longDescription;
	private Integer durationSeconds;
	// private String durationHHMMSS (should not be required)
	private Date airingTime;
	private String network;
	private String vRating;
	private String weekday;
	private String isWeekend;
	private String isRepeat;
	private String isFirstAir;
	private String isCaptioned;
	private String isDescVideo;
	private String audienceDescretion;
	private String yearProduced;
	private Integer episodeNumber;
	private String episodeOrder;
	private Date previousAir;
	
	private String defSeriesTitle;
	private String defSeriesDescription;
	private String defProgramTitle;
	private String defProgramDescription;
	private String defLongDescription;
}
