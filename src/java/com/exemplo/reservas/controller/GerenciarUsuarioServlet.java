package com.exemplo.reservas.controller;

import com.exemplo.reservas.service.UsuarioService;
import com.exemplos.reserva.factory.UsuarioFactory;
import com.exemplo.reservas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gerenciarUsuario")
public class GerenciarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        try {
            UsuarioService usuarioService = new UsuarioService();

            if ("novo".equals(acao)) {
                request.getRequestDispatcher("/jsp/usuarios/formUsuario.jsp").forward(request, response);

            } else if ("editar".equals(acao) && idParam != null) {
                int id = Integer.parseInt(idParam);
                Usuario usuario = usuarioService.buscarPorId(id);
                if (usuario == null) {
                    response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=Usuario nao encontrado");
                    return;
                }
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/jsp/usuarios/formUsuario.jsp").forward(request, response);

            } else if ("excluir".equals(acao) && idParam != null) {
                int id = Integer.parseInt(idParam);
                if (!usuarioService.podeExcluir(id)) {
                    response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=Nao e possivel excluir usuario com reservas ativas");
                    return;
                }
                usuarioService.excluir(id);
                response.sendRedirect(request.getContextPath() + "/listarUsuarios?sucesso=Usuario excluido com sucesso!");

            } else {
                response.sendRedirect(request.getContextPath() + "/listarUsuarios");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarUsuarios?erro=Erro ao processar operacao");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");

        if (nome == null || nome.trim().isEmpty() ||
            tipo == null || tipo.trim().isEmpty()) {
            request.setAttribute("erro", "Todos os campos são obrigatórios");
            request.getRequestDispatcher("/jsp/usuarios/formUsuario.jsp").forward(request, response);
            return;
        }

        try {
            UsuarioService usuarioService = new UsuarioService();

            if (idParam != null && !idParam.isEmpty()) {
                Usuario usuario = UsuarioFactory.criarComId(Integer.parseInt(idParam), nome.trim(), tipo);
                usuarioService.atualizar(usuario);
                response.sendRedirect(request.getContextPath() + "/listarUsuarios?sucesso=Usuario atualizado!");
            } else {
                Usuario usuario = UsuarioFactory.criar(nome.trim(), tipo);
                usuarioService.inserir(usuario);
                response.sendRedirect(request.getContextPath() + "/listarUsuarios?sucesso=Usuario cadastrado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao salvar usuario");
            request.getRequestDispatcher("/jsp/usuarios/formUsuario.jsp").forward(request, response);
        }
    }
}
