package LifeDesigner;

import java.util.ArrayList;

public class EventManager {
	ArrayList<EventList> alel;
	int size;
	EventManager() {
		alel = new ArrayList<EventList>(100);
		size = 0;
	}
	public Event addEvent(int year, int month, int date, String title, String detail) {
		int id = year * 100 + month;
		EventList el;
		if((el = fetch(id)) == null) {
			alel.add(size, new EventList(id));
			size += 1;
			return alel.get(size-1).addEvent(date, title, detail);
		} else {
			return el.addEvent(date, title, detail);
		}
	}
	EventList fetch(int id) {
		int index;
		for(index=0; index<size; index++) {
			if(id == alel.get(index).getId()) {
				return alel.get(index);
			}
		}
		return null;
	}
	public Event changeEvent(int year, int month, int date, String title, String detail, int eventId) {
		int id = year * 100 + month;
		Event e = fetch(id).fetch(eventId);
		e.setTitle(title);
		e.setDetail(detail);
		return e;
	}
	public int deleteEvent(int year, int month, int date, int eventId) {
		int id = year * 100 + month;
		EventList el = fetch(id);
		el.deleteEvent(eventId);
		return eventId;
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
	public int getSize() {
		return size;
	}
	Event fetch(int eventId) {
		int i;
		for(i=0; i<size; i++) {
			if(eventId == get(i).getId()) {
				return get(i);
			}
		}
		return null;
	}
	public Event addEvent(int date, String title, String detail) {
		int i;
		for(i=0; i<size ; i++) {
			if(date < get(i).getDate()) {
				break;
			}
		}
		add(i, new Event(eventId, date, title, detail));
		size += 1;
		eventId += 1;
		return get(size-1);
	}
	public void deleteEvent(int eventId) {
		Event e = fetch(eventId);
		remove(e);
		size -= 1;
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
	void setTitle(String title) {
		this.title = title;
	}
	void setDetail(String detail) {
		this.detail = detail;
	}
}