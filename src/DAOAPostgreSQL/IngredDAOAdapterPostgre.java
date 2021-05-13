package DAOAPostgreSQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.InterIngredienteDAO;
import DTO.IngredientesDTO;
import view.ExceptionIngre;


public class IngredDAOAdapterPostgre implements InterIngredienteDAO{
	
	private Connection con = null;
	PreparedStatement pst = null;
	private IngredientesDTO ingred;
	
	public IngredDAOAdapterPostgre() {
		ingred = new IngredientesDTO();
	}

	@Override
	public void salvar(IngredientesDTO novo) throws ExceptionIngre {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO ingredientes (id,nome,quant, preco, vencimento) VALUES (?,?,?,?,?)");
			pst.setInt(1, novo.getCod());
			pst.setString(2, novo.getNome());
			pst.setFloat(3, novo.getQunat());
			pst.setFloat(4, novo.getPreco());
			pst.setString(5, novo.getVencimewnto());
			pst.execute();
			
			
		}catch(Exception e) {
			throw new ExceptionIngre();
		}
	}

	@Override
	public void remove(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM ingredientes WHERE id = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void editar(IngredientesDTO editado) {
		try {
			String sql = "UPDATE ingredientes SET nome=?,quant=?, preco=?, vencimento=? WHERE id = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(2, editado.getNome());
			pst.setFloat(3, editado.getQunat());
			pst.setFloat(4, editado.getPreco());
			pst.setString(5, editado.getVencimewnto());
			
			pst.setInt(1, editado.getCod());
			pst.execute();
			System.out.println("editado com sucesso");
			
		}catch(Exception e) {
			e.getStackTrace();
			
		}
	}

	@Override
	public IngredientesDTO consultar(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM ingredientes where id=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				ingred = new IngredientesDTO();
				ingred.setCod(rs.getInt("id"));
				ingred.setNome(rs.getString("nome"));
				ingred.setQunat(rs.getFloat("quant"));
				ingred.setPreco(rs.getFloat("preco"));
				ingred.setVencimewnto(rs.getString("vencimento"));
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingred;
	}

	@Override
	public int id() {
		int id = tamanho();
		return ++id;
	}

	@Override
	public IngredientesDTO lista() {
		
		ArrayList<IngredientesDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM ingredientes");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				ingred = new IngredientesDTO();
				ingred.setCod(rs.getInt("id"));
				ingred.setNome(rs.getString("nome"));
				ingred.setQunat(rs.getFloat("quant"));
				ingred.setPreco(rs.getFloat("preco"));
				ingred.setVencimewnto(rs.getString("vencimento"));
				lista.add(ingred);
				
			}
			ingred.setLista(lista);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return ingred;
	}

	@Override
	public int tamanho() {
		return lista().getLista().size();
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

	
}
