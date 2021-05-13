package view;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DTO.PizzasDTO;
import controller.ControlPizzas;
import model.Pizzas;

public class TablePizza extends JTable{

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
		modelo.addColumn("Ingredientes");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Q.Fatia");
		modelo.addColumn("Modo de preparar");
		modelo.addColumn("Preço fatia");
		modelo.addColumn("Preço comp");
		modelo.addColumn("Custo");
	
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(80);
		
		contener = new JScrollPane(tabela);
		
		
	
		
		for(PizzasDTO e:ControlPizzas.contListaPizzas().getLista()) {
		modelo.addRow(new Object[] {e.getCod(),
				                    e.getNome(),
				                    e.getPizza(),
				                    e.getTamnaho(),
				                    e.getQuantft(),
				                    e.getPrepara(),			         
				                    moeda.format(e.getValorft()),
				                    moeda.format(e.getValorcomp()),
				                    moeda.format(e.getCusto())
		                             });
		}
		contener.setBounds(10,400 ,1080,200);
		janela.add(contener);
		tabela.repaint();
		
	}
	public void ajuste(JFrame janela){
		contener.setBounds(10, 150,660,300);
		janela.add(contener);
		tabela.repaint();
	}
	public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
					modelo.removeRow(0);
				
		}
		}
	
	public void adicionarLinha(PizzasDTO e) {
		modelo.addRow(new Object[] {e.getCod(),
					                e.getNome(),
					                e.getPizza(),
					                e.getTamnaho(),
					                e.getQuantft(),
					                e.getPrepara(),			         
					                moeda.format(e.getValorft()),
					                moeda.format(e.getValorcomp()),
					                moeda.format(e.getCusto())
										                 });
		
	}
	
	
   public boolean filtroNome(String n) {
	   boolean band=false;
	 
		
		for(PizzasDTO e:ControlPizzas.contListaPizzas().getLista()) {
			if(e.getNome().contains(n)) {
				band=true;
								modelo.addRow(new Object[] {e.getCod(),
					                    e.getNome(),
					                    e.getIngred().getNome(),
					                    e.getTamnaho(),
					                    e.getQuantft(),
					                    e.getPrepara(),			         
					                    moeda.format(e.getValorft()),
					                    moeda.format(e.getValorcomp()),
					                    moeda.format(e.getCusto())
											                 });
			}
		}
		return band;
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
   public boolean filtroCod(int n) {
	   boolean band=false;
			
		for(PizzasDTO e:ControlPizzas.contListaPizzas().getLista()) {
			if(e.getCod()<=n) {
				band=true;
								modelo.addRow(new Object[] {e.getCod(),
										e.getCod(),
					                    e.getNome(),
					                    e.getIngred().getNome(),
					                    e.getTamnaho(),
					                    e.getQuantft(),
					                    e.getPrepara(),			         
					                    moeda.format(e.getValorft()),
					                    moeda.format(e.getValorcomp()),
					                    moeda.format(e.getCusto())
											                 });					                 
			}
		}
		return band;
	}
}
   

	
