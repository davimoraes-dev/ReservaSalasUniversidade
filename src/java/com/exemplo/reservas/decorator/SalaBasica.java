package com.exemplo.reservas.decorator;

public class SalaBasica implements SalaComponente {

    @Override
    public String getDescricao() {
        return "Sala básica";
    }

    @Override
    public double custo() {
        return 0.0;
    }
}
