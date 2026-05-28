package com.exemplo.reservas.decorator;

public class Auditorio implements SalaComponente {

    @Override
    public String getDescricao() {
        return "Auditório";
    }
}
