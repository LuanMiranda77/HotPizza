package DAOAPostgreSQL;

import DAO.ClienteDAOXml;
import DAO.FuncionarioDAOXml;
import DAO.IncredienteDAOXml;
import DAO.InterClienteDAO;
import DAO.InterFuncionarioDAO;
import DAO.InterIngredienteDAO;
import DAO.InterPedidoDAO;
import DAO.InterPizzaDAO;
import DAO.PedidoDAOXml;
import DAO.PizzaDAOXml;

public class BDarquivo implements FabFactory {

	public InterClienteDAO fabricarClie() {
		return  new ClienteDAOXml();
	}

	public  InterFuncionarioDAO fabricarFun() {
		return new FuncionarioDAOXml();
	}

	@Override
	public InterPizzaDAO fabricarPizza() {
		return new PizzaDAOXml();
	}

	@Override
	public InterPedidoDAO fabricarPed() {
		return new PedidoDAOXml();
	}

	@Override
	public InterIngredienteDAO fabricarIngred() {
		return new IncredienteDAOXml();
	}

}
