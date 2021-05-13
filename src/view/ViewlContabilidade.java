package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import com.itextpdf.text.DocumentException;

import controller.BotoesGeral;
import controller.ControlContabil;

public class ViewlContabilidade extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TableContabil tabela;
	Object index;
	
	public ViewlContabilidade() {
		setTitle("Lista de funcionarios");
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
	
	BotoesGeral cad = new BotoesGeral("Cadastrar",new ImageIcon("Icon/salvarcont.png"), 10, 10, 100, 110);
	 add(cad);
	 cad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewContMes();
			}
		});
	 
	 BotoesGeral editar = new BotoesGeral("Editar",new ImageIcon("Icon/editarcont.png"), 111, 10, 100, 110);
	 add(editar);
	 editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabela.selectLinha();
			
				index++;
				if(index<0) {
			    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
				
				else {
				dispose();
				new ViewRelatorio(index);
				//System.out.println(index);
				}
			
			}
		});
	 
	 BotoesGeral excluir = new BotoesGeral("Excluir",new ImageIcon("Icon/excluircont.png"), 212, 10, 100, 110);
	 add(excluir);
	 excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int linha1 =tabela.selectLinha();
						
				    if(linha1<0) {
				    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
				    
				    else {
						    tabela.excluirLinha(linha1);
							ControlContabil.excluirContab(linha1);
					}
			}
		});
	 
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/pescont.png"), 313, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 try {
	     int n= Integer.parseInt(JOptionPane.showInputDialog(null, "Digite codigo do relatorio", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE));
	     
	     if (tabela.filtroCod(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Relatorio não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	     }
	     else
	     tabela.limparTabela();
	     tabela.filtroCod(n);
	     tabela.repaint();
	     
	}catch(NullPointerException k) {
		
	}
	}
	});
	 
	 BotoesGeral impri = new BotoesGeral("Imprimir",new ImageIcon("Icon/impre.png"), 420, 10, 110, 110);
	 add(impri);
	 impri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linha1 =tabela.selectLinha();
				   
						
				    if(linha1<0) {
				    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);	}	
				    
				    else {
						
						  try {
							  new Relatoriocont(linha1);
						} catch (DocumentException | IOException e1) {
							JOptionPane.showInternalMessageDialog(null, "Documento Aberto");
						}
					}
			}
		});
	 
	}
	public void tabela(JFrame janela) {
		tabela = new TableContabil();
		tabela.adicionarJTable(janela);
		tabela.repaint();
		janela.add(tabela);
	}
	public static void main(String[] args) {
		new ViewlContabilidade();
	}

}
