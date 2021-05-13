package model;



import DAO.InterPedidoDAO;
import DAO.PedidoDAOXml;
import DAOAPostgreSQL.PedidoDAOAdapterPostgre;
import DAOAPostgreSQL.TestConexao;
import DTO.PedidosDTO;
import view.observado;

public class Pedidos extends observado {
	
	private int cod;
	private Clientes clientes;
	private String pizza;
	private float valor;
	private String data;
	private String status;
	private String tam;
	private int quant;
	

	
	
	public Pedidos() {
		
	}
	
	
	
	public Pedidos(int cod, Clientes clientes, String pizza, float valor, String data, String status) {
		super();
		this.cod = cod;
		this.clientes = clientes;
		this.pizza = pizza;
		this.valor = valor;
		this.data = data;
		this.status = status;
	}



	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Clientes getClientes() {
		return clientes;
	}
	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}
	
	

	public String getTam() {
		return tam;
	}



	public void setTam(String tam) {
		this.tam = tam;
	}
	


	public String getPizza() {
		return pizza;
	}



	public void setPizza(String pizza) {
		this.pizza = pizza;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}



	public int getQuant() {
		return quant;
	}



	public void setQuant(int quant) {
		this.quant = quant;
	}
	
	public static void salvarPedido(PedidosDTO novo) {
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();

		ped.salvar(novo);
		
		
		mudarEstado(novo);
	
	}
	
	public static void editarPedido(PedidosDTO editado) {
		
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();

		ped.editar(editado);
		
		mudarEstado(editado);
		
	}
		
	
	public static void excluirPedido(int index) {
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();
		
		ped.remove(index);
	}
	
	
	public static void editarStatus(int cod, String status) {
		
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();
		
		ped.editarStatus(cod,status);
				
	}
	
	public static int qntPedEnd() {
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();

		
		return ped.quntPed();
		
		
	}
	public static PedidosDTO ped(int cod) {
		
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();
		
		return ped.consultar(cod);
	}
	public static PedidosDTO listaPedido(){
		InterPedidoDAO ped = TestConexao.retornar().fabricarPed();
		
		return ped.listaP();
	}
	public static int id() {
        InterPedidoDAO ped = TestConexao.retornar().fabricarPed();
		
		return ped.id();
		
	}
	
	public static void mudarEstado(PedidosDTO status) {
		notificacao(status);
	}


}
