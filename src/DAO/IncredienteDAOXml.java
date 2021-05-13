package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.itextpdf.awt.geom.Point2D.Float;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DTO.IngredientesDTO;
import view.ExceptionIngre;




public class IncredienteDAOXml implements InterIngredienteDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>lista=new ArrayList<>();
	 
	 
	    public ArrayList<ArrayList<String>>getlista() {
			return lista;
		}
	    

		public void setlista(ArrayList<ArrayList<String>> listar) {
			this.lista = listar;
		}
		

		public void addingredcionarios(ArrayList<String> novo) {
			lista.add(novo);
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
	public void salvar(IngredientesDTO novo)throws ExceptionIngre{
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
		
		ArrayList<String> ingredcionario= new ArrayList<>();
			ingredcionario.add(""+novo.getCod());
			ingredcionario.add(novo.getNome());
			ingredcionario.add(""+novo.getQunat());
			ingredcionario.add(""+novo.getPreco());
			ingredcionario.add(novo.getVencimewnto());
			
			lista.add(ingredcionario);
		    salvarCentral(lista, "xml/ingred.xml");
		
	}
	
	@Override
	public void remove(int cod) {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
		lista.remove(cod);
	    salvarCentral(lista, "xml/ingred.xml");
		
	}
	
	@Override
	public void editar(IngredientesDTO novo) {
		
      String id=""+novo.getCod();
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
			for(int i=0;i<1;i++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1, novo.getNome());
						lista.get(cont).set(2, ""+novo.getQunat());
						lista.get(cont).set(3, ""+novo.getPreco());
						lista.get(cont).set(4, novo.getVencimewnto());
					    salvarCentral(lista, "xml/ingred.xml");
					}
			}
		}
	}
	
	@Override
	public IngredientesDTO consultar(int cod) {
		IngredientesDTO  ingredcionario= new IngredientesDTO ();
		
		 String id=""+cod;
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
					if(id.equals(lista.get(cont).get(0))) {
						ingredcionario.setCod(Integer.parseInt(lista.get(cont).get(0)));
						ingredcionario.setNome(lista.get(cont).get(1));
						ingredcionario.setQunat(java.lang.Float.parseFloat(lista.get(cont).get(2)));
						ingredcionario.setPreco(java.lang.Float.parseFloat(lista.get(cont).get(3)));
						ingredcionario.setVencimewnto(lista.get(cont).get(4));
					}
		}
		return ingredcionario;
	}
	
	@Override
	public int id() {
		int id=0;
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
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
	public IngredientesDTO lista() {
		
	IngredientesDTO ingred= new IngredientesDTO();
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
		ArrayList<IngredientesDTO> listIng = new ArrayList<>();
		
		for(int cont= 1; cont<lista.size();cont++) {
			ingred = consultar(cont); 
			listIng.add(ingred);
		}
		ingred.setLista(listIng);
		return ingred;
	}


	@Override
	public int tamanho() {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/ingred.xml");
		return lista.size();
	}

	
}
