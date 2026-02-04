package Classes;

import Classes.Musicas.Musica;
import Classes.Planos.PlanoPremiumTop;
import Classes.Planos.PlanoSubscricao;
import Classes.Playlists.Playlist;
import Classes.Playlists.PlaylistConstruida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    private Biblioteca biblioteca;
    private Playlist playlist1;
    private Playlist playlist2;
    private Album album1;
    private Album album2;

    @BeforeEach
    void setUp() {
        PlanoSubscricao plano = new PlanoPremiumTop();

        ArrayList<Playlist> playlists = new ArrayList<>();
        ArrayList<Album> albuns = new ArrayList<>();

        Utilizador utilizador = new Utilizador("João", "joao@email.com", "Braga", "senha123", 100, plano, Cargo.ADMIN, biblioteca);
        Artista artista = new Artista("Artista 1", "Portugal");
        Musica m1 = new Musica("Música 1", artista, "Editora A", Arrays.asList("linha 1"), Arrays.asList("nota 1"), Genero.POP, 180, 5);
        Musica m2 = new Musica("Música 2", artista, "Editora B", Arrays.asList("linha 2"), Arrays.asList("nota 2"), Genero.ROCK, 200, 7);
        ArrayList<Musica> musicas = new ArrayList<>(Arrays.asList(m1, m2));

        playlist1 = new PlaylistConstruida("Minha Playlist", utilizador, LocalDate.of(2025, 05, 13), true, musicas);

        album1 = new Album("Meu Álbum", LocalDate.of(2023, 10, 1), artista, musicas);

        playlist2 = new PlaylistConstruida("Minha Segunda Playlist", utilizador, LocalDate.now(), true, musicas);

        album2 = new Album("Outro Álbum", LocalDate.of(2024, 1, 1), artista, musicas);

        playlists.add(playlist1);
        // playlists.add(playlist2);
        albuns.add(album1);
        // albuns.add(album2);

        biblioteca = new Biblioteca(playlists, albuns);
    }


    @Test
    void testConstrutorPorOmissao() {
        Biblioteca b = new Biblioteca();
        assertNotNull(b.getPlaylist());
        assertNotNull(b.getAlbuns());
        assertTrue(b.getPlaylist().isEmpty());
        assertTrue(b.getAlbuns().isEmpty());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals(1, biblioteca.getPlaylist().size());
        assertEquals(1, biblioteca.getAlbuns().size());
        assertEquals("Minha Playlist", biblioteca.getPlaylist().get(0).getNome());
        assertEquals("Meu Álbum", biblioteca.getAlbuns().get(0).getNome());
    }

    @Test
    void testConstrutorDeCopia() {
        Biblioteca copia = new Biblioteca(biblioteca);
        assertEquals(biblioteca, copia);
        assertNotSame(biblioteca, copia);
    }

    @Test
    void testAdicionarPlaylist() {
        biblioteca.adicionarPlaylist(playlist2);
        assertEquals(2, biblioteca.getPlaylist().size());
        assertTrue(biblioteca.contemPlaylist(playlist2));
    }

    @Test
    void testAdicionarPlaylistDuplicada() {
        biblioteca.adicionarPlaylist(playlist1);
        assertEquals(1, biblioteca.getPlaylist().size()); // não adiciona duplicados
    }

    @Test
    void testAdicionarAlbum() {
        biblioteca.adicionarAlbum(album2);
        assertEquals(2, biblioteca.getAlbuns().size());
        assertTrue(biblioteca.contemAlbum(album2));
    }

    @Test
    void testAdicionarAlbumDuplicado() {
        biblioteca.adicionarAlbum(album1);
        assertEquals(1, biblioteca.getAlbuns().size()); // não adiciona duplicados
    }

    @Test
    void testRemoverPlaylist() {
        biblioteca.removePlaylist("Minha Playlist");
        assertFalse(biblioteca.contemPlaylist(playlist1));
    }

    @Test
    void testRemoverAlbum() {
        biblioteca.removeAlbum("Meu Álbum");
        assertFalse(biblioteca.contemAlbum(album1));
    }

    @Test
    void testClone() {
        Biblioteca clone = biblioteca.clone();
        assertEquals(biblioteca.toString(), clone.toString());
        assertNotSame(biblioteca, clone);
    }

    @Test
    void testToString() {
        String expected = "Biblioteca{" +
                "playlist=[PlaylistConstruida{nome='Minha Playlist', utilizador='João', dataCriacao=2025-05-13, publica=true, listaMusicas=[Nome: Música 1; Interprete: NomeArtista: Artista 1; Pais: Portugal; NomeEditora: Editora A; Letra: [linha 1]; Musica: [nota 1]; Genero: POP; Duracao: 180; NumReproducoes: 5, Nome: Música 2; Interprete: NomeArtista: Artista 1; Pais: Portugal; NomeEditora: Editora B; Letra: [linha 2]; Musica: [nota 2]; Genero: ROCK; Duracao: 200; NumReproducoes: 7], indiceAtual='0', modoAleatorio='false'}], " +
                "albuns=[NomeAlbum: Meu Álbum; DataLancamento: 2023-10-01; Autor: NomeArtista: Artista 1; Pais: Portugal; Musicas: [Nome: Música 1; Interprete: NomeArtista: Artista 1; Pais: Portugal; NomeEditora: Editora A; Letra: [linha 1]; Musica: [nota 1]; Genero: POP; Duracao: 180; NumReproducoes: 5, Nome: Música 2; Interprete: NomeArtista: Artista 1; Pais: Portugal; NomeEditora: Editora B; Letra: [linha 2]; Musica: [nota 2]; Genero: ROCK; Duracao: 200; NumReproducoes: 7]]}";

        assertEquals(expected, biblioteca.toString());
    }


    @Test
    void testContemPlaylist() {
        assertTrue(biblioteca.contemPlaylist(playlist1));
        assertFalse(biblioteca.contemPlaylist(playlist2));
    }

    @Test
    void testContemAlbum() {
        assertTrue(biblioteca.contemAlbum(album1));
        assertFalse(biblioteca.contemAlbum(album2));
    }

}
