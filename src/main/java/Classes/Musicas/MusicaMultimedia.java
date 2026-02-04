package Classes.Musicas;

import Classes.Artista;
import Classes.Genero;

import java.io.Serializable;
import java.util.List;

/**
 * Classe que representa uma música com conteúdo multimédia (vídeo).
 * Estende a classe {@link Musica} e inclui informações adicionais como
 * o nome do vídeo e o seu formato.
 */
public class MusicaMultimedia extends Musica implements Serializable {

    private String nomeVideo;
    private String formato;

    /**
     * Construtor por omissão.
     * Inicializa os campos com valores vazios.
     */
    public MusicaMultimedia(){
        super();
        this.nomeVideo = "";
        this.formato = "";
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome             Nome da música.
     * @param interprete       Artista que interpreta a música.
     * @param nomeEditora      Nome da editora.
     * @param letra            Letra da música.
     * @param musica           Representação da melodia.
     * @param genero           Género musical.
     * @param duracao          Duração da música em segundos.
     * @param numReproducoes   Número de vezes que foi reproduzida.
     * @param nomeVideo        Nome do ficheiro de vídeo associado.
     * @param formato          Formato do vídeo (ex: mp4, avi).
     */
    public MusicaMultimedia(String nome, Artista interprete, String nomeEditora, List<String> letra, List<String> musica,
                            Genero genero, int duracao, int numReproducoes, String nomeVideo, String formato){
        super(nome, interprete, nomeEditora, letra, musica, genero, duracao, numReproducoes);
        this.nomeVideo = nomeVideo;
        this.formato = formato;
    }

    /**
     * Construtor de cópia.
     *
     * @param musica Instância de {@code MusicaMultimedia} a copiar.
     */
    public MusicaMultimedia(MusicaMultimedia musica){
        super(musica);
        this.nomeVideo = musica.getNomeVideo();
        this.formato = musica.getFormato();
    }

    /**
     * Retorna o nome do vídeo associado à música.
     *
     * @return Nome do vídeo.
     */
    public String getNomeVideo(){
        return this.nomeVideo;
    }

    /**
     * Define o nome do vídeo associado à música.
     *
     * @param nomeVideo Nome do vídeo.
     */
    public void setNomeVideo(String nomeVideo){
        this.nomeVideo = nomeVideo;
    }

    /**
     * Retorna o formato do vídeo.
     *
     * @return Formato do vídeo.
     */
    public String getFormato(){
        return this.formato;
    }

    /**
     * Define o formato do vídeo.
     *
     * @param formato Formato do vídeo (ex: mp4, avi).
     */
    public void setFormato(String formato){
        this.formato = formato;
    }

    /**
     * Retorna a representação textual da música multimédia.
     *
     * @return String com as informações da música, nome do vídeo e formato.
     */
    public String toString(){
        return super.toString() + "; NomeVideo: " + this.nomeVideo + "; Formato: " + this.formato;
    }

    /**
     * Compara esta música com outra para verificar igualdade.
     *
     * @param o Objeto a comparar.
     * @return {@code true} se forem iguais, {@code false} caso contrário.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }

        MusicaMultimedia mm = (MusicaMultimedia) o;
        return super.equals(o) &&
                this.nomeVideo.equals(mm.getNomeVideo()) &&
                this.formato.equals(mm.getFormato());
    }

    /**
     * Cria uma cópia desta instância de {@code MusicaMultimedia}.
     *
     * @return Nova instância com os mesmos dados.
     */
    public MusicaMultimedia clone(){
        return new MusicaMultimedia(this);
    }

    /**
     * Simula a reprodução da música multimédia.
     * Incrementa o número de reproduções e imprime o nome do vídeo e a letra.
     * @return String formatada com a reprodução.
     */
    @Override
    public String reproduzir() {
        return "Música Multimédia - Vídeo: " + this.nomeVideo + "\n" + super.reproduzir();
    }
}