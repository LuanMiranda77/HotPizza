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


import controller.BotoesGeral;

import controller.ControlContabil;

import controller.FonteBem;



public class ViewContMes extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField  mes;



	

	
	public ViewContMes() {
		setTitle("Cadastro");
		setBounds(600, 150, 350, 350);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField(this);
		botao();
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
		JLabel nome  = new JLabel("CADASTRO DE RELATORIO");
		nome.setFont(new Font("Times New Roman",Font.ITALIC,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(10, 1, 500, 30);
		add(nome);
		
		JLabel label1  = new FonteBem("MES DO RELATORIO",90, 90);
		add(label1);
		
	}
	public void textField(JFrame janela) {
		
		
		MaskFormatter cod;
		try {
			cod = new MaskFormatter("**/****");
			cod.setValidCharacters("0123456789.");
			mes =new JFormattedTextField(cod);
			mes.setBounds(90,120, 100, 30);
			add(mes);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "So numeros");
		}
		
	}
		
	private void botao() {

		
		BotoesGeral ed= new BotoesGeral("<html>Salvar<html>",new ImageIcon("Icon/salvarcont.png"),50,190,100,100);
		add(ed);
		ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean band = false;
				
				if(mes.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null, "Campo vazio!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int cod = ControlContabil.id();
					ControlContabil.contContabil(cod,mes.getText());
					band =true;
					
				JOptionPane.showMessageDialog(null, "Relatorio geral com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new  ViewlContabilidade();
					}
				if (band==false) {
						JOptionPane.showMessageDialog(null, "Data não tem Vendas neste periodo !", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			});
		
					
		
		JButton botao2 = new BotoesGeral("Cancelar",new ImageIcon("Icon/excluircont.png"),170,190,100,100);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ViewlContabilidade();
			}
		});
		add(botao2);
	
}
}
		


