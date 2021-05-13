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
import model.Empresa;
import model.Funcionarios;


public class CentralDAOXml implements InterCentralDAO {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>> lista=new ArrayList<>();
	 
	 
	    public ArrayList<ArrayList<String>> getListaClientes() {
			return lista;
		}
		

		public void addClientes(ArrayList<ArrayList<String>> novo) {
			lista.addAll(novo);
		 }
		

		public void salvarCentral(ArrayList<ArrayList<String>> c) {


		try {
			String xml = xstream.toXML(c);

			File arquivo = new File("xml/central.xml");
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
	public ArrayList<ArrayList<String>> recuperarCentral() {
		File arquivo = new File("xml/central.xml");

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
		public void salvarFun(FuncionariosDTO logado) {
			ArrayList<String> logador = new ArrayList<>();
			logador.add(logado.getNome());
			logador.add(logado.getCargo());
			
			lista.add(logador);
			salvarCentral(lista);
		}


		@Override
		public void salvarEmpresa(Empresa novo) {
			
		}


		@Override
		public FuncionariosDTO retornarFun() {
			ArrayList<ArrayList<String>> lista = recuperarCentral();
			FuncionariosDTO novo = new FuncionariosDTO();
			novo.setNome(lista.get(0).get(0));
			novo.setCargo(lista.get(0).get(1));
			return novo;
		}


		@Override
		public Empresa retornaEmp() {
			Empresa novo = new Empresa();
			return novo;
		}


}
