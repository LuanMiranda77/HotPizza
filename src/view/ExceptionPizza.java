package view;

import javax.swing.JOptionPane;

public class ExceptionPizza extends Exception {
	public ExceptionPizza() {
		JOptionPane.showMessageDialog(null,"Pizza j� cadastrado !","Aviso", JOptionPane.ERROR_MESSAGE);
		}

}
