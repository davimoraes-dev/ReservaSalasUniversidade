package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.ISalaDAO;
import com.exemplo.reservas.model.Sala;

public class InserirSalaComando implements Comando {
    private final ISalaDAO dao;
    private final Sala sala;

    public InserirSalaComando(ISalaDAO dao, Sala sala) {
        this.dao = dao;
        this.sala = sala;
    }

    @Override
    public void executar() throws Exception {
        dao.inserir(sala);
    }
}