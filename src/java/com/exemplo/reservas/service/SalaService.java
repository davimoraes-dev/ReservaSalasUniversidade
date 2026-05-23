package com.exemplo.reservas.service;

import com.exemplo.reservas.command.AtualizarSalaComando;
import com.exemplo.reservas.command.ExcluirSalaComando;
import com.exemplo.reservas.command.InserirSalaComando;
import com.exemplo.reservas.dao.ISalaDAO;
import com.exemplo.reservas.dao.SalaDAO;
import com.exemplo.reservas.decorator.SalaComponente;
import com.exemplo.reservas.decorator.SalaDecoradorBuilder;
import com.exemplo.reservas.model.Sala;
import java.sql.SQLException;
import java.util.List;

public class SalaService {

    private final ISalaDAO salaDAO;

    public SalaService() {
        this.salaDAO = new SalaDAO();
    }

    public Sala buscarPorId(int id) throws SQLException {
        return salaDAO.buscarPorId(id);
    }

    public List<Sala> listarTodas() throws SQLException {
        return salaDAO.listarTodas();
    }

    public boolean podeExcluir(int id) throws SQLException {
        return !salaDAO.temReservas(id);
    }

    public void inserir(Sala sala) throws Exception {
        new InserirSalaComando(salaDAO, sala).executar();
    }

    public void atualizar(Sala sala) throws Exception {
        new AtualizarSalaComando(salaDAO, sala).executar();
    }

    public void excluir(int id) throws Exception {
        new ExcluirSalaComando(salaDAO, id).executar();
    }

    public SalaComponente construirEquipamentos(Sala sala) {
        SalaDecoradorBuilder builder = new SalaDecoradorBuilder();
        if (sala.isTemProjetor()) builder.comProjetor();
        if (sala.isTemComputador()) builder.comComputador();
        return builder.build();
    }
}
