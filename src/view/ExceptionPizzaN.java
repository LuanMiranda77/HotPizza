package view;

import javax.swing.JOptionPane;

public class ExceptionPizzaN extends Exception {
	public ExceptionPizzaN() {
		JOptionPane.showMessageDialog(null,"Pizza Não encontrada ! !","Aviso", JOptionPane.ERROR_MESSAGE);
		}

}
