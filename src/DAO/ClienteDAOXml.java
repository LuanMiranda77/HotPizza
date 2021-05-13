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
import model.Clientes;
import view.ExceptionCliente;


public class ClienteDAOXml implements InterClienteDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>listaClientes=new ArrayList<>();
	 
	 
	    public ArrayList<ArrayList<String>>getListaClientes() {
			return listaClientes;
		}
	    

		public void setListaClientes(ArrayList<ArrayList<String>> listaClientesr) {
			this.listaClientes = listaClientesr;
		}
		

		public void addClientes(ArrayList<String> novo) {
			listaClientes.add(novo);
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
					return ((ArrayList<ArrayList<String>>) xstream.fromXML(arq));
				}
			} catch (FileNotFoundException a) {
				a.printStackTrace();
			}
		return new ArrayList<ArrayList<String>>();
	}
	
	@Override
	public void salvar(ClienteDTO novo) throws ExceptionCliente {
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		
		ArrayList<String> cliente = new ArrayList<>();
		
		if(lista.size()==0) {
			cliente.add(""+novo.getCod());
			cliente.add(novo.getNome());
			cliente.add(novo.getEnd());
			cliente.add(novo.getCPF());
			cliente.add(novo.getTelefone());
			lista.add(cliente);
		    salvarCentral(lista, "xml/cliente.xml");
	}
		else if(lista.size()>0) {
			for(int cont= 0; cont<lista.size();cont++) {
				for(int i=0;i<5;i++) {
						if(novo.getCPF().equals(lista.get(cont).get(3))) {
							throw new ExceptionCliente();
					}
				}
				
			}
			cliente.add(""+novo.getCod());
			cliente.add(novo.getNome());
			cliente.add(novo.getEnd());
			cliente.add(novo.getCPF());
			cliente.add(novo.getTelefone());
			
			lista.add(cliente);
		    salvarCentral(lista, "xml/cliente.xml");
		}
		
	}
	@Override
	public void remove(int cod) {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		lista.remove(cod);
	    salvarCentral(lista, "xml/cliente.xml");
		
	}
	
	@Override
	public void editar(ClienteDTO editado) {
		
      String id=""+editado.getCod();
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
			for(int i=0;i<1;i++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1, editado.getNome());
						lista.get(cont).set(2, editado.getEnd());
						lista.get(cont).set(3, editado.getCPF());
						lista.get(cont).set(4, editado.getTelefone());
						
					    salvarCentral(lista, "xml/cliente.xml");
					}
			}
		}
		
		
		
	}
	@Override
	public ClienteDTO consultar(int cod) {
        ClienteDTO cliente = new ClienteDTO();
		String id = ""+cod;
        
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
					if(id.equals(lista.get(cont).get(0))) {
						cliente.setCod(Integer.parseInt(lista.get(cont).get(0)));
						cliente.setNome((lista.get(cont).get(1)));
						cliente.setEnd((lista.get(cont).get(2)));
						cliente.setCPF((lista.get(cont).get(3)));
						cliente.setTelefone((lista.get(cont).get(4)));
					}
			}
		return cliente;
	}
	@Override
	public int id() {
		int id=0;
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		
		if(lista.size()==0) {
			id++;
			
		}
		else {
		for(int cont= 0; cont<lista.size();cont++) {
			for(int i=0;i<1;i++) {
			        id=Integer.parseInt(lista.get(cont).get(0));
			        id++;
			}
			}
		
		}
		
		return id;
	}
	@Override
	public ClienteDTO lista() {
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		ClienteDTO listaCliente = new ClienteDTO();
		ArrayList<ClienteDTO> lista1 = new ArrayList<>();
		
		for(int cont= 0; cont<lista.size();cont++) {
			cont++;
			lista1.add(consultar(cont));
			cont--;
		}
		listaCliente.setLista(lista1);
		return listaCliente;
	}


	@Override
	public int tamanho() {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
		
		return lista.size();
	}

	
}
