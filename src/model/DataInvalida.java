package model;

import javax.swing.JOptionPane;

public class DataInvalida  extends Exception{
	
	public DataInvalida() {
		JOptionPane.showMessageDialog(null,"Data invalida");
		
	}
	

}
