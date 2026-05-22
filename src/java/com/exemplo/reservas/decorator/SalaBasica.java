package com.exemplo.reservas.decorator;

public class SalaBasica implements SalaComponente {

    @Override
    public String getDescricao() {
        return "Sem equipamentos";
    }
}
