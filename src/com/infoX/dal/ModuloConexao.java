package com.infoX.dal;

/**
 *
 * @author Eduardo Azevedo
 */
import java.sql.*;

public class ModuloConexao {

    //Metodo para estabelecer a conexao com o banco
    public static Connection conector() {
        Connection conexao = null;
        // Chama o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        // Armazena informacoes do banco
        String url = "jdbc:mysql://localhost:3306/dbinfox?characterEncoding=utf-8";
        String user = "dba";
        String password = "Eduardo10.";

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
