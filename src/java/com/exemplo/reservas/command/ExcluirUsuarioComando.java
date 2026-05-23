package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.IUsuarioDAO;

public class ExcluirUsuarioComando implements Comando {
    private final IUsuarioDAO dao;
    private final int id;

    public ExcluirUsuarioComando(IUsuarioDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        dao.excluir(id);
    }
}