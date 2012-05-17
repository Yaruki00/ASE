package LifeDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class MonthPanel extends JPanel implements ActionListener {
	GUI gui;
	Calendar cal;
	int year, month, begin, length;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	YearMonthPanel ymp;
	LabelPanel lp;
	CalendarPanel cp;
	ProjectListPanel plp;
	DetailPanel dp;
	JLabel l;
	JButton b;
	MonthPanel(GUI gui) {
		this.gui = gui;
		
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
		length = cal.getActualMaximum(cal.DATE);
		
		ymp = new YearMonthPanel(this, year, month);
		addComponent(gbl, gbc, ymp, 0, 0, 5, 1);
		
		lp = new LabelPanel();
		addComponent(gbl, gbc, lp, 5, 0, 1, 1);
		
		cp = new CalendarPanel(this, begin, length);
		addComponent(gbl, gbc, cp, 0, 1, 5, 5);
		
		plp = new ProjectListPanel(this);
		addComponent(gbl, gbc, plp, 5, 1, 1, 5);
		
		dp = new DetailPanel();
		addComponent(gbl, gbc, dp, 0, 6, 5, 1);
		
		b = new JButton("change!");
		b.setActionCommand("a");
		b.addActionListener(this);
		addComponent(gbl, gbc, b, 5, 6, 1, 1);
		
	}
	void addComponent(GridBagLayout gbl,GridBagConstraints gbc, Component c, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(c, gbc);
        add(c);
    }
	public void setDetail(String text) {
		dp.setDetail(text);
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
	int year, month;
	YearMonthPanel(MonthPanel mp, int year, int month) {
		this.mp = mp;
		this.year = year;
		this.month = month;
		setLayout(new GridLayout(1,3));
		
		previousMonth = new JButton("<-");
		previousMonth.setActionCommand("previous");
		previousMonth.addActionListener(this);
		add(previousMonth);
		
		year_month = new JLabel();
		year_month.setText("<html>" + year + "<br>" + month + "</html>");
		add(year_month);
		
		nextMonth = new JButton("->");
		nextMonth.setActionCommand("next");
		nextMonth.addActionListener(this);
		add(nextMonth);
		
	}
	public void changeMonth(int month) {
		if(month == 0) {
			this.year -= 1;
			this.month = 12;
		} else if(month == 13) {
			this.year += 1;
			this.month = 1;
		} else {
			this.month = month;
		}
		year_month.setText("<html>" + this.year + "<br>" + this.month + "</html>");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("previous")) {
			changeMonth(month-1);
		} else if(cmd.equals("next")) {
			changeMonth(month+1);
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

class CalendarPanel extends JPanel implements ActionListener {
	MonthPanel mp;
	CalendarCell[] cell;
	JLabel[] dayofweek;
	CalendarPanel(MonthPanel mp, int begin, int length) {
		int i;
		this.mp = mp;
		setLayout(new GridLayout(6,7));
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
		cell = new CalendarCell[35];
		for(i=0; i<35; i++) {
			cell[i] = new CalendarCell();
			add(cell[i]);
		}
		for(i=0; i<begin; i++) {
			cell[i].setVisible(false);
		}
		for(i=begin; i<begin+length; i++) {
			cell[i].setDate(i-begin+1);
		}
		for(i=begin+length; i<35; i++) {
			cell[i].setVisible(false);
		}
	}
	public void setMonth(int begin, int length) {
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
	}
}

class CalendarCell extends JPanel {
	ArrayList<JButton> b;
	CalendarCell() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		b = new ArrayList<JButton>();
		b.add(new JButton());
		add(b.get(0));
	}
	public void setDate(int date) {
		b.get(0).setText(Integer.toString(date));
	}
	public void addEvent(String title) {
		b.add(new JButton(title));
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
	JLabel detail;
	DetailPanel() {
		detail = new JLabel("detail");
		add(detail);
	}
	public void setDetail(String text) {
		detail.setText(text);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}