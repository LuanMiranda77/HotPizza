package DAOAPostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

import DAO.ClienteDAOXml;
import DAO.InterClienteDAO;
import DTO.ClienteDTO;
import view.ExceptionCliente;

public class ClienteDAOPostgre implements InterClienteDAO{
	private Connection con = null;
	PreparedStatement pst = null;
	private ClienteDTO cliente;
	
	public ClienteDAOPostgre() {
		cliente = new ClienteDTO();
	}

	@Override
	public void salvar(ClienteDTO novo) throws ExceptionCliente {
		//codigo pra conexao de banco de daos pSostgre
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO cliente (nome,cpf,telefone,endereco) VALUES (?,?,?,?)");
			pst.setString(1, novo.getNome());
			pst.setString(2, novo.getCPF());
			pst.setString(3, novo.getTelefone());
			pst.setString(4, novo.getEnd());
			pst.execute();
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
			
		}catch(Exception e) {
			throw new ExceptionCliente();
		}
	}

	@Override
	public void remove(int cod) {
		//codigo pra conexao de banco de daos postgre
		
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void editar(ClienteDTO editado) {
		try {
			String sql = "UPDATE CLIENTE SET nome=?,cpf=?,telefone=?,endereco=? WHERE id = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(1, editado.getNome());
			pst.setString(2, editado.getCPF());
			pst.setString(3, editado.getTelefone());
			pst.setString(4, editado.getEnd());
			
			pst.setInt(5, editado.getCod());
			pst.execute();
			System.out.println("editado com sucesso");
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		}catch(Exception e) {
			e.getStackTrace();
			
		}
	}

	@Override
	public ClienteDTO consultar(int cod) {
		
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM cliente where id=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				cliente = new ClienteDTO();
				cliente.setCod(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEnd(rs.getString("endereco"));
				cliente.setCPF(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
			}
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
		
	}

	@Override
	public int id() {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM cliente");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				cliente = new ClienteDTO();
				cliente.setCod(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEnd(rs.getString("endereco"));
				cliente.setCPF(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
			}
			//rs.close();
			//ConexaoPostgreSQL.sairConexao(con,pst);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = cliente.getCod();
		return ++id;
	}

	@Override
	public ClienteDTO lista() {
		
		ArrayList<ClienteDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM cliente");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				cliente = new ClienteDTO();
				cliente.setCod(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEnd(rs.getString("endereco"));
				cliente.setCPF(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				lista.add(cliente);
				
			}
			cliente.setLista(lista);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return cliente;
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
