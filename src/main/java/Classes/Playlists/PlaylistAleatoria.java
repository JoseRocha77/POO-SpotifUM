package Classes.Playlists;

import Classes.Musicas.Musica;
import Classes.Utilizador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa uma playlist do tipo aleatória.
 * Esta playlist embaralha a ordem das músicas sempre que é reproduzida.
 * Todos os planos de subscrição têm acesso a este tipo de playlist.
 */
public class PlaylistAleatoria extends Playlist implements Serializable {

    /**
     * Construtor por omissão.
     * Inicializa a playlist com valores padrão.
     */
    public PlaylistAleatoria(){
        super();
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome Nome da playlist.
     * @param utilizador Utilizador dono da playlist.
     * @param dataCriacao Data de criação da playlist.
     * @param publica Flag que indica se a playlist é pública.
     * @param listaMusicas Lista de músicas.
     */
    public PlaylistAleatoria(String nome, Utilizador utilizador, LocalDate dataCriacao,
                             boolean publica, ArrayList<Musica> listaMusicas){
        super(nome, utilizador, dataCriacao, publica, listaMusicas);
    }

    /**
     * Construtor de cópia.
     *
     * @param playlist Playlist a copiar.
     */
    public PlaylistAleatoria(PlaylistAleatoria playlist){
        super(playlist);
    }

    /**
     * Gera uma representação textual da playlist aleatória.
     *
     * @return String com as informações da playlist.
     */
    public String toString() {
        return "PlaylistAleatoria{" +
                "nome='" + this.getNome() + '\'' +
                ", utilizador='" + this.getUtilizador().getNome() + '\'' +
                ", dataCriacao=" + this.getDataCriacao() +
                ", publica=" + this.getPublica() +
                ", listaMusicas=" + this.getListaMusicas().toString() +
                '}';
    }

    /**
     * Compara esta playlist com outra.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if((o == null) || this.getClass() != o.getClass())
            return false;

        PlaylistAleatoria p = (PlaylistAleatoria) o;
        return (this.getNome().equals(p.getNome())) && (this.getUtilizador().equals(p.getUtilizador()))
                && (this.getDataCriacao().equals(p.getDataCriacao())) && (this.getPublica() == p.getPublica())
                && (this.getListaMusicas().equals(p.getListaMusicas()));
    }

    public PlaylistAleatoria clone(){
        return new PlaylistAleatoria(this);
    }

    /**
     * Devolve uma cópia embaralhada das músicas da playlist.
     *
     * @return Lista de músicas em ordem aleatória.
     */
    public ArrayList<Musica> embaralhar(){
        ArrayList<Musica> embaralhada = this.getListaMusicas(); // cópia segura
        Collections.shuffle(embaralhada);
        return embaralhada;
    }

    /**
     * Reproduz a playlist em ordem aleatória.
     * Cada reprodução da playlist gera uma nova ordem.
     */
    public void reproduzir(){
        ArrayList<Musica> playlist = this.embaralhar();
        for(Musica musica : playlist){
            musica.reproduzir();
        }
    }

}
