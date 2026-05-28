package com.exemplo.reservas.controller;

import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.service.ReservaService;
import com.exemplo.reservas.service.SalaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listarReservas")
public class ListarReservasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ReservaService reservaService = new ReservaService();
            SalaService salaService = new SalaService();
            List<Reserva> reservas = reservaService.listarTodas();

            Map<Integer, String> equipamentosDescricao = new LinkedHashMap<>();
            for (Reserva r : reservas) {
                String descricao = salaService.construirDescricaoEquipamentos(r.isUsarComputadores(), r.isUsarProjetor());
                equipamentosDescricao.put(r.getId(), descricao);
            }

            request.setAttribute("reservas", reservas);
            request.setAttribute("equipamentosDescricao", equipamentosDescricao);
            request.setAttribute("hoje", LocalDate.now().toString());
            request.getRequestDispatcher("/jsp/reservas/listaReservas.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarReservas?erro=Erro ao carregar reservas");
        }
    }
}