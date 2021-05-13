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
import controller.CampoGeral;

import controller.ControlEmpresa;
import controller.ControlFuncionarios;
import controller.FonteBem;


public class ViewConfig extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nome;
	private JTextField end;
	private JTextField cep;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField tel;
	private JTextField CPF;
	int cod1=0;


	
	public ViewConfig() {
		setTitle("Cadastro");
		setBounds(400, 50, 500, 650);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField(this);
		jLabels();
        botao();
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
		JLabel nome  = new JLabel("CADASTRO DA EMPRESA");
		nome.setFont(new Font("Times New Roman",Font.ITALIC,25));
		nome.setForeground(Color.WHITE);
		nome.setBounds(50, 1, 500, 30);
		add(nome);
		
		JLabel label1  = new FonteBem("CNPJ",10 , 40);
		add(label1);
		JLabel label  = new FonteBem("Nome", 10, 100
				);
		add(label);
		
		JLabel end  =new FonteBem("Endereço", 10, 160);
		add(end);
		
		JLabel bairro  =new FonteBem("Bairro", 10, 230);
		add(bairro);
		
		JLabel CEP  =new FonteBem("CEP", 180, 230);
		add(CEP);
		
		JLabel cidade  =new FonteBem("Cidade", 10, 290);
		add(cidade);
		
		JLabel label2  =new FonteBem("Fone", 10, 345);
		add(label2);
	}
	public void textField(JFrame janela) {
		MaskFormatter cpf;
		try {
			cpf = new MaskFormatter("##.###.###/####-##");
			CPF = new JFormattedTextField(cpf);
			CPF.setBounds(10, 70, 150, 25);
			CPF.setToolTipText("Digite CPF");
			add(CPF);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "CNPJ invalido", "Alerta", 3);
		}
		
	    nome = new CampoGeral("",10, 130, 300, 25);
		nome.setToolTipText("nome");
		janela.add(nome);
		
		bairro = new CampoGeral("",10, 260, 150, 25);
		bairro.setToolTipText("bairro");
		janela.add(bairro);
		
		MaskFormatter cp;
		try {
			cp = new MaskFormatter("#####-###");
			cep = new JFormattedTextField(cp);
		cep.setBounds(180, 260, 100, 25);;
		cep.setToolTipText("nome");
		janela.add(cep);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "CNPJ invalido", "Alerta", 3);
		}

		cidade = new CampoGeral("",10, 320, 200, 25);
		cidade.setToolTipText("nome");
		janela.add(cidade);
		
		
		end = new CampoGeral("",10, 190, 300, 25);
		end.setToolTipText("endereço");
		janela.add(end);
		
		MaskFormatter m;
		try {
			m = new MaskFormatter("(##)#.####-####");
			tel = new JFormattedTextField(m);
			tel.setBounds(10, 370, 150, 25);
			tel.setToolTipText("Digite celular");
			add(tel);
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null, "Celular invalido", "Alerta", 3);
			
		}
		
		
	}
	
	private void botao() {

		try {
		if (!ControlEmpresa.retEmpresa().getNome().equals("")) {
		CPF.setText(ControlEmpresa.retEmpresa().getCNPJ());
		nome.setText(ControlEmpresa.retEmpresa().getNome());
		end.setText(ControlEmpresa.retEmpresa().getEnd());
		bairro.setText(ControlEmpresa.retEmpresa().getBairro());
		cep.setText(ControlEmpresa.retEmpresa().getCep());
		cidade.setText(ControlEmpresa.retEmpresa().getCidade());
		tel.setText(ControlEmpresa.retEmpresa().getTel());
		}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Empresa não cadastrado", "Alerta", 3);
		}
		BotoesGeral ed= new BotoesGeral("<html>Salvar<html>",new ImageIcon("Icon/predio.png"),50,400,100,110);
		ed.repaint();
		add(ed);
		ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlEmpresa ctl = new ControlEmpresa();
				String n =nome.getText().toUpperCase();
				if(nome.getText().equals("")|| end.getText().equals("")||bairro.getText().equals("")||
						cep.getText().equals("     -   ")||cidade.getText().equals("")||tel.getText().equals("(  ) .    -    ")
						||CPF.getText().equals("  .   .   /    -  ") ) {
					JOptionPane.showMessageDialog(null, "Campo Vazio", "Alerta",JOptionPane.ERROR_MESSAGE);
			}
				
			else {
				ctl.caqEmpresa(CPF.getText(),n,end.getText().toUpperCase(),bairro.getText().toUpperCase(), cep.getText(),
						cidade.getText().toUpperCase(),tel.getText());
				
				JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!", "Alerta",JOptionPane.INFORMATION_MESSAGE);
				if(ControlFuncionarios.ContTamanho()==0) {
					 new TelaUsuario(true,"<html>Salvar<html>",0);
		        	dispose();
		        }
				else
				dispose();
			}
			}	
			});

		
		JButton botao2 = new BotoesGeral("Cancelar",new ImageIcon("Icon/apaempresa.PNG"),180,400,100,110);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		}
		);
		add(botao2);
	}
	public static void main(String[] args) {
		new ViewConfig();
	}
	
		
	
}
