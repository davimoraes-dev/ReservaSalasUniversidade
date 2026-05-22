package com.exemplo.reservas.controller;

import com.exemplo.reservas.command.InserirReservaComando;
import com.exemplo.reservas.command.InserirUsuarioComando;
import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplos.reserva.factory.ReservaFactory;
import com.exemplos.reserva.factory.UsuarioFactory;
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

@WebServlet("/NovaReservaServlet")
public class NovaReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String salaNome = request.getParameter("salaNome");
            String usuarioNome = request.getParameter("usuarioNome");
            String dataParam = request.getParameter("data");
            String horaInicioParam = request.getParameter("horaInicio");
            String horaFimParam = request.getParameter("horaFim");
            String motivo = request.getParameter("motivo");

            if (salaNome == null || salaNome.trim().isEmpty() ||
                usuarioNome == null || usuarioNome.trim().isEmpty() ||
                dataParam == null || horaInicioParam == null ||
                horaFimParam == null || motivo == null || motivo.trim().isEmpty()) {
                request.setAttribute("erro", "Todos os campos são obrigatórios");
                request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
                return;
            }

            LocalDate data = LocalDate.parse(dataParam);
            LocalTime horaInicio = LocalTime.parse(horaInicioParam);
            LocalTime horaFim = LocalTime.parse(horaFimParam);

            if (horaFim.isBefore(horaInicio) || horaFim.equals(horaInicio)) {
                request.setAttribute("erro", "Hora fim deve ser depois da hora início");
                request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
                return;
            }

            SalaDAO salaDAO = new SalaDAO();
            Sala sala = salaDAO.buscarPorNome(salaNome.trim());
            if (sala == null) {
                request.setAttribute("erro", "Sala não encontrada. Cadastre a sala primeiro em 'Salas'.");
                request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
                return;
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.buscarPorNome(usuarioNome.trim());
            if (usuario == null) {
                usuario = UsuarioFactory.criarAluno(usuarioNome.trim());
                new InserirUsuarioComando(usuarioDAO, usuario).executar();
            }

            ReservaDAO reservaDAO = new ReservaDAO();
            boolean temConflito = reservaDAO.existeConflito(sala.getId(), data, horaInicio, horaFim);
            if (temConflito) {
                request.setAttribute("erro", "Já existe uma reserva para esta sala neste horário");
                request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
                return;
            }

            Reserva reserva = ReservaFactory.criar(sala, usuario, data, horaInicio, horaFim, motivo);
            new InserirReservaComando(reservaDAO, reserva).executar();
            response.sendRedirect(request.getContextPath() + "/listarReservas?sucesso=Reserva criada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao salvar reserva");
            try { request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response); } catch (Exception ex) { ex.printStackTrace(); }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Dados inválidos");
            try { request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
}