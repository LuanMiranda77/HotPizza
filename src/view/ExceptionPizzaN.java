package view;

import javax.swing.JOptionPane;

public class ExceptionPizzaN extends Exception {
	public ExceptionPizzaN() {
		JOptionPane.showMessageDialog(null,"Pizza N�o encontrada ! !","Aviso", JOptionPane.ERROR_MESSAGE);
		}

}
