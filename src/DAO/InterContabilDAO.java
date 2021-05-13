package DAO;

import java.util.ArrayList;

import DTO.ContabilidadeDTO;


public interface InterContabilDAO {
	
	public void salvar(int cod, float quant, float lucro, String pizza, String data);
		
	
    public void remove(int cod);
    

    public void editar(int cod, float quant, float lucro, String pizza, String data);
    

    public  ContabilidadeDTO consultar(int cod);
   
   
    public  int id();
   
   
    public ContabilidadeDTO lista() ;
   
   
    public void salvarCentral(ArrayList<ArrayList<String>> c, String a);
   
   
    public ArrayList<ArrayList<String>> recuperarCentral(String lista);
    
    public int tamanho();
    	

}
