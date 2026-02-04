package Classes;

import Classes.Musicas.Musica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa um álbum musical que contém várias músicas de um determinado artista.
 * Um álbum tem um nome, uma data de lançamento, um autor (artista) e uma lista de músicas.
 */
public class Album implements Serializable {
    private String nome;
    private LocalDate dataLancamento;
    private Artista autor;
    private ArrayList<Musica> musicas;

    /**
     * Construtor por omissão.
     * Inicializa os campos com valores vazios ou nulos.
     */
    public Album(){
        this.nome = "";
        this.dataLancamento = null;
        this.autor = null;
        this.musicas = new ArrayList<>();
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome Nome do álbum.
     * @param dataLancamento Data de lançamento.
     * @param autor Artista autor do álbum.
     * @param musicas Lista de músicas do álbum.
     */
    public Album(String nome, LocalDate dataLancamento, Artista autor, ArrayList<Musica> musicas){
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.autor = autor;
        this.musicas = musicas;
    }

    /**
     * Construtor de cópia.
     *
     * @param album Álbum a copiar.
     */
    public Album(Album album){
        this.nome = album.getNome();
        this.dataLancamento = album.getDataLancamento();
        this.autor = album.getAutor();
        this.musicas = album.getMusicas();
    }

    /**
     * Retorna o nome do álbum.
     *
     * @return Nome do álbum.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a data de lançamento do álbum.
     *
     * @return Data de lançamento.
     */
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Retorna o artista autor do álbum.
     *
     * @return Autor do álbum.
     */
    public Artista getAutor() {
        return autor;
    }

    /**
     * Retorna uma cópia da lista de músicas do álbum.
     *
     * @return Lista de músicas.
     */
    public ArrayList<Musica> getMusicas() {
        ArrayList<Musica> musicasCopy = new ArrayList<>();
        for(Musica musica : this.musicas){
            musicasCopy.add(musica.clone());
        }
        return musicasCopy;
    }

    /**
     * Define o nome do álbum.
     *
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a data de lançamento do álbum.
     *
     * @param dataLancamento Nova data.
     */
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * Define o artista autor do álbum.
     *
     * @param autor Novo autor.
     */
    public void setAutor(Artista autor) {
        this.autor = autor;
    }

    /**
     * Define a lista de músicas do álbum, usando cópias defensivas.
     *
     * @param musicas Lista de músicas.
     */
    public void setMusicas(ArrayList<Musica> musicas) {
        ArrayList<Musica> newMusicas = new ArrayList<>();
        for(Musica musica : musicas){
            newMusicas.add(musica.clone());
        }
        this.musicas = newMusicas;
    }

    /**
     * Retorna uma representação textual do álbum.
     *
     * @return String com as informações do álbum.
     */
    public String toString(){
        return "NomeAlbum: " + this.nome + "; DataLancamento: " + this.dataLancamento +
                "; Autor: " + autor.toString() + "; Musicas: " + musicas.toString();
    }

    /**
     * Verifica se dois álbuns são iguais.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;

        Album a = (Album)o;
        return (this.nome.equals(a.getNome())) &&
                (this.dataLancamento.equals(a.getDataLancamento())) &&
                (this.autor.equals(a.getAutor())) &&
                (this.musicas.equals(a.musicas));
    }

    /**
     * Cria uma cópia do álbum.
     *
     * @return Novo objeto Album igual ao atual.
     */
    public Album clone(){
        return new Album(this);
    }

    /**
     * Calcula a duração total do álbum com base nas músicas.
     *
     * @return Duração total em segundos.
     */
    public int getDuracaoTotal(){
        int duracaoTotal = 0;
        if (this.musicas != null) {
            for (Musica musica : this.musicas) {
                duracaoTotal += musica.getDuracao();
            }
        }
        return duracaoTotal;
    }

    /**
     * Adiciona uma música ao álbum.
     *
     * @param musica Música a adicionar.
     */
    public void adicionarMusica(Musica musica){
        if (musica != null) {
            if (this.musicas == null) {
                this.musicas = new ArrayList<>();
            }
            this.musicas.add(musica.clone());
        }
    }

    /**
     * Remove uma música do álbum.
     *
     * @param musica Música a remover.
     */
    public void removerMusica(Musica musica){
        if (this.musicas != null && musica != null) {
            this.musicas.removeIf(m -> m.equals(musica));
        }
    }
}