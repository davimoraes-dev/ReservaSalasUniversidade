package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.UsuarioDAO;

public class ExcluirUsuarioComando implements Comando {
    private final UsuarioDAO dao;
    private final int id;

    public ExcluirUsuarioComando(UsuarioDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        dao.excluir(id);
    }
}