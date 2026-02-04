package Classes.Playlists;

import Classes.Musicas.Musica;
import Classes.Utilizador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa uma playlist de músicas favoritas, gerada automaticamente com base nos gostos do utilizador.
 * Esta playlist é exclusiva para utilizadores com plano PremiumTop, permitindo identificar o género musical favorito
 * e criar uma lista de músicas correspondente.
 */
public class PlaylistFavoritos extends Playlist implements Serializable {

    /**
     * Construtor por omissão.
     * Inicializa a playlist com valores padrão.
     */
    public PlaylistFavoritos() {
        super();
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome         Nome da playlist.
     * @param utilizador   Utilizador proprietário da playlist.
     * @param dataCriacao  Data de criação da playlist.
     * @param publica      Visibilidade da playlist (true se for pública).
     * @param listaMusicas Lista de músicas incluídas na playlist.
     */
    public PlaylistFavoritos(String nome, Utilizador utilizador, LocalDate dataCriacao,
                             boolean publica, ArrayList<Musica> listaMusicas) {
        super(nome, utilizador, dataCriacao, publica, listaMusicas);
    }

    /**
     * Construtor de cópia.
     *
     * @param playlist PlaylistFavoritos a copiar.
     */
    public PlaylistFavoritos(PlaylistFavoritos playlist) {
        super(playlist);
    }

    /**
     * Devolve uma representação textual da playlist.
     *
     * @return String com os dados da playlist.
     */
    @Override
    public String toString() {
        return "PlaylistFavoritos{" +
                "nome='" + this.getNome() + '\'' +
                ", utilizador='" + this.getUtilizador().getNome() + '\'' +
                ", dataCriacao=" + this.getDataCriacao() +
                ", publica=" + this.getPublica() +
                ", listaMusicas=" + this.getListaMusicas().toString() +
                '}';
    }

    /**
     * Compara esta playlist com outra para verificar igualdade.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;

        PlaylistFavoritos p = (PlaylistFavoritos) o;
        return (this.getNome().equals(p.getNome())) &&
                (this.getUtilizador().equals(p.getUtilizador())) &&
                (this.getDataCriacao().equals(p.getDataCriacao())) &&
                (this.getPublica() == p.getPublica()) &&
                (this.getListaMusicas().equals(p.getListaMusicas()));
    }

    /**
     * Cria uma cópia da playlist.
     *
     * @return Nova instância com os mesmos dados.
     */
    @Override
    public PlaylistFavoritos clone() {
        return new PlaylistFavoritos(this);
    }
}