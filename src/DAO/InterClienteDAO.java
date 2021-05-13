package DAO;

import java.util.ArrayList;

import DTO.ClienteDTO;
import view.ExceptionCliente;

public interface InterClienteDAO {
	
	public void salvar(ClienteDTO novo) throws ExceptionCliente;
		
	
    public void remove(int cod);
    

    public void editar(ClienteDTO editado);
    

    public  ClienteDTO consultar(int cod);
   
   
    public  int id();
   
   
    public ClienteDTO lista() ;
   
   
    public void salvarCentral(ArrayList<ArrayList<String>> c, String a);
   
   
    public ArrayList<ArrayList<String>> recuperarCentral(String lista);
    
    public int tamanho();
    	

}
