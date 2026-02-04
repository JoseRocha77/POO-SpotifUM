package Classes.Musicas;

import Classes.Artista;
import Classes.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MusicaTest {

    private Musica musica;
    private Artista artista;

    @BeforeEach
    void setUp() {
        artista = new Artista("Artista 1","Portugal");
        musica = new Musica(
                "Música 1",
                artista,
                "Editora A",
                Arrays.asList("linha 1", "linha 2"),
                Arrays.asList("nota 1", "nota 2"),
                Genero.POP,
                200,
                5
        );
    }

    @Test
    void testConstrutorPorOmissao() {
        Musica m = new Musica();

        assertEquals("", m.getNome());
        assertNull(m.getInterprete());
        assertEquals("", m.getNomeEditora());
        assertTrue(m.getLetra().isEmpty());
        assertTrue(m.getMusica().isEmpty());
        assertNull(m.getGenero());
        assertEquals(0, m.getDuracao());
        assertEquals(0, m.getNumReproducoes());
    }


    @Test
    void testConstrutorParametrizado() {
        assertEquals("Música 1", musica.getNome());
        assertEquals("Editora A", musica.getNomeEditora());
        assertEquals(Arrays.asList("linha 1", "linha 2"), musica.getLetra());
        assertEquals(Arrays.asList("nota 1", "nota 2"), musica.getMusica());
        assertEquals(Genero.POP, musica.getGenero());
        assertEquals(200, musica.getDuracao());
        assertEquals(5, musica.getNumReproducoes());
    }

    @Test
    void testConstrutorDeCopia() {
        Musica copiaConstrutor = new Musica(musica);
        assertEquals(musica, copiaConstrutor);
        assertNotSame(musica, copiaConstrutor);
    }


    @Test
    void testGetters() {
        assertEquals("Música 1", musica.getNome());
        assertEquals("Artista 1", musica.getInterprete().getNome());
        assertEquals("Editora A", musica.getNomeEditora());
        assertEquals(Arrays.asList("linha 1", "linha 2"), musica.getLetra());
        assertEquals(Arrays.asList("nota 1", "nota 2"), musica.getMusica());
        assertEquals(Genero.POP, musica.getGenero());
        assertEquals(200, musica.getDuracao());
        assertEquals(5, musica.getNumReproducoes());
    }

    @Test
    void testSetters() {
        Musica m = new Musica();
        Artista novoArtista = new Artista("Artista 2", "Portugal");
        m.setNome("Nova");
        m.setInterprete(novoArtista);
        m.setNomeEditora("Editora X");
        m.setLetra(Arrays.asList("verso 1", "verso 2"));
        m.setMusica(Arrays.asList("nota X", "nota Y"));
        m.setGenero(Genero.ROCK);
        m.setDuracao(300);
        m.setNumReproducoes(10);

        assertEquals("Nova", m.getNome());
        assertEquals("Artista 2", m.getInterprete().getNome());
        assertEquals("Editora X", m.getNomeEditora());
        assertEquals(Arrays.asList("verso 1", "verso 2"), m.getLetra());
        assertEquals(Arrays.asList("nota X", "nota Y"), m.getMusica());
        assertEquals(Genero.ROCK, m.getGenero());
        assertEquals(300, m.getDuracao());
        assertEquals(10, m.getNumReproducoes());
    }

    @Test
    void testReproduzir() {
        int antes = musica.getNumReproducoes();
        String resultado = musica.reproduzir();

        assertEquals(antes + 1, musica.getNumReproducoes());
        assertTrue(resultado.contains("A reproduzir: Música 1 - Artista 1"));
        assertTrue(resultado.contains("linha 1"));
        assertTrue(resultado.contains("linha 2"));
    }

    @Test
    void testClone() {
        Musica copiaClone = musica.clone();
        assertEquals(musica, copiaClone);
        assertNotSame(musica, copiaClone);
    }

    @Test
    void testEquals() {
        Musica m2 = new Musica();
        assertNotEquals(musica, m2);
        assertNotEquals(musica, null);
        assertNotEquals(musica, "String qualquer");

    }

    @Test
    void testToString() {
        String expected = "Nome: Música 1; Interprete: NomeArtista: Artista 1; Pais: Portugal" +
                "; NomeEditora: Editora A" +
                "; Letra: [linha 1, linha 2]" +
                "; Musica: [nota 1, nota 2]" +
                "; Genero: POP" +
                "; Duracao: 200" +
                "; NumReproducoes: 5";

        assertEquals(expected, musica.toString());
    }

}
