package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.IReservaDAO;
import com.exemplo.reservas.model.Reserva;

public class InserirReservaComando implements Comando {
    private final IReservaDAO dao;
    private final Reserva reserva;

    public InserirReservaComando(IReservaDAO dao, Reserva reserva) {
        this.dao = dao;
        this.reserva = reserva;
    }

    @Override
    public void executar() throws Exception {
        dao.inserir(reserva);
    }
}