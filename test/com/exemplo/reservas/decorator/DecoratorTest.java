package com.exemplo.reservas.decorator;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecoratorTest {

    

    @Test
    public void salaBasica_semEquipamentos_deveRetornarDescricaoCorreta() {
        
        SalaComponente sala = new SalaDecoradorBuilder().build();

        
        String descricao = sala.getDescricao();

        
        assertEquals("Sala básica", descricao);
    }

    @Test
    public void salaBasica_semEquipamentos_naoDeveConterNomeDeEquipamento() {
        
        SalaComponente sala = new SalaDecoradorBuilder().build();

        
        String descricao = sala.getDescricao();

        
        assertFalse(descricao.contains("Projetor"));
        assertFalse(descricao.contains("Computador"));
    }

    

    @Test
    public void projetorDecorator_comSalaBasica_deveAdicionarProjetor() {
        
        SalaComponente sala = new SalaDecoradorBuilder()
                .comProjetor()
                .build();

        
        assertEquals("Sala básica + Projetor", sala.getDescricao());
    }

    @Test
    public void projetorDecorator_comSalaBasica_naoDeveConterComputador() {
        
        SalaComponente sala = new SalaDecoradorBuilder()
                .comProjetor()
                .build();

        
        assertFalse(sala.getDescricao().contains("Computador"));
    }

    

    @Test
    public void computadorDecorator_comSalaBasica_deveAdicionarComputador() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comComputador()
                .build();

        
        assertEquals("Sala básica + Computador", sala.getDescricao());
    }

    @Test
    public void computadorDecorator_comSalaBasica_naoDeveConterProjetor() {
       
        SalaComponente sala = new SalaDecoradorBuilder()
                .comComputador()
                .build();

        
        assertFalse(sala.getDescricao().contains("Projetor"));
    }

    

    @Test
    public void decorators_comProjetorEComputador_deveListarAmbos() {
        // Arrange + Act
        SalaComponente sala = new SalaDecoradorBuilder()
                .comProjetor()
                .comComputador()
                .build();

        
        assertTrue(sala.getDescricao().contains("Projetor"));
        assertTrue(sala.getDescricao().contains("Computador"));
    }

    @Test
    public void decorators_comProjetorEComputador_naoDeveSerIgualASalaBasica() {
        
        SalaComponente salaBasica = new SalaDecoradorBuilder().build();
        SalaComponente salaDecorada = new SalaDecoradorBuilder()
                .comProjetor()
                .comComputador()
                .build();

        
        String descricaoBasica = salaBasica.getDescricao();
        String descricaoDecorada = salaDecorada.getDescricao();

        
        assertNotEquals(descricaoBasica, descricaoDecorada);
    }
}
