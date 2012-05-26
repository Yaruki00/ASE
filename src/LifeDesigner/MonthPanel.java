package LifeDesigner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class MonthPanel extends JPanel implements ActionListener {
	GUI gui;
	EventManager em;
	Calendar cal;
	int year, month, begin, length;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	YearMonthPanel ymp;
	LabelPanel lp;
	CalendarPanel cp;
	ProjectListPanel plp;
	DetailPanel dp;
	JButton b;
	MonthPanel(GUI gui, EventManager em) {
		this.gui = gui;
		this.em = em;
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 100.0;
		gbc.weighty = 100.0;
		
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		cal.set(year, month-1, 1);
		begin = cal.get(Calendar.DAY_OF_WEEK) - 1;
		length = cal.getActualMaximum(Calendar.DATE);
		
		ymp = new YearMonthPanel(this);
		ymp.setYearMonth(year, month);
		addComponent(gbl, gbc, ymp, 0, 0, 5, 1);
		/*
		lp = new LabelPanel();
		addComponent(gbl, gbc, lp, 5, 0, 1, 1);
		*/
		cp = new CalendarPanel(this);
		cp.setMonth(begin, length);
		addComponent(gbl, gbc, cp, 0, 1, 5, 5);
		/*
		plp = new ProjectListPanel(this);
		addComponent(gbl, gbc, plp, 5, 1, 1, 5);
		*/
		dp = new DetailPanel(this);
		addComponent(gbl, gbc, dp, 0, 6, 5, 1);
		/*
		b = new JButton("change!");
		b.setActionCommand("a");
		b.addActionListener(this);
		addComponent(gbl, gbc, b, 5, 6, 1, 1);
		*/
	}
	void addComponent(GridBagLayout gbl,GridBagConstraints gbc, Component c, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(c, gbc);
        add(c);
    }
	public void setYearMonth(String operation) {
		if(operation.equals("previous")) {
			if(month == 1) {
				year -= 1;
				month = 12;
			} else {
				month -= 1;
			}
		} else if(operation.equals("next")) {
			if(month == 12) {
				year += 1;
				month = 1;
			} else {
				month += 1;
			}
		} else {
		}
		ymp.setYearMonth(year, month);
		cal.set(year, month-1, 1);
		begin = cal.get(Calendar.DAY_OF_WEEK) - 1;
		length = cal.getActualMaximum(Calendar.DATE);
		cp.setMonth(begin, length);
	}
	public void addEvent(int year, int month, int date, String title, String detail) {
		cp.addEventButton(em.addEvent(year, month, date, title, detail));
	}
	public void changeEvent(int year, int month, int date, String title, String detail, int eventId) {
		cp.changeEventButton(em.changeEvent(year, month, date, title, detail, eventId));
	}
	public void setDetail(int date) {
		dp.setDetail(this.year, this.month, date);
	}
	public void setDetail(int date, String title, String detail, int eventId) {
		dp.setDetail(this.year, this.month, date, title, detail, eventId);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("a")) {
			gui.changeMode("halfyear");
		}
	}
}

class YearMonthPanel extends JPanel implements ActionListener {
	MonthPanel mp;
	JLabel year_month;
	JButton nextMonth, previousMonth;
	YearMonthPanel(MonthPanel mp) {
		this.mp = mp;
		setLayout(new GridLayout(1,3));
		
		previousMonth = new JButton("<-");
		previousMonth.setActionCommand("previous");
		previousMonth.addActionListener(this);
		add(previousMonth);
		
		year_month = new JLabel();
		add(year_month);
		
		nextMonth = new JButton("->");
		nextMonth.setActionCommand("next");
		nextMonth.addActionListener(this);
		add(nextMonth);
		
	}
	public void setYearMonth(int year, int month) {
		year_month.setText("<html>" + year + "<br>" + month + "</html>");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("previous")) {
			mp.setYearMonth("previous");
		} else if(cmd.equals("next")) {
			mp.setYearMonth("next");
		} else {
		}
	}
}

class LabelPanel extends JPanel {
	JLabel projectList;
	LabelPanel() {
		projectList = new JLabel("Progect List");
		add(projectList);
	}
}

class CalendarPanel extends JPanel{
	MonthPanel mp;
	CalendarCell[] cell;
	JLabel[] dayofweek;
	int begin;
	CalendarPanel(MonthPanel mp) {
		int i;
		this.mp = mp;
		setLayout(new GridLayout(7,7));
		dayofweek = new JLabel[7];
		for(i=0; i<7; i++) {
			dayofweek[i] = new JLabel();
			add(dayofweek[i]);
		}
		dayofweek[0].setText("Sun");
		dayofweek[1].setText("Mon");
		dayofweek[2].setText("Tue");
		dayofweek[3].setText("Wed");
		dayofweek[4].setText("Thu");
		dayofweek[5].setText("Fri");
		dayofweek[6].setText("Sat");
		cell = new CalendarCell[42];
		for(i=0; i<42; i++) {
			cell[i] = new CalendarCell(mp);
			add(cell[i]);
		}
	}
	public void setMonth(int begin, int length) {
		int i;
		this.begin = begin;
		for(i=0; i<begin; i++) {
			cell[i].setVisible(false);
		}
		for(i=begin; i<begin+length; i++) {
			cell[i].setDate(i-begin+1);
			cell[i].setVisible(true);
		}
		for(i=begin+length; i<42; i++) {
			cell[i].setVisible(false);
		}
	}
	public void addEventButton(Event e) {
		cell[begin+e.getDate()-1].addEventButton(e);
	}
	public void changeEventButton(Event e) {
		cell[begin+e.getDate()-1].changeEventButton(e);
	}
}

class CalendarCell extends JPanel implements ActionListener {
	MonthPanel mp;
	ArrayList<JButton> b;
	ArrayList<Event> el;
	int size;
	CalendarCell(MonthPanel mp) {
		this.mp = mp;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		b = new ArrayList<JButton>();
		b.add(new JButton());
		add(b.get(0));
		size = 1;
		el = new ArrayList<Event>();
	}
	public void setDate(int date) {
		String d = Integer.toString(date);
		b.get(0).setText(d);
		b.get(0).setActionCommand(d);
		b.get(0).addActionListener(this);
	}
	public void addEventButton(Event e) {
		el.add(e);
		b.add(new JButton(e.getTitle()));
		b.get(size).setActionCommand(e.getTitle());
		b.get(size).addActionListener(this);
		add(b.get(size));
		revalidate();
		size += 1;
	}
	public void changeEventButton(Event e) {
		int i;
		for(i=0; i<size-1; i++) {
			if(el.get(i).getId() == e.getId()) break;
		}
		el.set(i, e);
		b.get(i+1).setText(e.getTitle());
		b.get(i+1).setActionCommand(e.getTitle());
		revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int date;
		String cmd = e.getActionCommand();
		try {
			date = Integer.valueOf(cmd);
			mp.setDetail(date);
		} catch (NumberFormatException nfe) {
			date = Integer.valueOf(b.get(0).getText());
			int i;
			for(i=0; i<size-1; i++) {
				if(el.get(i).getTitle() == cmd) break;
			}
			mp.setDetail(date, cmd, el.get(i).getDetail(), el.get(i).getId());
		}
	}
}

class ProjectListPanel extends JPanel implements ActionListener {
	MonthPanel mp;
	JLabel list[];
	ProjectListPanel(MonthPanel mp) {
		this.mp = mp;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		list = new JLabel[10];
		for(int i=0; i<10; i++) {
			list[i] = new JLabel(i+1 +"");
			add(list[i]);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

class DetailPanel extends JPanel implements ActionListener {
	MonthPanel mp;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	JLabel yearLabel, monthLabel, dateLabel, titleLabel, detailLabel;
	JTextField yearText, monthText, dateText, titleText;
	JTextArea detailText;
	JButton b1, b2;
	int eventId;
	DetailPanel(MonthPanel mp) {
		this.mp = mp;
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 100.0;
		gbc.weighty = 100.0;

		yearLabel = new JLabel("year:");
		addComponent(gbl, gbc, yearLabel, 0, 0, 1, 1);
		yearText = new JTextField(4);
		addComponent(gbl, gbc, yearText, 1, 0, 1, 1);
		
		monthLabel = new JLabel("month:");
		addComponent(gbl, gbc, monthLabel, 2, 0, 1, 1);
		monthText  = new JTextField(2);
		addComponent(gbl, gbc, monthText, 3, 0, 1, 1);
		
		dateLabel = new JLabel("date:");
		addComponent(gbl, gbc, dateLabel, 4, 0, 1, 1);
		dateText = new JTextField(2);
		addComponent(gbl, gbc, dateText, 5, 0, 1, 1);
		
		titleLabel = new JLabel("title:");
		addComponent(gbl, gbc, titleLabel, 0, 1, 1, 1);
		titleText = new JTextField(30);
		addComponent(gbl, gbc, titleText, 1, 1, 5, 1);
		
		detailLabel = new JLabel("detail:");
		addComponent(gbl, gbc, detailLabel, 0, 2, 1, 3);
		detailText = new JTextArea(3, 30);
		addComponent(gbl, gbc, detailText, 1, 2, 5, 3);
		
		b1 = new JButton("add");
		b1.setActionCommand("add");
		b1.addActionListener(this);
		addComponent(gbl, gbc, b1, 3, 5, 1, 1);
		
		b2 = new JButton("reset");
		b2.setActionCommand("reset");
		b2.addActionListener(this);
		addComponent(gbl, gbc, b2, 4, 5, 1, 1);
	}
	void addComponent(GridBagLayout gbl,GridBagConstraints gbc, Component c, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(c, gbc);
        add(c);
    }
	public void setDetail(int year, int month, int date) {
		yearText.setText(Integer.toString(year));
		monthText.setText(Integer.toString(month));
		dateText.setText(Integer.toString(date));
		titleText.setText("");
		detailText.setText("");
		b1.setText("add");
		b1.setActionCommand("add");
		b2.setText("reset");
		b2.setActionCommand("reset");
	}
	public void setDetail(int year, int month, int date, String title, String detail, int eventId) {
		yearText.setText(Integer.toString(year));
		monthText.setText(Integer.toString(month));
		dateText.setText(Integer.toString(date));
		titleText.setText(title);
		detailText.setText(detail);
		this.eventId = eventId;
		b1.setText("change");
		b1.setActionCommand("change");
		b2.setText("delete");
		b2.setActionCommand("delete");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("add")) {
			mp.addEvent(Integer.valueOf(yearText.getText()), Integer.valueOf(monthText.getText()), Integer.valueOf(dateText.getText()), titleText.getText(), detailText.getText());
		} else if(cmd.equals("reset")) {
			titleText.setText("");
			detailText.setText("");
		} else if(cmd.equals("change")) {
			mp.changeEvent(Integer.valueOf(yearText.getText()), Integer.valueOf(monthText.getText()), Integer.valueOf(dateText.getText()), titleText.getText(), detailText.getText(), eventId);
		} else if(cmd.equals("delete")) {
			
		} else {
		}
	}
}