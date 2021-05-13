package model;

import DAO.InterPizzaDAO;

import DAOAPostgreSQL.PizzaDAOAdapterPostgre;
import DAOAPostgreSQL.TestConexao;
import DTO.PizzasDTO;
import view.ExceptionPizza;

public class Pizzas {
	//atributos
	private int cod;
	private String nome;
	private ListaIngred ingred;
	private String tamnaho;
	private int quantft;
	private float valorft;
	private float valorcomp;
	private String prepara;
	private float custo;
	private float quantvend;
	
	//construtor normal
	public Pizzas() {
		
	}
	
	//construtor composto
	
	//gets e sets
	public int getCod() {
		return cod;
	}
	public Pizzas(int cod, String nome, ListaIngred desc, String tamnaho, int quantft, float valorft, float valorcomp,
			String prepara, float custo) {
		this.cod = cod;
		this.nome = nome;
		this.ingred=desc;
		this.tamnaho = tamnaho;
		this.quantft = quantft;
		this.valorft = valorft;
		this.valorcomp = valorcomp;
		this.prepara = prepara;
		this.custo = custo;
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

	public void setIngred(ListaIngred ingred) {
		this.ingred = ingred;
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
	public static void salvarPizza(PizzasDTO novo) throws ExceptionPizza{
		
		InterPizzaDAO pizza = TestConexao.retornar().fabricarPizza();
		
		
		pizza.salvar(novo);
		
		
	}
	
	public static void editarPizza(PizzasDTO novo) {
		InterPizzaDAO pizza = TestConexao.retornar().fabricarPizza();
		
		pizza.editar(novo);
		
		
	}
	
	public static PizzasDTO pesPizza(int cod) {
		
		InterPizzaDAO pizza = TestConexao.retornar().fabricarPizza();
	
		
		return pizza.consultar(cod);

		
	}
	

	public static void excluirPizza(int index) {
		
		InterPizzaDAO pizza = TestConexao.retornar().fabricarPizza();
		pizza.remove(index);
		
	}
	
	public static int idPizza() {

		
		InterPizzaDAO pizza = TestConexao.retornar().fabricarPizza();
		
		return pizza.id(); 
	}
	
	public static PizzasDTO listaPizza(){
		
		InterPizzaDAO pizza = TestConexao.retornar().fabricarPizza();
		return pizza.lista();
		
	}
	
}
	