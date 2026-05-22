package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.ReservaDAO;

public class CancelarReservaComando implements Comando {
    private final ReservaDAO dao;
    private final int id;

    public CancelarReservaComando(ReservaDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        dao.cancelarReserva(id);
    }
}