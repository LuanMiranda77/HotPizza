package view;

import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.ContabilidadeDTO;
import controller.ControlContabil;
import model.Contabilidade;


public class TableContabil extends JTable{

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
	    modelo.addColumn("Qnt De Pizza vendida");
	    modelo.addColumn("Margem de lucro do mes");
		modelo.addColumn("Sabores mais Vendidos");
		modelo.addColumn("Data");
		
		
	
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);

		
		contener = new JScrollPane(tabela);
		
		for(ContabilidadeDTO e:ControlContabil.listaContab().getLista()) {
	    	modelo.addRow(new Object[] {e.getCod(),
					                    e.getQunat(),
					                    moeda.format(e.getLucro()),				             
					                    e.getSabor(),
					                    e.getData(),
				                    
		                             });
		}
		contener.setBounds(10, 150,660,300);
		janela.add(contener);
		tabela.repaint();
		
	}
	
	   
	   public void excluirLinha(int linha) {
		    modelo.removeRow(linha);;	
		   }
	   public void limparTabela() {
			while(tabela.getModel().getRowCount()>0) {
						modelo.removeRow(0);
					
			}
			}
	   
	   public int selectLinha() {
			return tabela.getSelectedRow();	
		   }

	
	public void adicionarLinha(Contabilidade e) {
		modelo.addRow(new Object[] {e.getCod(),
					                e.getQunat(),
					                moeda.format(e.getLucro()),
					                e.getSabor(),
					                e.getData(),
					                 });
		
	}
   public boolean filtroCod(int n) {
	   boolean band = false;
		
		for(ContabilidadeDTO e: ControlContabil.listaContab().getLista()) {
			if(e.getCod()==n) {
				band=true;
			modelo.addRow(new Object[] {e.getCod(),
										e.getQunat(),
					                    moeda.format(e.getLucro()),
					                    e.getSabor(),
					                    e.getData(),
											                 });
							                 
			}
		}
		return band;
	}
}
   

	
