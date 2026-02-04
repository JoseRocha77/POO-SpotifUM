package Classes;

import Classes.Musicas.Musica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistaTest {
    private Artista artista;
    private Artista artistaVazio;

    @BeforeEach
    void setUp() {
        artista = new Artista("Artista 1", "Portugal");
        artistaVazio = new Artista();
    }

    @Test
    void testConstrutorPorOmissao() {
        assertEquals("", artistaVazio.getNome());
        assertEquals("", artistaVazio.getPais());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Artista 1", artista.getNome());
        assertEquals("Portugal", artista.getPais());
    }

    @Test
    void testConstrutorDeCopia() {
        Artista copia = new Artista(artista);
        assertEquals(artista.getNome(), copia.getNome());
        assertEquals(artista.getPais(), copia.getPais());
        assertNotSame(artista, copia);
    }

    @Test
    void testGetters() {
        assertEquals("Artista 1", artista.getNome());
        assertEquals("Portugal", artista.getPais());
    }

    @Test
    void testSetters() {
        Artista a = new Artista();
        a.setNome("Artista 2");
        a.setPais("Brasil");

        assertEquals("Artista 2", a.getNome());
        assertEquals("Brasil", a.getPais());
    }

    @Test
    void testToString() {
        String expected = "NomeArtista: " + "Artista 1" + "; Pais: " + "Portugal";
        assertEquals(expected, artista.toString());
    }

    @Test
    void testEquals() {
        Artista mesmoArtista = new Artista("Artista 1", "Portugal");
        assertTrue(artista.equals(mesmoArtista));
        assertFalse(artista.equals(null));
        assertFalse(artista.equals("String qualquer"));
    }

    @Test
    void testClone() {
        Artista clone = artista.clone();
        assertEquals(artista.getNome(), clone.getNome());
        assertEquals(artista.getPais(), clone.getPais());
        assertNotSame(artista, clone);
    }

}
