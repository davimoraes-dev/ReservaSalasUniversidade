package com.exemplo.reservas.model;

import com.exemplo.reservas.model.Reserva;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int id;
    private String nome;
    private int capacidade;
    private boolean temProjetor;
    private boolean temComputador;
    private String localizacao;
    private String createdAt;
    private List<Reserva> reservas = new ArrayList<>();

    public Sala() {}

    private Sala(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.capacidade = builder.capacidade;
        this.temProjetor = builder.temProjetor;
        this.temComputador = builder.temComputador;
        this.localizacao = builder.localizacao;
    }

    public static class Builder {
        private int id;
        private String nome;
        private int capacidade;
        private boolean temProjetor;
        private boolean temComputador;
        private String localizacao;

        public Builder id(int id) { this.id = id; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder capacidade(int capacidade) { this.capacidade = capacidade; return this; }
        public Builder temProjetor(boolean temProjetor) { this.temProjetor = temProjetor; return this; }
        public Builder temComputador(boolean temComputador) { this.temComputador = temComputador; return this; }
        public Builder localizacao(String localizacao) { this.localizacao = localizacao; return this; }
        public Sala build() { return new Sala(this); }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public boolean isTemProjetor() { return temProjetor; }
    public void setTemProjetor(boolean temProjetor) { this.temProjetor = temProjetor; }
    public boolean isTemComputador() { return temComputador; }
    public void setTemComputador(boolean temComputador) { this.temComputador = temComputador; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }

    @Override
    public String toString() {
        return "Sala{" + "id=" + id + ", nome=" + nome + ", capacidade=" + capacidade + '}';
    }
}