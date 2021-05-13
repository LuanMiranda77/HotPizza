package view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import javax.swing.text.MaskFormatter;

import DTO.ClienteDTO;
import controller.ControlCliente;

public class TelaCadastroCliente extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNome;
	private JTextField campoTipoCadastro;
	private JTextField campoTelefone;
	private JTextField campoCidade;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoBairro;
	private JButton botaoCadastrar;
	private JButton botaoVoltar;
	
	public TelaCadastroCliente(boolean boo, ClienteDTO dto) {
		
		setTitle("Cadastro de Cliente");
		setSize(750, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 220));
		
		labels();
		fields();
		botoes(boo, dto);
		
		this.setVisible(true);
		
	}

	public void labels() {
		
		JLabel tipoPessoa = new LabelPadrao("Tipo Pessoa", 30, 40, 80, 30);
		add(tipoPessoa);
		JLabel tipoPessoa2 = new LabelPadrao("CPF ou CNPJ", 20, 120, 80, 30);
		add(tipoPessoa2);
		JLabel nome = new LabelPadrao("NOME", 350, 40, 50, 30);
		add(nome);
		JLabel telefone = new LabelPadrao("TELEFONE", 20, 280, 70, 30);
		add(telefone);		
		JLabel cidade = new LabelPadrao("CIDADE", 350, 200, 80, 30);
		add(cidade);	
		JLabel rua = new LabelPadrao("RUA / AVENIDA", 350, 120, 100, 30);
		add(rua);	
		JLabel numero = new LabelPadrao("N�MERO", 670, 120, 110, 30);
		add(numero);	
		JLabel bairro = new LabelPadrao("BAIRRO", 20, 200, 50, 30);
		add(bairro);	
		JLabel imagem = new JLabel(new ImageIcon("casa.png"));
		imagem.setBounds(150, 320, 512, 512);
		add(imagem);
		JLabel quadro = new JLabel(new ImageIcon("quadro.png"));
		quadro.setBounds(22, 44, 86, 67);
		add(quadro);
		
	}

	public void fields() {
		
		OuvinteExternoDeFoco ouvinteDeFoco = new OuvinteExternoDeFoco();
		campoNome = new TextFieldPadrao(350, 70, 250, 35);
		add(campoNome);

		JRadioButton rbCPF = new JRadioButton("CPF", false);
		JRadioButton rbCNPJ = new JRadioButton("CNPJ", false);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbCPF);
		bg.add(rbCNPJ);
		rbCPF.setBounds(30, 70, 50, 15);
		rbCNPJ.setBounds(30, 90, 60, 15);
		add(rbCNPJ);
		add(rbCPF);
		
		campoTipoCadastro = new JTextField();
		campoTipoCadastro.setBounds(20, 150, 130, 35);
		campoTipoCadastro.setEnabled(false);
		
		add(campoTipoCadastro); 
		
		rbCPF.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MaskFormatter cpf;
				try {
					campoTipoCadastro.setEnabled(true);
					cpf =  new MaskFormatter(format("CPF"));
					remove(campoTipoCadastro);
					cpf.setValidCharacters("0123456789");
					campoTipoCadastro = new JFormattedTextField(cpf);
					campoTipoCadastro.setBounds(20, 150, 130, 35);
				}catch (ParseException e1) {}
				add(campoTipoCadastro);    
			}
		});
		
		rbCNPJ.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MaskFormatter cnpj;
				try {
					campoTipoCadastro.setEnabled(true);
					cnpj =  new MaskFormatter(format("CNPJ"));
					remove(campoTipoCadastro);
					cnpj.setValidCharacters("0123456789");
					campoTipoCadastro = new JFormattedTextField(cnpj);
					campoTipoCadastro.setBounds(20, 150, 130, 35);
				}catch (ParseException e1) {}
				add(campoTipoCadastro);  
			}
		});
		  
		try {
			MaskFormatter telefone = new MaskFormatter("(**) * ****-****");
			telefone.setValidCharacters("0123456789");		
			campoTelefone = new JFormattedTextField(telefone);
			campoTelefone.setBounds(20, 310, 120, 35);
			campoTelefone.addFocusListener(ouvinteDeFoco);
		}catch (ParseException e2) {}
		add(campoTelefone);
					
		campoRua = new TextFieldPadrao(350, 150, 250, 35);
		add(campoRua);
		campoNumero = new TextFieldPadrao(670, 150, 50, 35);
		add(campoNumero);
		campoCidade = new TextFieldPadrao(350, 230, 250, 35);
		add(campoCidade);
		campoBairro = new TextFieldPadrao(20, 230, 250, 35);
		add(campoBairro);
		
	}
	
	private void botoes(boolean condicao, ClienteDTO dto) {
		
		if(condicao) {

			botaoCadastrar = new JButton("CADASTRAR", new ImageIcon("ok.png"));
			botaoCadastrar.setBounds(160, 380, 150, 35);
			botaoCadastrar.setToolTipText("Clique para cadastrar o im�vel");
			
			OuvinteInternoCadastroCliente ouvinteCliente = new OuvinteInternoCadastroCliente();
	
			botaoCadastrar.addActionListener(ouvinteCliente);
	
			add(botaoCadastrar);
		
		}else {
			setTitle("Edi��o de Cadastro de Cliente");

			JButton botaoEditar = new JButton("SALVAR EDI��O");
			botaoEditar.setBounds(75, 380, 150, 35);
			botaoEditar.setToolTipText("Clique para salvar a edi��o do cadastro do cliente");
			
			//seta com o dto de cliente
			campoNome.setText("Agemiro");//dto.getNome();
			campoTipoCadastro.setText("111.111.111-11"); // e assim por diante
			campoTelefone.setText("(99) 9 9999-9999");
			campoCidade.setText("Monteiro");
			campoRua.setText("Monteiro");
			campoNumero.setText("66");
			campoBairro.setText("mutir�o");
			
			botaoEditar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//ClienteControl control = new ClienteControl();
		            dto.setNome(campoNome.getText());
		            dto.setCPF(campoTipoCadastro.getText());
		            dto.setTelefone(campoTelefone.getText());
		            dto.setEnd(campoCidade.getText() + " - " + campoRua.getText() + " - " + campoNumero.getText() + " - " + campoBairro.getText());
					JOptionPane.showMessageDialog(null, "Edi��o realizada com sucesso!", "Feito", JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		            //new TelaGerenciarCliente();
				}
			});
			add(botaoEditar);
		}
		
		botaoVoltar = new JButton("VOLTAR");
		botaoVoltar.setBounds(430, 380, 150, 35);
		botaoVoltar.setToolTipText("Clique para voltar");
			
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaGerenciarCliente();
			}
		});
		add(botaoVoltar);

	}
	
	private String format(String type) {
	    switch(type) {
	        case "CNPJ":
	            return "**.***.***/****-**";
	        default:
	             return "***.***.***-**";
	    }
	}

	
	public class OuvinteInternoCadastroCliente implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			
			String label = evento.getActionCommand();
			
			switch (label) {
			
				case "CADASTRAR":

					String nome = campoNome.getText();
					String cpf = campoTipoCadastro.getText();
					String telefone = campoTelefone.getText();
					String endereco = campoCidade.getText() + " - " + campoRua.getText() + " - " + campoNumero.getText() + " - " + campoBairro.getText();
			
					boolean confere = true;
					
					if (nome.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o nome do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if(cpf.equals("   .   .   -  ")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o CPF do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}		
					if(telefone.equals("(  )       -    ")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o telefone do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}			
					if(campoRua.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite a rua do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}			
					if(campoNumero.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o numero residencial do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}			
					if(campoBairro.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o bairro do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}

					if (confere) {
				
						ControlCliente control = new ControlCliente();

						ClienteDTO dto = new ClienteDTO();
				
						dto.setNome(nome);
						dto.setCPF(cpf);
						dto.setTelefone(telefone);
						dto.setEnd(endereco);
				
						try {
							control.contClientes(dto);
						} catch (ExceptionCliente e) {}
						
						JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new TelaGerenciarCliente();
				
					}
					break;
			}
		}	
	} 
}
