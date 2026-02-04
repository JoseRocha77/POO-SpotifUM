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

class PlaylistGeneroTest {

    private PlaylistGenero playlistGenero;
    private Utilizador utilizador;
    private ArrayList<Musica> musicas;
    private LocalDate data;
    private Genero genero;

    @BeforeEach
    void setUp() {
        PlanoSubscricao plano = new PlanoPremiumTop();
        Biblioteca biblioteca = new Biblioteca();
        utilizador = new Utilizador("Maria", "maria@email.com", "Lisboa", "1234", 100, plano, Cargo.USER, biblioteca);

        Artista artista = new Artista("Artista Exemplo", "Portugal");
        Musica m1 = new Musica("Música A", artista, "Editora1", Arrays.asList("verso1"), Arrays.asList("nota1"), Genero.POP, 150, 3);
        Musica m2 = new Musica("Música B", artista, "Editora2", Arrays.asList("verso2"), Arrays.asList("nota2"), Genero.POP, 200, 4);
        musicas = new ArrayList<>(Arrays.asList(m1, m2));

        data = LocalDate.of(2024, 5, 10);
        genero = Genero.POP;

        playlistGenero = new PlaylistGenero("Playlist Pop", utilizador, data, false, musicas, genero, 400);
    }

    @Test
    void testConstrutorPorOmissao() {
        PlaylistGenero p = new PlaylistGenero();
        assertEquals("", p.getNome());
        assertNull(p.getUtilizador());
        assertNull(p.getDataCriacao());
        assertFalse(p.getPublica());
        assertTrue(p.getListaMusicas().isEmpty());
        assertNull(p.getGenero());
        assertEquals(0, p.getDuracaoMaxima());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Playlist Pop", playlistGenero.getNome());
        assertEquals(utilizador, playlistGenero.getUtilizador());
        assertEquals(data, playlistGenero.getDataCriacao());
        assertEquals(musicas, playlistGenero.getListaMusicas());
        assertFalse(playlistGenero.getPublica());
        assertEquals(Genero.POP, playlistGenero.getGenero());
        assertEquals(400, playlistGenero.getDuracaoMaxima());
    }

    @Test
    void testConstrutorCopia() {
        PlaylistGenero copia = new PlaylistGenero(playlistGenero);

        assertEquals(playlistGenero, copia);
        assertNotSame(playlistGenero, copia);
        assertNotSame(playlistGenero.getListaMusicas(), copia.getListaMusicas());
        assertEquals(playlistGenero.getListaMusicas(), copia.getListaMusicas());

    }


    @Test
    void testGetters() {
        assertEquals("Playlist Pop", playlistGenero.getNome());
        assertEquals(utilizador, playlistGenero.getUtilizador());
        assertEquals(data, playlistGenero.getDataCriacao());
        assertEquals(musicas, playlistGenero.getListaMusicas());
        assertFalse(playlistGenero.getPublica());
        assertEquals(Genero.POP, playlistGenero.getGenero());
        assertEquals(400, playlistGenero.getDuracaoMaxima());
    }

    @Test
    void testSetters() {
        playlistGenero.setNome("Nova Playlist");
        playlistGenero.setUtilizador(new Utilizador("Pedro", "pedro@email.com", "Porto", "pass", 100, new PlanoPremiumTop(), Cargo.USER, new Biblioteca()));
        playlistGenero.setDataCriacao(LocalDate.of(2023, 1, 1));
        playlistGenero.setPublica(true);

        ArrayList<Musica> novaLista = new ArrayList<>();
        playlistGenero.setListaMusicas(novaLista);

        playlistGenero.setGenero(Genero.ROCK);
        playlistGenero.setDuracaoMaxima(500);

        assertEquals("Nova Playlist", playlistGenero.getNome());
        assertEquals("Pedro", playlistGenero.getUtilizador().getNome());
        assertEquals(LocalDate.of(2023, 1, 1), playlistGenero.getDataCriacao());
        assertTrue(playlistGenero.getPublica());
        assertEquals(novaLista, playlistGenero.getListaMusicas());
        assertEquals(Genero.ROCK, playlistGenero.getGenero());
        assertEquals(500, playlistGenero.getDuracaoMaxima());
    }



    @Test
    void testToString() {
        String str = playlistGenero.toString();
        assertTrue(str.contains("PlaylistGenero"));
        assertTrue(str.contains("POP"));
        assertTrue(str.contains("400"));
        assertTrue(str.contains("Maria"));
    }

    @Test
    void testEquals() {
        PlaylistGenero igual = new PlaylistGenero("Playlist Pop", utilizador, data, false, musicas, Genero.POP, 400);
        assertEquals(playlistGenero, igual);

        PlaylistGenero diferente = new PlaylistGenero("Outra", utilizador, data, false, musicas, Genero.ROCK, 100);
        assertNotEquals(playlistGenero, diferente);

        assertNotEquals(playlistGenero, null);
        assertNotEquals(playlistGenero, "string qualquer");
    }

    @Test
    void testClone() {
        PlaylistGenero clone = playlistGenero.clone();
        assertEquals(playlistGenero, clone);
        assertNotSame(playlistGenero, clone);
    }

}
