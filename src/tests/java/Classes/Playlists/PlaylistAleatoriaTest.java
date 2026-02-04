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

class PlaylistAleatoriaTest {

    private PlaylistAleatoria playlistAleatoria;
    private Utilizador utilizador;
    private ArrayList<Musica> musicas;

    @BeforeEach
    void setUp() {
        PlanoSubscricao plano = new PlanoPremiumTop();
        Biblioteca biblioteca = new Biblioteca();
        utilizador = new Utilizador("João", "joao@email.com", "Braga" ,"senha123", 100, plano,Cargo.ADMIN, biblioteca);

        Artista artista1 = new Artista("Artista 1", "Portugal");

        Musica musica1 = new Musica("Música 1", artista1, "Editora A", Arrays.asList("linha 1", "linha 2"),
                Arrays.asList("nota 1", "nota 2"), Genero.CLASSICA, 180, 5);
        Musica musica2 = new Musica("Música 2", artista1, "Editora B", Arrays.asList("linha 3", "linha 4"),
                Arrays.asList("nota 3", "nota 4"), Genero.ROCK, 200, 10);
        Musica musica3 = new Musica("Música 3", artista1, "Editora C", Arrays.asList("linha 5", "linha 6"),
                Arrays.asList("nota 5", "nota 6"), Genero.FUNK, 150, 3);

        musicas = new ArrayList<>(Arrays.asList(musica1, musica2, musica3));

        playlistAleatoria = new PlaylistAleatoria("Minha Playlist", utilizador, LocalDate.now(), true, musicas);
    }

    @Test
    void testConstrutorPorOmissao() {
        PlaylistAleatoria playlist = new PlaylistAleatoria();
        assertEquals("", playlist.getNome());
        assertNull(playlist.getUtilizador());
        assertNull(playlist.getDataCriacao());
        assertFalse(playlist.getPublica());
        assertTrue(playlist.getListaMusicas().isEmpty());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Minha Playlist", playlistAleatoria.getNome());
        assertEquals(utilizador, playlistAleatoria.getUtilizador());
        assertTrue(playlistAleatoria.getPublica());
        assertEquals(3, playlistAleatoria.getListaMusicas().size());
    }

    @Test
    void testConstrutorCopia() {
        PlaylistAleatoria copia = new PlaylistAleatoria(playlistAleatoria);

        assertEquals(playlistAleatoria, copia);
        assertNotSame(playlistAleatoria, copia);
        assertNotSame(playlistAleatoria.getListaMusicas(), copia.getListaMusicas());
        assertEquals(playlistAleatoria.getListaMusicas(), copia.getListaMusicas());

    }


    @Test
    void testGetters() {
        assertEquals("Minha Playlist", playlistAleatoria.getNome());
        assertEquals(utilizador, playlistAleatoria.getUtilizador());
        assertEquals(LocalDate.now(), playlistAleatoria.getDataCriacao());
        assertTrue(playlistAleatoria.getPublica());
        assertEquals(musicas, playlistAleatoria.getListaMusicas());
    }

    @Test
    void testSetters() {
        PlaylistAleatoria playlist = new PlaylistAleatoria();

        Utilizador novoUtilizador = new Utilizador("Maria", "maria@email.com", "Lisboa", "senha456",
                150, new PlanoPremiumTop(), Cargo.USER, new Biblioteca());

        ArrayList<Musica> novaLista = new ArrayList<>(musicas);
        LocalDate novaData = LocalDate.of(2023, 12, 25);

        playlist.setNome("Playlist Nova");
        playlist.setUtilizador(novoUtilizador);
        playlist.setDataCriacao(novaData);
        playlist.setPublica(false);
        playlist.setListaMusicas(novaLista);

        assertEquals("Playlist Nova", playlist.getNome());
        assertEquals(novoUtilizador, playlist.getUtilizador());
        assertEquals(novaData, playlist.getDataCriacao());
        assertFalse(playlist.getPublica());
        assertEquals(novaLista, playlist.getListaMusicas());
    }


    @Test
    void testToString() {
        String expected = "PlaylistAleatoria{" +
                "nome='Minha Playlist', " +
                "utilizador='João', " +
                "dataCriacao=" + playlistAleatoria.getDataCriacao() +
                ", publica=true, " +
                "listaMusicas=" + playlistAleatoria.getListaMusicas() + '}';

        assertTrue(playlistAleatoria.toString().contains("Minha Playlist"));
    }

    @Test
    void testEquals() {
        PlaylistAleatoria playlist2 = new PlaylistAleatoria("Minha Playlist", utilizador, LocalDate.now(), true, musicas);
        assertTrue(playlistAleatoria.equals(playlist2));

        PlaylistAleatoria playlist3 = new PlaylistAleatoria("Outra Playlist", utilizador, LocalDate.now(), false, musicas);
        assertFalse(playlistAleatoria.equals(playlist3));
        assertNotEquals(playlistAleatoria, null);
        assertNotEquals(playlistAleatoria, "String qualquer");
    }

    @Test
    void testClone() {
        PlaylistAleatoria clone = playlistAleatoria.clone();
        assertEquals(playlistAleatoria, clone);
        assertNotSame(playlistAleatoria, clone);
    }

    @Test
    void testEmbaralhar() {
        ArrayList<Musica> originalList = new ArrayList<>(playlistAleatoria.getListaMusicas());
        ArrayList<Musica> embaralhada = playlistAleatoria.embaralhar();

        assertNotEquals(originalList, embaralhada);
    }

    @Test
    void testReproduzir() {

        for (Musica musica : playlistAleatoria.getListaMusicas()) {
            assertNotNull(musica.reproduzir());
        }
    }

    @Test
    void testAdicionarMusica() {
        Artista artista2 = new Artista("Artista 2", "Portugal");
        Musica musica4 = new Musica("Música 4", artista2, "Editora D", Arrays.asList("linha 7", "linha 8"),
                Arrays.asList("nota 7", "nota 8"), Genero.ELETRONICA, 210, 12);

        playlistAleatoria.adicionarMusica(musica4);

        assertEquals(4, playlistAleatoria.getListaMusicas().size());
        assertTrue(playlistAleatoria.getListaMusicas().contains(musica4));
    }

    @Test
    void testRemoverMusica() {
        Musica musicaParaRemover = playlistAleatoria.getListaMusicas().get(0);

        playlistAleatoria.removerMusica(musicaParaRemover);

        assertEquals(2, playlistAleatoria.getListaMusicas().size());
        assertFalse(playlistAleatoria.getListaMusicas().contains(musicaParaRemover));
    }

    @Test
    void testGetDuracaoTotal() {
        int duracaoTotal = playlistAleatoria.getDuracaoTotal();
        assertEquals(180 + 200 + 150, duracaoTotal);
    }
}
