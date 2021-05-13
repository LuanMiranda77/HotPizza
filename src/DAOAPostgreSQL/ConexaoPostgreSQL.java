package DAOAPostgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConexaoPostgreSQL {
    
	private final static String URL = "jdbc:postgresql://localhost:5433/bdPizza"; // incica o caminho do banco de dados
	private final static String USER = "postgres"; // aqui vai o nome usuario que vc quer acessar
	private final static String PASS = "ads54321"; // aqui a senha do seu banco
	private static Connection conexao;
	private volatile static ConexaoPostgreSQL instance;
	
	private ConexaoPostgreSQL() {
		
	}
	
	public  static ConexaoPostgreSQL getInstance() {
		if( instance == null ){
			synchronized (ConexaoPostgreSQL.class){
		if( instance == null ) {
		    instance = new ConexaoPostgreSQL();
			}
		}
	}
		return instance;
		}


	public  Connection criarConexao() {
		
		try {
			Class.forName("org.postgresql.Driver");
			//System.out.println("conectado");
			conexao= DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
			
		}
		return conexao;
	}
	
	public  void sairConexao(){
	
		try {
			   conexao.close(); 
			   conexao=null;
		   //System.out.println("Finalizado...");
		} catch (SQLException e) {
		   // Tratar Exceptions
		} 
	}
	public static void main(String[] args) {
		for(int cont=0;cont<10;cont++) {
			ConexaoPostgreSQL.getInstance().sairConexao();
		}
	}
	

}
