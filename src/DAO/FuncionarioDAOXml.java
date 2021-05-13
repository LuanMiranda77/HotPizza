package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DTO.FuncionariosDTO;
import model.Funcionarios;
import view.ExceptionFuncionarioCad;




public class FuncionarioDAOXml implements InterFuncionarioDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>listaFuncionario=new ArrayList<>();
	 
	 
	    public ArrayList<ArrayList<String>>getlistaFuncionario() {
			return listaFuncionario;
		}
	    

		public void setlistaFuncionario(ArrayList<ArrayList<String>> listaFuncionarior) {
			this.listaFuncionario = listaFuncionarior;
		}
		

		public void addfuncionarios(ArrayList<String> novo) {
			listaFuncionario.add(novo);
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
	public void salvar(FuncionariosDTO novo)throws ExceptionFuncionarioCad{
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		
		ArrayList<String> funcionario= new ArrayList<>();
			funcionario.add(""+novo.getCod());
			funcionario.add(novo.getNome());
			funcionario.add(novo.getCargo());
			funcionario.add(novo.getSenha());
			funcionario.add(novo.getNivel());
			
			lista.add(funcionario);
		    salvarCentral(lista, "xml/fun.xml");
		
	}
	
	@Override
	public void remove(int cod) {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		lista.remove(cod);
	    salvarCentral(lista, "xml/fun.xml");
		
	}
	
	@Override
	public void editar(FuncionariosDTO editado) {
		
      String id=""+editado.getCod();
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1, editado.getNome());
						lista.get(cont).set(2, editado.getCargo());
						lista.get(cont).set(3, editado.getSenha());
						lista.get(cont).set(4, editado.getNivel());
					    salvarCentral(lista, "xml/fun.xml");
			}
		}
	}
	
	@Override
	public FuncionariosDTO consultar(int cod) {
		FuncionariosDTO novo = new FuncionariosDTO();
		
		 String id=""+cod;
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
			for(int i=0;i<5;i++) {
					if(id.equals(lista.get(cont).get(0))) {
						novo.setCod(Integer.parseInt(lista.get(cont).get(0)));
						novo.setNome(lista.get(cont).get(1));
						novo.setCargo(lista.get(cont).get(2));
						novo.setSenha(lista.get(cont).get(3));
						novo.setNivel(lista.get(cont).get(4));
					}
			}
		}
		return novo;
	}
	
	@Override
	public int id() {
		int id=0;
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		
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
	public FuncionariosDTO lista() {
		
		FuncionariosDTO fundto = new FuncionariosDTO();
		ArrayList<FuncionariosDTO> listaftdo = new ArrayList<>();
		
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
			    cont++;
				listaftdo.add(consultar(cont));
				cont--;
		}
		fundto.setLista(listaftdo);
		return fundto;
	}


	@Override
	public int tamanho() {
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		return lista.size();
	}

	
}
