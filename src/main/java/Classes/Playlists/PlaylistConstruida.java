package Classes.Playlists;

import Classes.Musicas.Musica;
import Classes.Utilizador;
import Exceptions.PlaylistVaziaException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa uma playlist criada manualmente por um utilizador Premium.
 * Permite controlar a ordem de reprodução e alternar entre modo sequencial e aleatório.
 * Suporta avanço, retrocesso e reprodução da música atual.
 */
public class PlaylistConstruida extends Playlist implements Serializable {

    private int indiceAtual = 0;
    private boolean aleatorio = false;
    private ArrayList<Musica> ordemReproducao;

    /**
     * Construtor por omissão.
     * Inicializa a playlist com valores padrão.
     */
    public PlaylistConstruida(){
        super();
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome Nome da playlist.
     * @param utilizador Utilizador criador da playlist.
     * @param dataCriacao Data de criação da playlist.
     * @param publica Flag que indica se a playlist é pública.
     * @param listaMusicas Lista de músicas da playlist.
     */
    public PlaylistConstruida(String nome, Utilizador utilizador, LocalDate dataCriacao,
                              boolean publica, ArrayList<Musica> listaMusicas){
        super(nome, utilizador, dataCriacao, publica, listaMusicas);
    }

    /**
     * Construtor de cópia.
     *
     * @param playlist Playlist a copiar.
     */
    public PlaylistConstruida(PlaylistConstruida playlist){
        super(playlist);
    }

    /**
     * Retorna uma string com a descrição da playlist.
     *
     * @return String formatada com os dados da playlist.
     */
    public String toString() {
        return "PlaylistConstruida{" +
                "nome='" + this.getNome() + '\'' +
                ", utilizador='" + this.getUtilizador().getNome() + '\'' +
                ", dataCriacao=" + this.getDataCriacao() +
                ", publica=" + this.getPublica() +
                ", listaMusicas=" + this.getListaMusicas().toString() +
                ", indiceAtual='" + this.getIndiceAtual() + '\'' +
                ", modoAleatorio='" + this.getAleatorio() + '\'' +
                '}';
    }

    /**
     * Retorna o índice atual da reprodução.
     *
     * @return Índice da música atual.
     */
    public int getIndiceAtual() {
        return indiceAtual;
    }

    /**
     * Indica se a playlist está em modo aleatório.
     *
     * @return true se aleatório, false se sequencial.
     */
    public boolean getAleatorio(){
        return aleatorio;
    }

    /**
     * Define o índice da música atual.
     *
     * @param indiceAtual Novo índice.
     */
    public void setIndiceAtual(int indiceAtual) {
        this.indiceAtual = indiceAtual;
    }

    /**
     * Define o modo aleatório.
     *
     * @param aleatorio true para ativar modo aleatório.
     */
    public void setAleatorio(boolean aleatorio){
        this.aleatorio = aleatorio;
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
        if((o == null) || (this.getClass() != o.getClass()))
            return false;

        PlaylistConstruida p = (PlaylistConstruida) o;
        return (this.getNome().equals(p.getNome())) && (this.getUtilizador().equals(p.getUtilizador()))
                && (this.getDataCriacao().equals(p.getDataCriacao())) && (this.getPublica() == p.getPublica())
                && (this.getListaMusicas().equals(p.getListaMusicas())) && (this.indiceAtual == p.getIndiceAtual())
                && (this.aleatorio == p.getAleatorio());
    }

    /**
     * Cria uma cópia desta playlist.
     *
     * @return Nova instância de PlaylistConstruida.
     */
    public PlaylistConstruida clone(){
        return new PlaylistConstruida(this);
    }

    /**
     * Reproduz a música atual da playlist.
     *
     * @return String com o resultado da reprodução.
     * @throws PlaylistVaziaException se a playlist estiver vazia.
     */
    public String reproduzirAtual() throws PlaylistVaziaException {
        if (ordemReproducao == null || ordemReproducao.isEmpty()) {
            throw new PlaylistVaziaException(this.getNome());
        }

        return ordemReproducao.get(indiceAtual).reproduzir();
    }

    /**
     * Avança para a próxima música na ordem de reprodução.
     * Reinicia do início se estiver no fim.
     *
     * @return String da nova música a tocar.
     */
    public String avancar() {
        if (this.indiceAtual < ordemReproducao.size() - 1) {
            this.indiceAtual++;
            return reproduzirAtual();
        } else {
            this.indiceAtual = 0;
            return reproduzirAtual();
        }
    }

    /**
     * Retrocede para a música anterior.
     * Volta ao fim se estiver na primeira.
     *
     * @return String da nova música a tocar.
     */
    public String retroceder() {
        if (this.indiceAtual > 0) {
            this.indiceAtual--;
            return reproduzirAtual();
        } else {
            this.indiceAtual = this.ordemReproducao.size() - 1;
            return reproduzirAtual();
        }
    }

    /**
     * Ativa o modo aleatório de reprodução.
     * Embaralha a ordem das músicas.
     */
    public void ativarModoAleatorio() {
        this.aleatorio = true;
        this.ordemReproducao = getListaMusicas();
        Collections.shuffle(this.ordemReproducao);
        this.indiceAtual = 0;
    }

    /**
     * Desativa o modo aleatório.
     * Volta à ordem original das músicas.
     */
    public void desativarModoAleatorio() {
        this.aleatorio = false;
        this.ordemReproducao = getListaMusicas(); // ordem normal
        this.indiceAtual = 0;
    }
}
