package Classes.Musicas;

import Classes.Artista;
import Classes.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MusicaMultimediaTest {

    private MusicaMultimedia musicaMultimedia;
    private Artista artista;

    @BeforeEach
    void setUp() {
        artista = new Artista("Artista 1", "Portugal");
        musicaMultimedia = new MusicaMultimedia(
                "Música 1",
                artista,
                "Editora A",
                Arrays.asList("linha 1", "linha 2"),
                Arrays.asList("nota 1", "nota 2"),
                Genero.POP,
                200,
                5,
                "Video 1",
                "MP4"
        );
    }

    @Test
    void testConstrutorPorOmissao() {
        MusicaMultimedia mm = new MusicaMultimedia();

        assertEquals("", mm.getNome());
        assertNull(mm.getInterprete());
        assertEquals("", mm.getNomeEditora());
        assertTrue(mm.getLetra().isEmpty());
        assertTrue(mm.getMusica().isEmpty());
        assertNull(mm.getGenero());
        assertEquals(0, mm.getDuracao());
        assertEquals(0, mm.getNumReproducoes());
        assertEquals("", mm.getNomeVideo());
        assertEquals("", mm.getFormato());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Música 1", musicaMultimedia.getNome());
        assertEquals("Editora A", musicaMultimedia.getNomeEditora());
        assertEquals(Arrays.asList("linha 1", "linha 2"), musicaMultimedia.getLetra());
        assertEquals(Arrays.asList("nota 1", "nota 2"), musicaMultimedia.getMusica());
        assertEquals(Genero.POP, musicaMultimedia.getGenero());
        assertEquals(200, musicaMultimedia.getDuracao());
        assertEquals(5, musicaMultimedia.getNumReproducoes());
        assertEquals("Video 1", musicaMultimedia.getNomeVideo());
        assertEquals("MP4", musicaMultimedia.getFormato());
    }

    @Test
    void testConstrutorDeCopia() {
        MusicaMultimedia copiaConstrutor = new MusicaMultimedia(musicaMultimedia);
        assertEquals(musicaMultimedia, copiaConstrutor);
        assertNotSame(musicaMultimedia, copiaConstrutor);
    }

    @Test
    void testGetters() {
        assertEquals("Video 1", musicaMultimedia.getNomeVideo());
        assertEquals("MP4", musicaMultimedia.getFormato());
    }

    @Test
    void testSetters() {
        MusicaMultimedia mm = new MusicaMultimedia();
        mm.setNomeVideo("Video 2");
        mm.setFormato("AVI");

        assertEquals("Video 2", mm.getNomeVideo());
        assertEquals("AVI", mm.getFormato());
    }


    @Test
    void testEquals() {
        MusicaMultimedia mm2 = new MusicaMultimedia();
        assertNotEquals(musicaMultimedia, mm2);
        assertNotEquals(musicaMultimedia, null);
        assertNotEquals(musicaMultimedia, "String qualquer");
    }

    @Test
    void testClone() {
        MusicaMultimedia copiaClone = musicaMultimedia.clone();
        assertEquals(musicaMultimedia, copiaClone);
        assertNotSame(musicaMultimedia, copiaClone);
    }

    @Test
    void testToString() {
        String expected = "Nome: Música 1; Interprete: NomeArtista: Artista 1; Pais: Portugal" +
                "; NomeEditora: Editora A" +
                "; Letra: [linha 1, linha 2]" +
                "; Musica: [nota 1, nota 2]" +
                "; Genero: POP" +
                "; Duracao: 200" +
                "; NumReproducoes: 5" +
                "; NomeVideo: Video 1" +
                "; Formato: MP4";

        assertEquals(expected, musicaMultimedia.toString());
    }
}
