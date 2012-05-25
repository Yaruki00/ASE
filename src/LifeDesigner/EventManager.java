package LifeDesigner;

import java.util.ArrayList;

public class EventManager {
	ArrayList<EventList> ell;
	int size;
	EventManager() {
		ell = new ArrayList<EventList>(100);
		size = 0;
	}
	public int addEvent(int year, int month, int date, String title, String detail) {
		int id = year * 100 + month, index;
		if((index = fetch(id)) == -1) {
			ell.add(size, new EventList(id));
			return ell.get(size++).addEvent(date, title, detail);
		} else {
			return ell.get(index).addEvent(date, title, detail);
		}
	}
	int fetch(int id) {
		int index;
		for(index=0; index<size; index++) {
			if(id == ell.get(index).getId()) {
				return index;
			}
		}
		return -1;
	}
	String getEventDetail(int year, int month, int date) {
		return ell.get(fetch(year * 100 + month)).getEventDetail(date);
	}
}

class EventList extends ArrayList<Event> {
	int id, size, eventId;
	EventList(int id) {
		super(100);
		this.id = id;
		size = eventId = 0;
	}
	public int getId() {
		return id;
	}
	public String getEventDetail(int date) {
		return get(fetch(date)).getDetail();
	}
	int fetch(int date) {
		int index;
		for(index=0; index<size; index++) {
			if(date == get(index).getDate()) {
				return index;
			}
		}
		return -1;
	}
	public int addEvent(int date, String title, String detail) {
		int i;
		for(i=0; i<size ; i++) {
			if(date < get(i).getDate()) {
				break;
			}
		}
		add(i, new Event(eventId, date, title, detail));
		size = eventId += 1;
		return eventId;
	}
}

class Event {
	private int date, id;
	private String title, detail;
	Event(int id, int date, String title, String detail) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.detail = detail;
	}
	int getId() {
		return id;
	}
	int getDate() {
		return date;
	}
	String getTitle() {
		return title;
	}
	String getDetail() {
		return detail;
	}
}