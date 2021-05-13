package DAO;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import model.Pedidos;

public class ZerarPersistencia  {
	public static void zerar(String pers) {
		switch (pers) {
		
		case "FUNCIONARIO":
        InterFuncionarioDAO fun = new FuncionarioDAOXml();
        ArrayList<ArrayList<String>>lista = new ArrayList<ArrayList<String>>();
		fun.salvarCentral(lista,"xml/fun.xml");
		break;
		
		case "CLIENTE" :
		InterClienteDAO pres1 = new ClienteDAOXml();
		ArrayList<ArrayList<String>>cdi1 = new ArrayList<ArrayList<String>>();
		pres1.salvarCentral(cdi1,"xml/cliente.xml");
		break;
		
		case "PIZZA" :
		InterPizzaDAO pres2 = new PizzaDAOXml();
		ArrayList<ArrayList<String>>cdi2 = new ArrayList<ArrayList<String>>();
		pres2.salvarCentral(cdi2,"xml/pizza.xml");
		break;
		
		case "CONTABILIDADE":
		InterContabilDAO pres3 = new ContabilDAOXml();
		ArrayList<ArrayList<String>>cdi3 = new ArrayList<ArrayList<String>>();
		pres3.salvarCentral(cdi3,"xml/contabil.xml");
		break;
		
		case "INGREDIENTES":
		InterIngredienteDAO ingred = new IncredienteDAOXml();
		ArrayList<ArrayList<String>>lista1 = new ArrayList<ArrayList<String>>();
		ingred.salvarCentral(lista1,"xml/ingred.xml");
		break;
		
		case "PEDIDO":
		InterPedidoDAO pres7 = new PedidoDAOXml();
		ArrayList<ArrayList<String>> cdi7 = new ArrayList<>();
		pres7.salvarCentral(cdi7,"xml/pedido.xml");
		break;
		
		default:
		InterCentralDAO pres8 = new CentralDAOXml();
		ArrayList<ArrayList<String>>cdi8 = new ArrayList<ArrayList<String>>();
		pres8.salvarCentral(cdi8);
		break;
		}
		
	}
	public static void main(String[] args) {
		String para="";
		while(!para.equals("para")){
		ZerarPersistencia a = new ZerarPersistencia();
		String[] lista= {"PEDIDO","FUNCIONARIO","CLIENTE","INGREDIENTES","CONTABILIDADE", "PIZZA", "CENTRAL","para"};
		Object result = JOptionPane.showInputDialog(null,"Qual banco dados voce quer zera ?","lista",
													JOptionPane.QUESTION_MESSAGE,null, lista, lista[0]);
		para=(String)result;
		a.zerar((String)result);
		JOptionPane.showMessageDialog(null,"banco de dados zerado!");
		}		
	} 
   
		

	

}
