package com.infoX.dal;

/**
 *
 * @author eduaz
*/

import java.sql.*;
public class ModuloConexao {

	//Metodo para estabelecer a conexao com o banco
	public static Connection conector() {
		java.sql.Connection conexao = null;
		// Chama o driver
		String driver = "com.mysql.jdbc.Driver";
		// Armazena informacoes do banco
		String url = "jdbc:mysql://localhost:3306/dbinfox";
		String user = "root";
		String password = "root";
		
		// Estabelece a conexao
		try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {
			// Exibe erro
			System.out.println(e);
			return null;
		}
	}
}
