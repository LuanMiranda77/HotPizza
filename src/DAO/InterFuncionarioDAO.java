package DAO;

import java.util.ArrayList;

import DTO.FuncionariosDTO;
import view.ExceptionFuncionarioCad;

public interface InterFuncionarioDAO {
	
	public void salvar(FuncionariosDTO novo) throws ExceptionFuncionarioCad;
		
	
    public void remove(int cod);
    

    public void editar(FuncionariosDTO editado);
    

    public  FuncionariosDTO consultar(int cod);
   
   
    public  int id();
   
   
    public FuncionariosDTO lista() ;
   
   
    public void salvarCentral(ArrayList<ArrayList<String>> c, String a);
   
   
    public ArrayList<ArrayList<String>> recuperarCentral(String lista);
    
    
    public int tamanho();
    	

}
