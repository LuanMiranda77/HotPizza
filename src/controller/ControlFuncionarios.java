package controller;

import DTO.FuncionariosDTO;
import model.Funcionarios;
import view.ExceptionClienteN;
import view.ExceptionFuncionarioCad;

public class ControlFuncionarios {
	
	public static void contFuncionarios(FuncionariosDTO novo) throws ExceptionFuncionarioCad  {
		
		
		Funcionarios.salvarFuncionario(novo);
		
		
		}
	
	public static void conteditarFun(FuncionariosDTO editado){
		
		Funcionarios.editarFuncionarios(editado);

	}
	
	// metodo excluir
	public static void  contExcluirFun(int index){
		Funcionarios.excluirFuncionario(index);
	}	
	
	public FuncionariosDTO  ConsultarFun(int cod){
		
		 FuncionariosDTO c = new FuncionariosDTO ();
		
		try {
		  c=Funcionarios.pesFuncionario(cod);
			
		} catch (ExceptionClienteN e) {
			
		}
		return c;
	
	}
	public static int contIdFun() {
		
		return Funcionarios.idFun();
		
	}
	public static int ContTamanho() {
		return Funcionarios.tamnaho();
		
	}
	
	public static FuncionariosDTO listaFun() {
		return Funcionarios.listaFun();
		
	}
	
	

}
