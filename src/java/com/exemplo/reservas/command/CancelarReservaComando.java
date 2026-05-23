package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.IReservaDAO;

public class CancelarReservaComando implements Comando {
    private final IReservaDAO dao;
    private final int id;

    public CancelarReservaComando(IReservaDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        dao.cancelarReserva(id);
    }
}