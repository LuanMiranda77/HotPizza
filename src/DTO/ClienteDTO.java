package DTO;

import java.util.ArrayList;

import model.Clientes;


public class ClienteDTO {
	private int cod;
	private String nome;
	private String CPF;
	private String end;
	private String telefone;
	
	private ArrayList<ClienteDTO> lista = new ArrayList<>();
	
	public ArrayList<ClienteDTO> getLista() {
		return lista;
	}
	
	public void setLista(ArrayList<ClienteDTO> lista) {
		this.lista = lista;
	}
	
	public void addCliente(ClienteDTO novo) {
		lista.add(novo);
	}
	
	public int getCod() {
		return cod;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}


