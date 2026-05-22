package com.exemplo.reservas.controller;

import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ReservaDAO reservaDAO = new ReservaDAO();
            SalaDAO salaDAO = new SalaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            request.setAttribute("reservasHoje", reservaDAO.contarReservasHoje());
            request.setAttribute("totalSalas", salaDAO.contarTodas());
            request.setAttribute("totalUsuarios", usuarioDAO.contarTodos());

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("reservasHoje", 0);
            request.setAttribute("totalSalas", 0);
            request.setAttribute("totalUsuarios", 0);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}