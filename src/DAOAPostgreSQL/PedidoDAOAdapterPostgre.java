package DAOAPostgreSQL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.InterPedidoDAO;
import DTO.ClienteDTO;
import DTO.PedidosDTO;

import controller.ControlPedidos;



public class PedidoDAOAdapterPostgre  implements InterPedidoDAO{
	
	private Connection con = null;
	PreparedStatement pst = null;
	private PedidosDTO pedido;
	private ClienteDAOPostgre pesquisa = new ClienteDAOPostgre();

	
	public PedidoDAOAdapterPostgre() {
		pedido = new PedidosDTO();
	}


	@Override
	public void salvar(PedidosDTO novo) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO pedido (id, id_cliente, pizza, tamanho, valor, dt, status) VALUES (?,?,?,?,?,?,?)");
			pst.setInt(1, novo.getCod());
			pst.setInt(2, novo.getClientes().getCod());
			pst.setString(3, novo.getPizza());
			pst.setString(4, novo.getTam());
			pst.setFloat(5, novo.getValor());
			pst.setString(6, novo.getData());
			pst.setString(7, novo.getStatus());
			pst.execute();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

	
	}

	@Override
	public void remove(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM pedido WHERE id = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void editar(PedidosDTO editado) {
			
		
	}

	@Override
	public PedidosDTO consultar(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM pedido where id=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				pedido = new PedidosDTO();
				ClienteDTO novo = new ClienteDTO();
				pedido.setCod(rs.getInt("id"));
				
				//consultar cliente
				novo = pesquisa.consultar(rs.getInt("id_cliente"));
				pedido.setClientes(novo);
				pedido.setPizza(rs.getString("pizza"));
				pedido.setTam(rs.getString("tamanho"));
				pedido.setValor(rs.getFloat("valor"));
				pedido.setData(rs.getString("dt"));
				pedido.setStatus(rs.getString("status"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public int id() {
		int id = tamanho();
		return ++id;
	}

	@Override
	public int tamanho() {
		return listaP().getListaPedidos().size();
	}

	@Override
	public void editarStatus(int cod, String status) {
		
		try {
			String sql = "UPDATE pedido SET status=? WHERE id = ?";
			//coneção com bd
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			//edição do estado
			pst.setString(1,status);
	
			pst.setInt(2, cod);
			pst.execute();
			System.out.println("editado com sucesso");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	@Override
	public PedidosDTO listaP() {
			ArrayList<PedidosDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM pedido");
			ResultSet rs= pst.executeQuery();
			
			while(rs.next()) {
				pedido = new PedidosDTO();
				ClienteDTO novo = new ClienteDTO();
				
				
				pedido.setCod(rs.getInt("id"));
				//consultar cliente
				int cod =rs.getInt("id_cliente");
				novo = pesquisa.consultar(cod);
				pedido.setClientes(novo);
				pedido.setPizza(rs.getString("pizza"));
				pedido.setTam(rs.getString("tamanho"));
				pedido.setValor(rs.getFloat("valor"));
				pedido.setData(rs.getString("dt"));
				pedido.setStatus(rs.getString("status"));
				lista.add(pedido);
			}
			pedido.setListaPedidos(lista);
	
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return pedido;
	}

	@Override
	public int quntPed() {
		 int i = 0 , a=0;
			
			for(PedidosDTO e: listaP().getListaPedidos()) {
				if(e.getClientes().getCPF().equals(ControlPedidos.pesPedido().getListaPedidos().get(i).getClientes().getCPF())) {
					       a++; 
					 
					}
		     }
			return a;
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
