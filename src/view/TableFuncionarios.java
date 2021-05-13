package view;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.FuncionariosDTO;
import controller.ControlFuncionarios;
import model.Funcionarios;


public class TableFuncionarios extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tabela = new JTable(modelo);
	int i=0;
	
	public  void TabelaFuncionario(JFrame janela) {
		
		modelo.addColumn("Cod");
		modelo.addColumn("Nome");
		modelo.addColumn("Cargo");
		modelo.addColumn("Nivel");

		JScrollPane contener = new JScrollPane(tabela);
		contener.setBounds(10, 150,660,300);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for(FuncionariosDTO a: ControlFuncionarios.listaFun().getLista()) {
					modelo.addRow(new Object[]{a.getCod(),
							                   a.getNome(),  
							                   a.getCargo(),
							                   a.getNivel()});
		}
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		janela.add(contener);
	}
	public void adicionarNaTabela() {
		for(FuncionariosDTO a: ControlFuncionarios.listaFun().getLista()) {
			modelo.addRow(new Object[]{a.getCod(),
					                   a.getNome(),  
					                   a.getCargo(),
					                   a.getNivel()});
		}
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
		
		for(FuncionariosDTO a:ControlFuncionarios.listaFun().getLista()) {
			if(a.getCod()==(n)) {
				    band=true; 
				    modelo.addRow(new Object[]{a.getCod(),
			                   a.getNome(), 
			                   a.getNivel(), 
			                   a.getCargo()});
		  }
			
		}
		return band;
	}
	
		
	public boolean filtroNome(String n) {
		boolean band=false;

		
		for(FuncionariosDTO a: ControlFuncionarios.listaFun().getLista()) {
			
			if(a.getNome().contains((n))) {
				band=true;
				modelo.addRow(new Object[]{a.getCod(),
		                   a.getNome(), 
		                   a.getNivel(), 
		                   a.getCargo()
		                   });   
			}
		}
		return band;
	}
	
	

}
