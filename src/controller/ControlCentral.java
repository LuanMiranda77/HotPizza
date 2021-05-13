package controller;


import DTO.FuncionariosDTO;
import model.Central;
import model.Funcionarios;


public class ControlCentral {
	
	
	public static FuncionariosDTO userLogado () {
		return Central.pesUserLogado();
				
	}
	
	public static void salvarUserLogado(FuncionariosDTO logado) {
		Central.setUserlogado(logado);
		
	}
}


