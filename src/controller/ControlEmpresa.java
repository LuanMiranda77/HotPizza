package controller;

import model.Empresa;

public class ControlEmpresa {
	
	public void caqEmpresa (String CNPJ, String nome, String end, String bairro, String cep, String cidade, String tel) {
		
		
		Empresa emp = new Empresa(CNPJ,nome,end,bairro,cep,cidade,tel);
		
		Empresa.salvarEmpresa(emp);

	}
	public static Empresa retEmpresa() {
		return Empresa.pesEmpresa();
	}

}
