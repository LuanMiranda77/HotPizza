package DAO;

import java.util.ArrayList;

import DTO.PizzasDTO;
import view.ExceptionPizza;

public interface InterPizzaDAO {
	
	public void salvar(PizzasDTO novo) throws ExceptionPizza;
		
	
    public void remove(int cod);
    

    public void editar(PizzasDTO editado);
    

    public  PizzasDTO consultar(int cod);
   
   
    public  int id();
   
   
    public PizzasDTO lista() ;
   
   
    public void salvarCentral(ArrayList<ArrayList<String>> c, String a);
   
   
    public ArrayList<ArrayList<String>> recuperarCentral(String lista);
    
    
    public int tamanho();
    
    public int tamanholistaingred(int id);
    	

}
