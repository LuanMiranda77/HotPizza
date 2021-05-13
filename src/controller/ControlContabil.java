package controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import DTO.ContabilidadeDTO;
import DTO.PedidosDTO;
import DTO.PizzasDTO;
import model.Contabilidade;

import model.Pizzas;


public class ControlContabil {
	private static float quant=0;
    int q;
    private static String pizzamais;
    
       
	public static void contContabil(int cod,String dataIn){
	   float lucro=0;
	   
	   
	   Date d = new Date();
       String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
		String [] mes = dataIn.split("/");

		
		quant=contPizza();
		Contabilidade c = new Contabilidade (cod,quant,lucro,pizzamais);
		c.setData(hoje);
		
		Contabilidade.salvarContabil(c, mes);


		
	}
	public static void  conteditarRela(int cod, float quant, float lucro, String pizza, String data){
		
		Contabilidade c = new Contabilidade (cod,quant,lucro,pizza);
		c.setData(data);
		
		Contabilidade.editarContabil(c);
		
	}
	public static int contPizza() {
		int quant=0;
		for(int cont = 0 ; cont<ControlPedidos.pesPedido().getListaPedidos().size();cont++) {
			       quant+=ControlPedidos.pesPedido().getListaPedidos().get(cont).getPizza().length();
			}
		return quant;
		
	}
	public static int id (){
		return Contabilidade.pesId();
		
	}
	
	
	public static ContabilidadeDTO pesContab(int cod){
		return Contabilidade.pesContabil(cod);
		
	}
	public static ContabilidadeDTO listaContab(){
		return Contabilidade.getListaContab();
		
	}
	
	public static void main(String[] args) {
		contContabil(1, "03/2018");
	}
	public static void excluirContab(int index) {
		Contabilidade.excluirContab(index);
	}
	

}
