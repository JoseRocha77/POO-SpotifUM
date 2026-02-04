package Classes.Musicas;

import Classes.Artista;
import Classes.Genero;

import java.io.Serializable;
import java.util.List;

/**
 * Classe que representa uma música com conteúdo explícito.
 * Estende a classe {@link Musica} e adiciona informação adicional
 * como o motivo do aviso e a idade mínima recomendada para ouvir a música.
 */
public class MusicaExplicita extends Musica implements Serializable {
    private String motivoAviso;
    private int idadeMinima;

    /**
     * Construtor por omissão.
     * Inicializa os campos com valores padrão.
     */
    public MusicaExplicita(){
        super();
        this.motivoAviso = "";
        this.idadeMinima = 0;
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome            Nome da música.
     * @param interprete      Artista que interpreta a música.
     * @param nomeEditora     Nome da editora.
     * @param letra           Letra da música (lista de linhas).
     * @param musica          Representação da melodia.
     * @param genero          Género musical.
     * @param duracao         Duração da música em segundos.
     * @param numReproducoes  Número de vezes que foi reproduzida.
     * @param motivoAviso     Motivo do aviso explícito.
     * @param idadeMinima     Idade mínima recomendada para ouvir.
     */
    public MusicaExplicita(String nome, Artista interprete, String nomeEditora, List<String> letra, List<String> musica,
                           Genero genero, int duracao, int numReproducoes, String motivoAviso, int idadeMinima){
        super(nome, interprete, nomeEditora, letra, musica, genero, duracao, numReproducoes);
        this.motivoAviso = motivoAviso;
        this.idadeMinima = idadeMinima;
    }

    /**
     * Construtor de cópia.
     *
     * @param musica Música explícita a copiar.
     */
    public MusicaExplicita(MusicaExplicita musica){
        super(musica);
        this.motivoAviso = musica.getMotivoAviso();
        this.idadeMinima = musica.getIdadeMinima();
    }

    /**
     * Retorna o motivo do aviso explícito.
     *
     * @return Motivo do aviso.
     */
    public String getMotivoAviso(){
        return this.motivoAviso;
    }

    /**
     * Define o motivo do aviso explícito.
     *
     * @param motivoAviso Motivo do aviso.
     */
    public void setMotivoAviso(String motivoAviso){
        this.motivoAviso = motivoAviso;
    }

    /**
     * Retorna a idade mínima recomendada para ouvir a música.
     *
     * @return Idade mínima.
     */
    public int getIdadeMinima(){
        return this.idadeMinima;
    }

    /**
     * Define a idade mínima recomendada para ouvir a música.
     *
     * @param idadeMinima Idade mínima.
     */
    public void setIdadeMinima(int idadeMinima){
        this.idadeMinima = idadeMinima;
    }

    /**
     * Retorna a representação textual da música explícita.
     *
     * @return String com os dados da música e os dados explícitos.
     */
    public String toString(){
        return super.toString() + "; motivoAviso: " + this.motivoAviso + "; idadeMinima: " + this.idadeMinima;
    }

    /**
     * Verifica se esta música é igual a outro objeto.
     *
     * @param o Objeto a comparar.
     * @return true se os objetos forem iguais.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }

        MusicaExplicita me = (MusicaExplicita) o;
        return super.equals(me) && (this.motivoAviso.equals(me.getMotivoAviso())) && (this.idadeMinima == me.getIdadeMinima());
    }

    /**
     * Cria uma cópia desta música explícita.
     *
     * @return Nova instância de {@code MusicaExplicita}.
     */
    public MusicaExplicita clone(){
        return new MusicaExplicita(this);
    }

    /**
     * Simula a reprodução da música explicita.
     * Incrementa o número de reproduções e imprime o aviso, idade mínima e letra.
     * @return String formatada com a reprodução.
     */
    @Override
    public String reproduzir() {
        return "Música Explicita - Aviso: " + this.motivoAviso + " - Idade Mínima: " + this.idadeMinima + "\n" + super.reproduzir();
    }
}