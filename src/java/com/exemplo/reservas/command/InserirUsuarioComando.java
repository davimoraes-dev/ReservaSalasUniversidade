package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.UsuarioDAO;
import com.exemplo.reservas.model.Usuario;

public class InserirUsuarioComando implements Comando {
    private final UsuarioDAO dao;
    private final Usuario usuario;

    public InserirUsuarioComando(UsuarioDAO dao, Usuario usuario) {
        this.dao = dao;
        this.usuario = usuario;
    }

    @Override
    public void executar() throws Exception {
        dao.inserir(usuario);
    }
}