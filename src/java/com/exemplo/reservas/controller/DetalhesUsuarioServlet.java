package com.exemplo.reservas.controller;

import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/detalhesUsuario")
public class DetalhesUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=ID não informado");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ReservaDAO reservaDAO = new ReservaDAO();

            Usuario usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=Usuário não encontrado");
                return;
            }

            List<Reserva> reservas = reservaDAO.buscarPorUsuario(id);
            usuario.setReservas(reservas);

            request.setAttribute("usuario", usuario);
            request.setAttribute("reservas", reservas);
            request.getRequestDispatcher("/jsp/usuarios/detalhesUsuario.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=ID inválido");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=Erro ao carregar dados");
        }
    }
}