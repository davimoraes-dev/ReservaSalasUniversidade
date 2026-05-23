package com.exemplo.reservas.controller;

import com.exemplo.reservas.decorator.SalaComponente;
import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.service.ReservaService;
import com.exemplo.reservas.service.SalaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            SalaService salaService = new SalaService();
            ReservaService reservaService = new ReservaService();

            Sala sala = salaService.buscarPorId(id);
            if (sala == null) {
                response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Sala não encontrada");
                return;
            }

            List<Reserva> reservas = reservaService.buscarPorSala(id);
            sala.setReservas(reservas);

            SalaComponente salaDecorada = salaService.construirEquipamentos(sala);

            request.setAttribute("sala", sala);
            request.setAttribute("salaDecorada", salaDecorada);
            request.setAttribute("reservas", reservas);
            request.getRequestDispatcher("/jsp/salas/detalhesSala.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=ID inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Erro ao carregar dados");
        }
    }
}
