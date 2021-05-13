package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import DTO.PedidosDTO;
import controller.BotoesGeral;
import controller.ControlPedidos;
import model.Pedidos;

public class TelaEntregas extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private NumberFormat moeda = NumberFormat.getCurrencyInstance();
	private JTextArea ingre;
	
    public TelaEntregas() {
		
		setTitle("Lista de funcionarios");
		setBounds(400, 50, 700, 500);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    tabela();
		botoes();
		barra();
		setVisible(true);
		repaint();
		
	}
	
	public void barra() {
		
		
		
		JLabel barra4 = new JLabel("Ultimas Entregas:");
		barra4 .setFont(new Font("Times New Roman",Font.BOLD, 20));
		barra4 .setFont(new Font("Times New Roman",Font.ITALIC, 20));
		barra4 .setForeground(Color.BLACK);
		barra4.setBounds(480, -5, 250, 50);
		add(barra4);

		
		ingre =new JTextArea();
		ingre.setLineWrap(true);
		ingre.setWrapStyleWord(true);
		ingre.setForeground(Color.BLACK);
		ingre.setBackground(Color.ORANGE);
		ingre.setFont(new Font("Arial",Font.BOLD,12));
		ingre.setBounds(430,30,250,90);
	    add(ingre);  
	    
	    for(PedidosDTO p: ControlPedidos.pesPedido().getListaPedidos()) {
	    	 ingre.setText("Pedido: "+p.getCod()+" -"+p.getClientes().getNome()+" -"+p.getClientes().getEnd()+" -"+p.getStatus());
	    }
		
		JLabel barra2 = new JLabel();
		barra2.setBackground(Color.ORANGE);
		barra2.setOpaque(true);
		barra2.setFont(new Font("Arial",Font.BOLD,15));
		barra2.setBounds(400, 10, 300, 120);
		add(barra2);
		
		
		JLabel barra = new JLabel();
		barra.setOpaque(true);
		barra.setBackground(Color.GRAY);
		barra.setBounds(0, 10, 700, 120);
		add(barra);
		
		
			
			
}
	public void botoes() {
		
		BotoesGeral cad = new BotoesGeral("<html>Saio pra entrega<html>",new ImageIcon("Icon/entregaatual.png"), 10, 10, 100, 110);
		 add(cad);
		 cad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				        int linha =tabela.getSelectedRow();
			
				  
				    
				    if(linha<0) {
				    	JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Aviso", JOptionPane.ERROR_MESSAGE);	}	
				    
				    else {
				    	    int cod = (int) tabela.getValueAt(tabela.getSelectedRow(),0);
				    		ControlPedidos.contStatus(cod, "Saio pra Entrega",0);
				    	    JOptionPane.showMessageDialog(null, "Pedido atualizado *Saio pra Entrega*", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				    	    dispose();
			    		    new TelaEntregas();
				    }
			  }
			   
			    
			});
		 
		 
		 BotoesGeral cad1 = new BotoesGeral("<html>Entrega finalizada<html>",new ImageIcon("Icon/entregaf.png"), 140, 10, 100, 110);
		 add(cad1);
		 cad1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int linha =tabela.getSelectedRow();
					
				
				    if(linha<0) {
			    	JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Aviso", JOptionPane.ERROR_MESSAGE);	}
					
					
			         else {
			        	    
			        	    int cod = (int) tabela.getValueAt(tabela.getSelectedRow(),0);
						    ControlPedidos.contStatus(cod, "Entrega Finalizada",0);
						    JOptionPane.showMessageDialog(null, "Pedido atualizado *Entrega concluida*", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						    modelo.removeRow(linha);
						    			
						   
				}
					
				}
				});
		 
		 BotoesGeral ped = new BotoesGeral("<html>Ped Nao Recebido<html>",new ImageIcon("Icon/entregaex.png"), 250, 10, 100, 110);
		 add(ped);
		 ped.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int linha =tabela.getSelectedRow();
					
					
						
					if(linha<0) {
						JOptionPane.showMessageDialog(null, "Click em um pedido", "Aviso", JOptionPane.ERROR_MESSAGE);
						
					}
					else {
						int cod = (int) tabela.getValueAt(tabela.getSelectedRow(),0);
				    for(PedidosDTO p : ControlPedidos.pesPedido().getListaPedidos()) {
				    	if(p.getCod()==cod){
				    		    ControlPedidos.contStatus(cod, "Entrga nao realizada",0);
				    			JOptionPane.showMessageDialog(null, "Pedido atualizado *Entrga nao realizada*", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				    			dispose();
				    		    new TelaEntregas();
				    		 	
				    		}
				    	}
				    }
					}
				    		
			 
		 });
		}
		 
			
			public void tabela() {
				
				tabela = new JTable();
				modelo = new DefaultTableModel();
			    modelo.addColumn("Cod");
			    modelo.addColumn("Cliente");
				modelo.addColumn("Endereço");
				modelo.addColumn("CPF");
				modelo.addColumn("Pizza");
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
				tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
				
				contener = new JScrollPane(tabela);
				int a=0;;
				for(int cont=0;cont<ControlPedidos.pesPedido().getListaPedidos().size();cont++) {	
		            	if(ControlPedidos.pesPedido().getListaPedidos().get(cont).getStatus().equals("Espera")) {
		            		       a++;
		            		      // System.out.println(a);
		            	}
				   }
				 if(a==ControlPedidos.qntPedidos()) {
					 for(int cont=0;cont<ControlPedidos.pesPedido().getListaPedidos().size();cont++) {	
			            	if(ControlPedidos.pesPedido().getListaPedidos().get(cont).getStatus().equals("Espera")) {
			            		       cont++;
			            		       ControlPedidos.contStatus(cont, "Pronto", 0);
			            		       cont--;
			            	}
			            	
					
				}
				}
            
					
				boolean band1 = false;
			for(PedidosDTO e:ControlPedidos.pesPedido().getListaPedidos()) {
				  
				if(e.getStatus().equals("Pronto")||e.getStatus().equals("Saio pra Entrega")||e.getStatus().equals("Entrga nao realizada")) {
					   band1=true;
				
				if(!e.getStatus().equals("Espera") && band1==true) {		
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
				
			}
			
			
			contener.setBounds(10, 150,660,300);
			add(contener);
			tabela.repaint();
				
		
		}

}
