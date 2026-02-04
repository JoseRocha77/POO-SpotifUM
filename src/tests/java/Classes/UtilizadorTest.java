package Classes;

import Classes.Musicas.Musica;
import Classes.Planos.PlanoFree;
import Classes.Planos.PlanoPremiumBase;
import Classes.Planos.PlanoPremiumTop;
import Classes.Planos.PlanoSubscricao;
import Classes.Playlists.PlaylistConstruida;
import Classes.Playlists.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorTest {

    private Utilizador utilizador;
    private PlanoSubscricao planoTop;
    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        planoTop = new PlanoPremiumTop();
        biblioteca = new Biblioteca();
        utilizador = new Utilizador("Joao", "joao@email.com", "Rua A", "1234", 50, planoTop, Cargo.USER, biblioteca);
    }

    @Test
    void testConstrutorDefault() {
        Utilizador u = new Utilizador();
        assertEquals("", u.getNome());
        assertEquals("", u.getEmail());
        assertEquals("", u.getMorada());
        assertEquals("", u.getPassword());
        assertEquals(0, u.getPontos());
        assertEquals(Cargo.USER, u.getCargo());
        assertNotNull(u.getBiblioteca());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals("Joao", utilizador.getNome());
        assertEquals("joao@email.com", utilizador.getEmail());
        assertEquals("Rua A", utilizador.getMorada());
        assertEquals("1234", utilizador.getPassword());
        assertEquals(50, utilizador.getPontos());
        assertEquals(planoTop, utilizador.getPlanoSubscricao());
        assertEquals(Cargo.USER, utilizador.getCargo());
        assertEquals(biblioteca, utilizador.getBiblioteca());
    }

    @Test
    void testClone() {
        Utilizador clone = utilizador.clone();
        assertEquals(utilizador, clone);
        assertNotSame(utilizador, clone);
    }

    @Test
    void testEquals() {
        Utilizador u2 = utilizador.clone();
        assertTrue(utilizador.equals(u2));
        assertNotEquals(utilizador, null);
        assertNotEquals(utilizador, "string");
    }

    @Test
    void testSetters() {
        PlanoPremiumBase plano = new PlanoPremiumBase();
        Cargo cargo = Cargo.ADMIN;
        Biblioteca biblioteca1 = new Biblioteca();

        utilizador.setNome("Ana");
        utilizador.setEmail("ana@email.com");
        utilizador.setMorada("Rua B");
        utilizador.setPassword("5678");
        utilizador.setPontos(70);
        utilizador.setPlanoSubscricao(plano);
        utilizador.setCargo(cargo);
        utilizador.setBiblioteca(biblioteca1);

        assertEquals("Ana", utilizador.getNome());
        assertEquals("ana@email.com", utilizador.getEmail());
        assertEquals("Rua B", utilizador.getMorada());
        assertEquals("5678", utilizador.getPassword());
        assertEquals(70, utilizador.getPontos());
        assertEquals(plano, utilizador.getPlanoSubscricao());
        assertEquals(cargo, utilizador.getCargo());
        assertEquals(biblioteca1, utilizador.getBiblioteca());

    }

    @Test
    void testSetPlanoPremiumTopAtribuiPontos() {
        utilizador.setPlanoSubscricao(new PlanoPremiumTop());
        assertEquals(100, utilizador.getPontos());
    }

    @Test
    void testSetBiblioteca() {
        PlanoSubscricao planoBase = new PlanoPremiumBase();
        Utilizador u = new Utilizador("Teste", "teste@email.com", "Cidade", "abc", 0, planoBase, Cargo.USER, new Biblioteca());
        Biblioteca nova = new Biblioteca();
        u.setBiblioteca(nova);
        assertNotNull(u.getBiblioteca());
    }

    @Test
    void testSetBibliotecaComPlanoFreeLancaExcecao() {
        Utilizador u = new Utilizador();
        u.setPlanoSubscricao(null);
        assertThrows(UnsupportedOperationException.class, () -> u.setBiblioteca(new Biblioteca()));

        PlanoSubscricao plano = new PlanoFree();
        u.setPlanoSubscricao(plano);
        assertThrows(UnsupportedOperationException.class, () -> u.setBiblioteca(new Biblioteca()));

    }

    @Test
    void testAdicionarPlaylistEAlbum() {
        Artista artista = new Artista("Artista", "PT");
        Musica m = new Musica("Nome", artista, "Editora", Arrays.asList("letra"), Arrays.asList("nota"), Genero.POP, 200, 5);

        ArrayList<Musica> musicas = new ArrayList<>();
        musicas.add(m);

        Playlist p = new PlaylistConstruida("Playlist", utilizador, LocalDate.now(), true, musicas);
        Album a = new Album("Album", LocalDate.now(), artista, musicas);

        utilizador.adicionarPlaylist(p);
        utilizador.adicionarAlbum(a);

        assertTrue(utilizador.getBiblioteca().contemPlaylist(p));
        assertTrue(utilizador.getBiblioteca().contemAlbum(a));
    }


    @Test
    void testGanharPontos() {
        int pontosAntes = utilizador.getPontos();
        utilizador.ganharPontos(false);
        assertTrue(utilizador.getPontos() > pontosAntes);
    }

    @Test
    void testIsPremium() {
        assertTrue(utilizador.isPremium());

        Utilizador u2 = new Utilizador();
        utilizador.setPlanoSubscricao(new PlanoFree());
        assertFalse(u2.isPremium());
    }

    @Test
    void testToString() {
        String expected ="Utilizador{nome='Joao', email='joao@email.com', morada='Rua A', password='1234', pontos=50, planoSubscricao=Plano PremiumTop, cargo=USER, biblioteca=Biblioteca{playlist=[], albuns=[]}}";

        assertEquals(expected, utilizador.toString());
    }
}
