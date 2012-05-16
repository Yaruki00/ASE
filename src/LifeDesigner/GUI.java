package LifeDesigner;

import java.awt.CardLayout;
import javax.swing.*;

public class GUI extends JPanel {
	private MonthPanel mp;
	private HalfYearPanel hyp;
	private CardLayout cl;
	GUI() {
		super();
		cl = new CardLayout();
		setLayout(cl);
		mp = new MonthPanel(this);
		hyp = new HalfYearPanel(this);
		add("mp", mp);
		add("hyp", hyp);
	}
	public void changeMode(String str) {
		if(str.equals("month")) {
			cl.first(this);
		} else if(str.equals("halfyear")) {
			cl.last(this);
		} else {
			
		}
	}
}
