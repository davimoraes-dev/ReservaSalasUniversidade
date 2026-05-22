package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.model.Sala;

public class AtualizarSalaComando implements Comando {
    private final SalaDAO dao;
    private final Sala sala;

    public AtualizarSalaComando(SalaDAO dao, Sala sala) {
        this.dao = dao;
        this.sala = sala;
    }

    @Override
    public void executar() throws Exception {
        dao.atualizar(sala);
    }
}