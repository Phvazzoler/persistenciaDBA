package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String URL = "jbdc:sqlite:agenda.db";
    public static Connection conectar (){
        try {
            return DriverManager.getConnection(URL);

        }catch (SQLException e ){
            System.out.println("erro ao connectar ao banco de dados :" + e.getMessage());
            return null; // se a conexao falhar
        }
    }

    public static void criarTabaela() {
        String sql =  "CREATE TABLE IF NOT EXISTS contatos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT , "
                + "nome TEXT NOT NULL , "
                + "telefone TEXT "
                + ");";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        }catch (SQLException e ) {
            System.out.println("Erro ao criar a tabela " + e.getMessage());
        }

    }
}
