package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import controller.BotoesGeral;

public class ViewEstoque extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TablePizza tabela;
	
	public ViewEstoque() {
		setTitle("Lista de Estoque");
		setBounds(400, 50, 700, 500);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tabela(this);
		botoes();
		barra();
		setVisible(true);
		repaint();
		
	}
	public void barra() {
		JLabel barra = new JLabel();
		barra.setOpaque(true);
		barra.setBackground(Color.GRAY);
		barra.setBounds(0, 10, 700, 120);
		add(barra);
	}
	public void botoes() {
		
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/pespi.png"), 120, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 try {
	     String n= JOptionPane.showInputDialog(null, "Digite o nome do pra pesquisa", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE);
	     n=n.toUpperCase();
	     
	     if (tabela.filtroNome(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Pizza não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	     }
	     else
	     tabela.limparTabela();
	     tabela.filtroNome(n);
	     tabela.repaint();
	     
	}catch(NullPointerException k) {
		
	}
	}
	});
	 
	 BotoesGeral adic = new BotoesGeral("Adicionar",new ImageIcon("Icon/salvrapi.png"), 10, 10, 100, 110);
	 add(adic);
	 adic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha=tabela.selectLinha();
				linha++;
				System.out.print(linha);
				VIewPDV.receb(linha);
				dispose();
				
			}
				
			});
	 
	}
	public void tabela(JFrame janela) {
		tabela = new TablePizza();
		tabela.adicionarJTable(janela);
		tabela.repaint();
		tabela.ajuste(janela);
		janela.add(tabela);
	}
	public int buscaItem() {
		int cod=tabela.selectLinha();
		return cod;		
	}
	

}
