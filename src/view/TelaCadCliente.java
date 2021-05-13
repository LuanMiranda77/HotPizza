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

import DTO.ClienteDTO;
import controller.BotoesGeral;
import controller.CampoGeral;
import controller.ControlCliente;
import controller.FonteBem;


public class TelaCadCliente extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4613560546093688501L;
	private JTextField cod;
	private JTextField nome;
	private JTextField end;
	private JTextField tel;
	private JTextField CPF;

	
	public TelaCadCliente() {
		
	}
	
	public TelaCadCliente(boolean band,String tela, int index) {
		setTitle("Cadastro");
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
		JLabel nome  = new JLabel("CADASTRO DE CLIENTES");
		nome.setFont(new Font("Times New Roman",Font.ITALIC,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(50, 1, 500, 30);
		add(nome);
		
		JLabel label1  = new FonteBem("Codigo",10 , 40);
		add(label1);
		JLabel label  = new FonteBem("Nome", 10, 100);
		add(label);
		
		JLabel end  =new FonteBem("Endereço", 10, 180);
		add(end);
		
		JLabel label2  =new FonteBem("Fone", 10, 310);
		add(label2);
		
		JLabel cel  =new FonteBem("CPF", 10, 250);
		add(cel);
	}
	public void textField(JFrame janela) {
		
		cod = new CampoGeral("",10, 70, 100, 25);
		cod.setToolTipText("Codigo de Identificação");
		cod.setEnabled(false);
		cod.setText(Integer.toString(ControlCliente.contIdCliente()));
		janela.add(cod);
		
	    nome = new CampoGeral("",10, 130, 300, 25);
		nome.setToolTipText("nome");
		janela.add(nome);
		
		end = new CampoGeral("",10, 210, 300, 25);
		end.setToolTipText("endereço");
		janela.add(end);
		
		MaskFormatter m;
		try {
			m = new MaskFormatter("(##)#.####-####");
			tel = new JFormattedTextField(m);
			tel.setBounds(10, 340, 150, 25);
			tel.setToolTipText("Digite celular");
			add(tel);
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null, "Celular invalido", "Alerta", 3);
			
		}
		
		MaskFormatter cpf;
		try {
			cpf = new MaskFormatter("###.###.###-##");
			CPF = new JFormattedTextField(cpf);
			CPF.setBounds(10, 280, 150, 25);
			CPF.setToolTipText("Digite CPF");
			add(CPF);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "CPF invalido", "Alerta", 3);
		}
	}
	
	private void botao(boolean band1,String  tela,int index) {
		BotoesGeral ed= new BotoesGeral("<html>Editar<html>",new ImageIcon("Icon/editarcli.png"),80,400,100,120);
		ed.repaint();
		if(!tela.equals("Editar")) {
			 ed.setVisible(false);
				
			}
		else {
			ed.setVisible(true);
		    ControlCliente p = new ControlCliente();
		    cod.setText(""+index);
		    nome.setText(p.ConsultarCliente(index).getNome());
		    end.setText(p.ConsultarCliente(index).getEnd());
		    CPF.setText(p.ConsultarCliente(index).getCPF());
		    tel.setText(p.ConsultarCliente(index).getTelefone());
		    	
		    }
		add(ed);
		ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDTO cliente = new ClienteDTO();
				cliente.setCod(Integer.parseInt(cod.getText()));
				cliente.setNome(nome.getText().toUpperCase());
				cliente.setEnd(end.getText().toUpperCase());
				cliente.setCPF(CPF.getText());
				cliente.setTelefone(tel.getText());
				
				ControlCliente.conteditarCliente(cliente);
				JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new ViewlClientes("");
			}
			});
		
		BotoesGeral botao1 = new BotoesGeral("<html>Salvar<html>",new ImageIcon("Icon/salvarcli.png"),80,400,100,110);
		botao1.repaint();
		add(botao1);
		if(tela.equals("Editar")) {
			 botao1.setVisible(false);
			}
		else
			 botao1.setVisible(true);
			
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ControlCliente ctl = new ControlCliente();
				
					try {
						if(cod.getText().equals("") || nome.getText().equals("")|| end.getText().equals("")||tel.getText().equals("(  ) .    -    ")||CPF.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Campo Vazio", "Alerta",JOptionPane.ERROR_MESSAGE);
						
					}
					else {
						ClienteDTO cliente = new ClienteDTO();
						cliente.setCod(Integer.parseInt(cod.getText()));
						cliente.setNome(nome.getText().toUpperCase());
						cliente.setEnd(end.getText().toUpperCase());
						cliente.setCPF(CPF.getText());
						cliente.setTelefone(tel.getText());
						 ctl.contClientes(cliente);
						 JOptionPane.showMessageDialog(null, "Clientes cadastrado com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
						 dispose();
						 new ViewlClientes("");
					 }
						
					}catch(ExceptionCliente a) {
						cod.requestFocus();
						
					}	
			}
			
		});
		
		JButton botao2 = new BotoesGeral("Cancelar",new ImageIcon("Icon/exlcuircli.png"),200,400,100,110);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ViewlClientes("");
			}
		}
		);
		add(botao2);
		
	}	
	
	
}
