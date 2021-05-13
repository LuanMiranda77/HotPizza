package DAOAPostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFuncionarioDAO;
import DTO.FuncionariosDTO;

import view.ExceptionFuncionarioCad;

public class FuncionarioDAOAdapterPostgre implements InterFuncionarioDAO{
	private Connection con = null;
	PreparedStatement pst = null;
	private FuncionariosDTO func;
	
	public FuncionarioDAOAdapterPostgre() {
		func = new FuncionariosDTO();
	
	}

	@Override
	public void salvar(FuncionariosDTO novo) throws ExceptionFuncionarioCad {
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO FUNCIONARIO (nome,cargo,nivel,senha) VALUES (?,?,?,?)");
			pst.setString(1, novo.getNome());
			pst.setString(2, novo.getCargo());
			pst.setString(3, novo.getNivel());
			pst.setString(4, novo.getSenha());
			pst.execute();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void remove(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM FUNCIONARIO WHERE id = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void editar(FuncionariosDTO editado) {
		try {
			String sql = "UPDATE FUNCIONARIO SET nome=?,cargo=?, nivel=?, senha=? WHERE id = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(1, editado.getNome());
			pst.setString(2, editado.getCargo());
			pst.setString(3, editado.getNivel());
			pst.setString(4, editado.getSenha());
			
			pst.setInt(5, editado.getCod());
			pst.execute();
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		}catch(Exception e) {
			e.getStackTrace();
			
		}
	}

	@Override
	public FuncionariosDTO consultar(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM funcionario where id=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				func = new FuncionariosDTO();
				func.setCod(rs.getInt("id"));
				func.setNome(rs.getString("nome"));
				func.setCargo(rs.getString("cargo"));
				func.setNivel(rs.getString("nivel"));
				func.setSenha(rs.getString("senha"));
			}
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return func;
	}

	@Override
	public int id() {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM funcionario");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				func = new FuncionariosDTO();
				func.setCod(rs.getInt("id"));
				func.setNome(rs.getString("nome"));
				func.setCargo(rs.getString("cargo"));
				func.setNivel(rs.getString("nivel"));
				func.setSenha(rs.getString("senha"));
			}
			
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = func.getCod();
		return ++id;
	}

	@Override
	public FuncionariosDTO lista() {
		ArrayList<FuncionariosDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM funcionario");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				func = new FuncionariosDTO();
				func.setCod(rs.getInt("id"));
				func.setNome(rs.getString("nome"));
				func.setCargo(rs.getString("cargo"));
				func.setNivel(rs.getString("nivel"));
				func.setSenha(rs.getString("senha"));
				lista.add(func);
				
			}
			func.setLista(lista);
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return func;
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
