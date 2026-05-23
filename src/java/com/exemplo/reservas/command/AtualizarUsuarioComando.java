package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.IUsuarioDAO;
import com.exemplo.reservas.model.Usuario;

public class AtualizarUsuarioComando implements Comando {
    private final IUsuarioDAO dao;
    private final Usuario usuario;

    public AtualizarUsuarioComando(IUsuarioDAO dao, Usuario usuario) {
        this.dao = dao;
        this.usuario = usuario;
    }

    @Override
    public void executar() throws Exception {
        dao.atualizar(usuario);
    }
}