package DTO;



import java.util.ArrayList;

import view.observado;

public class PedidosDTO extends observado {
	
	private int cod;
	private ClienteDTO clientes;
	private String pizza;
	private float valor;
	private String data;
	private String status;
	private String tam;
	private int quant;
	
	private ArrayList<PedidosDTO> listaPedidos = new ArrayList<>();



	public ArrayList<PedidosDTO> getListaPedidos() {
		return listaPedidos;
	}
	public void setListaPedidos(ArrayList<PedidosDTO> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public ClienteDTO getClientes() {
		return clientes;
	}
	public void setClientes(ClienteDTO clientes) {
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
	public void adicionar(PedidosDTO novo) {
		listaPedidos.add(novo);
	}
	
	


}
