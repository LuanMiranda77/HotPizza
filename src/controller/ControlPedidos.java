package controller;

import java.text.DateFormat;
import java.util.Date;

import DTO.PedidosDTO;
import DTO.PizzasDTO;
import model.CalculePrecoStartegy;
import model.InteiraPizza;
import model.MeiaPizza;
import model.Pedidos;
import view.TablePedido;

public class ControlPedidos {
	
	
	
	
	
	// metodo salvar 
public static void contSalvarPedido(PedidosDTO novo) {
	
		
		Pedidos.salvarPedido(novo);
		
		}

	//metodo editar 
	public static void conteditarPedido(PedidosDTO editado){
      
		
		Pedidos.editarPedido(editado);

		
	}
	
	// metodo excluir
	public static void contExcluirPedido(int index){
	
			Pedidos.excluirPedido(index);
	
	}
	
	//filtro por codigo
	public static boolean contFiltroCod (int cod) {
		TablePedido tabela = new TablePedido();
		return tabela.filtroCod(cod);	
	}
	
	//filtro por nome
	public static boolean contFiltroNome (String nome) {
		TablePedido tabela = new TablePedido();
		return tabela.filtroNome(nome);	
		
	}
	
	public static void contStatus(int cod, String status,int i) {
		
		Pedidos.editarStatus(cod,status);
	
	}
	public static String dataDoDia() {
		Date d = new Date();
        String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		return hoje;
	}
	
	public static PedidosDTO pesPedido(){
		return Pedidos.listaPedido();
		
	}
	
	public static int qntPedidos() {
		return Pedidos.qntPedEnd();
		
	}
	
	public static PedidosDTO retPedido(int cod) {
		return Pedidos.ped(cod);
		
	}
	public static int id() {
		return Pedidos.id();
	}
	public static float calPrecoPizza(PizzasDTO pizza, String tipo) {
		float preco = 0;
		
	    CalculePrecoStartegy calculo;
	    
	    if (tipo.equals("MEIA")) {
	    	calculo = new MeiaPizza();
	    }
	    else {
	    	calculo = new InteiraPizza();
	    }
	    preco= calculo.calcular(pizza);
		return preco;
	}
	
	

}
