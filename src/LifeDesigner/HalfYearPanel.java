package LifeDesigner;

import javax.swing.*;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Calendar;

public class HalfYearPanel extends JPanel implements ActionListener {
	GUI gui;
	EventManager em;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	YearMonthPanel ymp;
	TermPanel tp;
	VisibleProjectPanel vpp;
	JButton b;
	HalfYearPanel(GUI gui, EventManager em) {
		this.gui = gui;
		this.em = em;
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 100.0;
		gbc.weighty = 100.0;
		
		tp = new TermPanel();
		addComponent(gbl, gbc, tp, 0, 0 , 6, 1);
		
		vpp = new VisibleProjectPanel();
		addComponent(gbl,gbc, vpp, 0, 1, 6, 4);
		
		b = new JButton("CHANGE!");
		b.setActionCommand("a");
		b.addActionListener(this);
		addComponent(gbl, gbc, b, 0, 5, 6, 1);
		
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
			gui.changeMode("month");
		}
	}	
}

class TermPanel extends JPanel implements ActionListener {
	JLabel year_month;
	JButton nextMonth, previousMonth;
	Calendar cal;
	int nowYear, nowMonth;
	TermPanel() {
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

class VisibleProjectPanel extends JPanel implements ActionListener {
	JLabel project[][];
	VisibleProjectPanel() {
		setLayout(new GridLayout(10,7));
		project = new JLabel[10][];
		for(int i=0; i<10; i++) {
			project[i] = new JLabel[7];
			for(int j=0; j<7; j++) {
				project[i][j] = new JLabel();
				project[i][j].setText("<html> (" + i + "," + j + ") <br> line 2 </html>");
				add(project[i][j]);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			
	}
}