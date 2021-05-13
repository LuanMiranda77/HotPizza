package view;


import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.text.MaskFormatter;

import DTO.ClienteDTO;
import DTO.PedidosDTO;
import DTO.PizzasDTO;
import controller.BotoesGeral;
import controller.ControlCentral;
import controller.ControlCliente;

import controller.ControlPedidos;
import controller.ControlPizzas;
import controller.FontesGeral;
import controller.SistemaFacadePedido;
import model.Pizzas;



public class VIewPDV  extends Principal{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//## atributos
	private static JTextField cod;
	private JComboBox<String>quant;
	private JTextField desc;
	private JTextField precouni;
	private JTextField  subtotal;
	private float total;
	private  int cont=0;
	private JLabel totalGeral;
	private float q;
	private String pizza="";
	private static int codcli;
	private 	String t = "";
	private SistemaFacadePedido sistema = new SistemaFacadePedido();
	private String tipo="";
	
	
	 DefaultTableModel modelo;
	 JScrollPane contener;
	 JTable tabela;
	
	

	
	
	public VIewPDV() {
		setTitle("Tela de PDV");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 50, 1100, 650);
		campos();
		botao();
		label();
		barra();
		tabelaIgre();
		setVisible(true);
		repaint();
	}
	public void barra() {
		
		
		
		JLabel logo = new JLabel("",new ImageIcon("Icon/logo_pequena.png"),JLabel.LEFT);
		logo.setBounds(900, 10, 260, 200);
		add(logo);
		
		totalGeral = new JLabel();
		totalGeral.setBounds(510, 395, 240, 90);
		totalGeral.setFont(new Font("Times New Roman",Font.BOLD, 45));
		totalGeral.setFont(new Font("Times New Roman",Font.ITALIC, 45));
		totalGeral.setForeground(Color.BLACK);
		add(totalGeral);
		
		JLabel barra5 = new JLabel("R$");
		barra5.setOpaque(true);
		barra5.setBackground(Color.ORANGE);
		barra5.setBounds(450, 395, 240, 90);
		barra5.setFont(new Font("Times New Roman",Font.BOLD, 45));
		barra5.setFont(new Font("Times New Roman",Font.ITALIC, 45));
		barra5.setForeground(Color.BLACK);
		add(barra5);
		
		
		
		JLabel barra = new JLabel();
		barra.setOpaque(true);
		barra.setBackground(Color.GRAY);
		barra.setBounds(0, 5, 700, 90);
		add(barra);
		
		JLabel barra2 = new JLabel();
		barra2.setOpaque(true);
		barra2.setBackground(Color.DARK_GRAY);
		barra2.setBounds(0, 395, 700, 90);
		add(barra2);
		
		
		
		JLabel barra3 = new JLabel();
		barra3.setOpaque(true);
		barra3.setBackground(Color.darkGray);
		barra3.setBounds(700, 5, 395, 200);
		add(barra3);
		
		
		
		JLabel barra4 = new JLabel();
		barra4.setOpaque(true);
		barra4.setBackground(Color.ORANGE);
		barra4.setBounds(700, 200, 395, 420);
		add(barra4);
		
		
	}
	public void campos() {
		String[]lista= {"MEIA","INTEIRA"};
		
		cod =new JTextField();
		cod.setBounds(20,35,150,40);
		add(cod);
		cod.requestFocus();
		cod.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				proximo(e);
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				addPizza(e);
				
			}
		});
		   
		
		
		 
	    
			quant = new JComboBox<String>(lista);
			quant.setBounds(200,35,80,40);
			add(quant);
             quant.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String op= (String) quant.getSelectedItem();
					tipo=op;
					switch (op) {
					
					case "MEIA":
						q=(float) 0.5;
						
						break;

					default:
					    q=1;
						break;
					}
			         
					
				}
			});
			
			
		
		
		
		
		    MaskFormatter p;
			try {
				p = new MaskFormatter("***");
				p.setValidCharacters("0123456789,.");
				desc = new JFormattedTextField(p);
				desc.setBounds(300, 35, 80, 40);
				add(desc);
				desc.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void keyReleased(KeyEvent e) {
						proximo(e);
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			} catch (ParseException e) {
			}
			
		
		
			precouni = new JTextField();
			precouni.setBounds(395, 35, 80, 40);
			add(precouni);
			precouni.repaint();
			precouni.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				public void keyReleased(KeyEvent e) {
					addPizza(e);
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
				
				
			
			subtotal = new JTextField();
			subtotal.setBounds(500, 35, 80, 40);
			add(subtotal);
			subtotal.setEnabled(false);
			subtotal.repaint();
			
		
	
		
			
		  
}
	public static void receb(int c) {
		cod.setText(""+c);
	}
	
	public static void recCliente(int c) {
		codcli=c;
	}
public void botao() {
	
		
		BotoesGeral botcad = new  BotoesGeral("<HTML>Finalizar<HTML>",new ImageIcon("Icon/salvarped.png"),130,500, 100, 100);
	
		botcad.setToolTipText("Finalizar Pedido");
		botcad.addActionListener(new Ouvir());
		
		BotoesGeral botpesquisa = new  BotoesGeral("<HTML>Cancelar<HTML>",new ImageIcon("Icon/canped.png"),350,500,100, 100);
		botpesquisa.addActionListener(new Ouvir());
		
		BotoesGeral pes = new  BotoesGeral("Pesquisar",new ImageIcon("Icon/pesquisaped.png"),250,500,100, 100);
		pes.addActionListener(new Ouvir());
		
		BotoesGeral cli= new  BotoesGeral("<html>Add Cliente<html>",new ImageIcon("Icon/cadcli.png"),30,500,100, 100);
		cli.addActionListener(new Ouvir());
		
		add(cli);
		add(pes);
		add(botpesquisa);
		add(botcad);
		
		
	//botcad.addActionListener(new ActionListener() {
	}
	public void label() {
		
		
		add(new FontesGeral("Cod",20,-90,70,30));
		add(new FontesGeral("Quant",200,-90,100,30));
		add(new FontesGeral("Desconto",300,-90,100,30));
		add(new FontesGeral("Preço",395,-90,50,30));
		add(new FontesGeral("Sub.Total",500,-90,100,30));
		
	    JLabel total = new JLabel("Total---------------------->");
	    total.setBounds(10,390,500,100);
	    total.setFont(new Font("Times New Roman",Font.BOLD, 40));
	    total.setFont(new Font("Times New Roman",Font.ITALIC, 40));
	    total.setForeground(Color.WHITE);
	    add(total);
	    
	    JLabel caixa = new JLabel("Caixa: 01");
	    caixa.setBounds(720,-20,500,100);
	    caixa.setFont(new Font("Times New Roman",Font.BOLD, 30));
	    caixa.setFont(new Font("Times New Roman",Font.ITALIC, 30));
	    caixa.setForeground(Color.WHITE);
	    add(caixa);
	    
	    JLabel at = new JLabel("Atendente: ");
	    at.setBounds(720,50,500,30);
	    at.setFont(new Font("Times New Roman",Font.BOLD, 20));
	    at.setFont(new Font("Times New Roman",Font.ITALIC, 20));
	    at.setForeground(Color.WHITE);
	    add(at);
	    
	    JLabel at2 = new JLabel("");
	    at2.setBounds(720,70,200,50);
	    at2.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at2.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at2.setForeground(Color.WHITE);
	    at2.setText(ControlCentral.userLogado().getNome());
	    add(at2);
	    
	    JLabel at3 = new JLabel("Lista de Comandos:");
	    at3.setBounds(770,220,300,50);
	    at3.setFont(new Font("Times New Roman",Font.BOLD, 32));
	    at3.setFont(new Font("Times New Roman",Font.ITALIC, 32));
	    at3.setForeground(Color.BLACK);
	    add(at3);
	    
	    JLabel at4 = new JLabel("Pesquisar Item--> Tecle F1");
	    at4.setBounds(710,270,300,50);
	    at4.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at4.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at4.setForeground(Color.BLACK);
	    add(at4);
	    
	    JLabel at5 = new JLabel("Adicionar Item--> Tecle Enter");
	    at5.setBounds(710,320,350,50);
	    at5.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at5.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at5.setForeground(Color.BLACK);
	    add(at5);
	    
	    JLabel at6 = new JLabel("Deletar Item--> Tecle Del");
	    at6.setBounds(710,370,300,50);
	    at6.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at6.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at6.setForeground(Color.BLACK);
	    add(at6);
	    
	    JLabel at7 = new JLabel("_____________________________");
	    at7.setBounds(705,390,400,50);
	    at7.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at7.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at7.setForeground(Color.BLACK);
	    add(at7);
	    
	    JLabel at8 = new JLabel("Ultimo Pedido Finalizado:");
	    at8.setBounds(710,430,400,50);
	    at8.setFont(new Font("Times New Roman",Font.BOLD, 28));
	    at8.setFont(new Font("Times New Roman",Font.ITALIC, 28));
	    at8.setForeground(Color.BLACK);
	    add(at8);
	    
	    JLabel ped0 = new JLabel("");
	    ped0.setBounds(710,470,400,50);
	    ped0.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    ped0.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    ped0.setForeground(Color.BLACK);
	    
	    JLabel ped = new JLabel("");
	    ped.setBounds(710,505,400,50);
	    ped.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    ped.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    ped.setForeground(Color.BLACK);
	    
	    JLabel ped1 = new JLabel("");
	    ped1.setBounds(710,535,400,50);
	    ped1.setFont(new Font("Times New Roman",Font.BOLD, 18));
	    ped1.setFont(new Font("Times New Roman",Font.ITALIC, 18));
	    ped1.setForeground(Color.BLACK);
	    
	    JLabel ped2 = new JLabel("");
	    ped2.setBounds(710,565,400,50);
	    ped2.setFont(new Font("Times New Roman",Font.BOLD, 20));
	    ped2.setFont(new Font("Times New Roman",Font.ITALIC, 20));
	    ped2.setForeground(Color.BLACK);
	    
	    for(PedidosDTO p : ControlPedidos.pesPedido().getListaPedidos()) {
	    	 ped0.setText("Pedido:  Nº-"+p.getCod());
	    	 ped.setText("Cliente:"+p.getClientes().getNome());
	    	 ped1.setText("Pizza:"+p.getPizza());
	    	 ped2.setText("Valor:"+p.getValor()+" - "+ "Status: "+p.getStatus());
	    }
	    add(ped0);
	    add(ped);
	    add(ped1);
	    add(ped2);
	    

	
}
	
public void proximo(java.awt.event.KeyEvent even) {
	      
		
		if(even.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER || even.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {
			PizzasDTO pizza1 = sistema.buscarPizza(Integer.parseInt(cod.getText()));
			if(cont==0) {
			   try {
				int c=Integer.parseInt(cod.getText());
				
				for(PizzasDTO p :  ControlPizzas.contListaPizzas().getLista()) {
					if(p.getCod() == c)  {
						precouni.setText(String.valueOf(p.getValorcomp()));
						quant.requestFocus();
						cont=1;
					}
				}
			   }catch (NumberFormatException e) {
				   JOptionPane.showMessageDialog(null, "Pizza não cadastrada","Aviso",JOptionPane.ERROR_MESSAGE);
					cod.requestFocus();
			}
			}
			else if(cont==1) {
				desc.requestFocus();
				cont=2;
			    
			}
			else if(cont==2) {
				if(!desc.getText().equals("0.00")) {
					 float t=Float.parseFloat(precouni.getText());
					  float t1=Float.parseFloat(desc.getText());
					  t=t-t1;
					  precouni.setText(String.valueOf(t));
				}
				precouni.requestFocus();
                 float t1 = ControlPedidos.calPrecoPizza(pizza1, tipo);
			     subtotal.setText(String.valueOf(t1));
			     cont=0;      
			}
		}
		}
	public void addPizza(java.awt.event.KeyEvent even) {
		
		if(even.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			try {
					PizzasDTO p = sistema.buscarPizza(Integer.parseInt(cod.getText()));
							p.setQuantvend(q);
							p.setValorcomp(Float.parseFloat(precouni.getText()));
							t=p.getTamnaho();
							addlinha(p);
							pizza+=p.getNome()+" - ";
							total+=Float.parseFloat(subtotal.getText());
							totalGeral.setText(String.valueOf(total));
							
							//limpar campo
							cod.setText(null);
							desc.setText(null);
							precouni.setText(null);
							subtotal.setText(null);
							cod.requestFocus();
			} catch (NumberFormatException e) {
			}
					
					
		}
		else if(even.getKeyCode() == java.awt.event.KeyEvent.VK_F1) {
		    new ViewEstoque();
	}
		else if(even.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
				   int linha = tabela.getSelectedRow();
				   float q1 = (float) tabela.getValueAt(tabela.getSelectedRow(),6);	
				   total-=q1;
				   totalGeral.setText(""+total);
				   modelo.removeRow(linha);
				
		        
	}	
		
		
	}
	public void tabelaIgre() {



			modelo = new DefaultTableModel();
			 modelo.addColumn("Item");
		    modelo.addColumn("Cod");
		    modelo.addColumn("Pizza");
		    modelo.addColumn("Medida");
		    modelo.addColumn("Tipo");
		    modelo.addColumn("Preço");
		    modelo.addColumn("Total");
			
			
			tabela = new  JTable(modelo);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(300);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(60);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(60);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(107);
				
			contener = new JScrollPane(tabela);
			
			
			
			
		
			contener.setBounds(0,95,700,300);
			add(contener);
			tabela.repaint();
			
			tabela.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				public void keyReleased(KeyEvent e) {
					
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					addPizza(e);
					
				}
			});
			}
	public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
					modelo.removeRow(0);
				
		}
		}
	int item =0;
	public void addlinha(PizzasDTO e) {
		NumberFormat moeda = NumberFormat.getCurrencyInstance();
		item++;
		modelo.addRow(new Object[] {item,
				                     e.getCod(),
                                    e.getNome(),
                                    e.getTamnaho(),
                                    tipo,
                                    moeda.format(Float.parseFloat(precouni.getText())),
                                    Float.parseFloat(subtotal.getText()),
												});
		
	}
	
	private class Ouvir implements ActionListener{		
		
		public void actionPerformed(ActionEvent event) {
			String nome = event.getActionCommand();
		 
			//ControlCliente cliente = new ControlCliente();
			
			switch (nome) {	
		
			
			
			case"<HTML>Finalizar<HTML>":
				//serador de codigo de pedido
				//int cod1=;
				
				if(codcli>0) {
			try {
			
				ClienteDTO p=sistema.buscaCLiente((codcli));
				PedidosDTO novo = new PedidosDTO();
				novo.setCod(ControlPedidos.id());
				novo.setClientes(p);
				novo.setPizza(pizza);
				novo.setValor(total);
				novo.setData(sistema.dataPed());
				novo.setTam(t);
				novo.setStatus("Em Producao");
				ControlPedidos.contSalvarPedido(novo);
				JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);   
				dispose();
				new VIewPDV();
				
				} 
				catch (NumberFormatException | NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Cliente nao cadastardo !","Aviso", JOptionPane.ERROR_MESSAGE);
					  
				}
				}
				else
					JOptionPane.showMessageDialog(null, "Escolha o Cliente pra gerar o pedido !","Aviso", JOptionPane.ERROR_MESSAGE);
			
			
				break;
				
			case"Pesquisar":
				new ViewlPedido();
				
				break;
			    

				
			case"<HTML>Cancelar<HTML>":
				dispose();
				break;
				
			case"<html>Add Cliente<html>":
				new ViewlClientes(nome);
				break;	
				
			}
		}
		
	
	}
}
