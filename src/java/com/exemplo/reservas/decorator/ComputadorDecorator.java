package com.exemplo.reservas.decorator;

public class ComputadorDecorator extends EquipamentoDecorator {

    public ComputadorDecorator(SalaComponente sala) {
        super(sala);
    }

    @Override
    public String getDescricao() {
        return salaDecorada.getDescricao() + " + Computador";
    }

    @Override
    public double custo() {
        return salaDecorada.custo() + 50.0;
    }
}
