package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.SalaDAO;

public class ExcluirSalaComando implements Comando {
    private final SalaDAO dao;
    private final int id;

    public ExcluirSalaComando(SalaDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        dao.excluir(id);
    }
}