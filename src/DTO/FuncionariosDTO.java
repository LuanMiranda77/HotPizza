package DTO;

import java.util.ArrayList;
import model.Funcionarios;

public class FuncionariosDTO {
	private int cod;
	private String nome;
	private String cargo;
	private String senha;
	private String nivel;
	
	private ArrayList<FuncionariosDTO>lista = new ArrayList<>();
	
	public ArrayList<FuncionariosDTO> getLista() {
		return lista;
	}
	public void setLista(ArrayList<FuncionariosDTO> lista) {
		this.lista = lista;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
}
