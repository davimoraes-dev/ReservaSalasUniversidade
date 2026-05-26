package com.exemplo.reservas.decorator;

public class ProjetorDecorator extends EquipamentoDecorator {

    public ProjetorDecorator(SalaComponente sala) {
        super(sala);
    }

    @Override
    public String getDescricao() {
        return salaDecorada.getDescricao() + " + Projetor";
    }

    @Override
    public double custo() {
        return salaDecorada.custo() + 30.0;
    }
}
