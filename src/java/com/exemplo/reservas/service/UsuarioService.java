package com.exemplo.reservas.service;

import com.exemplo.reservas.command.AtualizarUsuarioComando;
import com.exemplo.reservas.command.ExcluirUsuarioComando;
import com.exemplo.reservas.command.InserirUsuarioComando;
import com.exemplo.reservas.dao.IUsuarioDAO;
import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplos.reserva.factory.UsuarioFactory;
import com.exemplo.reservas.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return usuarioDAO.buscarPorId(id);
    }

    public Usuario buscarPorNome(String nome) throws SQLException {
        return usuarioDAO.buscarPorNome(nome);
    }

    public List<Usuario> listarTodos() throws SQLException {
        return usuarioDAO.listarTodos();
    }

    public boolean podeExcluir(int id) throws SQLException {
        return !usuarioDAO.temReservas(id);
    }

    public void inserir(Usuario usuario) throws Exception {
        new InserirUsuarioComando(usuarioDAO, usuario).executar();
    }

    public void atualizar(Usuario usuario) throws Exception {
        new AtualizarUsuarioComando(usuarioDAO, usuario).executar();
    }

    public void excluir(int id) throws Exception {
        new ExcluirUsuarioComando(usuarioDAO, id).executar();
    }

    public Usuario buscarOuCriarAluno(String nome) throws Exception {
        Usuario usuario = usuarioDAO.buscarPorNome(nome);
        if (usuario == null) {
            usuario = UsuarioFactory.criarAluno(nome);
            new InserirUsuarioComando(usuarioDAO, usuario).executar();
        }
        return usuario;
    }
}
