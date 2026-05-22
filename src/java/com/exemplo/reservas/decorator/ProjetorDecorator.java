package com.exemplo.reservas.decorator;

public class ProjetorDecorator extends EquipamentoDecorator {

    public ProjetorDecorator(SalaComponente sala) {
        super(sala);
    }

    @Override
    public String getDescricao() {
        String base = salaDecorada.getDescricao();
        if (base.equals("Sem equipamentos")) {
            return "Projetor";
        }
        return base + ", Projetor";
    }
}
