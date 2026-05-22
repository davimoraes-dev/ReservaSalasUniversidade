package com.exemplos.reserva.factory;

import com.exemplo.reservas.model.Usuario;

public class UsuarioFactory {

    public static Usuario criar(String nome, String tipo) {
        String email = nome.trim().toLowerCase()
                .replaceAll("\\s+", ".")
                .replaceAll("[^a-z.]", "") + "@universidade.com";
        return new Usuario.Builder()
                .nome(nome.trim())
                .email(email)
                .tipo(tipo)
                .build();
    }

    public static Usuario criarComId(int id, String nome, String tipo) {
        String email = nome.trim().toLowerCase()
                .replaceAll("\\s+", ".")
                .replaceAll("[^a-z.]", "") + "@universidade.com";
        return new Usuario.Builder()
                .id(id)
                .nome(nome.trim())
                .email(email)
                .tipo(tipo)
                .build();
    }

    public static Usuario criarAluno(String nome) {
        return criar(nome, "aluno");
    }
}