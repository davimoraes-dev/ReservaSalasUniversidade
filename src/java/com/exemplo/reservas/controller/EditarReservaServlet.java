package com.exemplo.reservas.controller;

import com.exemplo.reservas.command.AtualizarReservaComando;
import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplos.reserva.factory.ReservaFactory;
import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/EditarReservaServlet")
public class EditarReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=ID não informado");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            ReservaDAO reservaDAO = new ReservaDAO();
            SalaDAO salaDAO = new SalaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Reserva reserva = reservaDAO.buscarPorId(id);
            if (reserva == null) {
                response.sendRedirect(request.getContextPath() + "/listarReservas?erro=Reserva não encontrada");
                return;
            }

            List<Sala> salas = salaDAO.listarTodas();
            List<Usuario> usuarios = usuarioDAO.listarTodos();

            request.setAttribute("reserva", reserva);
            request.setAttribute("salas", salas);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/jsp/reservas/editarReserva.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=ID inválido");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=Erro ao carregar dados");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idParam = request.getParameter("id");
            String salaIdParam = request.getParameter("salaId");
            String usuarioIdParam = request.getParameter("usuarioId");
            String dataParam = request.getParameter("data");
            String horaInicioParam = request.getParameter("horaInicio");
            String horaFimParam = request.getParameter("horaFim");
            String motivo = request.getParameter("motivo");

            if (idParam == null || salaIdParam == null || usuarioIdParam == null ||
                dataParam == null || horaInicioParam == null || horaFimParam == null ||
                motivo == null || motivo.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?id=" + idParam + "&erro=Todos os campos são obrigatórios");
                return;
            }

            int id = Integer.parseInt(idParam);
            int salaId = Integer.parseInt(salaIdParam);
            int usuarioId = Integer.parseInt(usuarioIdParam);
            LocalDate data = LocalDate.parse(dataParam);
            LocalTime horaInicio = LocalTime.parse(horaInicioParam);
            LocalTime horaFim = LocalTime.parse(horaFimParam);

            if (horaFim.isBefore(horaInicio) || horaFim.equals(horaInicio)) {
                response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?id=" + id + "&erro=Hora fim deve ser depois da hora início");
                return;
            }

            SalaDAO salaDAO = new SalaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Sala sala = salaDAO.buscarPorId(salaId);
            Usuario usuario = usuarioDAO.buscarPorId(usuarioId);

            if (sala == null || usuario == null) {
                response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?id=" + id + "&erro=Sala ou usuário não encontrado");
                return;
            }

            ReservaDAO reservaDAO = new ReservaDAO();
            boolean temConflito = reservaDAO.existeConflito(salaId, data, horaInicio, horaFim, id);
            if (temConflito) {
                response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?id=" + id + "&erro=Já existe uma reserva para esta sala neste horário");
                return;
            }

            Reserva reserva = ReservaFactory.criarComId(id, sala, usuario, data, horaInicio, horaFim, motivo);
            new AtualizarReservaComando(reservaDAO, reserva).executar();
            response.sendRedirect(request.getContextPath() + "/listarReservas?sucesso=Reserva atualizada com sucesso!");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?erro=Formato de número inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?erro=Erro ao atualizar reserva");
        }
    }
}