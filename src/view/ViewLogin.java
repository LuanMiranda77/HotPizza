package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAOAPostgreSQL.ConexaoPostgreSQL;
import DTO.FuncionariosDTO;
import controller.ControlCentral;
import controller.ControlFuncionarios;
import model.Funcionarios;

public class ViewLogin extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField email;
	private JPasswordField senha;
	
	private TelaMenu janela;
	
	public ViewLogin() {
	setSize(400,400);
	setResizable(false);
	setLocationRelativeTo(null);
	setTitle("Tela de Login");
	camposDoLogin(this);
	botoesDoLogin(this);
	labelDoLogin();
	blackgroud();
	//test();
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	repaint();
	}
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("Icon/fundologin.png"));
		contabil.setBounds(1,1,400,400
				);
		add(contabil);
		
	}
	private void labelDoLogin() {
		JLabel nome  = new JLabel("LOGIN");
		nome.setFont(new Font("Times New Roman",Font.BOLD,18));
		nome.setForeground(Color.WHITE);
		nome.setBounds(170, -3, 500, 30);
		nome.requestFocus();
		add(nome);
	
		JLabel label2 = new JLabel("Usuario", JLabel.HORIZONTAL);
		label2.setForeground(Color.black);
		label2.setBounds(-10, 78, 200, 200);
		add(label2);
		
		JLabel label3 = new JLabel("Senha", JLabel.HORIZONTAL);
		label3.setForeground(Color.black);
		label3.setBounds(-10, 110, 200, 200);
		add(label3);
		
		JLabel logo = new JLabel(new ImageIcon("Icon/login.png"));
		logo.setBounds(110, 15, 150, 150);
		add(logo);
	}

	public void botoesDoLogin(JFrame frame) {
		
		JButton botao2 = new JButton("Entrar");
		botao2.setBounds(130, 230, 130, 30);
		botao2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean band = false;
				
				
				if(email.getText().equals("")) {
					JOptionPane.showMessageDialog(janela, "Campo usuario vazio", "Aviso",JOptionPane.INFORMATION_MESSAGE);
					email.requestFocus();
					band=true;
				}
				else if(senha.getText().equals("")) {
					JOptionPane.showMessageDialog(janela, "Campo do senha vazio", "Aviso",JOptionPane.INFORMATION_MESSAGE);
				    senha.requestFocus();
				    band=true;
				}
				else {
				String n =email.getText().toUpperCase();
				for(FuncionariosDTO login: ControlFuncionarios.listaFun().getLista()) {
				    if(login.getNome().equals(n) && !senha.getText().equals(login.getSenha())){
						JOptionPane.showMessageDialog(janela, "Senha ou Login invalido", "Aviso",JOptionPane.ERROR_MESSAGE);
						    band=true;
						    break;
						}
					else if(login.getNome().equals(n) && senha.getText().equals(login.getSenha())) {
							JOptionPane.showMessageDialog(janela, "Logado como sucesso", "Aviso",JOptionPane.INFORMATION_MESSAGE);
							band=true;
							ControlCentral.salvarUserLogado(login);
							dispose();
							new TelaMenu(login.getCargo());
						    }
					}
					}
				
				if(band==false) {
					JOptionPane.showMessageDialog(janela, "Usuario nao cadastrado", "Aviso",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		frame.add(botao2);
		
	}
	public void camposDoLogin(JFrame frame) {
		email = new JTextField();
		email.setBounds(120,170,150,20);
		frame.add(email);
		
		senha = new JPasswordField();
		senha.setBounds(120,200,150,20);
		frame.add(senha);
		
		JTextField senha2 = new JTextField();
		senha2.setBounds(120,200,150,20);
		add(senha2);
		
		JLabel olho = new JLabel(new ImageIcon("Icon/olhof.png"));
		olho.setBounds(282,188,50,50);
		add(olho);
		olho.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhoa.png"));
				senha.setVisible(false);
				senha2.setText(senha.getText());
				senha2.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhof.png"));
				senha.setVisible(true);
				senha2.setVisible(false);
			}
			
		});
	}
		
		public void test() {
            Connection con = ConexaoPostgreSQL.getInstance().criarConexao();
	        
	        if(con==null) {
	        	JOptionPane.showMessageDialog(null, "Banco desconectado usar xml");
	        }
	        else {
	        	JOptionPane.showMessageDialog(null, "Banco Conectado com sucesso");
	        }	
		
			
		}
		
		
		



}
