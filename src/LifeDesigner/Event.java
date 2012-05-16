package LifeDesigner;

public class Event {
	private int year, month, day, beginHour, beginMinute, finishHour, finishMinute;
	private String title, detail;
	Event(int year, int month, int day, String title) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.title = title;
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
}