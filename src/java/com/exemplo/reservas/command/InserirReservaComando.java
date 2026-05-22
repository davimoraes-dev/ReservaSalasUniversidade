package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.ReservaDAO;
import com.exemplo.reservas.model.Reserva;

public class InserirReservaComando implements Comando {
    private final ReservaDAO dao;
    private final Reserva reserva;

    public InserirReservaComando(ReservaDAO dao, Reserva reserva) {
        this.dao = dao;
        this.reserva = reserva;
    }

    @Override
    public void executar() throws Exception {
        dao.inserir(reserva);
    }
}