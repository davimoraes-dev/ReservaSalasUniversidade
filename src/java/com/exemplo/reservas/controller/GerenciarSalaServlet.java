package com.exemplo.reservas.controller;

import com.exemplo.reservas.service.SalaService;
import com.exemplos.reserva.factory.SalaFactory;
import com.exemplo.reservas.model.Sala;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gerenciarSala")
public class GerenciarSalaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        try {
            SalaService salaService = new SalaService();

            if ("novo".equals(acao)) {
                request.getRequestDispatcher("/jsp/salas/formSala.jsp").forward(request, response);

            } else if ("editar".equals(acao) && idParam != null) {
                int id = Integer.parseInt(idParam);
                Sala sala = salaService.buscarPorId(id);
                if (sala == null) {
                    response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Sala nao encontrada");
                    return;
                }
                request.setAttribute("sala", sala);
                request.getRequestDispatcher("/jsp/salas/formSala.jsp").forward(request, response);

            } else if ("excluir".equals(acao) && idParam != null) {
                int id = Integer.parseInt(idParam);
                if (!salaService.podeExcluir(id)) {
                    response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Nao e possivel excluir sala com reservas ativas");
                    return;
                }
                salaService.excluir(id);
                response.sendRedirect(request.getContextPath() + "/listarSalas?sucesso=Sala excluida com sucesso!");

            } else {
                response.sendRedirect(request.getContextPath() + "/listarSalas");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarSalas?erro=Erro ao processar operacao");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String nome = request.getParameter("nome");
        String capacidadeParam = request.getParameter("capacidade");
        String localizacao = request.getParameter("localizacao");
        boolean temProjetor = "on".equals(request.getParameter("temProjetor"));
        boolean temComputador = "on".equals(request.getParameter("temComputador"));

        if (nome == null || nome.trim().isEmpty() ||
            capacidadeParam == null || capacidadeParam.trim().isEmpty() ||
            localizacao == null || localizacao.trim().isEmpty()) {
            request.setAttribute("erro", "Todos os campos são obrigatórios");
            request.getRequestDispatcher("/jsp/salas/formSala.jsp").forward(request, response);
            return;
        }

        try {
            SalaService salaService = new SalaService();

            if (idParam != null && !idParam.isEmpty()) {
                Sala sala = SalaFactory.criarComId(Integer.parseInt(idParam), nome.trim(),
                        Integer.parseInt(capacidadeParam), localizacao.trim(),
                        temProjetor, temComputador);
                salaService.atualizar(sala);
                response.sendRedirect(request.getContextPath() + "/listarSalas?sucesso=Sala atualizada com sucesso!");
            } else {
                Sala sala = SalaFactory.criar(nome.trim(), Integer.parseInt(capacidadeParam),
                        localizacao.trim(), temProjetor, temComputador);
                salaService.inserir(sala);
                response.sendRedirect(request.getContextPath() + "/listarSalas?sucesso=Sala cadastrada com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao salvar sala");
            request.getRequestDispatcher("/jsp/salas/formSala.jsp").forward(request, response);
        }
    }
}
