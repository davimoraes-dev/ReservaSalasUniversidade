package com.exemplo.reservas.controller;

import com.exemplo.reservas.service.ReservaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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

            new ReservaService().criarReserva(salaNome.trim(), usuarioNome.trim(), data, horaInicio, horaFim, motivo);
            response.sendRedirect(request.getContextPath() + "/listarReservas?sucesso=Reserva criada com sucesso!");

        } catch (IllegalArgumentException | IllegalStateException e) {
            request.setAttribute("erro", e.getMessage());
            try {
                request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao salvar reserva");
            try {
                request.getRequestDispatcher("/jsp/reservas/novaReserva.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
