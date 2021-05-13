package model;


import java.sql.Connection;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.core.util.Pool.Factory;

import DAO.ClienteDAOXml;
import DAO.InterClienteDAO;
import DAOAPostgreSQL.BDarquivo;
import DAOAPostgreSQL.BDrelacional;
import DAOAPostgreSQL.ClienteDAOPostgre;
import DAOAPostgreSQL.ConexaoSingleton;
import DAOAPostgreSQL.FabFactory;
import DAOAPostgreSQL.TestConexao;
import DTO.ClienteDTO;
import view.ExceptionCliente;
import view.ExceptionClienteN;

public class Clientes {
	private int cod;
	private String nome;
	private String CPF;
	private String end;
	private String telefone;
	
	public Clientes() {
		
	}
	
	public Clientes(int cod, String n,String l, String e,String t) {
		this.cod=cod;
		this.nome=n;
		this.end=l;

		this.telefone=t;
		this.CPF=e;
		
		
		
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
	
	
	
	public static void salvarClientes(ClienteDTO novo) throws ExceptionCliente {
		
		InterClienteDAO cliente = TestConexao.retornar().fabricarClie();
		
		cliente.salvar(novo);
		
	}
	
	public static void editarClientes(ClienteDTO editado) {
		InterClienteDAO cliente = TestConexao.retornar().fabricarClie();

		
		cliente.editar(editado);
}
	public static void excluirClientes(int index) {
		
		InterClienteDAO cliente = TestConexao.retornar().fabricarClie();
		
	    cliente.remove(index);
	}
	
	public static ClienteDTO pesClientes(int cod) throws ExceptionClienteN{
		InterClienteDAO cliente = TestConexao.retornar().fabricarClie();
		
        ClienteDTO cli = new ClienteDTO();
		 cli = cliente.consultar(cod);

		return cli;
	}
	
	public static int idCliente() {
		
		InterClienteDAO cliente = TestConexao.retornar().fabricarClie();
		
		return cliente.id(); 
	}
	
	public static ClienteDTO getlistacli(){
		
		InterClienteDAO cliente = TestConexao.retornar().fabricarClie();
		
		return cliente.lista();
		
	}
	
	
	
}


