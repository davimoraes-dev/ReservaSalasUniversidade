package com.exemplo.reservas.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private Sala sala;
    private Usuario usuario;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String motivo;
    private String status;
    private String createdAt;
    private boolean usarComputadores;
    private boolean usarProjetor;

    public Reserva() {}

    public Reserva(Sala sala, Usuario usuario, LocalDate data, LocalTime horaInicio, LocalTime horaFim, String motivo, String status) {
        this.sala = sala;
        this.usuario = usuario;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.motivo = motivo;
        this.status = status;
    }

    // Construtor privado usado pelo Builder
    private Reserva(Builder builder) {
        this.id = builder.id;
        this.sala = builder.sala;
        this.usuario = builder.usuario;
        this.data = builder.data;
        this.horaInicio = builder.horaInicio;
        this.horaFim = builder.horaFim;
        this.motivo = builder.motivo;
        this.status = builder.status;
        this.usarComputadores = builder.usarComputadores;
        this.usarProjetor = builder.usarProjetor;
    }

    // Builder
    public static class Builder {
        private int id;
        private Sala sala;
        private Usuario usuario;
        private LocalDate data;
        private LocalTime horaInicio;
        private LocalTime horaFim;
        private String motivo;
        private String status = "confirmada";
        private boolean usarComputadores;
        private boolean usarProjetor;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder sala(Sala sala) {
            this.sala = sala;
            return this;
        }

        public Builder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder data(LocalDate data) {
            this.data = data;
            return this;
        }

        public Builder horaInicio(LocalTime horaInicio) {
            this.horaInicio = horaInicio;
            return this;
        }

        public Builder horaFim(LocalTime horaFim) {
            this.horaFim = horaFim;
            return this;
        }

        public Builder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder usarComputadores(boolean usarComputadores) {
            this.usarComputadores = usarComputadores;
            return this;
        }

        public Builder usarProjetor(boolean usarProjetor) {
            this.usarProjetor = usarProjetor;
            return this;
        }

        public Reserva build() {
            return new Reserva(this);
        }
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFim() { return horaFim; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public boolean isUsarComputadores() { return usarComputadores; }
    public void setUsarComputadores(boolean usarComputadores) { this.usarComputadores = usarComputadores; }
    public boolean isUsarProjetor() { return usarProjetor; }
    public void setUsarProjetor(boolean usarProjetor) { this.usarProjetor = usarProjetor; }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", sala=" + sala.getNome() + ", usuario=" + usuario.getNome() + ", data=" + data + '}';
    }
}