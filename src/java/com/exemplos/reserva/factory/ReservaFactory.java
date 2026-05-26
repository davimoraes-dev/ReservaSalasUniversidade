package com.exemplos.reserva.factory;

import com.exemplo.reservas.model.Reserva;
import com.exemplo.reservas.model.Sala;
import com.exemplo.reservas.model.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaFactory {

    public static Reserva criar(Sala sala, Usuario usuario,
                                LocalDate data, LocalTime horaInicio,
                                LocalTime horaFim, String motivo,
                                boolean usarComputadores, boolean usarProjetor) {
        return new Reserva.Builder()
                .sala(sala)
                .usuario(usuario)
                .data(data)
                .horaInicio(horaInicio)
                .horaFim(horaFim)
                .motivo(motivo)
                .status("confirmada")
                .usarComputadores(usarComputadores)
                .usarProjetor(usarProjetor)
                .build();
    }

    public static Reserva criarComId(int id, Sala sala, Usuario usuario,
                                     LocalDate data, LocalTime horaInicio,
                                     LocalTime horaFim, String motivo,
                                     boolean usarComputadores, boolean usarProjetor) {
        return new Reserva.Builder()
                .id(id)
                .sala(sala)
                .usuario(usuario)
                .data(data)
                .horaInicio(horaInicio)
                .horaFim(horaFim)
                .motivo(motivo)
                .status("confirmada")
                .usarComputadores(usarComputadores)
                .usarProjetor(usarProjetor)
                .build();
    }
}