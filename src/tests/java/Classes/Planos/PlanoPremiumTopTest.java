package Classes.Planos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanoPremiumTopTest {

    private PlanoPremiumTop plano;

    @BeforeEach
    void setUp() {
        plano = new PlanoPremiumTop();
    }

    @Test
    void testPontosPorMusica() {
        // Quando é uma música nova
        assertEquals(2, plano.pontosPorMusica(100, false));  // 0.025 * 100 = 2.5 → cast para 2
        assertEquals(25, plano.pontosPorMusica(1000, false)); // 0.025 * 1000 = 25
        assertEquals(1, plano.pontosPorMusica(60, false));    // 0.025 * 60 = 1.5 → cast para 1
        assertEquals(3, plano.pontosPorMusica(140, false));   // 0.025 * 140 = 3.5 → cast para 3

        // Quando já ouviu
        assertEquals(0, plano.pontosPorMusica(500, true));
        assertEquals(0, plano.pontosPorMusica(1, true));
        assertEquals(0, plano.pontosPorMusica(9999, true));
    }


    @Test
    void testPodeCriarPlaylists() {
        assertTrue(plano.podeCriarPlaylists());
    }

    @Test
    void testPodeAcederAFavoritos() {
        assertTrue(plano.podeAcederAFavoritos());
    }

    @Test
    void testGetNomePlano() {
        assertEquals("Plano PremiumTop", plano.getNomePlano());
    }
}
