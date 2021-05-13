package view;

import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.IngredientesDTO;
import controller.ControIngre;
import model.Ingredientes;


public class TableIngredientes  extends TablePizza{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private NumberFormat moeda = NumberFormat.getCurrencyInstance();

	public void adicionarJTable(JFrame janela) {
		
		
	    modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
	    modelo.addColumn("Nome");
		modelo.addColumn("Preço");
		modelo.addColumn("Quant");
		modelo.addColumn("Validade");
		
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		contener = new JScrollPane(tabela);
		
		for(IngredientesDTO e: ControIngre.listaIngre().getLista()) {
		modelo.addRow(new Object[] {e.getCod(),
				                    e.getNome(),
				                    moeda.format( e.getPreco()),
				                    e.getQunat(),
				                    e.getVencimewnto(),		         
		                             });
		}
		contener.setBounds(10, 150,635,300);
		janela.add(contener);
		tabela.repaint();
		
	}
	 public int selectLinha() {
			return tabela.getSelectedRow();	
		   }
	 
	 public int selectID() {
		 
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
	
	public void adicionarLinha(Ingredientes e) {
		modelo.addRow(new Object[] {e.getCod(),
					                e.getNome(),
					                moeda.format( e.getPreco()),
					                e.getQunat(),
					                e.getVencimewnto(),		
					                 });
		
	}
	public boolean filtroNome(String n) {
		   boolean band=false;

			for(IngredientesDTO e: ControIngre.listaIngre().getLista()) {
				if(e.getNome().contains(n)) {
					band=true;
					modelo.addRow(new Object[] {e.getCod(),
			                e.getNome(),
			                
			                moeda.format( e.getPreco()),
			                e.getQunat(),
			                e.getVencimewnto(),		
			                 });
				}
			}
			return band;
		}
}
