package com.exemplo.reservas.model;

import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String tipo;
    private String createdAt;

    public Usuario() {}

    private Usuario(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.email = builder.email;
        this.tipo = builder.tipo;
    }

    public void setReservas(List<Reserva> reservas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static class Builder {
        private int id;
        private String nome;
        private String email;
        private String tipo;

        public Builder id(int id) { this.id = id; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder tipo(String tipo) { this.tipo = tipo; return this; }

        public Usuario build() { return new Usuario(this); }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", tipo=" + tipo + '}';
    }
}