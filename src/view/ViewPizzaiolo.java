package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import DTO.PedidosDTO;
import DTO.PizzasDTO;
import controller.BotoesGeral;

import controller.ControlPedidos;
import controller.ControlPizzas;
import model.Pizzas;

public class ViewPizzaiolo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int linha1=0;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private JTextArea preparo;
	private JTextArea ingre;
	
	
	public ViewPizzaiolo() {
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
		
		JLabel barra3 = new JLabel("MODO DE PREPARO:");
		barra3 .setFont(new Font("Times New Roman",Font.BOLD, 20));
		barra3 .setFont(new Font("Times New Roman",Font.ITALIC, 20));
		barra3 .setForeground(Color.WHITE);
		barra3.setBounds(210, -5, 250, 50);
		add(barra3);
		
		JLabel barra4 = new JLabel("INGREDIENTES:");
		barra4 .setFont(new Font("Times New Roman",Font.BOLD, 20));
		barra4 .setFont(new Font("Times New Roman",Font.ITALIC, 20));
		barra4 .setForeground(Color.BLACK);
		barra4.setBounds(480, -5, 250, 50);
		add(barra4);
		
		preparo =new JTextArea();
		preparo.setLineWrap(true);
		preparo.setWrapStyleWord(true);
		preparo.setForeground(Color.WHITE);
		preparo.setBackground(Color.DARK_GRAY);
		preparo.setFont(new Font("Arial",Font.BOLD,12));
	    preparo.setBounds(179,30,250,90);
	    add(preparo);  
	
		JLabel barra1 = new JLabel();
		barra1.setBackground(Color.DARK_GRAY);
		barra1.setOpaque(true);
		barra1.setFont(new Font("Arial",Font.BOLD,15));
		barra1.setBounds(179, 10, 250, 120);
		add(barra1);
		
		ingre =new JTextArea();
		ingre.setLineWrap(true);
		ingre.setWrapStyleWord(true);
		ingre.setForeground(Color.BLACK);
		ingre.setBackground(Color.ORANGE);
		ingre.setFont(new Font("Arial",Font.BOLD,12));
		ingre.setBounds(430,30,250,90);
	    add(ingre);  
		
		JLabel barra2 = new JLabel();
		barra2.setBackground(Color.ORANGE);
		barra2.setOpaque(true);
		barra2.setFont(new Font("Arial",Font.BOLD,15));
		barra2.setBounds(430, 10, 250, 120);
		add(barra2);
		
		
		JLabel barra = new JLabel();
		barra.setOpaque(true);
		barra.setBackground(Color.GRAY);
		barra.setBounds(0, 10, 700, 120);
		add(barra);
		
		
			
			
}
	public void botoes() {
		BotoesGeral cad1 = new BotoesGeral("<html>Atual lista<html>",new ImageIcon("Icon/patual.png"), 90, 10, 100, 110);
		 add(cad1);	
		 cad1.addActionListener(new  ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(linha1<0) {
			    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
			    else {
				int linha = tabela.getSelectedRow();
	
				linha++;
				for(PizzasDTO p: ControlPizzas.contListaPizzas().getLista()) {
					if(p.getCod() == linha) {
						preparo.setText(p.getPrepara());
						ingre.setText(p.getIngred().getNome());
					}
				}
					
				}
			
				
			}
		});
	
	BotoesGeral cad = new BotoesGeral("<html>Concluindo<html>",new ImageIcon("Icon/pizzaiolo.png"), -10, 10, 100, 110);
	 add(cad);
	 cad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int linha = tabela.selectLinha();
				
				
				linha1 =tabela.getSelectedRow();
				int cod = (int) tabela.getValueAt(tabela.getSelectedRow(),0); 
				
				if(linha1<0) {
		    	   JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
		   
				   
		    	else {
		    		boolean band = false;
		    		  int i = 0;
				    		 for(int cont=0;cont<ControlPedidos.pesPedido().getListaPedidos().size();cont++) {
								if(ControlPedidos.retPedido(cod).getClientes().getCPF().equals(ControlPedidos.pesPedido().getListaPedidos().get(cont).getClientes().getCPF())) {
									 i++;
								 }
				    		 }
							if(i>=2) {
									 ControlPedidos.contStatus(cod, "Espera", 0);
									 PedidosDTO ped = ControlPedidos.retPedido(cod);
									 observePed observe = new observePed();
									 observe.update(ped);
									 modelo.removeRow(linha1);
									JOptionPane.showMessageDialog(null, "Pedido atualizado pra *Espera*", "Aviso", JOptionPane.INFORMATION_MESSAGE);
									band=true;
				    			  }
				    	 if(band==false) {
			    		 ControlPedidos.contStatus(cod, "Pronto",0);
						 modelo.removeRow(linha1);
				    	JOptionPane.showMessageDialog(null, "Pedido atualizado pra *Pronto*", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				    	 }
		    	    }
			}
		});
	}
	
	public void tabela() {
		tabela = new JTable();
		modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
		modelo.addColumn("Pizza");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Data");
		modelo.addColumn("Status");
		
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(370);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
	
		
		contener = new JScrollPane(tabela);
	for(PedidosDTO e: ControlPedidos.pesPedido().getListaPedidos()) {
		if(e.getStatus().equals("Em Producao")) {
		modelo.addRow(new Object[] {e.getCod(),
									e.getPizza(),
					                e.getTam(),
				                    e.getData(),
				                    e.getStatus()
		                             });
		
		
	}
	}
	contener.setBounds(10, 150,660,300);
	add(contener);
	tabela.repaint();

}

}
