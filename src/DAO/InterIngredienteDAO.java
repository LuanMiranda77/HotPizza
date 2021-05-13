package DAO;

import java.util.ArrayList;

import DTO.IngredientesDTO;
import view.ExceptionIngre;

public interface InterIngredienteDAO {
	
	public void salvar(IngredientesDTO novo) throws ExceptionIngre;
		
	
    public void remove(int cod);
    

    public void editar(IngredientesDTO novo);
    

    public  IngredientesDTO consultar(int cod);
   
   
    public  int id();
   
   
    public IngredientesDTO lista() ;
   
   
    public void salvarCentral(ArrayList<ArrayList<String>> c, String a);
   
   
    public ArrayList<ArrayList<String>> recuperarCentral(String lista);
    
    
    public int tamanho();
    	

}
