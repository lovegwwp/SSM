package com.jyss.mst.entity.JsonEntity;

public class ExeMakeIf {
	private String week;
	private String weekday;
	private String daytime;
	private String timesmins;
	private String bfTime;

	public ExeMakeIf(String week, String weekday, String daytime,
			String timesmins, String bfTime) {
		super();
		this.week = week;
		this.weekday = weekday;
		this.daytime = daytime;
		this.timesmins = timesmins;
		this.bfTime = bfTime;
	}

	public ExeMakeIf() {
		super();
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getDaytime() {
		return daytime;
	}

	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	public String getTimesmins() {
		return timesmins;
	}

	public void setTimesmins(String timesmins) {
		this.timesmins = timesmins;
	}

	public String getBfTime() {
		return bfTime;
	}

	public void setBfTime(String bfTime) {
		this.bfTime = bfTime;
	}

}
