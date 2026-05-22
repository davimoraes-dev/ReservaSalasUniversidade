package com.exemplo.reservas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    // Altere os dados abaixo conforme sua configuração do MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/reservas_universidade?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";         
    
    static {
        try {
            // Registra o driver JDBC (necessário em algumas versões)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do MySQL não encontrado!", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    // Método main para testar a conexão
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Conexão com MySQL estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }
}