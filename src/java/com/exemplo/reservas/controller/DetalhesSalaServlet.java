package com.exemplo.reservas.controller;

import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.decorator.ComputadorDecorator;
import com.exemplo.reservas.decorator.ProjetorDecorator;
import com.exemplo.reservas.decorator.SalaBasica;
import com.exemplo.reservas.decorator.SalaComponente;
import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/detalhesSala")
public class DetalhesSalaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=ID não informado");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            SalaDAO salaDAO = new SalaDAO();
            ReservaDAO reservaDAO = new ReservaDAO();

            Sala sala = salaDAO.buscarPorId(id);
            if (sala == null) {
                response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Sala não encontrada");
                return;
            }

            List<Reserva> reservas = reservaDAO.buscarPorSala(id);
            sala.setReservas(reservas);

            SalaComponente salaDecorada = new SalaBasica();
            if (sala.isTemProjetor()) {
                salaDecorada = new ProjetorDecorator(salaDecorada);
            }
            if (sala.isTemComputador()) {
                salaDecorada = new ComputadorDecorator(salaDecorada);
            }

            request.setAttribute("sala", sala);
            request.setAttribute("salaDecorada", salaDecorada);
            request.setAttribute("reservas", reservas);
            request.getRequestDispatcher("/jsp/salas/detalhesSala.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=ID inválido");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Erro ao carregar dados");
        }
    }
}