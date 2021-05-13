package model;

import DAO.CentralDAOXml;
import DAO.InterCentralDAO;

public class Empresa {
	private String CNPJ;
	private String end;
	private String nome;
	private String bairro;
	private String cep;
	private String cidade;
	private String tel;
	
	
	public Empresa() {
		
	}
	
	
	public Empresa(String cNPJ, String nome, String end, String bairro, String cep, String cidade, String tel) {
		CNPJ = cNPJ;
		this.end = end;
		this.nome = nome;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.tel = tel;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String cep) {
		this.nome = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public static void salvarEmpresa(Empresa n) {
		InterCentralDAO novo = new CentralDAOXml();
		novo.salvarEmpresa(n);
		
	}
	public static Empresa pesEmpresa() {
		InterCentralDAO novo = new CentralDAOXml();
		return novo.retornaEmp();
	}

}
