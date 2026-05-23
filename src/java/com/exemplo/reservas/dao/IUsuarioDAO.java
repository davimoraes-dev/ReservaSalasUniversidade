package com.exemplo.reservas.dao;

import com.exemplo.reservas.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    void inserir(Usuario usuario) throws SQLException;
    void atualizar(Usuario usuario) throws SQLException;
    void excluir(int id) throws SQLException;
    Usuario buscarPorId(int id) throws SQLException;
    Usuario buscarPorNome(String nome) throws SQLException;
    List<Usuario> listarTodos() throws SQLException;
    int contarTodos() throws SQLException;
    boolean temReservas(int id) throws SQLException;
}
