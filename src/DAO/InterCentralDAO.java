package DAO;


import java.util.ArrayList;

import DTO.FuncionariosDTO;
import model.Empresa;

public interface InterCentralDAO {
	
	public void salvarFun(FuncionariosDTO logado);

    public void salvarEmpresa(Empresa novo);
    
    public FuncionariosDTO retornarFun() ;
    
    public Empresa retornaEmp() ;
    
    public void salvarCentral(ArrayList<ArrayList<String>> lista);
    
    public ArrayList<ArrayList<String>> recuperarCentral();

}
