package Classes.Playlists;

import Classes.*;
import Classes.Musicas.Musica;
import Classes.Planos.PlanoPremiumTop;
import Classes.Planos.PlanoSubscricao;
import Exceptions.PlaylistVaziaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistConstruidaTest {

    private PlaylistConstruida playlist;
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

        playlist = new PlaylistConstruida("Minha Playlist", utilizador, LocalDate.now(), true, musicas);
        playlist.desativarModoAleatorio();
    }

    @Test
    void testConstrutorPorOmissao() {
        PlaylistConstruida p = new PlaylistConstruida();
        assertEquals("", p.getNome());
        assertNull(p.getUtilizador());
        assertNull(p.getDataCriacao());
        assertFalse(p.getPublica());
        assertTrue(p.getListaMusicas().isEmpty());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Minha Playlist", playlist.getNome());
        assertEquals(utilizador, playlist.getUtilizador());
        assertEquals(musicas, playlist.getListaMusicas());
        assertTrue(playlist.getPublica());
    }

    @Test
    void testConstrutorCopia() {
        PlaylistConstruida copia = new PlaylistConstruida(playlist);

        assertEquals(playlist, copia);
        assertNotSame(playlist, copia);
        assertNotSame(playlist.getListaMusicas(), copia.getListaMusicas());
        assertEquals(playlist.getListaMusicas(), copia.getListaMusicas());

    }

    @Test
    void testGetters() {
        assertEquals(0, playlist.getIndiceAtual());
        assertFalse(playlist.getAleatorio());
    }

    @Test
    void testSetters() {
        playlist.setIndiceAtual(1);
        playlist.setAleatorio(true);

        assertEquals(1, playlist.getIndiceAtual());
        assertTrue(playlist.getAleatorio());
    }

    @Test
    void testToString() {
        String str = playlist.toString();
        assertTrue(str.contains("Minha Playlist"));
        assertTrue(str.contains("João"));
        assertTrue(str.contains("indiceAtual"));
    }

    @Test
    void testEquals() {
        PlaylistConstruida p2 = new PlaylistConstruida("Minha Playlist", utilizador, LocalDate.now(), true, musicas);
        p2.setIndiceAtual(0);
        p2.setAleatorio(false);
        assertEquals(playlist, p2);
        assertNotEquals(playlist, null);
        assertNotEquals(playlist, "String qualquer");
    }

    @Test
    void testClone() {
        PlaylistConstruida clone = playlist.clone();
        assertEquals(playlist, clone);
        assertNotSame(playlist, clone);
    }

    @Test
    void testReproduzirAtual() throws PlaylistVaziaException {
        String resultado = playlist.reproduzirAtual();
        assertNotNull(resultado);
    }

    @Test
    void testAvancar() {
        String primeira = playlist.reproduzirAtual();
        String segunda = playlist.avancar();
        assertNotEquals(primeira, segunda);
    }

    @Test
    void testRetroceder() {
        playlist.avancar(); // índice = 1
        String anterior = playlist.retroceder(); // deve voltar à música 0
        assertEquals(playlist.reproduzirAtual(), anterior);
    }

    @Test
    void testAtivarDesativarModoAleatorio() {
        playlist.ativarModoAleatorio();
        assertTrue(playlist.getAleatorio());

        playlist.desativarModoAleatorio();
        assertFalse(playlist.getAleatorio());
    }

    @Test
    void testReproduzirAtualVazia() {
        PlaylistConstruida vazia = new PlaylistConstruida();
        vazia.desativarModoAleatorio(); // garante que ordemReproducao está null/vazia

        assertThrows(PlaylistVaziaException.class, vazia::reproduzirAtual);
    }
}
