package view;

import javax.swing.JOptionPane;

public class ExceptionIngre extends Exception {
	public ExceptionIngre() {
		JOptionPane.showMessageDialog(null,"Ingrediente j� cadastrado !","Aviso", JOptionPane.ERROR_MESSAGE);
	}

}
