package view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.BotoesGeral;
import controller.ControIngre;



public class CadIngre extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TableIngredientes tabela;

	
	public CadIngre() {
		setTitle("Lista de Igredientes");
		setBounds(400, 50, 700, 500);
		setResizable(false);
		getContentPane().setBackground(new Color(190, 190, 190));
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
	
	BotoesGeral cad = new BotoesGeral("Cadastrar",new ImageIcon("Icon/salvain.png"), 10, 10, 100, 110);
	 add(cad);
	 cad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tela=e.getActionCommand();
				dispose();
				new TelaIngred(false,tela,0);
			}
		});
	 
	 BotoesGeral editar = new BotoesGeral("Editar",new ImageIcon("Icon/editarin.png"), 120, 10, 100, 110);
	 add(editar);
	 editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String tela=e.getActionCommand();
				int linha1 =tabela.selectID();
				dispose();
				new TelaIngred(false,tela,linha1);
				}catch (Exception d) {
					
				}
			
			}
		});
	 
	 BotoesGeral excluir = new BotoesGeral("Excluir",new ImageIcon("Icon/excluirin.png"), 230, 10, 100, 110);
	 add(excluir);
	 excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha1 =tabela.selectID();
	
	

    if(linha1<0) {
    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
    else {
    		tabela.excluirLinha(tabela.selectLinha());
    		ControIngre.contExcluirPedido(linha1);
    		JOptionPane.showMessageDialog(null, "Item excluido com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
			}
		});
	 
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/pesin.png"), 340, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 try {
	     String n= JOptionPane.showInputDialog(null, "Digite o nome do pra pesquisar", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE).toUpperCase();
	     
	     if (tabela.filtroNome(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Ingrediente não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	     }
	     else
	     tabela.limparTabela();
	     tabela.filtroNome(n);
	     tabela.repaint();
	     
	}catch(NullPointerException k) {
		
	}
	}
	});
	 
	}
	public void tabela(JFrame janela) {
		tabela = new TableIngredientes();
		tabela.adicionarJTable(janela);
		tabela.repaint();
		janela.add(tabela);
	}

}
