package com.exemplo.reservas.dao;

import com.exemplo.reservas.model.Sala;
import java.sql.SQLException;
import java.util.List;

public interface ISalaDAO {
    void inserir(Sala sala) throws SQLException;
    void atualizar(Sala sala) throws SQLException;
    void excluir(int id) throws SQLException;
    Sala buscarPorId(int id) throws SQLException;
    Sala buscarPorNome(String nome) throws SQLException;
    List<Sala> listarTodas() throws SQLException;
    int contarTodas() throws SQLException;
    boolean temReservas(int id) throws SQLException;
}
