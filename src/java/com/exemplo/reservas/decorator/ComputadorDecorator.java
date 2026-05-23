package com.exemplo.reservas.decorator;

public class ComputadorDecorator extends EquipamentoDecorator {

    public ComputadorDecorator(SalaComponente sala) {
        super(sala);
    }

    @Override
    public String getDescricao() {
        String base = salaDecorada.getDescricao();
        return base.equals("Sem equipamentos") ? "Computador" : base + ", Computador";
    }
}
