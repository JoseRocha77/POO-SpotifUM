package SpotifUM;

import Classes.*;
import Classes.Musicas.Musica;
import Classes.Musicas.MusicaExplicita;
import Classes.Planos.*;
import Classes.Playlists.Playlist;
import Classes.Playlists.PlaylistFavoritos;
import Classes.Playlists.PlaylistGenero;
import Exceptions.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Classe principal do modelo que representa o sistema SpotifUM.
 * Armazena e gere dados sobre utilizadores, músicas, reproduções, álbuns, playlists e artistas.
 */
public class SpotifUM implements Serializable {

    /**
     * Mapa de utilizadores, indexados pelo seu email.
     */
    private HashMap<String, Utilizador> utilizadores;

    /**
     * Lista de reproduções feitas no sistema.
     */
    private ArrayList<Reproducao> reproducoes;

    /**
     * Lista de músicas disponíveis na plataforma.
     */
    private ArrayList<Musica> musicas;

    /**
     * Lista de álbuns disponíveis.
     */
    private ArrayList<Album> albuns;

    /**
     * Lista de playlists disponíveis.
     */
    private ArrayList<Playlist> playlists;

    /**
     * Mapa de artistas disponíveis, indexados pelo nome.
     */
    private HashMap<String, Artista> artistas;

    /**
     * Construtor por omissão. Inicializa todas as estruturas de dados vazias.
     */
    public SpotifUM() {
        this.utilizadores = new HashMap<>();
        this.reproducoes = new ArrayList<>();
        this.musicas = new ArrayList<>();
        this.albuns = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.artistas = new HashMap<>();
    }

    /**
     * Construtor com parâmetros. Clona todos os objetos recebidos para manter encapsulamento.
     *
     * @param utilizadores Mapa de utilizadores.
     * @param reproducoes  Lista de reproduções.
     * @param musicas      Lista de músicas.
     * @param albuns       Lista de álbuns.
     * @param playlists    Lista de playlists.
     * @param artistas     Mapa de artistas.
     */
    public SpotifUM(HashMap<String, Utilizador> utilizadores, ArrayList<Reproducao> reproducoes,
                    ArrayList<Musica> musicas, ArrayList<Album> albuns,
                    ArrayList<Playlist> playlists, HashMap<String, Artista> artistas) {

        HashMap<String, Utilizador> newUtilizadores = new HashMap<>();
        for (Utilizador utilizador : utilizadores.values()) {
            newUtilizadores.put(utilizador.getNome(), utilizador.clone());
        }
        this.utilizadores = newUtilizadores;

        ArrayList<Reproducao> newReproducoes = new ArrayList<>();
        for (Reproducao reproducao : reproducoes) {
            newReproducoes.add(reproducao.clone());
        }
        this.reproducoes = newReproducoes;

        ArrayList<Musica> newMusicas = new ArrayList<>();
        for (Musica musica : musicas) {
            newMusicas.add(musica.clone());
        }
        this.musicas = newMusicas;

        ArrayList<Album> newAlbuns = new ArrayList<>();
        for (Album album : albuns) {
            newAlbuns.add(album.clone());
        }
        this.albuns = newAlbuns;

        ArrayList<Playlist> newPlaylists = new ArrayList<>();
        for (Playlist playlist : playlists) {
            newPlaylists.add(playlist.clone());
        }
        this.playlists = newPlaylists;

        HashMap<String, Artista> newArtistas = new HashMap<>();
        for (Artista artista : artistas.values()) {
            newArtistas.put(artista.getNome(), artista.clone());
        }
        this.artistas = newArtistas;
    }

    /**
     * Construtor de cópia.
     *
     * @param spotifum Instância a copiar.
     */
    public SpotifUM(SpotifUM spotifum) {
        this.utilizadores = spotifum.getUtilizadores();
        this.reproducoes = spotifum.getReproducoes();
        this.musicas = spotifum.getMusicas();
        this.albuns = spotifum.getAlbuns();
        this.playlists = spotifum.getPlaylists();
    }

    /**
     * Devolve uma cópia defensiva dos utilizadores.
     *
     * @return Mapa de utilizadores clonados.
     */
    public HashMap<String, Utilizador> getUtilizadores() {
        HashMap<String, Utilizador> newUtilizadores = new HashMap<>();
        for (Utilizador utilizador : this.utilizadores.values()) {
            newUtilizadores.put(utilizador.getNome(), utilizador.clone());
        }
        return newUtilizadores;
    }

    /**
     * Devolve uma cópia defensiva da lista de reproduções.
     *
     * @return Lista de reproduções clonadas.
     */
    public ArrayList<Reproducao> getReproducoes() {
        ArrayList<Reproducao> newReproducoes = new ArrayList<>();
        for (Reproducao reproducao : reproducoes) {
            newReproducoes.add(reproducao.clone());
        }
        return newReproducoes;
    }

    /**
     * Devolve uma cópia defensiva da lista de músicas.
     *
     * @return Lista de músicas clonadas.
     */
    public ArrayList<Musica> getMusicas() {
        ArrayList<Musica> newMusicas = new ArrayList<>();
        for (Musica musica : musicas) {
            newMusicas.add(musica.clone());
        }
        return newMusicas;
    }

    /**
     * Devolve uma cópia defensiva da lista de álbuns.
     *
     * @return Lista de álbuns clonados.
     */
    public ArrayList<Album> getAlbuns() {
        ArrayList<Album> newAlbuns = new ArrayList<>();
        for (Album album : albuns) {
            newAlbuns.add(album.clone());
        }
        return newAlbuns;
    }


    /**
     * Devolve uma cópia defensiva da lista de playlists.
     *
     * @return Lista de playlists clonadas.
     */
    public ArrayList<Playlist> getPlaylists() {
        ArrayList<Playlist> newPlaylists = new ArrayList<>();
        for (Playlist playlist : playlists) {
            newPlaylists.add(playlist.clone());
        }
        return newPlaylists;
    }

    /**
     * Devolve uma cópia defensiva do mapa de artistas.
     *
     * @return Mapa de artistas clonados.
     */
    public HashMap<String, Artista> getArtistas() {
        HashMap<String, Artista> newArtistas = new HashMap<>();
        for (Artista artista : this.artistas.values()) {
            newArtistas.put(artista.getNome(), artista.clone());
        }
        return newArtistas;
    }

    /**
     * Define o mapa de utilizadores através de cópia defensiva.
     *
     * @param utilizadores Mapa de utilizadores a copiar.
     */
    public void setUtilizadores(HashMap<String, Utilizador> utilizadores) {
        HashMap<String, Utilizador> newUtilizadores = new HashMap<>();
        for (Utilizador utilizador : this.utilizadores.values()) {
            newUtilizadores.put(utilizador.getNome(), utilizador.clone());
        }
        this.utilizadores = newUtilizadores;
    }

    /**
     * Define a lista de reproduções através de cópia defensiva.
     *
     * @param reproducoes Lista de reproduções.
     */
    public void setReproducoes(ArrayList<Reproducao> reproducoes) {
        ArrayList<Reproducao> newReproducoes = new ArrayList<>();
        for (Reproducao reproducao : reproducoes) {
            newReproducoes.add(reproducao.clone());
        }
        this.reproducoes = newReproducoes;
    }

    /**
     * Define a lista de músicas através de cópia defensiva.
     *
     * @param musicas Lista de músicas.
     */
    public void setMusicas(ArrayList<Musica> musicas) {
        ArrayList<Musica> newMusicas = new ArrayList<>();
        for (Musica musica : musicas) {
            newMusicas.add(musica.clone());
        }
        this.musicas = newMusicas;
    }

    /**
     * Define a lista de álbuns através de cópia defensiva.
     *
     * @param albuns Lista de álbuns.
     */
    public void setAlbuns(ArrayList<Album> albuns) {
        ArrayList<Album> newAlbuns = new ArrayList<>();
        for (Album album : albuns) {
            newAlbuns.add(album.clone());
        }
        this.albuns = newAlbuns;
    }

    /**
     * Define a lista de playlists através de cópia defensiva.
     *
     * @param playlists Lista de playlists.
     */
    public void setPlaylists(ArrayList<Playlist> playlists) {
        ArrayList<Playlist> newPlaylists = new ArrayList<>();
        for (Playlist playlist : playlists) {
            newPlaylists.add(playlist.clone());
        }
        this.playlists = newPlaylists;
    }

    /**
     * Define o mapa de artistas através de cópia defensiva.
     *
     * @param artistas Mapa de artistas.
     */
    public void setArtistas(HashMap<String, Artista> artistas) {
        HashMap<String, Artista> newArtistas = new HashMap<>();
        for (Artista artista : this.artistas.values()) {
            newArtistas.put(artista.getNome(), artista.clone());
        }
        this.artistas = newArtistas;
    }

    /**
     * Obtém um utilizador a partir do seu email.
     *
     * @param email Email do utilizador.
     * @return Utilizador correspondente ou null se não existir.
     */
    public Utilizador getUtilizadorPorEmail(String email) {
        return this.utilizadores.get(email);
    }

    /**
     * Obtém um artista a partir do seu nome.
     *
     * @param nomeArtista Nome do artista.
     * @return Artista correspondente ou null se não existir.
     */
    public Artista getArtistaPorNome(String nomeArtista) {
        return this.artistas.get(nomeArtista);
    }

    /**
     * Obtém uma música a partir do seu nome.
     *
     * @param nomeMusica Nome da música.
     * @return Música correspondente ou null se não existir.
     */
    public Musica getMusicaPorNome(String nomeMusica) {
        for (Musica musica : musicas) {
            if (musica.getNome().equals(nomeMusica)) return musica;
        }
        return null;
    }

    /**
     * Obtém um álbum a partir do seu nome.
     *
     * @param nomeAlbum Nome do álbum.
     * @return Álbum correspondente ou null se não existir.
     */
    public Album getAlbumPorNome(String nomeAlbum) {
        for (Album album : this.albuns) {
            if (album.getNome().equals(nomeAlbum)) {
                return album;
            }
        }
        return null;
    }

    /**
     * Obtém uma playlist pública ou qualquer playlist se for do próprio utilizador.
     *
     * @param nomePlaylist Nome da playlist.
     * @param email        Email do utilizador.
     * @return Playlist correspondente ou null se não existir ou não for acessível.
     */
    public Playlist getPlaylistPorNome(String nomePlaylist, String email) {
        for (Playlist playlist : playlists) {
            if (playlist.getNome().equals(nomePlaylist)) {
                if (playlist.getPublica() || playlist.getUtilizador().getEmail().equals(email)) {
                    return playlist;
                }
            }
        }
        return null;
    }

    /**
     * Obtém uma playlist a partir do seu nome (independentemente da visibilidade).
     *
     * @param nomePlaylist Nome da playlist.
     * @return Playlist correspondente ou null se não existir.
     */
    public Playlist getPlaylistPorNome(String nomePlaylist) {
        for (Playlist playlist : playlists) {
            if (playlist.getNome().equals(nomePlaylist)) {
                return playlist;
            }
        }
        return null;
    }

    /**
     * Verifica as credenciais de um utilizador.
     *
     * @param nome     Email ou nome do utilizador.
     * @param password Password do utilizador.
     * @return true se os dados forem válidos, false caso contrário.
     */
    public boolean loginUtilizador(String nome, String password) {
        Utilizador u = this.utilizadores.get(nome);
        return u != null && u.getPassword().equals(password);
    }

    /**
     * Regista um novo utilizador na plataforma.
     *
     * @param utilizador Utilizador a registar.
     * @throws EmailExistenteException Caso já exista um utilizador com o mesmo email.
     */
    public void registarUtilizador(Utilizador utilizador) throws EmailExistenteException {
        if (this.utilizadores.containsKey(utilizador.getEmail())) {
            throw new EmailExistenteException(utilizador.getEmail());
        }
        this.utilizadores.put(utilizador.getEmail(), utilizador);
    }

    /**
     * Regista um utilizador com base nos dados fornecidos por um script.
     *
     * @param email    Email do utilizador.
     * @param nome     Nome do utilizador.
     * @param morada   Morada do utilizador.
     * @param password Password do utilizador.
     * @param plano    Tipo de plano ("free", "premiumbase", "premiumtop").
     * @param cargo    Cargo do utilizador ("admin" ou "user").
     */
    public void registarUtilizadorPorScript(String email, String nome, String morada, String password, String plano, String cargo) {
        PlanoSubscricao planoSubscricao;
        Cargo tipoCargo;

        switch (plano.toLowerCase()) {
            case "free" -> planoSubscricao = new PlanoFree();
            case "premiumbase" -> planoSubscricao = new PlanoPremiumBase();
            case "premiumtop" -> planoSubscricao = new PlanoPremiumTop();
            default -> planoSubscricao = new PlanoFree();
        }

        switch (cargo.toLowerCase()) {
            case "admin":
                tipoCargo = Cargo.ADMIN;
                break;
            default:
                tipoCargo = Cargo.USER;
        }

        Utilizador u = new Utilizador(nome, email, morada, password, 0, planoSubscricao, tipoCargo, new Biblioteca());
        u.setPlanoSubscricao(planoSubscricao);
        this.utilizadores.put(email, u);
    }

    /**
     * Adiciona uma música à lista de músicas.
     *
     * @param musica Música a adicionar.
     */
    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica.clone());
    }

    /**
     * Adiciona um artista ao mapa de artistas.
     *
     * @param artista Artista a adicionar.
     */
    public void adicionarArtista(Artista artista) {
        this.artistas.put(artista.getNome(), artista.clone());
    }

    /**
     * Adiciona um álbum à lista de álbuns.
     *
     * @param album Álbum a adicionar.
     */
    public void adicionarAlbum(Album album) {
        this.albuns.add(album.clone());
    }

    /**
     * Adiciona uma playlist à lista de playlists.
     *
     * @param playlist Playlist a adicionar.
     */
    public void adicionarPlaylist(Playlist playlist) {
        this.playlists.add(playlist.clone());
    }

    /**
     * Verifica se o utilizador já ouviu uma música.
     *
     * @param email      Email do utilizador.
     * @param nomeMusica Nome da música.
     * @return true se já ouviu, false caso contrário.
     */
    public boolean jaOuviuMusica(String email, String nomeMusica) {
        for (Reproducao r : this.reproducoes) {
            if (r.getUtilizador().getEmail().equals(email) &&
                    r.getMusica().getNome().equals(nomeMusica)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reproduz uma música para um utilizador, atualizando o histórico e os pontos.
     *
     * @param email      Email do utilizador.
     * @param nomeMusica Nome da música.
     * @return Resultado da reprodução.
     * @throws MusicaInexistenteException Caso a música não exista.
     */
    public String reproduzirMusica(String email, String nomeMusica) {
        Utilizador u = this.utilizadores.get(email);

        Musica m = null;
        for (Musica musica : musicas) {
            if (musica.getNome().equals(nomeMusica)) {
                m = musica;
                break;
            }
        }

        if (m == null) throw new MusicaInexistenteException(nomeMusica);

        boolean jaOuviu = jaOuviuMusica(email, nomeMusica);
        u.ganharPontos(jaOuviu);
        this.reproducoes.add(new Reproducao(u, m, LocalDateTime.now()));
        String resultadoReproducao = m.reproduzir();

        return resultadoReproducao + "\nPontos atuais: " + u.getPontos();
    }

    /**
     * Lista todas as músicas disponíveis na plataforma.
     *
     * @return String formatada com as músicas.
     */
    public String listarMusicas() {
        StringBuilder sb = new StringBuilder();
        sb.append("== Lista de Músicas SpotifUM ==\n");
        for (Musica musica : this.musicas) {
            sb.append(musica.getNome()).append(" - ").append(musica.getInterprete().getNome()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Lista todas as playlists criadas.
     *
     * @return String formatada com os nomes das playlists.
     */
    public String listarPlaylist(String email) {
        StringBuilder sb = new StringBuilder();
        for (Playlist playlist : this.playlists) {
            if (playlist.getPublica() || playlist.getUtilizador().getEmail().equals(email)) {
                sb.append(playlist.getNome()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Lista a biblioteca de um utilizador.
     *
     * @param email Email do utilizador.
     * @return String formatada da biblioteca.
     * @throws UtilizadorFreeNaoPossuiBibliotecaException Se o utilizador for do plano Free.
     */
    public String listarBiblioteca(String email) throws UtilizadorFreeNaoPossuiBibliotecaException {
        Utilizador u = this.utilizadores.get(email);

        if (u.getPlanoSubscricao().getNomePlano().equals("Plano Free")) {
            throw new UtilizadorFreeNaoPossuiBibliotecaException(email);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Biblioteca do Utilizador ===\n");

        sb.append("Álbuns:\n");
        for (Album a : u.getBiblioteca().getAlbuns()) {
            sb.append("- ").append(a.getNome()).append(" - ").append(a.getAutor().getNome()).append("\n");
        }

        sb.append("\nPlaylists:\n");
        for (Playlist p : u.getBiblioteca().getPlaylist()) {
            sb.append("- ").append(p.getNome()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Converte uma lista de nomes de músicas em objetos Musica.
     *
     * @param listaMusicas Lista com nomes de músicas.
     * @return Lista de objetos Musica.
     */
    public ArrayList<Musica> musicasToArray(List<String> listaMusicas) {
        ArrayList<Musica> musicas = new ArrayList<>();
        for (String c : listaMusicas) {
            Musica musica = getMusicaPorNome(c);
            if (musica != null) {
                musicas.add(musica);
            } else {
                throw new MusicaInexistenteException(c);
            }
        }
        return musicas;
    }

    /**
     * Obtém o género musical favorito de um utilizador com base no histórico.
     *
     * @param utilizador Utilizador a analisar.
     * @param historico  Lista de reproduções.
     * @return Género favorito ou null se não houver dados suficientes.
     */
    public Genero obterGeneroFavorito(Utilizador utilizador, ArrayList<Reproducao> historico) {
        Map<Genero, Integer> contagem = new HashMap<>();

        for (Reproducao r : historico) {
            if (r.getUtilizador().getEmail().equals(utilizador.getEmail())) {
                Musica m = r.getMusica();
                Genero genero = m.getGenero();

                if (genero != null) {
                    contagem.put(genero, contagem.getOrDefault(genero, 0) + 1);
                }
            }
        }

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Gera uma playlist com músicas do género favorito do utilizador.
     *
     * @param nome         Nome da playlist.
     * @param utilizador   Utilizador que a cria.
     * @param historico    Histórico de reproduções.
     * @param todasMusicas Lista de músicas disponíveis.
     * @return Playlist gerada.
     * @throws SemReproducoesException Se o utilizador não tiver reproduções.
     */
    public Playlist gerarPlaylistFavorita(String nome, Utilizador utilizador, ArrayList<Reproducao> historico, ArrayList<Musica> todasMusicas) {
        Genero generoFavorito = obterGeneroFavorito(utilizador, historico);
        if (generoFavorito == null) throw new SemReproducoesException(utilizador.getNome());

        ArrayList<Musica> musicas = new ArrayList<>();
        Playlist playlist = new PlaylistFavoritos(nome, utilizador, LocalDate.now(), true, musicas);

        for (Musica m : todasMusicas) {
            if (m.getGenero() == generoFavorito) {
                playlist.adicionarMusica(m);
            }
        }

        return playlist;
    }

    /**
     * Gera uma playlist com músicas do género favorito até um tempo máximo.
     *
     * @param nome             Nome da playlist.
     * @param utilizador       Utilizador.
     * @param historico        Histórico de reproduções.
     * @param todasMusicas     Todas as músicas disponíveis.
     * @param tempoMaxSegundos Tempo máximo em segundos.
     * @return Playlist gerada.
     */
    public Playlist gerarPlaylistPorTempo(String nome, Utilizador utilizador, ArrayList<Reproducao> historico, ArrayList<Musica> todasMusicas, int tempoMaxSegundos) {
        Genero generoFavorito = obterGeneroFavorito(utilizador, historico);
        if (generoFavorito == null) throw new SemReproducoesException(utilizador.getNome());

        ArrayList<Musica> musicas = new ArrayList<>();
        int duracaoTotal = 0;

        for (Musica m : todasMusicas) {
            if (m.getGenero() == generoFavorito && duracaoTotal + m.getDuracao() <= tempoMaxSegundos) {
                musicas.add(m);
                duracaoTotal += m.getDuracao();
            }
        }

        return new PlaylistGenero(nome, utilizador, LocalDate.now(), true, musicas, generoFavorito, duracaoTotal);
    }

    /**
     * Gera uma playlist de músicas explícitas do género favorito do utilizador.
     *
     * @param nome         Nome da playlist.
     * @param utilizador   Utilizador.
     * @param historico    Histórico de reproduções.
     * @param todasMusicas Todas as músicas disponíveis.
     * @return Playlist com músicas explícitas.
     */
    public Playlist gerarPlaylistFavoritaExplicita(String nome, Utilizador utilizador, ArrayList<Reproducao> historico, ArrayList<Musica> todasMusicas) throws SemReproducoesException {
        Genero generoFavorito = obterGeneroFavorito(utilizador, historico);
        if (generoFavorito == null) throw new SemReproducoesException(utilizador.getNome());

        ArrayList<Musica> musicas = new ArrayList<>();
        Playlist playlist = new PlaylistFavoritos(nome, utilizador, LocalDate.now(), true, musicas);

        for (Musica m : todasMusicas) {
            if (m instanceof MusicaExplicita && m.getGenero() == generoFavorito) {
                playlist.adicionarMusica(m);
            }
        }

        return playlist;
    }

    /**
     * Devolve uma lista de músicas aleatórias.
     *
     * @param num Número de músicas.
     * @return Lista de músicas aleatórias.
     */
    public ArrayList<Musica> criaArrayMusicasAleatorio(int num) {
        ArrayList<Musica> musicas = getMusicas();
        Collections.shuffle(musicas);
        musicas.subList(0, num); // OBS: esta linha não altera a lista original, mas também não retorna o sublist
        return musicas;
    }

    /**
     * Verifica se o utilizador tem um plano premium.
     *
     * @param email Email do utilizador.
     * @return true se for premium.
     * @throws UtilizadorNaoTemPermissoesException Se não for premium.
     */
    public boolean validarUtilizadorPremium(String email) throws UtilizadorNaoTemPermissoesException {
        Utilizador u = this.utilizadores.get(email);
        boolean premium = u.isPremium();
        if (!premium) {
            throw new UtilizadorNaoTemPermissoesException(u.getEmail());
        }
        return true;
    }
}