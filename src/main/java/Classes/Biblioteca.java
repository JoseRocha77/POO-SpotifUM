package Classes;

import Classes.Playlists.Playlist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Representa a biblioteca de um utilizador, contendo playlists e álbuns.
 * Permite adicionar, remover, verificar e clonar conteúdos musicais.
 */
public class Biblioteca implements Serializable {

    private ArrayList<Playlist> playlists;
    private ArrayList<Album> albuns;

    /**
     * Construtor por omissão. Inicializa listas vazias.
     */
    public Biblioteca(){
        this.playlists = new ArrayList<>();
        this.albuns = new ArrayList<>();
    }

    /**
     * Construtor parametrizado.
     *
     * @param playlists Lista de playlists.
     * @param albuns Lista de álbuns.
     */
    public Biblioteca(ArrayList<Playlist> playlists, ArrayList<Album> albuns){
        this.playlists = new ArrayList<>(playlists);
        this.albuns = new ArrayList<>(albuns);
    }

    /**
     * Construtor de cópia.
     *
     * @param b Biblioteca a copiar.
     */
    public Biblioteca(Biblioteca b){
        if (b == null) {
            this.playlists = new ArrayList<>();
            this.albuns = new ArrayList<>();
        } else {
            this.playlists = b.getPlaylist();
            this.albuns = b.getAlbuns();
        }
    }

    /**
     * Adiciona uma playlist à biblioteca, se ainda não existir.
     *
     * @param p Playlist a adicionar.
     */
    public void adicionarPlaylist(Playlist p){
        if(!playlists.contains(p))
            this.playlists.add(p);
    }

    /**
     * Adiciona um álbum à biblioteca, se ainda não existir.
     *
     * @param a Álbum a adicionar.
     */
    public void adicionarAlbum(Album a){
        if(!albuns.contains(a))
            this.albuns.add(a);
    }

    /**
     * Remove uma playlist pelo nome.
     *
     * @param nomePlay Nome da playlist a remover.
     */
    public void removePlaylist(String nomePlay){
        for (Playlist p : playlists){
            if (p.getNome().equals(nomePlay)) {
                this.playlists.remove(p);
                break;
            }
        }
    }

    /**
     * Remove um álbum pelo nome.
     *
     * @param nomeAlbum Nome do álbum a remover.
     */
    public void removeAlbum(String nomeAlbum){
        for (Album a : albuns){
            if (a.getNome().equals(nomeAlbum)) {
                this.albuns.remove(a);
                break;
            }
        }
    }

    /**
     * Devolve uma cópia da lista de playlists.
     *
     * @return Lista clonada de playlists.
     */
    public ArrayList<Playlist> getPlaylist() {
        ArrayList<Playlist> copia = new ArrayList<>();
        for (Playlist p : this.playlists) {
            copia.add(p.clone());
        }
        return copia;
    }

    /**
     * Define a lista de playlists, com cópias das playlists fornecidas.
     *
     * @param playlist Lista de playlists.
     */
    public void setPlaylist(ArrayList<Playlist> playlist) {
        this.playlists = new ArrayList<>();
        for (Playlist p : playlist) {
            this.playlists.add(p.clone());
        }
    }

    /**
     * Devolve uma cópia da lista de álbuns.
     *
     * @return Lista clonada de álbuns.
     */
    public ArrayList<Album> getAlbuns() {
        ArrayList<Album> copia = new ArrayList<>();
        for (Album a : this.albuns) {
            copia.add(a.clone());
        }
        return copia;
    }

    /**
     * Define a lista de álbuns, com cópias dos álbuns fornecidos.
     *
     * @param albuns Lista de álbuns.
     */
    public void setAlbuns(ArrayList<Album> albuns) {
        this.albuns = new ArrayList<>();
        for (Album a : albuns) {
            this.albuns.add(a.clone());
        }
    }

    /**
     * Verifica se esta biblioteca é igual a outra.
     *
     * @param obj Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Biblioteca biblioteca = (Biblioteca) obj;
        return this.playlists.equals(biblioteca.playlists) && this.albuns.equals(biblioteca.albuns);
    }

    /**
     * Devolve uma representação textual da biblioteca.
     *
     * @return String com a informação da biblioteca.
     */
    @Override
    public String toString() {
        return "Biblioteca{" +
                "playlist=" + playlists.toString() +
                ", albuns=" + albuns.toString() +
                '}';
    }

    /**
     * Cria uma cópia desta biblioteca.
     *
     * @return Cópia da biblioteca.
     */
    public Biblioteca clone (){
        return new Biblioteca(this);
    }

    /**
     * Verifica se a biblioteca contém a playlist indicada.
     *
     * @param playlist Playlist a verificar.
     * @return true se existir, false caso contrário.
     */
    public boolean contemPlaylist(Playlist playlist) {
        for (Playlist p : this.playlists) {
            if (p.equals(playlist)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a biblioteca contém o álbum indicado.
     *
     * @param album Álbum a verificar.
     * @return true se existir, false caso contrário.
     */
    public boolean contemAlbum(Album album) {
        for (Album a : this.albuns) {
            if (a.equals(album)) {
                return true;
            }
        }
        return false;
    }
}
