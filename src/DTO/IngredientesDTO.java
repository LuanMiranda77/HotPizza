package DTO;

import java.util.ArrayList;
import DAO.IncredienteDAOXml;
import DAO.InterIngredienteDAO;
import view.ExceptionIngre;

public class IngredientesDTO {
	private int cod;
	
	private String nome;
	private float preco;
	private float qunat;
	private String vencimewnto;
	
	private ArrayList<IngredientesDTO> lista = new ArrayList<>();
	
	public ArrayList<IngredientesDTO>  getLista() {
		return lista;
	}
	public void setLista(ArrayList<IngredientesDTO>  lista) {
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
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public float getQunat() {
		return qunat;
	}
	public void setQunat(float qunat) {
		this.qunat = qunat;
	}

	public String getVencimewnto() {
		return vencimewnto;
	}

	public void setVencimewnto(String vencimewnto) {
		this.vencimewnto = vencimewnto;
	}
	public String toString() {
		return this.nome;
		
	}

	
}
