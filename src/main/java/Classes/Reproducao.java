package Classes;

import Classes.Musicas.Musica;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe que representa uma reprodução de uma música por um utilizador no sistema.
 * Armazena o utilizador que efetuou a reprodução, a música ouvida e o instante da reprodução.
 */
public class Reproducao implements Serializable {
    private Utilizador utilizador;
    private Musica musica;
    private LocalDateTime dataHora;

    /**
     * Construtor por omissão.
     * Inicializa a reprodução com valores nulos.
     */
    public Reproducao(){
        this.utilizador = null;
        this.musica = null;
        this.dataHora = null;
    }

    /**
     * Construtor parametrizado.
     *
     * @param utilizador Utilizador que efetuou a reprodução.
     * @param musica Música reproduzida.
     * @param dataHora Data e hora da reprodução.
     */
    public Reproducao(Utilizador utilizador, Musica musica, LocalDateTime dataHora){
        this.utilizador = utilizador;
        this.musica = musica;
        this.dataHora = dataHora;
    }

    /**
     * Construtor de cópia.
     *
     * @param reproducao Reprodução a copiar.
     */
    public Reproducao(Reproducao reproducao){
        this.utilizador = reproducao.getUtilizador();
        this.musica = reproducao.getMusica();
        this.dataHora = reproducao.getDataHora();
    }

    /**
     * Retorna o utilizador da reprodução.
     *
     * @return Utilizador associado (clone defensivo).
     */
    public Utilizador getUtilizador() {
        return (this.utilizador == null) ? null : this.utilizador.clone();
    }

    /**
     * Retorna a música reproduzida.
     *
     * @return Música associada (clone defensivo).
     */
    public Musica getMusica() {
        return (this.musica == null) ? null : this.musica.clone();
    }

    /**
     * Retorna a data e hora da reprodução.
     *
     * @return Data e hora da reprodução.
     */
    public LocalDateTime getDataHora(){
        return dataHora;
    }

    /**
     * Define o utilizador da reprodução.
     *
     * @param utilizador Utilizador que ouviu a música.
     */
    public void setUtilizador(Utilizador utilizador){
        this.utilizador = utilizador;
    }

    /**
     * Define a música da reprodução.
     *
     * @param musica Música que foi ouvida.
     */
    public void setMusica(Musica musica){
        this.musica = musica;
    }

    /**
     * Define a data e hora da reprodução.
     *
     * @param dataHora Instante em que a música foi reproduzida.
     */
    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }

    /**
     * Retorna uma representação textual da reprodução.
     *
     * @return String com os dados da reprodução.
     */
    public String toString() {
        return "Reproducao{" +
                "utilizador=" + utilizador.toString() +
                ", musica=" + musica.toString() +
                ", dataHora=" + dataHora.toString() +
                '}';
    }

    /**
     * Verifica se duas reproduções são iguais.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;

        Reproducao r = (Reproducao) o;
        return (this.getUtilizador().equals(r.getUtilizador()))
                && (this.getMusica().equals(r.getMusica()))
                && (this.getDataHora().equals(r.getDataHora()));
    }

    /**
     * Cria uma cópia da reprodução.
     *
     * @return Objeto Reproducao clonado.
     */
    public Reproducao clone(){
        return new Reproducao(this);
    }
}
