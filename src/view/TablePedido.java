package view;

import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.PedidosDTO;
import controller.ControlPedidos;
import model.Pedidos;


public class TablePedido extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private NumberFormat moeda = NumberFormat.getCurrencyInstance();

	public void adicionarTabela(JFrame janela, String cargo) {


		if(cargo.equals("Atendente")||cargo.equals("Gerente")){
			modelo = new DefaultTableModel();
		    modelo.addColumn("Cod");
		    modelo.addColumn("Cliente");
			modelo.addColumn("Endereço");
			modelo.addColumn("CPF");
			modelo.addColumn("Pizza");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Valor");
			modelo.addColumn("Data");
			modelo.addColumn("Status");
			
			tabela = new  JTable(modelo);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(100);
			
			contener = new JScrollPane(tabela);
			
		for(PedidosDTO e:ControlPedidos.pesPedido().getListaPedidos()) {
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getClientes().getNome(),
					                    e.getClientes().getEnd(),
					                    e.getClientes().getCPF(),
					                    e.getPizza(),
					                    e.getTam(),
					                    moeda.format(e.getValor()),
					                    e.getData(),
					                    e.getStatus()
			                             });
	
		}
		contener.setBounds(10, 150,660,300);
		janela.add(contener);
		tabela.repaint();
		
		}
	 }
	 public int selectLinha() {
			return tabela.getSelectedRow();	
		   }
		   
		   public void excluirLinha(int linha) {
			    modelo.removeRow(linha);;	
			   }
		public void adicionarLinha(PedidosDTO e) {
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getClientes().getNome(),
					                    e.getClientes().getEnd(),
					                    e.getClientes().getCPF(),
					                    e.getPizza(),
					                    e.getTam(),
					                    moeda.format(e.getValor()),
					                    e.getData(),
					                    e.getStatus()
					                     });
			
		}
		public int seletctID() {
		
		return (int) tabela.getValueAt(selectLinha(),0);
			
	 }
		
		public boolean filtroCod(int n) {
			boolean band=false;
			
			
			for(PedidosDTO e: ControlPedidos.pesPedido().getListaPedidos()) {
				if(e.getCod()==(n)) {
					    band=true; 
						modelo.addRow(new Object[] {e.getCod(),
								                    e.getClientes().getNome(),
								                    e.getClientes().getEnd(),
								                    e.getClientes().getCPF(),
								                    e.getPizza(),
								                    e.getTam(),
								                    moeda.format(e.getValor()),
								                    e.getData(),
								                    e.getStatus()
						                             });
									              
			  }
				
			}
			return band;
		}
	
		public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
			modelo.removeRow(0);
		}
		

}
		public boolean filtroNome(String n) {
			   boolean band=false;
				for(PedidosDTO e: ControlPedidos.pesPedido().getListaPedidos()) {
					if(e.getClientes().getNome().contains(n)) {
						band=true;
										modelo.addRow(new Object[] {e.getCod(),
							                    e.getClientes().getNome(),
							                    e.getClientes().getEnd(),
							                    e.getClientes().getCPF(),
							                    e.getPizza().toString(),
							                    moeda.format(e.getValor()),
							                    e.getData(),
							                    e.getStatus()
													                 });
					}
				}
				return band;
			}
}	
