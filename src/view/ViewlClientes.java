package view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.BotoesGeral;
import controller.ControlCliente;

public class ViewlClientes extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6100634636482570749L;
	TableClientes tabela;

	
	public ViewlClientes(String bot) {
		setTitle("Lista de Clientes");
		setBounds(400, 50, 700, 500);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tabela(this);
		botoes(bot);
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
	public void botoes(String bot) {
	
	BotoesGeral cad = new BotoesGeral("Cadastrar",new ImageIcon("Icon/cadcli.png"), 10, 10, 100, 110);
	 add(cad);
	 cad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tela=e.getActionCommand();
				dispose();
				new TelaCadCliente(false,tela,0);
			}
		});
	 
	 BotoesGeral editar = new BotoesGeral("Editar",new ImageIcon("Icon/editarcli.png"), 111, 10, 100, 110);
	 add(editar);
	 editar.setVisible(true);
	 editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				String tela=e.getActionCommand();
				int linha1 =tabela.seletctID();
				System.out.println(linha1);
				dispose();
				new TelaCadCliente(false,tela,linha1);
				}catch (Exception d) {
					
				}
			
			}
		});
	 
	 BotoesGeral excluir = new BotoesGeral("Excluir",new ImageIcon("Icon/exlcuircli.png"), 212, 10, 100, 110);
	 add(excluir);
	 excluir.setVisible(true);
	 excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha1 =tabela.seletctID();
				System.out.println(linha1);
			 	
			    if(linha1<0) {
			    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
			    else {
		    		ControlCliente.contExcluirCliente(linha1);
		    		tabela.excluirLinha(tabela.selectLinha());
		    		JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso !", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
			}
		});
	 
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/peesquisacli.png"), 313, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 try {
	     String n= JOptionPane.showInputDialog(null, "Digite o nome do pra pesquisa", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE);
	     n=n.toUpperCase();
	     
	     if (tabela.filtroNome(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	     }
	     else
	     tabela.limparTabela();
	     tabela.filtroNome(n);
	     tabela.repaint();
	     
	}catch(NullPointerException k) {
		
	}
	}
	});
	 
	 BotoesGeral adic = new BotoesGeral("Adicionar",new ImageIcon("Icon/salvarcli.png"), 10, 10, 100, 110);
	 add(adic);
	 if(bot.equals("<html>Add Cliente<html>")) {
		 cad.setVisible(false);
		 editar.setVisible(false);
		 excluir.setVisible(false);
	 }
	 else
		 adic.setVisible(false
				 );
	 adic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha=tabela.seletctID();
				VIewPDV.recCliente(linha);
				dispose();
				
			}
				
			});
	 
	}
	public void tabela(JFrame janela) {
		tabela = new TableClientes();
		tabela.adicionarTabela(janela);
		tabela.repaint();
		janela.add(tabela);
	}
public static void main(String[] args) {
	new ViewlClientes("");
}

}
