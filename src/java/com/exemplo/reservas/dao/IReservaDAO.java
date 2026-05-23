package com.exemplo.reservas.dao;

import com.exemplo.reservas.model.Reserva;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IReservaDAO {
    void inserir(Reserva reserva) throws SQLException;
    void atualizar(Reserva reserva) throws SQLException;
    void excluir(int id) throws SQLException;
    void cancelarReserva(int id) throws SQLException;
    Reserva buscarPorId(int id) throws SQLException;
    List<Reserva> listarTodas() throws SQLException;
    List<Reserva> buscarPorSala(int salaId) throws SQLException;
    List<Reserva> buscarPorUsuario(int usuarioId) throws SQLException;
    int contarReservasHoje() throws SQLException;
    boolean existeConflito(int salaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim) throws SQLException;
    boolean existeConflito(int salaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim, int ignorarId) throws SQLException;
}
