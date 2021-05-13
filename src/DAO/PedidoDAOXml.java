package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DTO.ClienteDTO;
import DTO.PedidosDTO;
import DTO.PizzasDTO;
import controller.ControlPedidos;

public class PedidoDAOXml implements InterPedidoDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>> listaPedido=new ArrayList<>();
	 
	 
	    public ArrayList<ArrayList<String>>getListaPedido() {
			return listaPedido;
		}
	    

		public void setListaPedido(ArrayList<ArrayList<String>> listaClientesr) {
			this.listaPedido = listaClientesr;
		}
		

		public void addPedido(ArrayList<String> novo) {
			listaPedido.add(novo);
		 }
		

		public void salvarCentral(ArrayList<ArrayList<String>> c, String a) {


		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(a);
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		/**
		 * Ela retorna um objeto do tipo central
		 * recebe uma String com o nome do arquivo
		 * Se o arquivo já esxistir ele tranforma o xml em objeto
		 * E se não existir ele cria uma nova central
		 */
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> recuperarCentral(String n) {
		File arquivo = new File(n);

			try {
				if (arquivo.exists()) {
					FileInputStream arq = new FileInputStream(arquivo);
					return (ArrayList<ArrayList<String>>) xstream.fromXML(arq);
				}
			} catch (FileNotFoundException a) {
				a.printStackTrace();
			}
		return new ArrayList<ArrayList<String>>();
	}
	
	@Override
	public void salvar(PedidosDTO novo){
		
		   ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pedido.xml");
		   
		   ArrayList<String> pedido = new ArrayList<>();
		     
		   
	       pedido.add(""+novo.getCod());
	       pedido.add(""+novo.getClientes().getCod());
	       pedido.add(novo.getPizza());
	       pedido.add(""+novo.getValor());
	       pedido.add(novo.getData());
	       pedido.add(novo.getStatus());
	       
		   lista.add(pedido);
		   salvarCentral(lista, "xml/pedido.xml");
		
	}
	@Override
	public void remove(int cod) {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pedido.xml");
		lista.remove(cod);
	    salvarCentral(lista, "xml/pedido.xml");
		
	}
	
	@Override
	public void editar(PedidosDTO editado) {
				
			
	}
	
	
	@Override
	public PedidosDTO consultar(int cod) {
		ArrayList<ArrayList<String>>lista = recuperarCentral("xml/pedido.xml");
		
		PedidosDTO ped = new PedidosDTO();
		
		
		InterClienteDAO cli = new ClienteDAOXml();
		
		InterPizzaDAO p = new PizzaDAOXml();
		
		ClienteDTO c = new ClienteDTO();
		
		PizzasDTO pi = new PizzasDTO();
		
		ArrayList<PizzasDTO> listapi = new ArrayList<>();
		
		String id =""+cod;
	  	for(int cont=0;cont< lista.size();cont++) {
		 if(id.equals(lista.get(cont).get(0))) { 
		    ped.setCod(Integer.parseInt(lista.get(cont).get(0)));
			c=cli.consultar(Integer.parseInt(lista.get(cont).get(1)));
			ClienteDTO cliente = new ClienteDTO();
			cliente.setCod(c.getCod());
			cliente.setNome(c.getNome());
			cliente.setEnd(c.getEnd());
			cliente.setCPF(c.getCPF());
			cliente.setTelefone(c.getTelefone());
			ped.setClientes(cliente);
			ped.setPizza(lista.get(cont).get(2));
			ped.setValor(Float.parseFloat(lista.get(cont).get(3)));
			ped.setData(lista.get(cont).get(4));
			ped.setStatus(lista.get(cont).get(5)); 
			 
		 }
	  	}
		
		return ped;
	}
	
	@Override
	public int id() {
		int id=0;
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pedido.xml");
		
		if(lista.size()==0) {
			id++;
			
		}
		else {
		for(int cont= 0; cont<lista.size();cont++) {
			     id=Integer.parseInt(lista.get(cont).get(0));
			      id++;
			}
		
		}
		
		return id;
	}


	@Override
	public int tamanho() {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pedido.xml");
		return lista.size();
	}

	@Override
	public void editarStatus(int cod, String status) {
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pedido.xml");
		String id =""+cod; 
		
		for(int cont=0; cont<lista.size();cont++) {
			if(id.equals(lista.get(cont).get(0))) {
				lista.get(cont).add(5 ,status);
				salvarCentral(lista,"xml/pedido.xml");
			}
	}
	}


	@Override
	public PedidosDTO listaP() {
		
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pedido.xml");
		
		PedidosDTO list = new PedidosDTO();
		
		ArrayList<PedidosDTO> listaped = new ArrayList<>();
		
		
		for(int cont=0; cont<lista.size();cont++) {
			cont++;
	        list=consultar(cont);
			listaped.add(list);
			cont--;
			}
		
		list.setListaPedidos(listaped);
		return list;
	}


	@Override
	public int quntPed() {
       int i = 0 , a=0;
		
		for(PedidosDTO e: listaP().getListaPedidos()) {
			if(e.getClientes().getCPF().equals(ControlPedidos.pesPedido().getListaPedidos().get(i).getClientes().getCPF())) {
				       a++;
				      
				 
				}
	     }
		return a;
	}

	
}
