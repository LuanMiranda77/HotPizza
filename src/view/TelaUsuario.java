package view;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import javax.swing.JTextField;

import DTO.FuncionariosDTO;
import controller.BotoesGeral;
import controller.CampoGeral;
import controller.ControlFuncionarios;
import controller.FonteBem;



public class TelaUsuario extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2341624009284424660L;
	private JTextField cod;
	private JTextField nome;
	private JPasswordField  senha;
	private JTextField   senha2;
	private JComboBox<String> boxCargo;
	private JComboBox<String> boxNivel;
	private JCheckBox administrador;

	public TelaUsuario(boolean band,String tela, int index) {
		setTitle("Cadastro de fuincionarios");
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
		JLabel nome  = new JLabel("CADASTRO DE FUNCIONÁRIOS");
		nome.setFont(new Font("Times New Roman",Font.ITALIC,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(50, 1, 500, 30);
		add(nome);
		
		JLabel label1  = new FonteBem("Codigo",10 , 40);
		add(label1);
		JLabel label  = new FonteBem("Nome", 10, 100);
		add(label);
		
		JLabel end  =new FonteBem("Cargo", 10, 180);
		add(end);
		
		JLabel label2  =new FonteBem("Senha", 10, 310);
		add(label2);
		
		JLabel cel  =new FonteBem("Nilvel", 10, 250);
		add(cel);
	}
	public void textField(JFrame janela) {
		cod = new CampoGeral("",10, 70, 100, 25);
		cod.setToolTipText("Codigo de Identificação");
		janela.add(cod);
		cod.setEnabled(false);
	
		cod.setText(""+ControlFuncionarios.contIdFun());
		
	    nome = new CampoGeral("",10, 130, 300, 25);
		nome.setToolTipText("nome");
		janela.add(nome);
		
		
		String [] listaCargo= {"Gerente","Atendente","Pizzaiolo","Motoboy", "Serviços gerais"};
		boxCargo =new JComboBox<String>(listaCargo);
		boxCargo.setBounds(70,180,100,30);
		
		
		if(ControlFuncionarios.ContTamanho()==0) {
			boxCargo.setVisible(false);	
		}
		else
			boxCargo.setVisible(true);
		add(boxCargo);
		
		String [] listaNivel= {"Maximo","Medio","Minimo","Nada"};
		boxNivel =new JComboBox<String>(listaNivel);
		boxNivel.setBounds(70,250,100,30);
		add(boxNivel);
		
		senha = new JPasswordField("");
		senha.setBounds(10, 335, 150, 25);
		senha.setToolTipText("Digite senha");
		janela.add(senha);
		
		senha2 = new JTextField("");
		senha2.setBounds(10, 335, 150, 25);
		senha2.setToolTipText("Digite senha");
		janela.add(senha2);
	
		
	}
	private void botao(boolean band1,String  tela,int index) {
		
		BotoesGeral ed= new BotoesGeral("<html>Editar<html>",new ImageIcon("Icon/editarfun.png"),80,400,100,110);
		ed.repaint();
		if(!tela.equals("Editar")) {
			 ed.setVisible(false);
				
			}
		else {
			ed.setVisible(true);
		    ControlFuncionarios p = new ControlFuncionarios();
		    cod.setText(""+index);
		    nome.setText(p.ConsultarFun(index).getNome());
		    boxCargo.setSelectedItem(p.ConsultarFun(index).getCargo());
		    boxNivel.setSelectedItem(p.ConsultarFun(index).getNivel());
		    senha.setText(p.ConsultarFun(index).getSenha());
		    }	 	
		add(ed);
		ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionariosDTO editado = new FuncionariosDTO();
				editado.setCod(Integer.parseInt(cod.getText()));
				editado.setNome(nome.getText().toUpperCase());
				editado.setCargo((String) boxCargo.getSelectedItem());
				editado.setSenha(senha.getText());
				editado.setNivel((String)boxNivel.getSelectedItem());
		
				ControlFuncionarios.conteditarFun(editado);
				JOptionPane.showMessageDialog(null, "Funcinario editado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new ViewlFuncionarios();
			}
			});
		
		BotoesGeral botao1 = new BotoesGeral("<html>Salvar<html>",new ImageIcon("Icon/salvarfun.png"),80,400,100,110);
		botao1.repaint();
		add(botao1);
		if(tela.contentEquals("Editar")) {
			 botao1.setVisible(false);
			}
		else
			 botao1.setVisible(true);
			
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						if(cod.getText().equals("") || nome.getText().equals("")|| senha.getText().equals("")||boxNivel.getSelectedItem().equals("")||boxCargo.getSelectedItem().equals("")) {
							JOptionPane.showMessageDialog(null, "Campo Vazio", "Alerta",JOptionPane.ERROR_MESSAGE);
						}
					else if(administrador.isSelected()==true) {
							FuncionariosDTO editado = new FuncionariosDTO();
							editado.setCod(Integer.parseInt(cod.getText()));
							editado.setNome(nome.getText().toUpperCase());
							editado.setCargo("Administrador");
							editado.setSenha(senha.getText());
							editado.setNivel((String)boxNivel.getSelectedItem());
						    ControlFuncionarios.contFuncionarios(editado);
							JOptionPane.showMessageDialog(null, "Admisttrador cadastrado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
							dispose();
							new ViewLogin();
					}
					else {
						FuncionariosDTO editado = new FuncionariosDTO();
						editado.setCod(Integer.parseInt(cod.getText()));
						editado.setNome(nome.getText().toUpperCase());
						editado.setCargo((String) boxCargo.getSelectedItem());
						editado.setSenha(senha.getText());
						editado.setNivel((String)boxNivel.getSelectedItem());
						 ControlFuncionarios.contFuncionarios(editado);
						 JOptionPane.showMessageDialog(null, "Funcinario cadastrado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
						 cod.setText("");
						 nome.setText("");
						 senha.setText("");
						 cod.requestFocus();
						 dispose();
						 new ViewlFuncionarios();
					 }
						
					}catch(ExceptionFuncionarioCad a) {
						cod.requestFocus();
						
					}	
			}
			
		});
		
		JButton botao2 = new BotoesGeral("Cancelar",new ImageIcon("Icon/apagrafun.png"),230,400,100,110);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			    new ViewlFuncionarios();
			}
		}
		);
		add(botao2);
		
		administrador = new JCheckBox("Administrador");
		administrador.setOpaque(true);
		administrador.setVisible(band1);
		administrador.setSelected(band1);
		administrador.setEnabled(false);
		administrador.setBackground(new Color(255,198,91));
		administrador.setBounds(10, 380, 150, 30);
		add(administrador);
		
		JLabel olho = new JLabel(new ImageIcon("Icon/olhof.png"));
		olho.setBounds(55,300,50,50);
		add(olho);
		olho.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
			
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhoa.png"));
				senha.setVisible(false);
				senha2.setText(senha.getText());
				senha2.setVisible(true);
			}

			public void mouseReleased(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhof.png"));
				senha.setVisible(true);
				senha2.setVisible(false);
			}
			
		});
	}		
}
