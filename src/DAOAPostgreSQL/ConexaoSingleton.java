package DAOAPostgreSQL;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoSingleton {
	
	private volatile static Connection instance;
	
	private ConexaoSingleton( ){ 
		
	}
	
	public static Connection getInstance() throws ClassNotFoundException, SQLException{
		if( instance == null ){
			synchronized (ConexaoSingleton.class){
		if( instance == null ) {
		    //instance = ConexaoPostgreSQL.getInstance().criarConexao();
			}
		}
	}
		return instance;
		}
}
