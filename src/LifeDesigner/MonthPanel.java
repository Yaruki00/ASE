package LifeDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
		
		ymp = new YearMonthPanel();
		addComponent(gbl, gbc, ymp, 0, 0, 5, 1);
		
		lp = new LabelPanel();
		addComponent(gbl, gbc, lp, 5, 0, 1, 1);
		
		cp = new CalendarPanel();
		addComponent(gbl, gbc, cp, 0, 1, 5, 5);
		
		plp = new ProjectListPanel();
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
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("a")) {
			gui.changeMode("halfyear");
		}
	}
}

class YearMonthPanel extends JPanel implements ActionListener {
	JLabel year_month;
	JButton nextMonth, previousMonth;
	Calendar cal;
	int nowYear, nowMonth;
	YearMonthPanel() {
		setLayout(new GridLayout(1,3));
		
		previousMonth = new JButton("<-");
		previousMonth.setActionCommand("previous");
		previousMonth.addActionListener(this);
		add(previousMonth);
		
		cal = Calendar.getInstance();
		nowYear = cal.get(Calendar.YEAR);
		nowMonth = cal.get(Calendar.MONTH);
		year_month = new JLabel(nowYear + "\n" + nowMonth);
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
	JLabel cell[];
	CalendarPanel() {
		setLayout(new GridLayout(5,7));
		cell = new JLabel[35];
		for(int i=0; i<35; i++) {
			cell[i] = new JLabel(i+1 +"");
			add(cell[i]);
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

class ProjectListPanel extends JPanel implements ActionListener {
	JLabel list[];
	ProjectListPanel() {
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
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}