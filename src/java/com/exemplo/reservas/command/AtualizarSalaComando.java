package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.ISalaDAO;
import com.exemplo.reservas.model.Sala;

public class AtualizarSalaComando implements Comando {
    private final ISalaDAO dao;
    private final Sala sala;

    public AtualizarSalaComando(ISalaDAO dao, Sala sala) {
        this.dao = dao;
        this.sala = sala;
    }

    @Override
    public void executar() throws Exception {
        dao.atualizar(sala);
    }
}