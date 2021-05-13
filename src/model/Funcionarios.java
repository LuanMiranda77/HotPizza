package model;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.InterFuncionarioDAO;
import DAOAPostgreSQL.BDarquivo;
import DAOAPostgreSQL.BDrelacional;
import DAOAPostgreSQL.ConexaoSingleton;
import DAOAPostgreSQL.FabFactory;
import DAOAPostgreSQL.TestConexao;
import DTO.FuncionariosDTO;
import view.ExceptionClienteN;
import view.ExceptionFuncionarioCad;

public class Funcionarios {
	private int cod;
	private String nome;
	private String cargo;
	private String senha;
	private String nivel;
	 static FabFactory criar;
	
	public Funcionarios() {
	}
	
	public Funcionarios(int cod, String nome, String cargo, String senha, String nivel) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.cargo = cargo;
		this.senha = senha;
		this.nivel = nivel;
		
		
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
	
	
	public static void salvarFuncionario(FuncionariosDTO novo) throws ExceptionFuncionarioCad {
		
        InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
		
		fun.salvar(novo);

	}
	
	public static void editarFuncionarios(FuncionariosDTO editado) {
			
		 InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
			
			fun.editar(editado);
				
	}
		public static void excluirFuncionario(int index) {
			 InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
		
			 fun.remove(index);
			 
		}
		
		public static FuncionariosDTO pesFuncionario(int cod) throws ExceptionClienteN{
			
			InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
			return fun.consultar(cod);
		}
		
		public static int idFun() {
			InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
			
			return fun.id(); 
		}
		public static int tamnaho() {
		
	        
			InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
			
			return fun.tamanho();
			
		}
		public static FuncionariosDTO listaFun(){
				
				InterFuncionarioDAO fun = TestConexao.retornar().fabricarFun();
				
				return fun.lista();
				
			}
		

}
