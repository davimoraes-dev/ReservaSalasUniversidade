package com.exemplo.reservas.dao;

import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.model.Usuario;
import com.exemplo.reservas.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO implements IReservaDAO {

    public void inserir(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas (sala_id, usuario_id, data_reserva, hora_inicio, hora_fim, motivo, status, usar_computadores, usar_projetor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, reserva.getSala().getId());
            stmt.setInt(2, reserva.getUsuario().getId());
            stmt.setDate(3, Date.valueOf(reserva.getData()));
            stmt.setTime(4, Time.valueOf(reserva.getHoraInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHoraFim()));
            stmt.setString(6, reserva.getMotivo());
            stmt.setString(7, reserva.getStatus());
            stmt.setBoolean(8, reserva.isUsarComputadores());
            stmt.setBoolean(9, reserva.isUsarProjetor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) reserva.setId(rs.getInt(1));
        }
    }

    public void atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reservas SET sala_id = ?, usuario_id = ?, data_reserva = ?, hora_inicio = ?, hora_fim = ?, motivo = ?, status = ?, usar_computadores = ?, usar_projetor = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getSala().getId());
            stmt.setInt(2, reserva.getUsuario().getId());
            stmt.setDate(3, Date.valueOf(reserva.getData()));
            stmt.setTime(4, Time.valueOf(reserva.getHoraInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHoraFim()));
            stmt.setString(6, reserva.getMotivo());
            stmt.setString(7, reserva.getStatus());
            stmt.setBoolean(8, reserva.isUsarComputadores());
            stmt.setBoolean(9, reserva.isUsarProjetor());
            stmt.setInt(10, reserva.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT r.*, s.nome AS sala_nome, s.capacidade, s.tem_projetor, s.localizacao, " +
                     "u.nome AS usuario_nome, u.email, u.tipo " +
                     "FROM reservas r " +
                     "JOIN salas s ON r.sala_id = s.id " +
                     "JOIN usuarios u ON r.usuario_id = u.id " +
                     "WHERE r.id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return extrairReserva(rs);
            }
        }
        return null;
    }

    public List<Reserva> listarTodas() throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.*, s.nome AS sala_nome, s.capacidade, s.tem_projetor, s.localizacao, " +
                     "u.nome AS usuario_nome, u.email, u.tipo " +
                     "FROM reservas r " +
                     "JOIN salas s ON r.sala_id = s.id " +
                     "JOIN usuarios u ON r.usuario_id = u.id " +
                     "WHERE r.status != 'cancelada' " +
                     "ORDER BY r.data_reserva DESC, r.hora_inicio";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(extrairReserva(rs));
        }
        return lista;
    }

    public List<Reserva> buscarPorSala(int salaId) throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.*, s.nome AS sala_nome, s.capacidade, s.tem_projetor, s.localizacao, " +
                     "u.nome AS usuario_nome, u.email, u.tipo " +
                     "FROM reservas r " +
                     "JOIN salas s ON r.sala_id = s.id " +
                     "JOIN usuarios u ON r.usuario_id = u.id " +
                     "WHERE r.sala_id = ? " +
                     "ORDER BY r.data_reserva DESC, r.hora_inicio";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) lista.add(extrairReserva(rs));
            }
        }
        return lista;
    }

    public List<Reserva> buscarPorUsuario(int usuarioId) throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.*, s.nome AS sala_nome, s.capacidade, s.tem_projetor, s.localizacao, " +
                     "u.nome AS usuario_nome, u.email, u.tipo " +
                     "FROM reservas r " +
                     "JOIN salas s ON r.sala_id = s.id " +
                     "JOIN usuarios u ON r.usuario_id = u.id " +
                     "WHERE r.usuario_id = ? " +
                     "ORDER BY r.data_reserva DESC, r.hora_inicio";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) lista.add(extrairReserva(rs));
            }
        }
        return lista;
    }

    public int contarReservasHoje() throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas WHERE data_reserva = ? AND status != 'cancelada'";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public boolean existeConflito(int salaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas WHERE sala_id = ? AND data_reserva = ? AND status != 'cancelada' AND hora_inicio < ? AND hora_fim > ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salaId);
            stmt.setDate(2, Date.valueOf(data));
            stmt.setTime(3, Time.valueOf(horaFim));
            stmt.setTime(4, Time.valueOf(horaInicio));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean existeConflito(int salaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim, int ignorarId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas WHERE sala_id = ? AND data_reserva = ? AND status != 'cancelada' AND id != ? AND hora_inicio < ? AND hora_fim > ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salaId);
            stmt.setDate(2, Date.valueOf(data));
            stmt.setInt(3, ignorarId);
            stmt.setTime(4, Time.valueOf(horaFim));
            stmt.setTime(5, Time.valueOf(horaInicio));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public void cancelarReserva(int id) throws SQLException {
        String sql = "UPDATE reservas SET status = 'cancelada' WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) throw new SQLException("Reserva não encontrada com ID: " + id);
        }
    }

    private Reserva extrairReserva(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setId(rs.getInt("id"));
        r.setData(rs.getDate("data_reserva").toLocalDate());
        r.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
        r.setHoraFim(rs.getTime("hora_fim").toLocalTime());
        r.setMotivo(rs.getString("motivo"));
        r.setStatus(rs.getString("status"));
        r.setUsarComputadores(rs.getBoolean("usar_computadores"));
        r.setUsarProjetor(rs.getBoolean("usar_projetor"));

        Sala s = new Sala();
        s.setId(rs.getInt("sala_id"));
        s.setNome(rs.getString("sala_nome"));
        s.setCapacidade(rs.getInt("capacidade"));
        s.setTemProjetor(rs.getBoolean("tem_projetor"));
        s.setLocalizacao(rs.getString("localizacao"));
        r.setSala(s);

        Usuario u = new Usuario();
        u.setId(rs.getInt("usuario_id"));
        u.setNome(rs.getString("usuario_nome"));
        u.setEmail(rs.getString("email"));
        u.setTipo(rs.getString("tipo"));
        r.setUsuario(u);

        return r;
    }
}