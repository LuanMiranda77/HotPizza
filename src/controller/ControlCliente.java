package controller;

import DTO.ClienteDTO;
import model.Clientes;
import view.ExceptionCliente;
import view.ExceptionClienteN;


public class ControlCliente {
	
	public void contClientes(ClienteDTO novo) throws ExceptionCliente {
	
		Clientes.salvarClientes(novo);
			   
			
		}
	public static void conteditarCliente(ClienteDTO editado){
		
		Clientes.editarClientes(editado);
	}
		
	
	// metodo excluir
	public static void contExcluirCliente(int index){
		
		Clientes.excluirClientes(index);
	
	}
	
	public static ClienteDTO  ConsultarCliente(int cod){
		
		 ClienteDTO c = new ClienteDTO ();
		
		try {
		  c=Clientes.pesClientes(cod);
			
		} catch (ExceptionClienteN e) {
			
		}
		return c;
	
	}
	public static int contIdCliente() {
		
		return Clientes.idCliente();
		
	}
	public static ClienteDTO listaClientes(){
		return Clientes.getlistacli();
	}
	
	
}


