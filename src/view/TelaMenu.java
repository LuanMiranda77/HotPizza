

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;

import java.util.Date;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;

import DAOAPostgreSQL.ConexaoPostgreSQL;
import DAOAPostgreSQL.ConexaoSingleton;
import controller.BotoesGeral;
import controller.ControlCentral;

import controller.OuvinteMenu;
import controller.SistemaFacadePedido;



public class TelaMenu extends Principal {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TelaMenu(String cargo) {
		setTitle("Menu");
	   setLayout(null);
	   setSize(1400,750);
	   setLocationRelativeTo(null);
	   setResizable(false);
	   adicionarlogo(this);
	   adicionarbutton(cargo);
	   bcl();
	   blackgroud();
	   setVisible(true);
	 //  fechar();
	   repaint();
	   
	   

	}
	public void bcl() {
		JLabel barra = new JLabel(new ImageIcon("Icon/barramenu.png"));
		barra.setBounds(1,-20
				,1250,180);
		add(barra);
		
	}
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("Icon/fundo.png"));
		contabil.setBounds(1,1,1380,730);
		add(contabil);
		
	}
	public void adicionarbutton(String cargo) {
		OuvinteMenu ouvinte= new OuvinteMenu();
		
		int tam=2;
		
		BotoesGeral pedido = new BotoesGeral("PDV",new ImageIcon("Icon/PEDIDO.png"),tam,30,100,100);
		pedido.repaint();
		add(pedido);
		pedido.addActionListener(ouvinte);
		
		BotoesGeral cliente = new BotoesGeral("<html>Clientes<html>",new ImageIcon("Icon/clientes.png"),tam+101,30,100,100);
		cliente.repaint();
		add(cliente);
		cliente.addActionListener(ouvinte);
		
		
		BotoesGeral pizza = new BotoesGeral("<html>Pizzas<html>",new ImageIcon("Icon/pizza.png"),tam+202,30,100,100);
		pizza.repaint();
		add(pizza);
		pizza.addActionListener(ouvinte);
		BotoesGeral entrega = new BotoesGeral("<html>Entregas<html>",new ImageIcon("Icon/entregado.png"),tam+303,30,100,100);
		entrega.repaint();
		add(entrega);
		entrega.addActionListener(ouvinte);
		
		BotoesGeral pizzaiolo = new BotoesGeral("<html>Pizzaiolo<html>",new ImageIcon("Icon/pizzaiolo.png"),tam+404,30,100,100);
		pizzaiolo.repaint();
		add(pizzaiolo);
		pizzaiolo.addActionListener(ouvinte);
		
		BotoesGeral func = new BotoesGeral("<html>Funcionarios<html>",new ImageIcon("Icon/func.png"),tam+505,30,100,100);
		func.repaint();
		add(func);
		func.addActionListener(ouvinte);
		
		BotoesGeral cont = new BotoesGeral("<html>Contabilidade<html>",new ImageIcon("Icon/contabil.png"),tam+606,30,110,100);
		add(cont);
		cont.addActionListener(ouvinte);
		
		
		BotoesGeral config = new BotoesGeral("<html>Configuração<html>",new ImageIcon("Icon/confg.png"),tam+708,30,110,100);
		add(config);
		config.addActionListener(ouvinte);
		
		BotoesGeral sair = new BotoesGeral("<html>Sair<html>",new ImageIcon("Icon/sair.png"),tam+808,30,100,100);
		add(sair);
		sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
			});
		
		
	    switch (cargo) {
	    
		case "Atendente":
			entrega.setEnabled(false);
			pizzaiolo.setEnabled(false);
			pizza.setEnabled(false);
			func.setEnabled(false);
			cont.setEnabled(false);
			config.setEnabled(false);
			break;
			
		case "Pizzaiolo":
			cliente.setEnabled(false);
			pedido.setEnabled(false);
			pizza.setEnabled(false);
			entrega.setEnabled(false);
			func.setEnabled(false);
			cont.setEnabled(false);
			config.setEnabled(false);
			
			break;
			
		case "Motoboy":
			cliente.setEnabled(false);
			pedido.setEnabled(false);
			pizza.setEnabled(false);
			pizzaiolo.setEnabled(false);
			func.setEnabled(false);
			cont.setEnabled(false);
			config.setEnabled(false);
			
			break;

		default:
			break;
		}
	}
	public void adicionarlogo(JFrame frame) {
		
		JLabel nome  = new JLabel("MENU PRINCIPAL");
		nome.setFont(new Font("Times New Roman",Font.BOLD,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(500, 1, 500, 30);
		add(nome);
		
		 Date d = new Date();
	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
	
		JLabel data=  new JLabel("Data: "+hoje);
		data.setBounds(900,635,400,100);
		data.setFont(new Font("Arial",Font.BOLD,25));
		data.setForeground(Color.WHITE);
		frame.add(data);
		
		
		JLabel user=  new JLabel("Usuario Logado: "+ControlCentral.userLogado().getNome());
		user.setBounds(20,635,400,100);
		user.setFont(new Font("Arial",Font.BOLD,25));
		user.setForeground(Color.WHITE);
		frame.add(user);
		
		JLabel user1=  new JLabel("Cargo: "+ControlCentral.userLogado().getCargo());
		user1.setBounds(500,635,400,100);
		user1.setFont(new Font("Arial",Font.BOLD,25));
		user1.setForeground(Color.WHITE);
		frame.add(user1);
		
		
		
	}
	public void fechar() {
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   
	}
	public static void main(String[] args) {
		new TelaMenu("Administrador");
	}
	
}
