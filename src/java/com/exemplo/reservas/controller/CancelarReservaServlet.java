package com.exemplo.reservas.controller;

import com.exemplo.reservas.command.CancelarReservaComando;
import com.exemplo.reservas.dao.ReservaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CancelarReservaServlet")
public class CancelarReservaServlet extends HttpServlet {

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
            ReservaDAO dao = new ReservaDAO();
            new CancelarReservaComando(dao, id).executar();
            response.sendRedirect(request.getContextPath() + "/listarReservas?sucesso=Reserva cancelada com sucesso!");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=ID inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=Erro ao cancelar reserva");
        }
    }
}