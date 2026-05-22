package com.exemplo.reservas.decorator;

public abstract class EquipamentoDecorator implements SalaComponente {

    protected SalaComponente salaDecorada;

    public EquipamentoDecorator(SalaComponente sala) {
        this.salaDecorada = sala;
    }

    @Override
    public String getDescricao() {
        return salaDecorada.getDescricao();
    }
}
