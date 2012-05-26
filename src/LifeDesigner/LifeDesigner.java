package LifeDesigner;

import javax.swing.JFrame;


public class LifeDesigner {

	public static void main(String[] args) {
		JFrame f = new JFrame("LifeDesigner");
		EventManager em = new EventManager();
		GUI gui = new GUI(em);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(gui);
		f.setSize(1200,800);
		f.setVisible(true);
	}

}
