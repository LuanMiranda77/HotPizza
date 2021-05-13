package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaCadPizza;
import view.TelaEntregas;
import view.TelaMenu;
import view.VIewPDV;
import view.ViewConfig;
import view.ViewPizzaiolo;
import view.ViewlClientes;
import view.ViewlContabilidade;
import view.ViewlFuncionarios;




public class OuvinteMenu implements ActionListener {
	TelaMenu t;
	
	public void actionPerformed(ActionEvent e) {
		
		String tela=e.getActionCommand();
		
		switch (tela) {
		
			
		case"PDV":
			new VIewPDV();
				
			break;
			
		case"<html>Clientes<html>":
			new ViewlClientes("");
			
			break;
			
		case"<html>Pizzas<html>":
			new TelaCadPizza();
				
			break;
			
		case"<html>Entregas<html>":
			new TelaEntregas();
		
			
			break;
			
		case"<html>Funcionarios<html>":
			new ViewlFuncionarios();
			break;	
			
		case"<html>Pizzaiolo<html>":
			new ViewPizzaiolo();
			break;	
			
		
	   case"<html>Contabilidade<html>":
		   new ViewlContabilidade();
		
		 break;	
		 
	   case"<html>Configuração<html>":
		   new ViewConfig();
			
			 break;	
		 
		 
		}
		
	}

}
