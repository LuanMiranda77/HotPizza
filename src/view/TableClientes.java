package view;



import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.ClienteDTO;
import controller.ControlCliente;
import model.Clientes;


public class TableClientes extends TablePizza{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	int i;

	public void adicionarTabela(JFrame janela) {
		
		
	    modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
	    modelo.addColumn("Cliente");
		modelo.addColumn("Endereço");
		modelo.addColumn("CPF");
		modelo.addColumn("telefone");
		
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(47);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(190);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		contener = new JScrollPane(tabela);
		
		
		for(ClienteDTO e: ControlCliente.listaClientes().getLista()) {
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getNome(),
					                    e.getEnd(),
					                    e.getCPF(),
					                    e.getTelefone(),
					               
			                             });
			
		}
		contener.setBounds(10, 150,660,300);
		janela.add(contener);
		tabela.repaint();

}
	 public int selectLinha() {
			return tabela.getSelectedRow();	
		   }
	 
	 public int seletctID() {
		 
		 try {
			return (int) tabela.getValueAt(tabela.getSelectedRow(),0);
		 }catch (ArrayIndexOutOfBoundsException e) {
			return -1; 
		}
	 }
		   
		   public void excluirLinha(int linha) {
			    modelo.removeRow(linha);;	
			   }
		   
		   public void limparTabela() {
				while(tabela.getModel().getRowCount()>0) {
							modelo.removeRow(0);
						
				}
				}
	

	
	public boolean filtroCod(int n) {
		boolean band=false;
		
		for(ClienteDTO e:ControlCliente.listaClientes().getLista()) {
			if(e.getCod()==(n)) {
				  band=true; 
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getNome(),
					                    e.getEnd(),
					                    e.getTelefone(),
					                    e.getCPF(),
					               
			                             });
		  }
			
		}
		return band;
	}
	public boolean filtroNome(String n) {
		boolean band=false;
		
		for(ClienteDTO e:ControlCliente.listaClientes().getLista()) {
			if(e.getNome().contains(n)) {
				  band=true; 
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getNome(),
					                    e.getEnd(),
					                    e.getTelefone(),
					                    e.getCPF(),
					               
			                             });
			band=true;
			}
		}
		return band;
	}

}