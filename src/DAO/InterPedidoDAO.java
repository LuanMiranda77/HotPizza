package DAO;

import java.util.ArrayList;

import DTO.PedidosDTO;

public interface InterPedidoDAO {
	
	public void salvar(PedidosDTO novo);
		
	
    public void remove(int cod);
    

    public void editar(PedidosDTO novo);
    

    public  PedidosDTO consultar(int cod);
   
   
    public  int id();
   
   
    public PedidosDTO listaP() ;
   
   
    public void salvarCentral(ArrayList<ArrayList<String>> c, String a);
   
   
    public ArrayList<ArrayList<String>> recuperarCentral(String lista);
    
    public int tamanho();
    
    public void editarStatus(int cod, String status);
    
    public int quntPed() ;
    	

}
