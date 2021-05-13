package view;

import java.awt.Button;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.text.MaskFormatter;

import DTO.IngredientesDTO;
import DTO.PizzasDTO;
import controller.BotoesGeral;
import controller.ControIngre;

import controller.ControlPizzas;
import controller.FontesGeral;
import model.IngBase;
import model.ListaIngred;
import model.montarDecore;


public class TelaCadPizza  extends Principal{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//## atributos
	private JTextField cod;
	private JTextField boxnome;
	private JTextField boxquant;
	private JTextField precof;
	private JComboBox<String>  boxtamanho;
	private JComboBox<String>  ingred;
	private JTextArea preparo;
	private JTextField precot;
	private JTextField precouni;
	private TablePizza tabela;
	private float custo;
	ListaIngred b;
	
	DefaultTableModel modelo;
	 JScrollPane contener;
	 JTable tabelai;
	
	

	
	
	public TelaCadPizza() {
		
		setTitle("Tela de Cadastro Pizza");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 50, 1100, 650);
		botao();
		label();
		campos();
		tabela(this);
		tabelaIgre();
		
		setVisible(true);
		repaint();
	}
	public void campos() {
		
		int cont=0;
		String[]lista= new String[ControIngre.contTamanho()];
		if(ControIngre.contTamanho()>0) {
		for(IngredientesDTO e: ControIngre.listaIngre().getLista()) {
			lista[cont]=e.getNome();
			cont++;
		}
		}
		String[]lista2= {"","P","M","G","GG"};
		
		
		cod =new JTextField();
		cod.setBounds(20,150,80,30);
		cod.setEnabled(false);
		cod.setText(""+ControlPizzas.idPizzas());
		add(cod);
		   
		boxnome =new JTextField();
		boxnome.setBounds(115,150,200,30);
		   add(boxnome);
		
		   
		   preparo =new JTextArea();
		   preparo.setText("");
		   preparo.setLineWrap(true);
		   preparo.setWrapStyleWord(true);
		   preparo.setBounds(420,150,200,150);
			   add(preparo);  
		    
	    MaskFormatter q;
		try {
			q = new MaskFormatter("##");
			boxquant =new JFormattedTextField(q);
			boxquant.setBounds(20,220,50,30);
			boxquant.setEnabled(true);
			add(boxquant);
			
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,"Só digita numero");
		}
		
		
		
		MaskFormatter p;
		try {
			p = new MaskFormatter("#.##");
			p.setValidCharacters("0123456789.");
			precof = new JFormattedTextField(p);
			precof.setBounds(90, 220, 80, 30);
			add(precof);
			
		} catch (ParseException e) {
			
		}
		
		MaskFormatter p1;
		try {
			p1 = new MaskFormatter("##.##");
			p1.setValidCharacters("0123456789.");
			precot = new JFormattedTextField(p1);
			precot.setBounds(200, 220, 80, 30);
			add(precot);
			
		} catch (ParseException e) {
			
		}
		
		
			precouni = new JTextField();
			precouni.setBounds(315, 220, 80, 30);
			add(precouni);
			precouni.repaint();
			precouni.setText(new DecimalFormat("R$ #,##0.00").format(custo));
			
		
	
		
		boxtamanho =new JComboBox<String>(lista2);
		boxtamanho.setBounds(330,150,60,30);
		add(boxtamanho);
		boxtamanho.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String n = (String)boxtamanho.getSelectedItem();
				if(n.equals("P")) {
					boxquant.setText("06");
					preparo.setText("1-DISCO MASSA P -");
					b = new IngBase("P");
					precot.setText(""+b.getPreco());
					   		
				}
				if(n.equals("M")) {
					boxquant.setText("08");
					preparo.setText("1-DISCO MASSA M -");
					b = new IngBase("M");
					precot.setText(""+b.getPreco());
				}
				if(n.equals("G")) {
					boxquant.setText("12");
					preparo.setText("1-DISCO MASSA G -"
					   		);
					b = new IngBase("G");
					precot.setText(""+b.getPreco());
				}
				if(n.equals("GG")) {
					boxquant.setText("14");
					preparo.setText("1-DISCO MASSA GG -"
					   		);
					b = new IngBase("GG");
					precot.setText(""+b.getPreco());
				}
			}
		});
		
		ingred =new JComboBox<String>(lista);
		ingred.setBounds(650,60,200,30);
		add(ingred);
		  
}
	private class Ouvir implements ActionListener{		
		
		public void actionPerformed(ActionEvent event){
			String nome = event.getActionCommand();
		 
			ControlPizzas pizza = new ControlPizzas();	
			
			switch (nome) {	
			
			case"<HTML>Cadastro<HTML>":
				if(boxnome.getText().equals("")||boxquant.getText().equals("  ")||
						precof.getText().equals("  .  ")||precot.getText().equals("  .  ")||preparo.getText().equals("")
						||precot.getText().equals("  .  ")) {
							JOptionPane.showMessageDialog(null, "Campo vazio ", "Aviso", JOptionPane.ERROR_MESSAGE);
						}
				else {
					try {
					int linha1 =tabela.seletctID();
					if(linha1==Integer.parseInt(cod.getText())) {
						PizzasDTO novo = new PizzasDTO();
						novo.setCod(Integer.parseInt(cod.getText()));
						novo.setNome(boxnome.getText().toUpperCase());
						novo.setIngred(b);
						novo.setTamnaho((String)boxtamanho.getSelectedItem());
						novo.setQuantft(Integer.parseInt(boxquant.getText()));
						novo.setValorft(Float.parseFloat(precof.getText()));
						novo.setValorcomp(Float.parseFloat(precot.getText()));
						novo.setPrepara(preparo.getText());
						novo.setCusto(Float.parseFloat(precouni.getText()));
						pizza.conteditarPizza(novo);
						   	JOptionPane.showMessageDialog(null, "Pizza editado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						   	dispose();
							  new TelaCadPizza();
					}
								
					else {
						PizzasDTO novo = new PizzasDTO();
						novo.setCod(Integer.parseInt(cod.getText()));
						novo.setNome(boxnome.getText().toUpperCase());
						novo.setIngred(b);
						novo.setTamnaho((String)boxtamanho.getSelectedItem());
						novo.setQuantft(Integer.parseInt(boxquant.getText()));
						novo.setValorft(Float.parseFloat(precof.getText()));
						novo.setValorcomp(Float.parseFloat(precot.getText()));
						novo.setPrepara(preparo.getText());
						novo.setCusto(Float.parseFloat(precouni.getText()));
						try {
							pizza.contPizza(novo);
						} catch (ExceptionPizza e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						};
					JOptionPane.showMessageDialog(null, "Pizza cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);   
					
					dispose();
					  new TelaCadPizza();
					}
					}catch (NumberFormatException e) {
						  JOptionPane.showMessageDialog(null, "Preço invalido");
					}
				}
				break;
			    
			case"Editar":
				try {
					
					int linha1 =tabela.seletctID();
				   		limparTabela();
				   		cod.setText(""+ControlPizzas.contPesPizza(linha1).getCod());
				   		boxnome.setText(ControlPizzas.contPesPizza(linha1).getNome().toUpperCase());
				   		boxtamanho.setSelectedItem((ControlPizzas.contPesPizza(linha1).getTamnaho()));
				   		boxquant.setText(""+ControlPizzas.contPesPizza(linha1).getQuantft());
				   		precof.setText(""+ControlPizzas.contPesPizza(linha1).getValorft());
				   		precot.setText(""+ControlPizzas.contPesPizza(linha1).getValorcomp());
				   		preparo.setText(ControlPizzas.contPesPizza(linha1).getPrepara().toUpperCase());
				   		precouni.setText(""+ControlPizzas.contPesPizza(linha1).getCusto());
				
				if(linha1<0) {
				JOptionPane.showMessageDialog(null, "Selecione a linha a ser editada", "Aviso", JOptionPane.ERROR_MESSAGE);
				}
				}catch (NullPointerException e) {
							
		
				}
				break;
				
			case"<HTML>Pesquisar<HTML>":

				try {
				     String n1= JOptionPane.showInputDialog(null, "Digite o nome do pra pesquisa", "Pesquisar",
					 JOptionPane.INFORMATION_MESSAGE);
				     n1=n1.toUpperCase();
				     if (tabela.filtroNome(n1)==false) {
				    	 JOptionPane.showMessageDialog(null, "Pizza não cadastrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				     }
				     else
				     tabela.limparTabela();
				     tabela.filtroNome(n1);
				     tabela.repaint();
				     
				}catch(NullPointerException k) {
					
				}
				
				break;
				
				
				
			case"Excluir":
				int linha1 =tabela.seletctID();
				
		    if(linha1<0) {
		    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.ERROR_MESSAGE);	}	
		    else {
		    		tabela.excluirLinha(tabela.selectLinha());
		    		ControlPizzas.contExcluirPizza(linha1);
		    		JOptionPane.showMessageDialog(null, "Item excluido com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		    		dispose();
		    		new TelaCadPizza();
			}
				break;
				
			case"<html>Cadastrar Ingredientes<hmtl>":
				dispose();
				new CadIngre();
			break;
			
			case"ADD Ingredientes":
				
				try{
				
				float q=Float.parseFloat( JOptionPane.showInputDialog(null, "Digite a quantidade do item", "Quantidade",
						JOptionPane.INFORMATION_MESSAGE));
				float q2=q;

						String i = (String)ingred.getSelectedItem();
						
						
						
						for(IngredientesDTO e : ControIngre.listaIngre().getLista() ) {
							if(e.getNome().equals(i)) {
								
							
							    IngredientesDTO novo = new IngredientesDTO();
								// apenas pra exibição na tela 
								novo.setCod(e.getCod());
								novo.setNome(e.getNome());
								novo.setQunat(q);
								novo.setPreco(e.getPreco());
								addlinha(novo);
								
								// pizza decorado por ingrediemtes
								b = new montarDecore(b);
			   					b.setNome(novo.getNome());
			   					b.setQuant(novo.getQunat());
			   					b.setPreco(novo.getPreco());
			   					precot.setText(new DecimalFormat("R$ #,##0.00").format(+b.getPreco()));
			   					preparo.setText(preparo.getText()+e.getNome()+"- QUANT KG("+q+")-");
								q=e.getQunat()-q;
								ControIngre.conteditarIngre(e);
								custo+=q2*b.getPreco();
								precouni.setText(""+custo);
					            precot.setText(""+custo*2);
								float res=Float.parseFloat(boxquant.getText());
								res=Float.parseFloat(precot.getText())/res;
								precof.setText(""+res);
								
							}
						}	
								
				}catch (NullPointerException |NumberFormatException e) {
				}
			break;
			
			case"DEL Ingredientes":
				int index = tabelai.getSelectedRow();
				
				
				float q1 = (float) tabelai.getValueAt(tabelai.getSelectedRow(),2);
				String linha = (String) tabelai.getValueAt(tabelai.getSelectedRow(),1);
				
				for(IngredientesDTO e : ControIngre.listaIngre().getLista()) {
					if(e.getNome().equals(linha)) {
						custo-=q1*e.getPreco();
						q1=q1+e.getQunat();
						ControIngre.conteditarIngre(e);
						precouni.setText(""+custo);
						modelo.removeRow(index);
					}
				}
				
				
			break;
			
			}
		}
	}

	public void botao() {
	
		
		BotoesGeral botcad = new  BotoesGeral("<HTML>Cadastro<HTML>",new ImageIcon("Icon/salvrapi.png"),20,10, 100, 100);
	
		botcad.setToolTipText("Cadastar de bem");
		botcad.addActionListener(new Ouvir());
		BotoesGeral botpesquisa = new  BotoesGeral("<HTML>Pesquisar<HTML>",new ImageIcon("Icon/pespi.png"),115,10,100, 100);
		botpesquisa.addActionListener(new Ouvir());
		BotoesGeral boteditar = new  BotoesGeral("Editar",new ImageIcon("Icon/editarpi.png"),210,10,100, 90);
	
		boteditar.addActionListener(new Ouvir());
		BotoesGeral botexcluir = new  BotoesGeral("Excluir",new ImageIcon("Icon/excluirpi.png"),305,10,100, 100);
		
		botexcluir.addActionListener(new Ouvir());
        BotoesGeral botingre = new  BotoesGeral("<html>Cadastrar Ingredientes<hmtl>",new ImageIcon("Icon/iingre.png"),930,10,120, 140);
        botingre.setFont(new Font("Arial", Font.BOLD, 17));
		botingre.addActionListener(new Ouvir());
		
		
        Button add = new  Button("ADD Ingredientes");
        add.setBounds(650,110,110, 30);
		add.addActionListener(new Ouvir());
		
        Button del = new  Button("DEL Ingredientes");
        del.setBounds(780,110,110, 30);
		del.addActionListener(new Ouvir());
		
		add(del);
		add(add);
		add(botingre);
		add(botexcluir);
		add(botexcluir);
		add(botpesquisa);
		add(botcad);
		add(boteditar);
		
	//botcad.addActionListener(new ActionListener() {
	}
	public void label() {
		add(new FontesGeral("Cod",25,20,70,30));
		add(new FontesGeral("Nome",120,20,100,30));
		add(new FontesGeral("Modo de Preparo",420,20,200,30));
		add(new FontesGeral("Tam",350,20,50,30));
		add(new FontesGeral("Fatias",20,95,80,30));
		add(new FontesGeral("Preço ft",95,95,100,30));
		add(new FontesGeral("Preço comp",200,95,120,30));
		add(new FontesGeral("Preço custo",320,95,120,30));
		add(new FontesGeral("Lista de Pizzas",400,270,300,30));

		JLabel logo = new JLabel("",new ImageIcon("Icones/logopequena.png"),JLabel.LEFT);
		logo.setBounds(800, -20, 260, 200);
		add(logo);
		

	}
	
	public void tabela(JFrame janela) {
		tabela = new TablePizza();
		tabela.adicionarJTable(janela);
		tabela.revalidate();
		janela.add(tabela);
		
	}
	public void tabelaIgre() {


		modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
	    modelo.addColumn("Ingrediente");
	    modelo.addColumn("Quant");
	    modelo.addColumn("Preço");
		
		
		tabelai = new  JTable(modelo);
		tabelai.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelai.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelai.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabelai.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabelai.getColumnModel().getColumn(3).setPreferredWidth(80);
			
		contener = new JScrollPane(tabelai);
		
		
		
		
	
		contener.setBounds(650,150,330,150);
		add(contener);
		tabela.repaint();
		}
	public void addlinha(IngredientesDTO e) {
		modelo.addRow(new Object[] {e.getCod(),
				                    e.getNome(),
                                    e.getQunat(),
                                    e.getPreco(),
                 		         
												});
		
	}
	public void limparTabela() {
		while(tabelai.getModel().getRowCount()>0) {
					modelo.removeRow(0);
				
		}
		}
	public static void main(String[] args) {
		new TelaCadPizza();
	}
}
