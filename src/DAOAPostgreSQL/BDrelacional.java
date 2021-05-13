package DAOAPostgreSQL;

import DAO.InterClienteDAO;
import DAO.InterFuncionarioDAO;
import DAO.InterIngredienteDAO;
import DAO.InterPedidoDAO;
import DAO.InterPizzaDAO;

public class BDrelacional implements FabFactory {

	public InterClienteDAO fabricarClie() {
		return new ClienteDAOPostgre();
	}

	public  InterFuncionarioDAO fabricarFun() {
		return new FuncionarioDAOAdapterPostgre();
	}

	@Override
	public InterPizzaDAO fabricarPizza() {
		return new PizzaDAOAdapterPostgre();
	}

	@Override
	public InterPedidoDAO fabricarPed() {
		return new PedidoDAOAdapterPostgre();
	}

	@Override
	public InterIngredienteDAO fabricarIngred() {
		return new IngredDAOAdapterPostgre();
	}

}
