package LifeDesigner;

public class EventManager {
	
}

class EventList {
	
}

class Event {
	private int year, month, day, beginHour, beginMinute, finishHour, finishMinute;
	private String title, detail;
	Event(int year, int month, int day, String title, String detail) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.title = title;
		this.detail = detail;
	}
	int getYear() {
		return year;
	}
	int getMonth() {
		return month;
	}
	int getDay() {
		return day;
	}
	String getTitle() {
		return title;
	}
	String getDetail() {
		return detail;
	}
}