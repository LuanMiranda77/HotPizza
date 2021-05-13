package DAOAPostgreSQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterPizzaDAO;
import DTO.PizzasDTO;
import view.ExceptionPizza;

public class PizzaDAOAdapterPostgre  implements InterPizzaDAO{
	private Connection con = null;
	PreparedStatement pst = null;
	private PizzasDTO pizza;
	
	public PizzaDAOAdapterPostgre() {
		pizza = new PizzasDTO();
	}

	@Override
	public void salvar(PizzasDTO novo) throws ExceptionPizza {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO pizza (id, nome, ingred, tamanho, Q_fatias, preparo, Preco_fatia, preco_complet, preco_custo ) VALUES (?,?,?,?,?,?,?,?,?)");
			pst.setInt(1, novo.getCod());
			pst.setString(2, novo.getNome());
			pst.setString(3, novo.getIngred().getNome());
			pst.setString(4, novo.getTamnaho());
			pst.setFloat(5, novo.getQuantft());
			pst.setString(6, novo.getPrepara());
			pst.setFloat(7, novo.getValorft());
			pst.setFloat(8, novo.getValorcomp());
			pst.setFloat(9, novo.getCusto());
			pst.execute();
			
			
		}catch(Exception e) {
			throw new ExceptionPizza();
		}
		
	}

	@Override
	public void editar(PizzasDTO novo) {
		try {
			String sql = "UPDATE pizza SET nome=?, ingred=?, tamanho=?, Q_fatias=?, preparo=?, Preco_fatia=?, preco_complet=?, preco_custo=? WHERE id = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(2, novo.getNome());
			pst.setString(3, novo.getIngred().getNome());
			pst.setString(4, novo.getTamnaho());
			pst.setFloat(5, novo.getQuantft());
			pst.setString(6, novo.getPrepara());
			pst.setFloat(7, novo.getValorft());
			pst.setFloat(8, novo.getValorcomp());
			pst.setFloat(9, novo.getCusto());
			
			pst.setInt(1, novo.getCod());
			pst.execute();
			
		}catch(Exception e) {
			e.getStackTrace();
			
		}
		
	}

	@Override
	public PizzasDTO consultar(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM pizza where id=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				
				pizza = new PizzasDTO();
				pizza.setCod(rs.getInt("id"));
				pizza.setNome(rs.getString("nome"));
				pizza.setPizza(rs.getString("ingred"));
				pizza.setTamnaho(rs.getString("tamanho"));
				pizza.setQuantft(rs.getInt("Q_fatias"));
				pizza.setPrepara(rs.getString("preparo"));
				pizza.setValorft(rs.getFloat("Preco_fatia"));
				pizza.setValorcomp(rs.getFloat("preco_complet"));
				pizza.setCusto(rs.getFloat("preco_custo"));
				
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pizza;
	}

	@Override
	public PizzasDTO lista() {
		
		ArrayList<PizzasDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM pizza");
			ResultSet rs= pst.executeQuery();
			
			while(rs.next()) {
				pizza = new PizzasDTO();
				pizza.setCod(rs.getInt("id"));
				pizza.setNome(rs.getString("nome"));
				pizza.setPizza(rs.getString("ingred"));
				pizza.setTamnaho(rs.getString("tamanho"));
				pizza.setQuantft(rs.getInt("Q_fatias"));
				pizza.setPrepara(rs.getString("preparo"));
				pizza.setValorft(rs.getFloat("Preco_fatia"));
				pizza.setValorcomp(rs.getFloat("preco_complet"));
				pizza.setCusto(rs.getFloat("preco_custo"));
				lista.add(pizza);
			}
			pizza.setLista(lista);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return pizza;
	}



	@Override
	public int tamanholistaingred(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM pizza WHERE id = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public int id() {
		int id = tamanho();
		return ++id;
	}

	@Override
	public void salvarCentral(ArrayList<ArrayList<String>> c, String a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ArrayList<String>> recuperarCentral(String lista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int tamanho() {
		return lista().getLista().size();
	}

}
