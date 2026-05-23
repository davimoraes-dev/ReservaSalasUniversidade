package com.exemplo.reservas.dao;

import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO implements ISalaDAO {

    public void inserir(Sala sala) throws SQLException {
        String sql = "INSERT INTO salas (nome, capacidade, tem_projetor, tem_computador, localizacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sala.getNome());
            stmt.setInt(2, sala.getCapacidade());
            stmt.setBoolean(3, sala.isTemProjetor());
            stmt.setBoolean(4, sala.isTemComputador());
            stmt.setString(5, sala.getLocalizacao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) sala.setId(rs.getInt(1));
        }
    }

    public void atualizar(Sala sala) throws SQLException {
        String sql = "UPDATE salas SET nome = ?, capacidade = ?, tem_projetor = ?, tem_computador = ?, localizacao = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sala.getNome());
            stmt.setInt(2, sala.getCapacidade());
            stmt.setBoolean(3, sala.isTemProjetor());
            stmt.setBoolean(4, sala.isTemComputador());
            stmt.setString(5, sala.getLocalizacao());
            stmt.setInt(6, sala.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM salas WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Sala buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM salas WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return extrairSala(rs);
            }
        }
        return null;
    }

    public Sala buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM salas WHERE nome = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return extrairSala(rs);
            }
        }
        return null;
    }

    public List<Sala> listarTodas() throws SQLException {
        List<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM salas ORDER BY nome";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(extrairSala(rs));
        }
        return lista;
    }

    public int contarTodas() throws SQLException {
        String sql = "SELECT COUNT(*) FROM salas";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public boolean temReservas(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas WHERE sala_id = ? AND status != 'cancelada'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    private Sala extrairSala(ResultSet rs) throws SQLException {
        return new Sala.Builder()
                .id(rs.getInt("id"))
                .nome(rs.getString("nome"))
                .capacidade(rs.getInt("capacidade"))
                .temProjetor(rs.getBoolean("tem_projetor"))
                .temComputador(rs.getBoolean("tem_computador"))
                .localizacao(rs.getString("localizacao"))
                .build();
    }
}