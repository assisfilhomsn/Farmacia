package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoFactory {
	// Dados para Conexão
	private static final String USUARIO = "root";
	private static final String SENHA = "123456";
	private static final String URL =  "jdbc:mysql://localhost:3306/sistema?useTimezone=true&serverTimezone=UTC"; 
	
	// Variavel de acesso externo para conexão
	public static Connection conectar() throws SQLException {
		
		
		//Referencia ao Driver mysql para versões antigas do java (Abaixo do 8) (Inicio)
		if (Integer.parseInt(System.getProperty("java.runtime.version").substring(2,3)) < 8 ) {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		} else {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		}
		//Referencia ao Driver mysql para versões antigas do java (Abaixo do 8) (Fim)

		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA); 
		return conexao;
	}

	public static void main(String[] args) {

		try {
			Connection conexao = conexaoFactory.conectar();
			System.out.println("Conectado com sucesso -> " + conexao);

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Falha na Conexão");
		}

	}

}
