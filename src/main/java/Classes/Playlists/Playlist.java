package Classes.Playlists;

import Classes.Musicas.Musica;
import Classes.Utilizador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe abstrata que representa uma Playlist.
 * Contém informações como nome, utilizador proprietário, data de criação,
 * visibilidade (pública ou privada) e a lista de músicas.
 *
 * Esta classe implementa Serializable para permitir a sua serialização.
 */
public abstract class Playlist implements Serializable {
    private String nome;
    private Utilizador utilizador;
    private LocalDate dataCriacao;
    private boolean publica;
    private ArrayList<Musica> listaMusicas;

    /**
     * Construtor por omissão.
     * Inicializa a playlist com valores padrão.
     */
    public Playlist(){
        this.nome = "";
        this.utilizador = null;
        this.dataCriacao = null;
        this.publica = false;
        this.listaMusicas = new ArrayList<>();
    }

    /**
     * Construtor parametrizado para inicializar todos os campos da playlist.
     *
     * @param nome Nome da playlist.
     * @param utilizador Utilizador que criou a playlist.
     * @param dataCriacao Data de criação da playlist.
     * @param publica Flag que indica se a playlist é pública.
     * @param listaMusicas Lista de músicas da playlist.
     */
    public Playlist(String nome, Utilizador utilizador, LocalDate dataCriacao,
                    boolean publica, ArrayList<Musica> listaMusicas){
        this.nome = nome;
        this.utilizador = utilizador;
        this.dataCriacao = dataCriacao;
        this.publica = publica;
        this.listaMusicas = listaMusicas; // CLONE ANTES DO CONSTRUTOR
    }

    /**
     * Construtor de cópia.
     *
     * @param playlist Playlist a ser copiada.
     */
    public Playlist(Playlist playlist){
        this.nome = playlist.getNome();
        this.utilizador = playlist.getUtilizador();
        this.dataCriacao = playlist.getDataCriacao();
        this.publica = playlist.getPublica();
        this.listaMusicas = playlist.getListaMusicas();
    }

    /**
     * Retorna o nome da playlist.
     *
     * @return Nome da playlist.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da playlist.
     *
     * @param nome Nome da playlist.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o utilizador que criou a playlist.
     *
     * @return Utilizador dono da playlist.
     */
    public Utilizador getUtilizador() {
        return utilizador;
    }

    /**
     * Define o utilizador dono da playlist.
     *
     * @param utilizador Utilizador.
     */
    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    /**
     * Retorna a data de criação da playlist.
     *
     * @return Data de criação.
     */
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Retorna a data de criação da playlist.
     *
     * @param dataCriacao data de criação.
     */
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * Indica se a playlist é pública.
     *
     * @return true se for pública, false caso contrário.
     */
    public boolean getPublica() {
        return publica;
    }

    /**
     * Define a visibilidade da playlist.
     *
     * @param publica true para pública, false para privada.
     */
    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    /**
     * Devolve uma cópia superficial da lista de músicas da playlist.
     *
     * @return Lista de músicas (referências).
     */
    public ArrayList<Musica> getListaMusicas() {
        ArrayList<Musica> listaMusicas = new ArrayList<>();
        for(Musica musica : this.listaMusicas){
            listaMusicas.add(musica);
        }
        return listaMusicas;
    }

    /**
     * Define a lista de músicas da playlist com cópias das músicas.
     *
     * @param listaMusicas Lista de músicas a copiar.
     */
    public void setListaMusicas(ArrayList<Musica> listaMusicas) {
        ArrayList<Musica> newListaMusicas = new ArrayList<>();
        for(Musica musica : listaMusicas){
            newListaMusicas.add(musica.clone());
        }
        this.listaMusicas = newListaMusicas;
    }

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract Playlist clone();

    /**
     * Reproduz todas as músicas da playlist pela ordem atual.
     */
    public void reproduzir(){
        for(Musica musica : this.listaMusicas){
            musica.reproduzir();
        }
    }

    /**
     * Adiciona uma música à playlist.
     *
     * @param musica Música a adicionar.
     */
    public void adicionarMusica(Musica musica){
        ArrayList<Musica> musicas = this.getListaMusicas();
        musicas.add(musica.clone());
        this.setListaMusicas(musicas);
    }

    /**
     * Remove uma música da playlist.
     *
     * @param musica Música a remover.
     */
    public void removerMusica(Musica musica){
        ArrayList<Musica> musicas = this.getListaMusicas();
        musicas.remove(musica);
        this.setListaMusicas(musicas);
    }

    /**
     * Calcula a duração total da playlist em segundos.
     *
     * @return Soma da duração de todas as músicas.
     */
    public int getDuracaoTotal(){
        int duracaoTotal = 0;
        for(Musica musica : this.listaMusicas){
            duracaoTotal += musica.getDuracao();
        }
        return duracaoTotal;
    }

}
