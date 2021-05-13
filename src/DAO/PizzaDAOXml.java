package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DTO.IngredientesDTO;
import DTO.PizzasDTO;
import model.IngBase;
import model.Ingredientes;
import model.ListaIngred;
import model.montarDecore;
import view.ExceptionPizza;




public class PizzaDAOXml implements InterPizzaDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>listaPizza=new ArrayList<>();
	 
	 private String ingred="";
	 
	    public ArrayList<ArrayList<String>>getlista() {
			return listaPizza;
		}
	    

		public void setlista(ArrayList<ArrayList<String>> listar) {
			this.listaPizza = listar;
		}
		

		public void addpizzacionarios(ArrayList<String> novo) {
			listaPizza.add(novo);
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
	public void salvar(PizzasDTO novo) throws ExceptionPizza{
		
		ArrayList<ArrayList<String>> lista1 = recuperarCentral("xml/pizza.xml");
		
		ArrayList<String> pizza= new ArrayList<>();
		
			pizza.add(""+novo.getCod());
			pizza.add(novo.getNome() );
			pizza.add(novo.getIngred().getNome());
			pizza.add(novo.getTamnaho());
			pizza.add(""+novo.getQuantft());
			pizza.add(""+novo.getValorft());
			pizza.add(""+novo.getValorcomp());
			pizza.add(novo.getPrepara());
			pizza.add(""+novo.getCusto());
			
			lista1.add(pizza);
		    salvarCentral(lista1, "xml/pizza.xml");
		
	}
	
	@Override
	public void remove(int cod) {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pizza.xml");
		lista.remove(cod);
	    salvarCentral(lista, "xml/pizza.xml");
		
	}
	
	@Override
	public void editar(PizzasDTO editado) {
		
      String id=""+editado.getCod();
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pizza.xml");
		
		
		for(int cont= 0; cont<lista.size();cont++) {
			for(int i=0;i<1;i++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1, editado.getNome());
						lista.get(cont).set(2, editado.getIngred().getNome());
						lista.get(cont).set(3, editado.getTamnaho());
						lista.get(cont).set(4, ""+editado.getQuantft());
						lista.get(cont).set(5, ""+editado.getValorft());
						lista.get(cont).set(6, ""+editado.getValorcomp());
						lista.get(cont).set(7, ""+editado.getPrepara());
						lista.get(cont).set(8, ""+editado.getCusto());         
						
					    salvarCentral(lista, "xml/pizza.xml");
					}
			}
		}
	}
	
	@Override
	public PizzasDTO consultar(int cod) {
		PizzasDTO pizzacionario= new PizzasDTO();
		
		 String id=""+cod;
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pizza.xml");
		
		
		
		for(int cont= 0; cont<lista.size();cont++) {
			int fator = 0;
			
					if(id.equals(lista.get(cont).get(0))) {
					ListaIngred novo = new IngBase("");
						novo.setNome(lista.get(cont).get(2));
						
						pizzacionario.setCod(Integer.parseInt(lista.get(cont).get(0)));
						pizzacionario.setNome(lista.get(cont).get(1));
					    pizzacionario.setIngred(novo);
						pizzacionario.setTamnaho(lista.get(cont).get(3));
						pizzacionario.setQuantft (Integer.parseInt(lista.get(cont).get(4)));
						pizzacionario.setValorft (Float.parseFloat(lista.get(cont).get(5)));
						pizzacionario.setValorcomp(Float.parseFloat(lista.get(cont).get(6)));
						pizzacionario.setPrepara(lista.get(cont).get(7));
						pizzacionario.setCusto(Float.parseFloat(lista.get(cont).get(8)));
		
					}
					
			}
		return pizzacionario;
	}
	
	@Override
	public int id() {
		int id=0;
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pizza.xml");  
		
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
	public PizzasDTO lista() {
		PizzasDTO pizzacionario= new PizzasDTO();
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pizza.xml");
		
		ArrayList<PizzasDTO> listaPizza = new ArrayList<>();
		
		for(int cont= 0; cont<lista.size();cont++) {
			      cont++;
			      listaPizza.add(consultar(cont));
			      cont--;
		}
		pizzacionario.setLista(listaPizza);
		return pizzacionario;
	}


	@Override
	public int tamanho() {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/pizza.xml");
		return lista.size();
	}


	@Override
	public int tamanholistaingred(int cod) {
         String id=""+cod;
		
		ArrayList<ArrayList<String>> lista1 = recuperarCentral("xml/pizza.xml");
		
		for(int cont= 0; cont<lista1.size();cont++) {
			for(int i=0;i<9;i++) {
					if(id.equals(lista1.get(cont).get(0))) {
						ingred=lista1.get(cont).get(2);
					}
			}
		}
		String [] lista =ingred.split("-");
		return lista.length;
	}

	
}
