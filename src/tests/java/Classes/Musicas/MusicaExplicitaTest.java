package Classes.Musicas;

import Classes.Artista;
import Classes.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MusicaExplicitaTest {

    private MusicaExplicita musicaExplicita;
    private Artista artista;

    @BeforeEach
    void setUp() {
        artista = new Artista("Artista X", "Brasil");
        musicaExplicita = new MusicaExplicita(
                "Explícita",
                artista,
                "Editora Y",
                Arrays.asList("linha 1", "linha 2"),
                Arrays.asList("nota 1", "nota 2"),
                Genero.JAZZ,
                210,
                20,
                "Linguagem imprópria",
                18
        );
    }

    @Test
    void testConstrutorPorOmissao() {
        MusicaExplicita m = new MusicaExplicita();

        assertEquals("", m.getNome());
        assertNull(m.getInterprete());
        assertEquals("", m.getNomeEditora());
        assertTrue(m.getLetra().isEmpty());
        assertTrue(m.getMusica().isEmpty());
        assertNull(m.getGenero());
        assertEquals(0, m.getDuracao());
        assertEquals(0, m.getNumReproducoes());
        assertEquals("", m.getMotivoAviso());
        assertEquals(0, m.getIdadeMinima());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Explícita", musicaExplicita.getNome());
        assertEquals("Artista X", musicaExplicita.getInterprete().getNome());
        assertEquals("Editora Y", musicaExplicita.getNomeEditora());
        assertEquals(Arrays.asList("linha 1", "linha 2"), musicaExplicita.getLetra());
        assertEquals(Arrays.asList("nota 1", "nota 2"), musicaExplicita.getMusica());
        assertEquals(Genero.JAZZ, musicaExplicita.getGenero());
        assertEquals(210, musicaExplicita.getDuracao());
        assertEquals(20, musicaExplicita.getNumReproducoes());
        assertEquals("Linguagem imprópria", musicaExplicita.getMotivoAviso());
        assertEquals(18, musicaExplicita.getIdadeMinima());
    }

    @Test
    void testConstrutorDeCopia() {
        MusicaExplicita copia = new MusicaExplicita(musicaExplicita);
        assertEquals(musicaExplicita, copia);
        assertNotSame(musicaExplicita, copia);
    }

    @Test
    void testGetters() {
        assertEquals("Linguagem imprópria", musicaExplicita.getMotivoAviso());
        assertEquals(18, musicaExplicita.getIdadeMinima());
    }



    @Test
    void testSetters() {
        MusicaExplicita m = new MusicaExplicita();
        Artista novoArtista = new Artista("Novo Artista", "Angola");
        m.setMotivoAviso("Violência");
        m.setIdadeMinima(16);

        assertEquals("Violência", m.getMotivoAviso());
        assertEquals(16, m.getIdadeMinima());
    }



    @Test
    void testClone() {
        MusicaExplicita copia = musicaExplicita.clone();
        assertEquals(musicaExplicita, copia);
        assertNotSame(musicaExplicita, copia);
    }


    @Test
    void testEquals() {
        MusicaExplicita m2 = new MusicaExplicita();
        assertNotEquals(musicaExplicita, m2);
        assertNotEquals(musicaExplicita, null);
        assertNotEquals(musicaExplicita, "String qualquer");
    }

    @Test
    void testToStringCompleto() {
        String expected = "Nome: Explícita" +
                "; Interprete: NomeArtista: Artista X; Pais: Brasil" +
                "; NomeEditora: Editora Y" +
                "; Letra: [linha 1, linha 2]" +
                "; Musica: [nota 1, nota 2]" +
                "; Genero: JAZZ" +
                "; Duracao: 210" +
                "; NumReproducoes: 20" +
                "; motivoAviso: Linguagem imprópria" +
                "; idadeMinima: 18";

        assertEquals(expected, musicaExplicita.toString());
    }


}
