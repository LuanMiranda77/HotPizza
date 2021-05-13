package DAOAPostgreSQL;

import DAO.ClienteDAOXml;
import DAO.FuncionarioDAOXml;
import DAO.IncredienteDAOXml;
import DAO.PedidoDAOXml;
import DAO.PizzaDAOXml;
import DAO.ZerarPersistencia;
import DTO.ClienteDTO;
import DTO.FuncionariosDTO;
import DTO.IngredientesDTO;
import DTO.PedidosDTO;
import DTO.PizzasDTO;
import view.ExceptionCliente;
import view.ExceptionFuncionarioCad;
import view.ExceptionIngre;
import view.ExceptionPizza;

public class VerificarXML {
	private static FuncionarioDAOXml listafun = new  FuncionarioDAOXml();
	private static FuncionarioDAOAdapterPostgre fun = new FuncionarioDAOAdapterPostgre();
	
	private static ClienteDAOXml listacli = new  ClienteDAOXml();
	private static ClienteDAOPostgre cli = new ClienteDAOPostgre();
	
	private static PizzaDAOXml listapizza = new  PizzaDAOXml();
	private static PizzaDAOAdapterPostgre pizza = new PizzaDAOAdapterPostgre();
	
	private static PedidoDAOXml listaped = new  PedidoDAOXml();
	private static PedidoDAOAdapterPostgre ped = new PedidoDAOAdapterPostgre();
	
	private static IncredienteDAOXml listaIngred = new  IncredienteDAOXml();
	private static IngredDAOAdapterPostgre ingred = new IngredDAOAdapterPostgre();
	
	public static void verificar() {
		
	if(listafun.tamanho()!=0) {
		for(int cont=2;cont<=listafun.tamanho();cont++) {
			FuncionariosDTO novo =listafun.consultar(cont);
             try {
            		fun.salvar(novo);
			} catch (ExceptionFuncionarioCad e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    ZerarPersistencia.zerar("FUNCIONARIO");
	}    
		
		if(listacli.tamanho()!=0) {
			for(int cont=1;cont<=listacli.tamanho();cont++) {
				ClienteDTO novo =listacli.consultar(cont);
	            		try {
							cli.salvar(novo);
						} catch (ExceptionCliente e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			ZerarPersistencia.zerar("CLIENTE");	
		
		}
		
		if(listapizza.tamanho()!=0) {
			for(int cont=1;cont<=listapizza.tamanho();cont++) {
				PizzasDTO novo =listapizza.consultar(cont);
	       
							try {
								pizza.salvar(novo);
							} catch (ExceptionPizza e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				}
			ZerarPersistencia.zerar("PIZZA");	
		
		}
		
		if(listaped.tamanho()!=0) {
			for(int cont=1;cont<=listaped.tamanho();cont++) {
				PedidosDTO novo =listaped.consultar(cont);
	       
					ped.salvar(novo);
		
				}
			ZerarPersistencia.zerar("PEDIDO");	
		
		}
		
		if(listaIngred.tamanho()!=0) {
			for(int cont=1;cont<=listaIngred.tamanho();cont++) {
				IngredientesDTO novo =listaIngred.consultar(cont);
	       
					try {
						ingred.salvar(novo);
					} catch (ExceptionIngre e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
				}
			ZerarPersistencia.zerar("INGREDIENTES");	
		
		}
		
		
		
		
	}//chave do metodo
	
	
}//chave class