package Classes.Playlists;

import Classes.*;
import Classes.Musicas.Musica;
import Classes.Planos.PlanoPremiumTop;
import Classes.Planos.PlanoSubscricao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistFavoritosTest {

    private PlaylistFavoritos playlistFavoritos;
    private Utilizador utilizador;
    private ArrayList<Musica> musicas;

    @BeforeEach
    void setUp() {
        PlanoSubscricao plano = new PlanoPremiumTop();
        Biblioteca biblioteca = new Biblioteca();
        utilizador = new Utilizador("João", "joao@email.com", "Braga", "senha123", 100, plano, Cargo.ADMIN, biblioteca);

        Artista artista = new Artista("Artista 1", "Portugal");
        Musica m1 = new Musica("Música 1", artista, "Editora A", Arrays.asList("linha 1"), Arrays.asList("nota 1"), Genero.POP, 180, 5);
        Musica m2 = new Musica("Música 2", artista, "Editora B", Arrays.asList("linha 2"), Arrays.asList("nota 2"), Genero.ROCK, 200, 7);
        musicas = new ArrayList<>(Arrays.asList(m1, m2));

        playlistFavoritos = new PlaylistFavoritos("Minha Playlist Favorita", utilizador, LocalDate.now(), true, musicas);
    }

    @Test
    void testConstrutorPorOmissao() {
        PlaylistFavoritos p = new PlaylistFavoritos();
        assertEquals("", p.getNome());
        assertNull(p.getUtilizador());
        assertNull(p.getDataCriacao());
        assertFalse(p.getPublica());
        assertTrue(p.getListaMusicas().isEmpty());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Minha Playlist Favorita", playlistFavoritos.getNome());
        assertEquals(utilizador, playlistFavoritos.getUtilizador());
        assertEquals(musicas, playlistFavoritos.getListaMusicas());
        assertTrue(playlistFavoritos.getPublica());
    }

    @Test
    void testConstrutorCopia() {
        PlaylistFavoritos copia = new PlaylistFavoritos(playlistFavoritos);

        assertEquals(playlistFavoritos, copia);
        assertNotSame(playlistFavoritos, copia);
        assertNotSame(playlistFavoritos.getListaMusicas(), copia.getListaMusicas());
        assertEquals(playlistFavoritos.getListaMusicas(), copia.getListaMusicas());

    }

    @Test
    void testGetters() {
        assertEquals("Minha Playlist Favorita", playlistFavoritos.getNome());
        assertEquals(utilizador, playlistFavoritos.getUtilizador());
        assertEquals(musicas, playlistFavoritos.getListaMusicas());
        assertTrue(playlistFavoritos.getPublica());
    }

    @Test
    void testSetters() {
        playlistFavoritos.setNome("Nova Playlist Favorita");
        playlistFavoritos.setPublica(false);

        assertEquals("Nova Playlist Favorita", playlistFavoritos.getNome());
        assertFalse(playlistFavoritos.getPublica());
    }

    @Test
    void testToString() {
        String str = playlistFavoritos.toString();
        assertTrue(str.contains("Minha Playlist Favorita"));
        assertTrue(str.contains("João"));
    }

    @Test
    void testEquals() {
        PlaylistFavoritos p2 = new PlaylistFavoritos("Minha Playlist Favorita", utilizador, LocalDate.now(), true, musicas);
        assertEquals(playlistFavoritos, p2);

        PlaylistFavoritos p3 = new PlaylistFavoritos("Outra Playlist", utilizador, LocalDate.now(), true, musicas);
        assertNotEquals(playlistFavoritos, p3);

        assertNotEquals(playlistFavoritos, null);
        assertNotEquals(playlistFavoritos, "String qualquer");
    }

    @Test
    void testClone() {
        PlaylistFavoritos clone = playlistFavoritos.clone();
        assertEquals(playlistFavoritos, clone);
        assertNotSame(playlistFavoritos, clone);
    }
}
