package DAOAPostgreSQL;

import DAO.InterClienteDAO;
import DAO.InterFuncionarioDAO;
import DAO.InterIngredienteDAO;
import DAO.InterPedidoDAO;
import DAO.InterPizzaDAO;

public interface FabFactory {
	
	public InterClienteDAO fabricarClie();
	
	public InterFuncionarioDAO fabricarFun();
	
	public InterPizzaDAO fabricarPizza();
	
	public InterPedidoDAO fabricarPed();
	
	public InterIngredienteDAO fabricarIngred();
	
}
