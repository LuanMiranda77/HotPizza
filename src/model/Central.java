package model;

import DAO.CentralDAOXml;
import DAO.InterCentralDAO;
import DTO.FuncionariosDTO;

public class  Central { 
	 
	 private Funcionarios userlogado;
	 private Empresa empresa;
	
	 

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Funcionarios getUserlogado() {
		return userlogado;
	}

	public static void setUserlogado(FuncionariosDTO userlogado) {
		InterCentralDAO novo = new CentralDAOXml();
		novo.salvarFun(userlogado);
	}

	public static FuncionariosDTO pesUserLogado() {
		InterCentralDAO novo = new CentralDAOXml();
	   return novo.retornarFun();
	}

}
		
	