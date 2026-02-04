package Classes.Musicas;

import Classes.Artista;
import Classes.Genero;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa uma música no sistema.
 * Contém informações como o nome, artista, editora, letra, melodia,
 * género, duração e número de reproduções.
 */
public class Musica implements Serializable {
    private String nome;
    private Artista interprete;
    private String nomeEditora;
    private List<String> letra;
    private List<String> musica;
    private Genero genero;
    private int duracao;
    private int numReproducoes;

    /**
     * Construtor por omissão.
     * Inicializa a música com valores default.
     */
    public Musica(){
        this.nome = "";
        this.interprete = null;
        this.nomeEditora = "";
        this.letra = new ArrayList<>();
        this.musica = new ArrayList<>();
        this.genero = null;
        this.duracao = 0;
        this.numReproducoes = 0;
    }

    /**
     * Construtor parametrizado.
     * @param nome Nome da música.
     * @param interprete Artista que interpreta a música.
     * @param nomeEditora Nome da editora.
     * @param letra Lista com a letra da música.
     * @param musica Lista com a melodia ou componentes musicais.
     * @param genero Género musical.
     * @param duracao Duração da música em segundos.
     * @param numReproducoes Número de vezes que a música foi reproduzida.
     */
    public Musica(String nome, Artista interprete, String nomeEditora, List<String> letra, List<String> musica,
                  Genero genero, int duracao, int numReproducoes){
        this.nome = nome;
        this.interprete = interprete;
        this.nomeEditora = nomeEditora;
        this.letra = letra;
        this.musica = musica;
        this.genero = genero;
        this.duracao = duracao;
        this.numReproducoes = numReproducoes;
    }

    /**
     * Construtor de cópia.
     * @param musica Música a copiar.
     */
    public Musica(Musica musica){
        this.nome = musica.getNome();
        this.interprete = musica.getInterprete();
        this.nomeEditora = musica.getNomeEditora();
        this.letra = musica.getLetra();
        this.musica = musica.getMusica();
        this.genero = musica.getGenero();
        this.duracao = musica.getDuracao();
        this.numReproducoes = musica.getNumReproducoes();
    }

    /**
     * Retorna o nome da música.
     *
     * @return Nome da música.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o artista que interpreta a música.
     *
     * @return Artista da música.
     */
    public Artista getInterprete() {
        return interprete;
    }

    /**
     * Retorna o nome da editora responsável pela música.
     *
     * @return Nome da editora.
     */
    public String getNomeEditora() {
        return nomeEditora;
    }

    /**
     * Retorna a letra da música, linha por linha.
     *
     * @return Lista de linhas da letra.
     */
    public List<String> getLetra() {
        return letra;
    }

    /**
     * Retorna a composição/melodia da música.
     *
     * @return Lista de instruções musicais.
     */
    public List<String> getMusica() {
        return musica;
    }

    /**
     * Retorna o género musical da música.
     *
     * @return Género musical.
     */
    public Genero getGenero() {
        return this.genero;
    }

    /**
     * Retorna a duração da música, em segundos.
     *
     * @return Duração em segundos.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Retorna o número de vezes que a música foi reproduzida.
     *
     * @return Número de reproduções.
     */
    public int getNumReproducoes(){
        return numReproducoes;
    }

    /**
     * Define o nome da música.
     *
     * @param nome Nome da música.
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Define o artista da música.
     *
     * @param interprete Artista da música.
     */
    public void setInterprete(Artista interprete){
        this.interprete = interprete;
    }

    /**
     * Define o nome da editora responsável pela música.
     *
     * @param nomeEditora Nome da editora.
     */
    public void setNomeEditora(String nomeEditora){
        this.nomeEditora = nomeEditora;
    }

    /**
     * Define a letra da música.
     *
     * @param letra Lista de linhas da letra.
     */
    public void setLetra(List<String> letra){
        this.letra = letra;
    }

    /**
     * Define a melodia ou instruções musicais da música.
     *
     * @param musica Lista de instruções musicais.
     */
    public void setMusica(List<String> musica){
        this.musica = musica;
    }

    /**
     * Define o género musical da música.
     *
     * @param genero Género da música.
     */
    public void setGenero(Genero genero){
        this.genero = genero;
    }

    /**
     * Define a duração da música, em segundos.
     *
     * @param duracao Duração em segundos.
     */
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }

    /**
     * Define o número de reproduções da música.
     *
     * @param numReproducoes Número de vezes que a música foi reproduzida.
     */
    public void setNumReproducoes(int numReproducoes){
        this.numReproducoes = numReproducoes;
    }

    /**
     * Retorna uma representação textual da música.
     * @return String formatada com todos os dados da música.
     */
    public String toString(){
        return "Nome: " + this.nome + "; Interprete: " + this.interprete.toString() + "; NomeEditora: " + this.nomeEditora +
                "; Letra: " + this.letra + "; Musica: " + this.musica + "; Genero: " + this.genero +
                "; Duracao: " + this.duracao + "; NumReproducoes: " + this.numReproducoes;
    }

    /**
     * Verifica se duas músicas são iguais.
     * @param o Objeto a comparar.
     * @return true se os objetos forem iguais.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }

        Musica m = (Musica)o;
        return ((this.getNome().equals(m.getNome())) && (this.getInterprete().equals(m.getInterprete()))
        && (this.getNomeEditora().equals(m.getNomeEditora())) && (this.getLetra().equals(m.getLetra()))
        && (this.getMusica().equals(m.getMusica())) && (this.getGenero().equals(m.getGenero()))
        && (this.getDuracao() == m.getDuracao())) && (this.getNumReproducoes() == m.getNumReproducoes());
    }

    /**
     * Cria uma cópia da música.
     * @return Objeto Musica clonado.
     */
    public Musica clone() {
        return new Musica(this);
    }


    /**
     * Simula a reprodução da música.
     * Incrementa o número de reproduções e imprime a letra.
     * @return String formatada com a reprodução.
     */
    public String reproduzir() {
        this.numReproducoes++;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("A reproduzir: %s - %s\n", this.nome, this.getInterprete().getNome()));
        sb.append("Letra:\n");
        for (String linha : this.letra) {
            sb.append(linha).append("\n");
        }

        return sb.toString();
    }
}
