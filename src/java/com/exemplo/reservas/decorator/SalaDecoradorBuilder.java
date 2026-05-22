package com.exemplo.reservas.decorator;

public class SalaDecoradorBuilder {

    private SalaComponente sala;

    public SalaDecoradorBuilder() {
        this.sala = new SalaBasica();
    }

    public SalaDecoradorBuilder comProjetor() {
        this.sala = new ProjetorDecorator(this.sala);
        return this;
    }

    public SalaDecoradorBuilder comComputador() {
        this.sala = new ComputadorDecorator(this.sala);
        return this;
    }

    public SalaComponente build() {
        return this.sala;
    }
}
