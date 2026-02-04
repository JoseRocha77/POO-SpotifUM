package Classes;

import Classes.Musicas.Musica;
import Classes.Genero;
import Classes.Planos.PlanoFree;
import Classes.Planos.PlanoSubscricao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReproducaoTest {

    private Utilizador utilizador;
    private Musica musica;
    private Reproducao reproducao;
    private LocalDateTime dataHora;

    @BeforeEach
    void setUp() {
        PlanoSubscricao plano = new PlanoFree();
        Biblioteca biblioteca = new Biblioteca();
        utilizador = new Utilizador("user1", "user1@email.com", "Rua 123", "password", 0, plano, Cargo.USER, new Biblioteca());
        musica = new Musica("Musica Teste", new Artista("Artista Teste", "Portugal"),
                "Editora Teste", Arrays.asList("letra1", "letra2"),
                Arrays.asList("nota1", "nota2"), Genero.POP, 180, 0);
        dataHora = LocalDateTime.of(2023, 5, 15, 14, 30);
        reproducao = new Reproducao(utilizador, musica, dataHora);
    }

    @Test
    void testConstrutorPorOmissao() {
        Reproducao r = new Reproducao();
        assertNull(r.getUtilizador());
        assertNull(r.getMusica());
        assertNull(r.getDataHora());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals(utilizador, reproducao.getUtilizador());
        assertEquals(musica, reproducao.getMusica());
        assertEquals(dataHora, reproducao.getDataHora());
    }

    @Test
    void testConstrutorDeCopia() {
        Reproducao copia = new Reproducao(reproducao);
        assertEquals(reproducao, copia);
        assertNotSame(reproducao, copia);
    }

    @Test
    void testGetters() {
        assertEquals(utilizador, reproducao.getUtilizador());
        assertEquals(musica, reproducao.getMusica());
        assertEquals(dataHora, reproducao.getDataHora());
    }

    @Test
    void testSetters() {
        Reproducao r = new Reproducao();
        Biblioteca biblioteca = new Biblioteca();
        Utilizador novoUser = new Utilizador("user2", "email2@email.com", "Rua Nova", "pass2", 0, new PlanoFree(), Cargo.USER, biblioteca);
        Musica novaMusica = new Musica("Nova Musica", new Artista("Novo Artista", "Brasil"),
                "Outra Editora", Arrays.asList("linha nova"), Arrays.asList("nota nova"), Genero.ROCK, 200, 5);
        LocalDateTime novaData = LocalDateTime.of(2024, 1, 1, 12, 0);

        r.setUtilizador(novoUser);
        r.setMusica(novaMusica);
        r.setDataHora(novaData);

        assertEquals(novoUser, r.getUtilizador());
        assertEquals(novaMusica, r.getMusica());
        assertEquals(novaData, r.getDataHora());
    }

    @Test
    void testEqualsAndClone() {
        Reproducao copia = reproducao.clone();
        assertEquals(reproducao, copia);
        assertNotSame(reproducao, copia);
    }

    @Test
    void testEquals() {
        Reproducao r2 = new Reproducao();
        assertNotEquals(reproducao, r2);
        assertNotEquals(reproducao, null);
        assertNotEquals(reproducao, "string");
    }

    @Test
    void testToString() {
        String expected = "Reproducao{" +
                "utilizador=Utilizador{" +
                "nome='user1'" +
                ", email='user1@email.com'" +
                ", morada='Rua 123'" +
                ", password='password'" +
                ", pontos=0" +
                ", planoSubscricao=Plano Free" +
                ", cargo=USER" +
                ", biblioteca=Biblioteca{playlist=[], albuns=[]}" +
                "}" +
                ", musica=Nome: Musica Teste" +
                "; Interprete: NomeArtista: Artista Teste; Pais: Portugal" +
                "; NomeEditora: Editora Teste" +
                "; Letra: [letra1, letra2]" +
                "; Musica: [nota1, nota2]" +
                "; Genero: POP" +
                "; Duracao: 180" +
                "; NumReproducoes: 0" +
                ", dataHora=2023-05-15T14:30" +
                '}';

        assertEquals(expected, reproducao.toString());
    }

}
