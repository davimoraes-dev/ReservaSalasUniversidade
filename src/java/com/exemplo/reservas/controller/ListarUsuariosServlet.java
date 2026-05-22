package com.exemplo.reservas.controller;

import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplo.reservas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarUsuarios")
public class ListarUsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            List<Usuario> usuarios = dao.listarTodos();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/jsp/usuarios/listarUsuarios.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=Erro ao carregar usuários");
        }
    }
}