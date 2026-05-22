package com.exemplo.reservas.controller;

import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.model.Sala;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarSalas")
public class ListarSalasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SalaDAO dao = new SalaDAO();
            List<Sala> salas = dao.listarTodas();
            request.setAttribute("salas", salas);
            request.getRequestDispatcher("/jsp/salas/listarSalas.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Erro ao carregar salas");
        }
    }
}