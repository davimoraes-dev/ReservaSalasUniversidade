package com.exemplo.reservas.controller;

import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.model.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/listarReservas")
public class ListarReservasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ReservaDAO dao = new ReservaDAO();
            List<Reserva> reservas = dao.listarTodas();
            request.setAttribute("reservas", reservas);
            request.setAttribute("hoje", LocalDate.now().toString());
            request.getRequestDispatcher("/jsp/reservas/listaReservas.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=Erro ao carregar reservas");
        }
    }
}