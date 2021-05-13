
package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import controller.ControlEmpresa;




public class TelaSplesh extends JWindow {
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int duration;

	    public TelaSplesh(int d) {
	        duration = d;
	    }

	// Este é um método simples para mostrar uma tela de apresentção

	// no centro da tela durante a quantidade de tempo passada no construtor

	    public void showSplash() {        
	        JPanel content = (JPanel)getContentPane();
	        content.setBackground(Color.white);

	        // Configura a posição e o tamanho da janela
	        int width = 600;
	        int height =420;
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width-width)/2;
	        int y = (screen.height-height)/2;
	        setBounds(x,y,width,height);

	        // Constrói o splash screen
	        JLabel label = new JLabel(new ImageIcon("Icon/pizzaload.gif"));
	        JLabel logo = new JLabel(new ImageIcon("Icon/logo.png"));
	        //logo.setBounds(200, 40, 150, 150);
	        JLabel load = new JLabel
	                ("Carregando...", JLabel.CENTER);
	        load.setFont(new Font("Sans-Serif", Font.BOLD, 40));
	        label.setBounds(130,400, 100, 250);
	        content.add(logo);
	        content.add(label,BorderLayout.BEFORE_LINE_BEGINS);
	        content.add(load, BorderLayout.SOUTH);
	        Color oraRed = new Color(156, 20, 20,  255);
	        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));        
	        // Torna visível
	        setVisible(true);

	        // Espera ate que os recursos estejam carregados
	        
	        try { Thread.sleep(duration); } catch (Exception e) {}        
	        setVisible(false);
	    }

	    public void showTela() {  
	        showSplash();
	        
	    }

	    public static void main(String[] args) throws SQLException, ClassNotFoundException {        
	        // Mostra uma imagem com o título da aplicação 
	     
	    	TelaSplesh splash = new TelaSplesh(2000);
	        splash.showTela();
	        
	        
	        if(ControlEmpresa.retEmpresa()==null) {
	        	new ViewConfig();
	        }
	        else	
	        new TelaMenu("Maximo");
	    }
}
