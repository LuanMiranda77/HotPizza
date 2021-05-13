package DAOAPostgreSQL;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class TestConexao {
private static FabFactory criar;
	
	public static  FabFactory retornar() {
	
	Connection con = null;
	try {
		con = ConexaoSingleton.getInstance();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    if(con==null) {
    	criar = new BDarquivo();
    	
    }
    else if(criar==null) {
    	
    	criar = new BDrelacional();
    	
    	VerificarXML.verificar();
    	
    }
	return criar;
	}
		
	
	//test de finalizar conexão
	public static void main(String[] args) {
	for(int cont=0;cont<20;cont++) {	
		ConexaoPostgreSQL.getInstance().sairConexao();
	}
	}
}
