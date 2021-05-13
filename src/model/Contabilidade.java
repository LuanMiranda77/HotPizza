package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.ContabilDAOXml;
import DAO.FuncionarioDAOXml;
import DAO.InterContabilDAO;
import DAO.InterFuncionarioDAO;
import DAO.InterPedidoDAO;
import DAO.PedidoDAOXml;
import DTO.ContabilidadeDTO;
import DTO.PedidosDTO;



public class Contabilidade {
	private int cod;
	private float qunat;
	private static float lucro;
	private static String sabor;
	private String data;

	private static float quant=0;
	int q;
	private static String pizzamais;
	private static ArrayList<Pizzas> listapizza = new ArrayList<>();
	public Contabilidade() {

	}


	public Contabilidade(int cod, float qunat, float lucro, String sabor) {
		this.cod=cod;
		this.qunat = qunat;
		this.lucro = lucro;
		this.sabor = sabor;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
		this.cod = cod;
	}

	public float getQunat() {
		return qunat;
	}
	public void setQunat(float qunat) {
		this.qunat = qunat;
	}
	public float getLucro() {
		return lucro;
	}
	public void setLucro(float lucro) {
		Contabilidade.lucro = lucro;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		Contabilidade.sabor = sabor;
	}

	public static void salvarContabil(Contabilidade novo, String[] mes) {
		InterContabilDAO contab = new ContabilDAOXml();

		InterPedidoDAO p = new PedidoDAOXml();



		for(PedidosDTO ped: p.listaP().getListaPedidos()) {
			String[] m= ped.getData().split("/");

			if(m[1].equals(mes[0])) { 
				lucro+= ped.getValor();
				sabor = ped.getPizza();
			
			}
		}

		novo.setLucro(lucro);
		
		contab.salvar(novo.getCod(), novo.getQunat(), lucro, sabor, novo.getData());
	}
	public static void editarContabil(Contabilidade editado) {

		InterContabilDAO contab = new ContabilDAOXml();

		contab.editar(editado.getCod(), editado.getQunat(), editado.getLucro(), editado.getSabor(), editado.getData());

	}

	public static int pesId() {
		InterContabilDAO contab = new ContabilDAOXml();
		return contab.id();

	}

	public static void excluirContab(int index) {

		InterContabilDAO contab = new ContabilDAOXml();
		contab.remove(index);
	}


	public static ContabilidadeDTO pesContabil(int cod){
		InterContabilDAO contab = new ContabilDAOXml();

		return contab.consultar(cod);
	}

	public static int tamnaho() {
		InterContabilDAO contab = new ContabilDAOXml();

		return contab.tamanho();

	}
	public static ContabilidadeDTO getListaContab(){
		
		InterContabilDAO contab = new ContabilDAOXml();

		return contab.lista();
		
	}


}
