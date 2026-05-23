package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.ISalaDAO;

public class ExcluirSalaComando implements Comando {
    private final ISalaDAO dao;
    private final int id;

    public ExcluirSalaComando(ISalaDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        dao.excluir(id);
    }
}