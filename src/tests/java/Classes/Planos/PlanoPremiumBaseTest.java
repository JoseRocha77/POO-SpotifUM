package Classes.Planos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanoPremiumBaseTest {

    private PlanoPremiumBase plano;

    @BeforeEach
    void setUp() {
        plano = new PlanoPremiumBase();
    }

    @Test
    void testPontosPorMusica() {
        assertEquals(10, plano.pontosPorMusica(0, true));
        assertEquals(10, plano.pontosPorMusica(50, false));
        assertEquals(10, plano.pontosPorMusica(100, true));
    }

    @Test
    void testPodeCriarPlaylists() {
        assertTrue(plano.podeCriarPlaylists());
    }

    @Test
    void testPodeAcederAFavoritos() {
        assertFalse(plano.podeAcederAFavoritos());
    }

    @Test
    void testGetNomePlano() {
        assertEquals("Plano PremiumBase", plano.getNomePlano());
    }
}
