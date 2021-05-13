package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DTO.ContabilidadeDTO;




public class ContabilDAOXml implements InterContabilDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>listaCont=new ArrayList<>();
	 
	 public ContabilDAOXml() {
			 listaCont = recuperarCentral("xml/contabil.xml");
			
		}
	 
	 
	    public ArrayList<ArrayList<String>>getlistaFuncionario() {
			return listaCont;
		}
	    

		public void setlistaFuncionario(ArrayList<ArrayList<String>> listaFuncionarior) {
			this.listaCont = listaFuncionarior;
		}
		

		public void addfuncionarios(ArrayList<String> novo) {
			listaCont.add(novo);
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
	public void salvar(int cod, float quant, float lucro, String pizza, String data){
		
		
		ArrayList<String> funcionario= new ArrayList<>();
			funcionario.add(""+cod);
			funcionario.add(""+quant);
			funcionario.add(""+lucro);
			funcionario.add(pizza);
			funcionario.add(data);
			
			listaCont.add(funcionario);
		    salvarCentral(listaCont, "xml/contabil.xml");
		
	}
	
	@Override
	public void remove(int cod) {
		
		listaCont.remove(cod);
	    salvarCentral(listaCont, "xml/contabil.xml");
		
	}
	
	@Override
	public void editar(int cod, float quant, float lucro, String pizza, String data) {
		
      String id=""+cod;
		
		for(int cont= 0; cont<listaCont.size();cont++) {
			for(int i=0;i<1;i++) {
					if(id.equals(listaCont.get(cont).get(0))) {
						listaCont.get(cont).set(1, ""+quant);
						listaCont.get(cont).set(2, ""+lucro);
						listaCont.get(cont).set(3, pizza);
						listaCont.get(cont).set(4, data);
					    salvarCentral(listaCont, "xml/contabil.xml");
					}
			}
		}
	}
	
	@Override
	public ContabilidadeDTO consultar(int cod) {
	ContabilidadeDTO conta= new ContabilidadeDTO();
		
		 String id=""+cod;
		
		
		for(int cont= 0; cont<listaCont.size();cont++) {
			  if(id.equals(listaCont.get(cont).get(0))) {
				  conta.setCod(Integer.parseInt(listaCont.get(cont).get(0)));
				  conta.setQunat(Float.parseFloat(listaCont.get(cont).get(1)));
				  conta.setLucro(Float.parseFloat(listaCont.get(cont).get(2)));
				  conta.setSabor(listaCont.get(cont).get(3));
				  conta.setData(listaCont.get(cont).get(4));
			  }
	
		}
		return conta;
	}
	
	@Override
	public int id() {
		int id=0;
		
		if(listaCont.size()==0) {
			id++;
		}
		
		else {
		for(int cont= 0; cont<listaCont.size();cont++) {
			for(int i=0;i<1;i++) {
			        id=Integer.parseInt(listaCont.get(cont).get(0));
			        id++;
			}
			}
		
		}
		
		return id;
	}
	
	@Override
	public ContabilidadeDTO lista() {
		ContabilidadeDTO lista1= new ContabilidadeDTO();
		
		ArrayList<ContabilidadeDTO>listaCont1 = new ArrayList<>();
		
		for(int cont= 0; cont<listaCont.size();cont++) {
			    cont++;
			    listaCont1.add(consultar(cont));
			    cont--;
		}
		lista1.setLista(listaCont1);
		return lista1;
	}


	@Override
	public int tamanho() {
		return listaCont.size();
	}

	
}
