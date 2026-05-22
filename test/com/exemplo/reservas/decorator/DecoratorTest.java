package com.exemplo.reservas.decorator;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecoratorTest {

    // ─── SalaBasica ───────────────────────────────────────────────

    @Test
    public void salaBasica_semEquipamentos_deveRetornarDescricaoCorreta() {
        // Arrange
        SalaComponente sala = new SalaDecoradorBuilder().build();

        // Act
        String descricao = sala.getDescricao();

        // Assert
        assertEquals("Sem equipamentos", descricao);
    }

    @Test
    public void salaBasica_semEquipamentos_naoDeveConterNomeDeEquipamento() {
        // Arrange
        SalaComponente sala = new SalaDecoradorBuilder().build();

        // Act
        String descricao = sala.getDescricao();

        // Assert
        assertFalse(descricao.contains("Projetor"));
        assertFalse(descricao.contains("Computador"));
    }

    // ─── ProjetorDecorator ────────────────────────────────────────

    @Test
    public void projetorDecorator_comSalaBasica_deveAdicionarProjetor() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comProjetor()
                .build();

        // Assert
        assertEquals("Projetor", sala.getDescricao());
    }

    @Test
    public void projetorDecorator_comSalaBasica_naoDeveConterComputador() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comProjetor()
                .build();

        // Assert
        assertFalse(sala.getDescricao().contains("Computador"));
    }

    // ─── ComputadorDecorator ──────────────────────────────────────

    @Test
    public void computadorDecorator_comSalaBasica_deveAdicionarComputador() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comComputador()
                .build();

        // Assert
        assertEquals("Computador", sala.getDescricao());
    }

    @Test
    public void computadorDecorator_comSalaBasica_naoDeveConterProjetor() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comComputador()
                .build();

        // Assert
        assertFalse(sala.getDescricao().contains("Projetor"));
    }

    // ─── Combinação de Decorators ─────────────────────────────────

    @Test
    public void decorators_comProjetorEComputador_deveListarAmbos() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comProjetor()
                .comComputador()
                .build();

        // Assert
        assertTrue(sala.getDescricao().contains("Projetor"));
        assertTrue(sala.getDescricao().contains("Computador"));
    }

    @Test
    public void decorators_comProjetorEComputador_naoDeveSerIgualASalaBasica() {
        // Arrange
        SalaComponente salaBasica = new SalaDecoradorBuilder().build();
        SalaComponente salaDecorada = new SalaDecoradorBuilder()
                .comProjetor()
                .comComputador()
                .build();

        // Act
        String descricaoBasica = salaBasica.getDescricao();
        String descricaoDecorada = salaDecorada.getDescricao();

        // Assert
        assertNotEquals(descricaoBasica, descricaoDecorada);
    }
}
