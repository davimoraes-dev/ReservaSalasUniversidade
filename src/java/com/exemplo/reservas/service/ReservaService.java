package com.exemplo.reservas.service;

import com.exemplo.reservas.command.AtualizarReservaComando;
import com.exemplo.reservas.command.InserirReservaComando;
import com.exemplo.reservas.dao.IReservaDAO;
import com.exemplo.reservas.dao.ISalaDAO;
import com.exemplo.reservas.dao.IUsuarioDAO;
import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplos.reserva.factory.ReservaFactory;
import com.exemplos.reserva.factory.UsuarioFactory;
import com.exemplo.reservas.command.InserirUsuarioComando;
import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.model.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaService {

    private final IReservaDAO reservaDAO;
    private final ISalaDAO salaDAO;
    private final IUsuarioDAO usuarioDAO;

    public ReservaService() {
        this.reservaDAO = new ReservaDAO();
        this.salaDAO = new SalaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public Reserva buscarPorId(int id) throws SQLException {
        return reservaDAO.buscarPorId(id);
    }

    public List<Reserva> listarTodas() throws SQLException {
        return reservaDAO.listarTodas();
    }

    public List<Reserva> buscarPorSala(int salaId) throws SQLException {
        return reservaDAO.buscarPorSala(salaId);
    }

    public List<Reserva> buscarPorUsuario(int usuarioId) throws SQLException {
        return reservaDAO.buscarPorUsuario(usuarioId);
    }

    public void cancelarReserva(int id) throws SQLException {
        reservaDAO.cancelarReserva(id);
    }

    public void validarHorario(LocalTime horaInicio, LocalTime horaFim) {
        if (horaFim.isBefore(horaInicio) || horaFim.equals(horaInicio)) {
            throw new IllegalArgumentException("Hora fim deve ser depois da hora início");
        }
    }

    public Reserva criarReserva(String salaNome, String usuarioNome, LocalDate data,
                                LocalTime horaInicio, LocalTime horaFim, String motivo) throws Exception {
        validarHorario(horaInicio, horaFim);

        Sala sala = salaDAO.buscarPorNome(salaNome);
        if (sala == null) {
            throw new IllegalArgumentException("Sala não encontrada. Cadastre a sala primeiro em 'Salas'.");
        }

        Usuario usuario = usuarioDAO.buscarPorNome(usuarioNome);
        if (usuario == null) {
            usuario = UsuarioFactory.criarAluno(usuarioNome);
            new InserirUsuarioComando(usuarioDAO, usuario).executar();
        }

        if (reservaDAO.existeConflito(sala.getId(), data, horaInicio, horaFim)) {
            throw new IllegalStateException("Já existe uma reserva para esta sala neste horário");
        }

        Reserva reserva = ReservaFactory.criar(sala, usuario, data, horaInicio, horaFim, motivo);
        new InserirReservaComando(reservaDAO, reserva).executar();
        return reserva;
    }

    public void atualizarReserva(int id, int salaId, int usuarioId, LocalDate data,
                                 LocalTime horaInicio, LocalTime horaFim, String motivo) throws Exception {
        validarHorario(horaInicio, horaFim);

        Sala sala = salaDAO.buscarPorId(salaId);
        Usuario usuario = usuarioDAO.buscarPorId(usuarioId);

        if (sala == null || usuario == null) {
            throw new IllegalArgumentException("Sala ou usuário não encontrado");
        }

        if (reservaDAO.existeConflito(salaId, data, horaInicio, horaFim, id)) {
            throw new IllegalStateException("Já existe uma reserva para esta sala neste horário");
        }

        Reserva reserva = ReservaFactory.criarComId(id, sala, usuario, data, horaInicio, horaFim, motivo);
        new AtualizarReservaComando(reservaDAO, reserva).executar();
    }
}
