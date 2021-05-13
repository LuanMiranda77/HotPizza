package model;

import DAO.IncredienteDAOXml;
import DAO.InterIngredienteDAO;
import DAOAPostgreSQL.IngredDAOAdapterPostgre;
import DAOAPostgreSQL.TestConexao;
import DTO.IngredientesDTO;
import view.ExceptionIngre;

public class Ingredientes {
	private int cod;
	
	private String nome;
	private float preco;
	private float qunat;
	private String vencimewnto;
	
	public Ingredientes() {
		
	}
	
	public Ingredientes(int cod, String nome, float quant, float preco, String vencimento) {
		
		this.cod = cod;
		this.nome = nome;
		this.preco = preco;
		this.qunat = quant;
		this.vencimewnto=vencimento;
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

	public static void salvarIngred(IngredientesDTO novo) throws ExceptionIngre {
		InterIngredienteDAO ingred= TestConexao.retornar().fabricarIngred();
		ingred.salvar(novo);
		
	}
	public static void editarIngre(IngredientesDTO editado) {
		
		InterIngredienteDAO ingred= TestConexao.retornar().fabricarIngred();
		ingred.editar(editado);

		
	}
	public static void excluirIngre(int index) {
		InterIngredienteDAO ingred= TestConexao.retornar().fabricarIngred();
		ingred.remove(index);	
	}
	
	public static IngredientesDTO pesIngred(int id)throws ExceptionIngre {
		
		InterIngredienteDAO ingred= TestConexao.retornar().fabricarIngred();
	
		return ingred.consultar(id);
		
	}
	
	public static int tamnaho() {
		InterIngredienteDAO ingred= TestConexao.retornar().fabricarIngred();
		
		return ingred.tamanho();
	}
	
	public static IngredientesDTO listaIngre(){
		InterIngredienteDAO ingred= TestConexao.retornar().fabricarIngred();
	
		
		return ingred.lista();
		
	}
}
