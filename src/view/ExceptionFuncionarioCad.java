package view;

import javax.swing.JOptionPane;

public class ExceptionFuncionarioCad extends Exception {
	
	public ExceptionFuncionarioCad(){
	JOptionPane.showMessageDialog(null,"Funcionario j� cadastrado !","Aviso", JOptionPane.ERROR_MESSAGE);
	}
	
}
