package DTO;

import java.util.ArrayList;
import DAO.InterPizzaDAO;

import DAO.PizzaDAOXml;
import model.Ingredientes;
import model.ListaIngred;
import model.PizzaDecorator;
import view.ExceptionIngre;
import view.ExceptionPizza;

public class PizzasDTO {
	//atributos
	private int cod;
	private String nome;
	private String pizza;
	private ListaIngred ingred;
	private String tamnaho;
	private int quantft;
	private float valorft;
	private float valorcomp;
	private String prepara;
	private float custo;
	private float quantvend;
	private ArrayList<PizzasDTO> lista = new ArrayList<>();
	
	public ArrayList<PizzasDTO> getLista() {
		return lista;
	}
	
	public String getPizza() {
		return pizza;
	}

	public void setPizza(String pizza) {
		this.pizza = pizza;
	}

	public void setLista(ArrayList<PizzasDTO> lista) {
		this.lista = lista;
	}
	
	public float getQuantvend() {
		return quantvend;
	}

	public void setQuantvend(float quantvend) {
		this.quantvend = quantvend;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public int getCod() {
		return cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTamnaho() {
		return tamnaho;
	}
	public void setTamnaho(String tamnaho) {
		this.tamnaho = tamnaho;
	}
	public int getQuantft() {
		return quantft;
	}
	public void setQuantft(int quantft) {
		this.quantft = quantft;
	}
	
	public ListaIngred getIngred() {
		return ingred;
	}
	public void setIngred(ListaIngred novo) {
		this.ingred = novo;
	}
	public float getValorft() {
		return valorft;
	}
	public void setValorft(float valorft) {
		this.valorft = valorft;
	}
	public float getValorcomp() {
		return valorcomp;
	}
	public void setValorcomp(float valorcomp) {
		this.valorcomp = valorcomp;
	}
	
	public String getPrepara() {
		return prepara;
	}

	public void setPrepara(String prepara) {
		this.prepara = prepara;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}
	public String toString() {
		return this.nome;
		
	}
	
}
	