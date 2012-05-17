package LifeDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class MonthPanel extends JPanel implements ActionListener {
	GUI gui;
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
		
		ymp = new YearMonthPanel(this);
		addComponent(gbl, gbc, ymp, 0, 0, 5, 1);
		
		lp = new LabelPanel();
		addComponent(gbl, gbc, lp, 5, 0, 1, 1);
		
		cp = new CalendarPanel(this);
		addComponent(gbl, gbc, cp, 0, 1, 5, 5);
		
		plp = new ProjectListPanel(this);
		addComponent(gbl, gbc, plp, 5, 1, 1, 5);
		
		dp = new DetailPanel();
		addComponent(gbl, gbc, dp, 0, 6, 5, 1);
		
		b = new JButton("CHANGE!");
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
	Calendar cal;
	int nowYear, nowMonth;
	YearMonthPanel(MonthPanel mp) {
		this.mp = mp;
		setLayout(new GridLayout(1,3));
		
		previousMonth = new JButton("<-");
		previousMonth.setActionCommand("previous");
		previousMonth.addActionListener(this);
		add(previousMonth);
		
		cal = Calendar.getInstance();
		nowYear = cal.get(Calendar.YEAR);
		nowMonth = cal.get(Calendar.MONTH);
		year_month = new JLabel();
		year_month.setText("<html>" + nowYear + "<br>" + nowMonth + "</html>");
		add(year_month);
		
		nextMonth = new JButton("->");
		nextMonth.setActionCommand("next");
		nextMonth.addActionListener(this);
		add(nextMonth);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
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
	JPanel[] cell;
	JButton[] date;
	CalendarPanel(MonthPanel mp) {
		this.mp = mp;
		setLayout(new GridLayout(5,7));
		cell = new JPanel[35];
		date = new JButton[35];
		for(int i=0; i<35; i++) {
			cell[i] = new JPanel();
			cell[i].setLayout(new BoxLayout(cell[i], BoxLayout.Y_AXIS));
			add(cell[i]);
			date[i] = new JButton(Integer.toString(i));
			date[i].addActionListener(this);
			date[i].setActionCommand(Integer.toString(i));
			cell[i].add(date[i]);
		}
	}
	public void setMonth(int begin, int length) {
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (Integer.valueOf(ae.getActionCommand())) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
		case 31:
		case 32:
		case 33:
		case 34:
		}
	}
}

class Cell extends JPanel {
	ArrayList<JButton> b;
	Cell() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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