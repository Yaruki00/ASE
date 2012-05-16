package LifeDesigner;

import javax.swing.JFrame;


public class LifeDesigner {

	public static void main(String[] args) {
		JFrame f = new JFrame("LifeDesigner");
		GUI gui = new GUI();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(gui);
		f.setSize(800,600);
		f.setVisible(true);
	}

}
