package Classes;

import java.io.Serializable;
/**
 * Classe que representa um artista, implementando a interface Serializable
 * para permitir a serialização de objetos.
 */
public class Artista implements Serializable {
    String nome;
    String pais;

    /**
     * Construtor por omissão (default) que inicializa as strings vazias.
     */
    public Artista(){
        this.nome = "";
        this.pais = "";
    }

    /**
     * Construtor parametrizado que recebe nome e país.
     * @param nome Nome do artista
     * @param pais País de origem do artista
     */
    public Artista(String nome, String pais){
        this.nome = nome;
        this.pais = pais;
    }

    /**
     * Construtor de cópia que cria um novo artista com base num artista existente.
     * @param artista Artista a ser copiado
     */
    public Artista(Artista artista){
        this.nome = artista.getNome();
        this.pais = artista.getPais();
    }

    // Métodos getters e setters

    /**
     * Obtém o nome do artista.
     * @return Nome do artista
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Obtém o país de origem do artista.
     * @return País do artista
     */
    public String getPais(){
        return this.pais;
    }

    /**
     * Define o nome do artista.
     * @param nome Novo nome do artista
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Define o país de origem do artista.
     * @param pais Novo país do artista
     */
    public void setPais(String pais){
        this.pais = pais;
    }

    /**
     * Representação textual do objeto Artista.
     * @return String formatada com a informação do artista
     */
    public String toString(){
        return "NomeArtista: " + this.nome + "; Pais: " + this.pais;
    }

    /**
     * Metodo para verificar igualdade entre objetos Artista.
     * @param o Objeto a comparar
     * @return true se os objetos forem iguais, false caso contrário
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;

        Artista a = (Artista)o;
        return (this.nome.equals(a.getNome())) && (this.pais.equals(a.getPais()));
    }
    /**
     * Metodo que cria e retorna uma cópia do objeto Artista.
     * @return Nova instância de Artista com os mesmos valores
     */
    public Artista clone(){
        return new Artista(this);
    }
}
