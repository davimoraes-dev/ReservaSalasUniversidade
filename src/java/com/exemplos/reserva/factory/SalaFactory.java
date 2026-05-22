package com.exemplos.reserva.factory;

import com.exemplo.reservas.model.Sala;

public class SalaFactory {

    public static Sala criar(String nome, int capacidade,
                             String localizacao, boolean temProjetor,
                             boolean temComputador) {
        return new Sala.Builder()
                .nome(nome)
                .capacidade(capacidade)
                .localizacao(localizacao)
                .temProjetor(temProjetor)
                .temComputador(temComputador)
                .build();
    }

    public static Sala criarComId(int id, String nome, int capacidade,
                                  String localizacao, boolean temProjetor,
                                  boolean temComputador) {
        return new Sala.Builder()
                .id(id)
                .nome(nome)
                .capacidade(capacidade)
                .localizacao(localizacao)
                .temProjetor(temProjetor)
                .temComputador(temComputador)
                .build();
    }
}