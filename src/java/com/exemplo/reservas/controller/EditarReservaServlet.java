package com.exemplo.reservas.controller;

import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.model.Usuario;
import com.exemplo.reservas.service.ReservaService;
import com.exemplo.reservas.service.SalaService;
import com.exemplo.reservas.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            ReservaService reservaService = new ReservaService();
            SalaService salaService = new SalaService();
            UsuarioService usuarioService = new UsuarioService();

            Reserva reserva = reservaService.buscarPorId(id);
            if (reserva == null) {
                response.sendRedirect(request.getContextPath() + "/listarReservas?erro=Reserva não encontrada");
                return;
            }

            List<Sala> salas = salaService.listarTodas();
            List<Usuario> usuarios = usuarioService.listarTodos();

            request.setAttribute("reserva", reserva);
            request.setAttribute("salas", salas);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/jsp/reservas/editarReserva.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=ID inválido");
        } catch (Exception e) {
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

            new ReservaService().atualizarReserva(id, salaId, usuarioId, data, horaInicio, horaFim, motivo);
            response.sendRedirect(request.getContextPath() + "/listarReservas?sucesso=Reserva atualizada com sucesso!");

        } catch (IllegalArgumentException | IllegalStateException e) {
            String idParam = request.getParameter("id");
            response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?id=" + idParam + "&erro=" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            String idParam = request.getParameter("id");
            response.sendRedirect(request.getContextPath() + "/jsp/reservas/editarReserva.jsp?id=" + idParam + "&erro=Erro ao atualizar reserva");
        }
    }
}
