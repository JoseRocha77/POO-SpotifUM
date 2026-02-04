package Classes;

import Classes.Musicas.Musica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {
    private Album album;
    private String nome;
    private LocalDate dataLancamento;
    private Artista autor;
    private ArrayList<Musica> musicas;

    @BeforeEach
    void setUp() {
        autor = new Artista("Artista 1","Portugal");
        Musica m1 = new Musica("Musica 1", autor, "Editora A", Arrays.asList("linha 1", "linha 2"), Arrays.asList("nota 1", "nota 2"), Genero.POP, 200, 5);
        Musica m2 = new Musica("Musica 2", autor, "Editora A", Arrays.asList("linha 1", "linha 2"), Arrays.asList("nota 1", "nota 2"), Genero.FUNK, 150, 10);
        musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);
        album = new Album("Album 1",LocalDate.of(2012, 1, 2),autor,musicas);
    }
@Test
    void testConstrutorPorOmissao() {
        Album a = new Album();

        assertEquals("", a.getNome());
        assertNull(a.getDataLancamento());
        assertNull(a.getAutor());
        assertTrue(a.getMusicas().isEmpty());
    }


    @Test
    void testConstrutorParametrizado() {
        assertEquals("Album 1", album.getNome());
        assertEquals(LocalDate.of(2012, 1, 2), album.getDataLancamento());
    }

    @Test
    void testConstrutorDeCopia() {
        Album copiaConstrutor = new Album(album);
        assertEquals(album, copiaConstrutor);
        assertNotSame(album, copiaConstrutor);
    }

    @Test
    void testGetters() {
        assertEquals("Album 1", album.getNome());
        assertEquals("Artista 1", album.getAutor().getNome());
        assertEquals((LocalDate.of(2012, 1,2)), album.getDataLancamento());
        assertEquals("Musica 1", album.getMusicas().getFirst().getNome());
    }

    @Test
    void testSetters() {
        Album a = new Album();
        Artista novoArtista = new Artista("Artista 2", "Portugal");
        Musica novamusica = new Musica("NovaMusica", novoArtista, "Editora X", Arrays.asList("linha 1.5", "linha 2.5"), Arrays.asList("nota 1.5", "nota 2.5"), Genero.POP, 150, 10);
        ArrayList<Musica> musicas = new ArrayList<Musica>();
        musicas.add(novamusica);
        a.setNome("Album 2");
        a.setAutor(novoArtista);
        a.setMusicas(musicas);
        a.setDataLancamento(LocalDate.of(2020, 5, 5));

        assertEquals("Album 2", a.getNome());
        assertEquals("Artista 2", a.getAutor().getNome());
        assertEquals((LocalDate.of(2020, 5, 5)), a.getDataLancamento());
        assertEquals("NovaMusica", a.getMusicas().getFirst().getNome());
    }

    @Test
    void testadicionarMusica() {
        Musica novaMusica = new Musica("Nova Musica", autor, "Editora B",
                Arrays.asList("linha 3", "linha 4"),
                Arrays.asList("nota 3", "nota 4"),
                Genero.ROCK, 180, 8);
        int tamanhoInicial = album.getMusicas().size();
        album.adicionarMusica(novaMusica);
        assertEquals(tamanhoInicial + 1, album.getMusicas().size());
        assertEquals("Nova Musica", album.getMusicas().getLast().getNome());
        album.adicionarMusica(null);
        assertEquals(tamanhoInicial + 1, album.getMusicas().size());
    }

    @Test
    void testremoverMusica(){
        Musica musicaParaRemover = album.getMusicas().getFirst().clone();
        int tamanhoInicial = album.getMusicas().size();
        album.removerMusica(musicaParaRemover);
        assertEquals(tamanhoInicial - 1, album.getMusicas().size());
        assertFalse(album.getMusicas().contains(musicaParaRemover));
        Musica musicaInexistente = new Musica("Não existe", autor, "Editora X",
                Arrays.asList("linha 1", "linha 2"),
                Arrays.asList("nota 5", "nota 6"),
                Genero.JAZZ, 100, 40);
        album.removerMusica(musicaInexistente);
        assertEquals(tamanhoInicial - 1, album.getMusicas().size());
        album.removerMusica(null);
        assertEquals(tamanhoInicial - 1, album.getMusicas().size());
    }

    @Test
    void testClone() {
        Album copiaClone = album.clone();
        assertEquals(album, copiaClone);
        assertNotSame(album, copiaClone);
    }

    @Test
    void testEquals() {
        Album a2 = new Album();
        assertNotEquals(album, a2);
        assertNotEquals(album, null);
        assertNotEquals(album, "String qualquer");

    }

    @Test
    void testToString() {
        String expected = "NomeAlbum: Album 1; DataLancamento: 2012-01-02; Autor: NomeArtista: Artista 1; Pais: Portugal" +
                "; Musicas: [Nome: Musica 1; Interprete: NomeArtista: Artista 1; Pais: Portugal" +
                "; NomeEditora: Editora A" +
                "; Letra: [linha 1, linha 2]" +
                "; Musica: [nota 1, nota 2]" +
                "; Genero: POP" +
                "; Duracao: 200" +
                "; NumReproducoes: 5, Nome: Musica 2; Interprete: NomeArtista: Artista 1; Pais: Portugal; NomeEditora: Editora A" +
                "; Letra: [linha 1, linha 2]; Musica: [nota 1, nota 2]; Genero: FUNK; Duracao: 150; NumReproducoes: 10]";

        assertEquals(expected, album.toString());
    }
}
