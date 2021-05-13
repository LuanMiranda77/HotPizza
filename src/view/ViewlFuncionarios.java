
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.BotoesGeral;
import controller.ControlFuncionarios;

public class ViewlFuncionarios extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TableFuncionarios tabela;
	Object index;
	
	public ViewlFuncionarios() {
		setTitle("Lista de funcionarios");
		setBounds(400, 50, 700, 500);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tabela(this);
		botoes();
		setVisible(true);
		repaint();
		
	}
	public void botoes() {
	
	BotoesGeral cad = new BotoesGeral("Cadastrar",new ImageIcon("Icon/cadfun.png"), 10, 10, 100, 110);
	 add(cad);
	 cad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tela=e.getActionCommand();
				dispose();
				new TelaUsuario(false,tela,0);
			}
		});
	 
	 BotoesGeral editar = new BotoesGeral("Editar",new ImageIcon("Icon/editarfun.png"), 111, 10, 100, 110);
	 add(editar);
	 editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String tela=e.getActionCommand();
				int linha1 =tabela.seletctID();
				dispose();
				new TelaUsuario(false,tela,linha1);
				}catch (Exception d) {
					
				}
			
			}
		});
	 
	 BotoesGeral excluir = new BotoesGeral("Excluir",new ImageIcon("Icon/apagrafun.png"), 212, 10, 100, 110);
	 add(excluir);
	 excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    int linha1 =tabela.seletctID();
				
		    if(linha1<0) {
		    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
		    
		    else {
				tabela.excluirLinha(tabela.selectLinha());
				ControlFuncionarios.contExcluirFun(linha1); 	
			}
					}
				});
	 
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/pesfun.png"), 313, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 try {
	     String n= JOptionPane.showInputDialog(null, "Digite o nome do pra pesquisa", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE);
	     n=n.toUpperCase();
	     if (tabela.filtroNome(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Funcionario não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
		tabela = new TableFuncionarios();
		tabela.TabelaFuncionario(janela);
		//tabela.revalidate();
		tabela.repaint();
		janela.add(tabela);
	}

}
