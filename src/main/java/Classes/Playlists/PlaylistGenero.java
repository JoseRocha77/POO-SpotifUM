package Classes.Playlists;

import Classes.Genero;
import Classes.Musicas.Musica;
import Classes.Utilizador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa uma playlist gerada com base num género musical e uma duração máxima.
 * Esta playlist é composta por músicas que pertencem a um género específico, até atingir o tempo limite definido.
 */
public class PlaylistGenero extends Playlist implements Serializable {

    private Genero genero;
    private int duracaoMaxima;

    /**
     * Construtor por omissão.
     * Inicializa os campos com valores padrão.
     */
    public PlaylistGenero(){
        super();
        this.genero = null;
        this.duracaoMaxima = 0;
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome           Nome da playlist.
     * @param utilizador     Utilizador proprietário da playlist.
     * @param dataCriacao    Data de criação da playlist.
     * @param publica        Flag que indica se a playlist é pública.
     * @param listaMusicas   Lista de músicas da playlist.
     * @param genero         Género musical da playlist.
     * @param duracaoMaxima  Duração máxima total da playlist (em segundos).
     */
    public PlaylistGenero(String nome, Utilizador utilizador, LocalDate dataCriacao,
                          boolean publica, ArrayList<Musica> listaMusicas, Genero genero, int duracaoMaxima){
        super(nome, utilizador, dataCriacao, publica, listaMusicas);
        this.genero = genero;
        this.duracaoMaxima = duracaoMaxima;
    }

    /**
     * Construtor de cópia.
     *
     * @param playlist PlaylistGenero a copiar.
     */
    public PlaylistGenero(PlaylistGenero playlist){
        super(playlist);
        this.genero = playlist.getGenero();
        this.duracaoMaxima = playlist.getDuracaoMaxima();
    }

    /**
     * Retorna o género musical da playlist.
     *
     * @return Género musical.
     */
    public Genero getGenero(){
        return this.genero;
    }

    /**
     * Retorna a duração máxima da playlist (em segundos).
     *
     * @return Duração máxima.
     */
    public int getDuracaoMaxima(){
        return this.duracaoMaxima;
    }

    /**
     * Define o género musical da playlist.
     *
     * @param genero Género a definir.
     */
    public void setGenero(Genero genero){
        this.genero = genero;
    }

    /**
     * Define a duração máxima da playlist (em segundos).
     *
     * @param duracaoMaxima Nova duração máxima.
     */
    public void setDuracaoMaxima(int duracaoMaxima){
        this.duracaoMaxima = duracaoMaxima;
    }

    /**
     * Retorna uma representação textual da playlist.
     *
     * @return String com as informações da playlist.
     */
    @Override
    public String toString() {
        return "PlaylistGenero{" +
                "nome='" + this.getNome() + '\'' +
                ", utilizador='" + this.getUtilizador().getNome() + '\'' +
                ", dataCriacao=" + this.getDataCriacao() +
                ", publica=" + this.getPublica() +
                ", listaMusicas=" + this.getListaMusicas().toString() +
                ", genero='" + this.getGenero() + '\'' +
                ", duracaoMaxima=" + this.getDuracaoMaxima() +
                '}';
    }

    /**
     * Compara esta playlist com outra para verificar igualdade.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;

        PlaylistGenero p = (PlaylistGenero) o;
        return (this.getNome().equals(p.getNome())) &&
                (this.getUtilizador().equals(p.getUtilizador())) &&
                (this.getDataCriacao().equals(p.getDataCriacao())) &&
                (this.getPublica() == p.getPublica()) &&
                (this.getListaMusicas().equals(p.getListaMusicas())) &&
                (this.getGenero().equals(p.getGenero())) &&
                (this.getDuracaoMaxima() == p.getDuracaoMaxima());
    }

    /**
     * Cria uma cópia desta playlist.
     *
     * @return Nova instância de PlaylistGenero.
     */
    @Override
    public PlaylistGenero clone(){
        return new PlaylistGenero(this);
    }
}