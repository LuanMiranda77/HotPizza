package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFormattedTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DTO.ContabilidadeDTO;
import controller.BotoesGeral;
import controller.CampoGeral;
import controller.ControlContabil;

import controller.FonteBem;

import model.Contabilidade;


public class ViewRelatorio extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField quant;
	private JTextField lucro;
	private JTextField pizza;
	private JTextField data;
	private int cod1;
	
	
	public ViewRelatorio() {
		
	}
	
	public ViewRelatorio(int index) {
		setTitle("Cadastro");
		setBounds(400, 50, 500, 650);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField(index);
		botao(index);
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
		JLabel nome  = new JLabel("CADASTRO DE RELÁTORIO");
		nome.setFont(new Font("Times New Roman",Font.ITALIC,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(50, 1, 500, 30);
		add(nome);
		
		JLabel label1  = new FonteBem("Q de pizza vendida:",10 , 70);
		add(label1);
		JLabel label  = new FonteBem("Lucro do mes:", 10, 140);
		add(label);
		
		JLabel end  =new FonteBem("Pizza mais vendidas", 10,210);
		add(end);
		
		JLabel label2  =new FonteBem("Data do relátorio", 10, 280);
		add(label2);
		
		
	}
	public void textField(int index) {
		quant = new CampoGeral("",10, 100, 70, 25);
		quant.setToolTipText("quantidade de pizza vendida");
		add(quant);
		
		
	    lucro = new CampoGeral("",10, 170, 100, 25);
		lucro.setToolTipText("lucro do mes");
		add(lucro);
		
		pizza = new CampoGeral("",10, 240, 250, 25);
		pizza.setToolTipText("pizza mais vendida");
		add(pizza);
		
		MaskFormatter cod;
		try {
			cod = new MaskFormatter("**/**/****");
			cod.setValidCharacters("0123456789.");
			data =new JFormattedTextField(cod);
			data.setBounds(10, 310, 80, 25);
			add(data);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "So numeros");
		};
		
		for(ContabilidadeDTO e: Contabilidade.getListaContab().getLista()) {
			System.out.println(index);
			if(index == e.getCod()) {
				quant.setText(""+e.getQunat());
				lucro.setText(""+e.getLucro());
				pizza.setText(e.getSabor());
				data.setText(e.getData());
			}
			
			
		}
		
		
		
		
	}
	private void botao(int index) {
		
		BotoesGeral botao1 = new BotoesGeral("<html>Salvar<html>",new ImageIcon("Icon/salvarcont.png"),80,400,100,110);
		botao1.repaint();
		add(botao1);
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
						if(quant.getText().equals("") || lucro.getText().equals("")|| pizza.getText().equals("")
								||data.getText().equals("  /  /    ")){
							JOptionPane.showMessageDialog(null, "Campo Vazio", "Alerta",JOptionPane.ERROR_MESSAGE);
						}
					else {
						 System.out.println(cod1);
						 ControlContabil.conteditarRela(cod1,Float.parseFloat(quant.getText()), Float.parseFloat(lucro.getText()), pizza.getText().toUpperCase(), data.getText());
						 JOptionPane.showMessageDialog(null, "Relatorio Editado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
						 dispose();
						 new ViewlContabilidade();
					 }
			}
			
		});
		
		JButton botao2 = new BotoesGeral("Cancelar",new ImageIcon("Icon/excluircont.png"),215,400,100,110);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ViewlContabilidade();
			}
		}
		);
		add(botao2);
	}
		
	
}
