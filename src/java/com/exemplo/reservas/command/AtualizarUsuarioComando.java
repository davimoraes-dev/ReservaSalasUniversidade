package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplo.reservas.model.Usuario;

public class AtualizarUsuarioComando implements Comando {
    private final UsuarioDAO dao;
    private final Usuario usuario;

    public AtualizarUsuarioComando(UsuarioDAO dao, Usuario usuario) {
        this.dao = dao;
        this.usuario = usuario;
    }

    @Override
    public void executar() throws Exception {
        dao.atualizar(usuario);
    }
}