package br.com.webcad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection createConnection(){
		
	       String stringDeConexao = "jdbc:mysql://localhost:3306/webcad";
	       String usuario = "root";
	       String senha = "1234";
	       
	       Connection conexao = null;
	       
	       try{
	    	   DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
	           conexao = DriverManager.getConnection(stringDeConexao, usuario, senha);
		} catch (SQLException e){
	           e.printStackTrace();
	       }
	       return conexao;
	    }
	
}
