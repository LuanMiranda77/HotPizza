package view;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DTO.IngredientesDTO;
import controller.BotoesGeral;
import controller.CampoGeral;
import controller.ControIngre;

import controller.FonteBem;


public class TelaIngred extends Principal{
	private JTextField cod;
	private JTextField nome;
	private JTextField  preco;
	private JTextField  vencimento;
	private JTextField  quant;
	private BotoesGeral botao1;

	

	
	public TelaIngred(boolean band,String tela, int index) {
		setTitle("Cadastro");
		setBounds(400, 50, 500, 650);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField(this);
		botao(band,tela, index);
		jLabels();
		blackgroud();
		setVisible(true);
		repaint();	
	}
	
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("Icon/fundocad.png"));
		contabil.setBounds(1,1,500,650);
		add(contabil);
	}
	
	private void jLabels () {
		JLabel nome  = new JLabel("CADASTRO DE INGREDIENTES");
		nome.setFont(new Font("Times New Roman",Font.ITALIC,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(50, 1, 500, 30);
		add(nome);
		
		JLabel label1  = new FonteBem("Codigo",10 , 40);
		add(label1);
		JLabel label  = new FonteBem("Nome", 10, 100);
		add(label);
		
		JLabel end  =new FonteBem("Quantidade KG", 10, 160);
		add(end);
		
		JLabel end1  =new FonteBem("Preço", 10, 225);
		add(end1);
		
		JLabel label2  =new FonteBem("Vencimento", 10, 285);
		add(label2);
	}
	public void textField(JFrame janela) {
		int cod1=0;
		for(IngredientesDTO e : ControIngre.listaIngre().getLista()) {
			cod1=e.getCod();
		}
		cod1++;
		cod = new CampoGeral("",10, 70, 100, 25);
		cod.setToolTipText("Codigo de Identificação");
		janela.add(cod);
		cod.setEnabled(false);
		cod.setText(""+cod1);
		
	    nome = new CampoGeral("",10, 130, 300, 25);
		nome.setToolTipText("nome");
		janela.add(nome);
		
		MaskFormatter p3;
		try {
			p3 = new MaskFormatter("##.###");
			p3.setValidCharacters("0123456789.");
			quant = new JFormattedTextField(p3);
			quant.setBounds(10, 185, 70, 30);
			add(quant);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "So numeros");
		}
		
		MaskFormatter p;
		try {
			p = new MaskFormatter("##.##");
			p.setValidCharacters("0123456789.");
			preco = new JFormattedTextField(p);
			preco.setBounds(10, 250, 80, 30);
			add(preco);
			
		} catch (ParseException e) {
			
		}
		MaskFormatter p2;
		try {
			p2 = new MaskFormatter("##/##/####");
			p2.setValidCharacters("0123456789.");
		vencimento = new JFormattedTextField(p2);
		vencimento.setBounds(10, 310, 150, 25);
		add(vencimento);
        } catch (ParseException e) {	
	}
	}
		
	private void botao(boolean band1,String  tela,int index) {
		BotoesGeral ed= new BotoesGeral("<html>Editar<html>",new ImageIcon("Icon/editarin.png"),60,400,100,110);
		ed.setVisible(false);
		ed.repaint();
		if(tela.equals("Editar")) {
				 ed.setVisible(true);
				 for(IngredientesDTO e:ControIngre.listaIngre().getLista()) {
				    	if(e.getCod()==index) {
				    		cod.setText(Integer.toString(e.getCod()));
				    		nome.setText(e.getNome());
				    		quant.setText(""+e.getQunat());
				    		preco.setText(""+e.getPreco());
				    		vencimento.setText(e.getVencimewnto());
		    }
		    }
		}
		add(ed);
		ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cod.getText().equals("") || nome.getText().equals("")|| quant.getText().equals("  .  ")||preco.getText().equals("  .  ")|| vencimento.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null, "Campo vazio!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
			
				IngredientesDTO novo = new IngredientesDTO();
				novo.setCod(Integer.parseInt(cod.getText()));
				novo.setNome(nome.getText().toUpperCase());
				novo.setQunat(Float.parseFloat(quant.getText()));
				novo.setPreco(Float.parseFloat(preco.getText()));
				novo.setVencimewnto(vencimento.getText());
				ControIngre.conteditarIngre(novo);
				JOptionPane.showMessageDialog(null, "editado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new CadIngre();
				}
			}
			});
		
		botao1 = new BotoesGeral("<html>Salvar<html>",new ImageIcon("Icon/salin.png"),60,400,100,110);
		botao1.setVisible(false);
		botao1.repaint();
		if(tela.equals("Cadastrar")) {
			 botao1.setVisible(true);
			}
		add(botao1);	
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
						if(cod.getText().equals("") || nome.getText().equals("")|| quant.getText().equals("  .  ")||
								preco.getText().equals("  .  ")||vencimento.getText().equals("  /  /    ")){
							JOptionPane.showMessageDialog(null, "Campo Vazio", "Alerta",JOptionPane.ERROR_MESSAGE);
						}
					else {
						 try {
							 IngredientesDTO novo = new IngredientesDTO();
								novo.setCod(Integer.parseInt(cod.getText()));
								novo.setNome(nome.getText().toUpperCase());
								novo.setQunat(Float.parseFloat(quant.getText()));
								novo.setPreco(Float.parseFloat(preco.getText()));
								novo.setVencimewnto(vencimento.getText());
							ControIngre.controlIngre(novo);
							JOptionPane.showMessageDialog(null, "cadastrado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
							dispose();
							new TelaIngred(false,"Cadastrar",0);
						} catch (ExceptionIngre e1) {
							
						 
					 }
						 	
					}
			}
		});
					
					
		
		JButton botao2 = new BotoesGeral("Cancelar",new ImageIcon("Icon/excluirin.png"),210,400,100,110);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new CadIngre();
			}
		});
		add(botao2);
	
}
	
}
		


