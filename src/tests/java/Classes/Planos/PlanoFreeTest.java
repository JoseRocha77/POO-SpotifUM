package Classes.Planos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanoFreeTest {

    private final PlanoSubscricao plano = new PlanoFree();

    @Test
    void testPontosPorMusica() {
        assertEquals(5, plano.pontosPorMusica(0, true));
        assertEquals(5, plano.pontosPorMusica(10, false));
        assertEquals(5, plano.pontosPorMusica(100, true));
    }

    @Test
    void testPodeCriarPlaylists() {
        assertFalse(plano.podeCriarPlaylists());
    }

    @Test
    void testPodeAcederAFavoritos() {
        assertFalse(plano.podeAcederAFavoritos());
    }

    @Test
    void testGetNomePlano() {
        assertEquals("Plano Free", plano.getNomePlano());
    }
}
