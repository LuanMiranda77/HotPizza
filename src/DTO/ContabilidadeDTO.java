package DTO;

import java.util.ArrayList;






public class ContabilidadeDTO {
	private int cod;
	private float qunat;
	private static float lucro;
	private static String sabor;
	private String data;
	private static float quant=0;
	private static String pizzamais;
	private ArrayList<ContabilidadeDTO> lista = new ArrayList<>();
	
	private static ArrayList<ArrayList<String>> listapizza = new ArrayList<>();
	
	
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
		ContabilidadeDTO.lucro = lucro;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		ContabilidadeDTO.sabor = sabor;
	}


	public ArrayList<ContabilidadeDTO> getLista() {
		return lista;
	}


	public void setLista(ArrayList<ContabilidadeDTO> lista) {
		this.lista = lista;
	}
	
}
