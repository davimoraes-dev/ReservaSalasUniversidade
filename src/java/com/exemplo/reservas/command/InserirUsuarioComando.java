package com.exemplo.reservas.command;

import com.exemplo.reservas.dao.IUsuarioDAO;
import com.exemplo.reservas.model.Usuario;

public class InserirUsuarioComando implements Comando {
    private final IUsuarioDAO dao;
    private final Usuario usuario;

    public InserirUsuarioComando(IUsuarioDAO dao, Usuario usuario) {
        this.dao = dao;
        this.usuario = usuario;
    }

    @Override
    public void executar() throws Exception {
        dao.inserir(usuario);
    }
}