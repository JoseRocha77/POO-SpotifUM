package Controller;
import Classes.*;
import Classes.Musicas.*;
import Classes.Planos.*;
import Classes.Playlists.Playlist;
import Classes.Playlists.PlaylistAleatoria;
import Classes.Playlists.PlaylistConstruida;
import Exceptions.*;
import SpotifUM.SpotifUM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe responsável por intermediar a comunicação entre a camada de apresentação (View) e o modelo (Model).
 * Contém a lógica de aplicação e operações possíveis sobre o sistema SpotifUM.
 */
public class Controller {
    private SpotifUM model;

    /**
     * Construtor do Controller.
     *
     * @param model Instância do modelo SpotifUM a ser controlada.
     */
    public Controller(SpotifUM model) {
        this.model = model;
    }

    /**
     * Verifica se um utilizador é administrador.
     *
     * @param email Email do utilizador.
     * @return true se o utilizador for administrador, false caso contrário.
     */
    public boolean isAdmin(String email){
        Utilizador u = model.getUtilizadorPorEmail(email);
        return u != null && (u.getCargo() == Cargo.ADMIN);
    }

    /**
     * Efetua o login de um utilizador.
     *
     * @param nome Nome/email do utilizador.
     * @param password Palavra-passe do utilizador.
     * @return true se as credenciais forem válidas, false caso contrário.
     */
    public boolean login(String nome, String password) {
        return model.loginUtilizador(nome, password);
    }

    /**
     * Regista um novo utilizador com base nas opções fornecidas.
     *
     * @param nome          Nome do utilizador.
     * @param email         Email do utilizador.
     * @param morada        Morada do utilizador.
     * @param password      Palavra-passe do utilizador.
     * @param planoOpcao    Opção de plano: "1" para Free, "2" para PremiumBase, "3" para PremiumTop.
     * @param cargoOpcao    Opção de cargo: "1" para USER, "2" para ADMIN.
     * @return true se o registo for bem sucedido.
     * @throws EmailExistenteException Se o email já estiver registado.
     */
    public boolean registar(String nome, String email, String morada, String password, String planoOpcao, String cargoOpcao) throws EmailExistenteException {
        PlanoSubscricao plano;
        switch (planoOpcao) {
            case "1": plano = new PlanoFree(); break;
            case "2": plano = new PlanoPremiumBase(); break;
            case "3": plano = new PlanoPremiumTop(); break;
            default:
                System.out.println("Plano inválido. Atribuído PlanoFree por defeito.");
                plano = new PlanoFree();
        }

        Cargo cargo;
        switch (cargoOpcao) {
            case "2": cargo = Cargo.ADMIN; break;
            default:
                System.out.println("Cargo inválido. Atribuído USER por defeito.");
                cargo = Cargo.USER;
        }

        Biblioteca biblioteca = (plano instanceof PlanoFree) ? new Biblioteca() : new Biblioteca();

        Utilizador u = new Utilizador(nome, email, morada, password, 0, plano, cargo, biblioteca);
        u.setPlanoSubscricao(plano);

        model.registarUtilizador(u);
        return true;
    }

    /**
     * Adiciona uma música simples ao sistema.
     *
     * @param nome         Nome da música.
     * @param nomeArtista  Nome do artista.
     * @param editora      Nome da editora.
     * @param letra        Letra da música.
     * @param melodia      Melodia da música.
     * @param genero       Género musical.
     * @param duracao      Duração da música em segundos.
     */
    public void adicionarMusica(String nome, String nomeArtista, String editora, List<String> letra, List<String> melodia,
                                Genero genero, int duracao) {
        Artista artista = model.getArtistaPorNome(nomeArtista);
        if (artista != null) {
            Musica newMusica = new Musica(nome, artista, editora, letra, melodia, genero, duracao, 0);
            model.adicionarMusica(newMusica);
        } else {
            throw new ArtistaInexistenteException(nomeArtista);
        }
    }

    /**
     * Adiciona uma música explícita ao sistema.
     *
     * @param nome         Nome da música.
     * @param nomeArtista  Nome do artista.
     * @param editora      Nome da editora.
     * @param letra        Letra da música.
     * @param melodia      Melodia da música.
     * @param genero       Género musical.
     * @param duracao      Duração da música em segundos.
     * @param motivo       Motivo do aviso de conteúdo explícito.
     * @param idademin     Idade mínima recomendada para ouvir a música.
     */
    public void adicionarMusicaExplicita(String nome, String nomeArtista, String editora, List<String> letra, List<String> melodia,
                                         Genero genero, int duracao, String motivo, Integer idademin) {
        Artista artista = model.getArtistaPorNome(nomeArtista);
        if (artista != null) {
            MusicaExplicita newMusica = new MusicaExplicita(nome, artista, editora, letra, melodia, genero, duracao, 0, motivo, idademin);
            model.adicionarMusica(newMusica);
        } else {
            throw new ArtistaInexistenteException(nomeArtista);
        }
    }

    /**
     * Adiciona uma música multimédia ao sistema.
     *
     * @param nome         Nome da música.
     * @param nomeArtista  Nome do artista.
     * @param editora      Nome da editora.
     * @param letra        Letra da música.
     * @param melodia      Melodia da música.
     * @param genero       Género musical.
     * @param duracao      Duração da música em segundos.
     * @param nomevideo    Nome do ficheiro de vídeo.
     * @param formato      Formato do vídeo (ex: MP4, AVI).
     */
    public void adicionarMusicaMultimedia(String nome, String nomeArtista, String editora, List<String> letra, List<String> melodia,
                                          Genero genero, int duracao, String nomevideo, String formato) {
        Artista artista = model.getArtistaPorNome(nomeArtista);
        if (artista != null) {
            MusicaMultimedia newMusica = new MusicaMultimedia(nome, artista, editora, letra, melodia, genero, duracao, 0, nomevideo, formato);
            model.adicionarMusica(newMusica);
        } else {
            throw new ArtistaInexistenteException(nomeArtista);
        }
    }

    /**
     * Adiciona um novo artista ao sistema.
     *
     * @param nome Nome do artista.
     * @param pais País de origem do artista.
     */
    public void adicionarArtista(String nome, String pais) {
        Artista newArtista = new Artista(nome, pais);
        model.adicionarArtista(newArtista);
    }

    /**
     * Reproduz uma música e atualiza as estatísticas de reprodução do utilizador.
     *
     * @param email Email do utilizador.
     * @param nome  Nome da música.
     * @return Mensagem com o resultado da reprodução.
     */
    public String ouvirMusica(String email, String nome) {
        return model.reproduzirMusica(email, nome);
    }

    /**
     * Lista todas as músicas disponíveis no sistema.
     *
     * @return String formatada com a lista de músicas.
     */
    public String listarMusicas() {
        return model.listarMusicas();
    }

    /**
     * Adiciona um novo álbum ao sistema.
     *
     * @param nome          Nome do álbum.
     * @param dataLancamento Data de lançamento.
     * @param nomeArtista   Nome do artista responsável.
     * @param listaMusicas  Lista de nomes das músicas incluídas.
     */
    public void adicionarAlbum(String nome, LocalDate dataLancamento, String nomeArtista, List<String> listaMusicas) {
        Artista artista = model.getArtistaPorNome(nomeArtista);
        if (artista == null) throw new ArtistaInexistenteException(nomeArtista);

        ArrayList<Musica> musicas = model.musicasToArray(listaMusicas);
        Album newAlbum = new Album(nome, dataLancamento, artista, musicas);
        model.adicionarAlbum(newAlbum);
    }

    /**
     * Adiciona uma playlist construída manualmente ao sistema.
     *
     * @param nome   Nome da playlist.
     * @param email  Email do utilizador criador.
     * @param publica Flag indicando se a playlist é pública.
     * @param lista  Lista de nomes das músicas.
     */
    public void adicionarPlaylistConstruida(String nome, String email, boolean publica, List<String> lista) {
        PlaylistConstruida playlist = new PlaylistConstruida(nome, model.getUtilizadorPorEmail(email), LocalDate.now(), publica, model.musicasToArray(lista));
        model.adicionarPlaylist(playlist);
    }

    /**
     * Adiciona uma playlist gerada aleatoriamente ao sistema.
     *
     * @param nome Nome da playlist.
     * @param email Email do utilizador.
     * @param num Número de músicas a incluir.
     */
    public void adicionarPlaylistAleatoria(String nome, String email, Integer num) {
        Playlist playlistAleatoria = new PlaylistAleatoria(nome, model.getUtilizadorPorEmail(email), LocalDate.now(), true, model.criaArrayMusicasAleatorio(num));
        model.adicionarPlaylist(playlistAleatoria);
    }

    /**
     * Lista todas as playlists do sistema.
     *
     * @return String com os nomes das playlists.
     */
    public String listarPlaylist(String email) {
        return model.listarPlaylist(email);
    }

    /**
     * Verifica se um utilizador pode criar playlists.
     *
     * @param email Email do utilizador.
     * @return true se o utilizador puder criar playlists, false caso contrário.
     */
    public boolean podeFazerPlaylist(String email) {
        return model.getUtilizadorPorEmail(email).getPlanoSubscricao().getNomePlano().equals("Plano Free");
    }

    /**
     * Devolve uma lista com os nomes das músicas de uma playlist, de acordo com as permissões e modo de reprodução.
     *
     * @param email Email do utilizador.
     * @param nomePlaylist Nome da playlist.
     * @param premium true se o utilizador for premium.
     * @param modoReproducao "S" para sequencial, "A" para aleatório.
     * @return Lista de nomes das músicas da playlist.
     * @throws PlaylistInexistenteException Se a playlist não existir.
     * @throws PlaylistIsNotAleatoriaException Se utilizador free tentar aceder a playlist não aleatória.
     */
    public List<String> playlistToArrayMusicas(String email, String nomePlaylist, boolean premium, String modoReproducao) {
        ArrayList<Musica> musicas = new ArrayList<>();

        Playlist playlist = model.getPlaylistPorNome(nomePlaylist, email);
        if (playlist == null) {
            throw new PlaylistInexistenteException(nomePlaylist);
        }

        if (!premium) {
            if (playlist instanceof PlaylistAleatoria) {
                musicas = playlist.getListaMusicas();
            } else {
                throw new PlaylistIsNotAleatoriaException(nomePlaylist);
            }
        } else {
            musicas = playlist.getListaMusicas();
            if (modoReproducao.equalsIgnoreCase("A")) {
                Collections.shuffle(musicas);
            }
        }

        return musicas.stream().map(Musica::getNome).toList();
    }

    /**
     * Devolve uma representação textual de todos os utilizadores registados no sistema.
     *
     * @return String com a informação dos utilizadores.
     */
    public String infoUtilizadores() {
        return model.getUtilizadores().toString();
    }

    /**
     * Devolve uma representação textual de todas as músicas disponíveis no sistema.
     *
     * @return String com a informação das músicas.
     */
    public String infoMusica() {
        return model.getMusicas().toString();
    }

    /**
     * Devolve uma representação textual de todos os álbuns presentes no sistema.
     *
     * @return String com a informação dos álbuns.
     */
    public String infoAlbum() {
        return model.getAlbuns().toString();
    }

    /**
     * Devolve uma representação textual de todas as playlists criadas no sistema.
     *
     * @return String com a informação das playlists.
     */
    public String infoPlaylist() {
        return model.getPlaylists().toString();
    }

    /**
     * Devolve uma representação textual de todos os artistas registados no sistema.
     *
     * @return String com a informação dos artistas.
     */
    public String infoArtista() {
        return model.getArtistas().toString();
    }

    /**
     * Devolve uma representação textual de todas as reproduções realizadas no sistema.
     *
     * @return String com a informação das reproduções.
     */
    public String infoReproducao() {
        return model.getReproducoes().toString();
    }

    /**
     * Gera e adiciona uma playlist com músicas do género favorito do utilizador.
     *
     * @param nome Nome da nova playlist.
     * @param email Email do utilizador.
     * @throws SemReproducoesException Se o utilizador não tiver reproduções suficientes.
     */
    public void adicionarPlaylistFavorita(String nome, String email) throws SemReproducoesException {
        if (model.gerarPlaylistFavorita(nome, model.getUtilizadorPorEmail(email), model.getReproducoes(), model.getMusicas()) != null) {
            model.adicionarPlaylist(model.gerarPlaylistFavorita(nome, model.getUtilizadorPorEmail(email), model.getReproducoes(), model.getMusicas()));
            adicionarBibliotecaPlaylist(email, nome);
        }
    }

    /**
     * Gera uma playlist baseada no género favorito com duração máxima definida.
     *
     * @param nome Nome da playlist.
     * @param email Email do utilizador.
     * @param tempomax Tempo máximo da playlist (em segundos).
     * @throws SemReproducoesException Se o utilizador não tiver reproduções.
     */
    public void adicionarPlaylistFavoritaGenero(String nome, String email, Integer tempomax) throws SemReproducoesException {
        if (model.gerarPlaylistPorTempo(nome, model.getUtilizadorPorEmail(email), model.getReproducoes(), model.getMusicas(), tempomax) != null) {
            model.adicionarPlaylist(model.gerarPlaylistPorTempo(nome, model.getUtilizadorPorEmail(email), model.getReproducoes(), model.getMusicas(), tempomax));
            adicionarBibliotecaPlaylist(email, nome);
        }
    }

    /**
     * Gera uma playlist favorita contendo apenas músicas explícitas do género favorito.
     *
     * @param nome Nome da playlist.
     * @param email Email do utilizador.
     * @throws SemReproducoesException Se o utilizador não tiver reproduções.
     */
    public void adicionarPlaylistExplicita(String nome, String email) throws SemReproducoesException {
        if (model.gerarPlaylistFavoritaExplicita(nome, model.getUtilizadorPorEmail(email), model.getReproducoes(), model.getMusicas()) != null) {
            model.adicionarPlaylist(model.gerarPlaylistFavoritaExplicita(nome, model.getUtilizadorPorEmail(email), model.getReproducoes(), model.getMusicas()));
            adicionarBibliotecaPlaylist(email, nome);
        }
    }

    /**
     * Lista os álbuns e playlists da biblioteca do utilizador.
     *
     * @param email Email do utilizador.
     * @return String com os dados da biblioteca.
     * @throws UtilizadorFreeNaoPossuiBibliotecaException Se o utilizador for do plano Free.
     */
    public String listarBiblioteca(String email) throws UtilizadorFreeNaoPossuiBibliotecaException {
        return model.listarBiblioteca(email);
    }

    /**
     * Adiciona uma playlist à biblioteca de um utilizador Premium.
     *
     * @param email Email do utilizador.
     * @param nome Nome da playlist.
     * @throws PlaylistInexistenteException Se a playlist não existir.
     * @throws PlaylistJaNaBibliotecaException Se a playlist já estiver na biblioteca.
     */
    public void adicionarBibliotecaPlaylist(String email, String nome) throws PlaylistInexistenteException, PlaylistJaNaBibliotecaException {
        Utilizador u = model.getUtilizadorPorEmail(email);
        if (!utilizadorIsPremium(email)) throw new UtilizadorFreeNaoPossuiBibliotecaException(email);

        Playlist p = model.getPlaylistPorNome(nome);
        if (p == null) {
            throw new PlaylistInexistenteException(nome);
        }
        if (u.getBiblioteca().contemPlaylist(p)) {
            throw new PlaylistJaNaBibliotecaException(nome);
        }

        u.adicionarPlaylist(p.clone());
    }

    /**
     * Adiciona um álbum à biblioteca de um utilizador Premium.
     *
     * @param email Email do utilizador.
     * @param nome Nome do álbum.
     * @throws AlbumInexistenteException Se o álbum não existir.
     * @throws AlbumJaNaBibliotecaException Se o álbum já estiver na biblioteca.
     */
    public void adicionarBibliotecaAlbum(String email, String nome) throws AlbumInexistenteException, AlbumJaNaBibliotecaException {
        Utilizador u = model.getUtilizadorPorEmail(email);
        if (!utilizadorIsPremium(email)) throw new UtilizadorFreeNaoPossuiBibliotecaException(email);

        Album a = model.getAlbumPorNome(nome);
        if (a == null) {
            throw new AlbumInexistenteException(nome);
        }
        if (u.getBiblioteca().contemAlbum(a)) {
            throw new AlbumJaNaBibliotecaException(nome);
        }

        u.adicionarAlbum(a.clone());
    }

    /**
     * Devolve o número total de músicas no sistema e o nome da mais reproduzida.
     *
     * @return Nome da música com mais reproduções ou "." se não houver músicas.
     */
    public String countMusicas() {
        ArrayList<Musica> musicas = model.getMusicas();
        return musicas.stream().max(Comparator.comparingInt(Musica::getNumReproducoes)).map(Musica::getNome).orElse(".");
    }

    /**
     * Obtém o utilizador com mais reproduções num intervalo de tempo.
     *
     * @param inicio Data/hora de início (pode ser null).
     * @param fim Data/hora de fim (pode ser null).
     * @return Email do utilizador com mais reproduções ou "." se nenhum.
     */
    public String obterUtilizadorComMaisReproducoes(LocalDateTime inicio, LocalDateTime fim) {
        List<Reproducao> reproducoes = model.getReproducoes();
        return reproducoes.stream()
                .filter(r -> (inicio == null || !r.getDataHora().isBefore(inicio)) &&
                        (fim == null || !r.getDataHora().isAfter(fim)))
                .collect(Collectors.groupingBy(r -> r.getUtilizador().getEmail(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(".");
    }

    /**
     * Calcula o nome do artista com maior número total de reproduções.
     *
     * @return Nome do artista mais escutado ou "." se não houver dados.
     */
    public String calcArtistaMaisEscutado() {
        return model.getMusicas().stream()
                .collect(Collectors.groupingBy(m -> m.getInterprete().getNome(), Collectors.summingInt(Musica::getNumReproducoes)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(".");
    }

    /**
     * Devolve o utilizador com mais pontos acumulados.
     *
     * @return Nome e pontos do utilizador com mais pontos, ou "." se não houver utilizadores.
     */
    public String countUtilizador() {
        return model.getUtilizadores().values().stream()
                .max(Comparator.comparingInt(Utilizador::getPontos))
                .map(u -> u.getNome() + " - " + u.getPontos())
                .orElse(".");
    }

    /**
     * Devolve o género musical com mais reproduções no sistema.
     *
     * @return Género mais reproduzido e número de reproduções, ou "." se nenhum.
     */
    public String generoMaisReproduzido() {
        return model.getMusicas().stream()
                .collect(Collectors.groupingBy(Musica::getGenero, Collectors.summingInt(Musica::getNumReproducoes)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + " - " + e.getValue())
                .orElse(".");
    }

    /**
     * Conta o número de playlists públicas no sistema.
     *
     * @return Número de playlists públicas.
     */
    public Integer playlistPublicas() {
        return (int) model.getPlaylists().stream().filter(Playlist::getPublica).count();
    }

    /**
     * Retorna o utilizador (email) que criou mais playlists.
     *
     * @return Email do utilizador com mais playlists ou "." se não houver playlists.
     */
    public String utilizadorMaisPlaylists() {
        return model.getPlaylists().stream()
                .collect(Collectors.groupingBy(p -> p.getUtilizador().getEmail(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(".");
    }

    /**
     * Verifica se um utilizador tem um plano premium.
     *
     * @param email Email do utilizador.
     * @return true se for premium.
     * @throws UtilizadorNaoTemPermissoesException Se o utilizador não for premium.
     */
    public boolean utilizadorIsPremium(String email) throws UtilizadorNaoTemPermissoesException {
        return model.validarUtilizadorPremium(email);
    }

    /**
     * Verifica se um utilizador tem o plano PremiumTop.
     *
     * @param email Email do utilizador.
     * @return true se for PremiumTop.
     */
    public boolean utilizadorIsPremiumTop(String email) {
        return model.getUtilizadorPorEmail(email).getPlanoSubscricao().getNomePlano().equals("Plano PremiumTop");
    }

    /**
     * Melhora o plano de um utilizador, se tiver pontos suficientes.
     *
     * @param email Email do utilizador.
     * @throws UtilizadorFaltaPontosException Se o utilizador não tiver pontos suficientes.
     */
    public void melhoraPlano(String email) throws UtilizadorFaltaPontosException {
        Utilizador u = model.getUtilizadorPorEmail(email);

        if (u.getPontos() >= 100) {
            if (u.getPlanoSubscricao() instanceof PlanoFree) {
                u.setPlanoSubscricao(new PlanoPremiumBase());
                u.setPontos(u.getPontos()-100);
            } else if (u.getPlanoSubscricao() instanceof PlanoPremiumBase) {
                u.setPlanoSubscricao(new PlanoPremiumTop());
                u.setPontos(100);
            } else {
                System.out.println("O utilizador já possui o plano PremiumTop.");
            }
        } else {
            throw new UtilizadorFaltaPontosException(email);
        }
    }

    /**
     * Altera a visibilidade (pública/privada) de uma playlist de um utilizador.
     *
     * A playlist só poderá ser modificada se existir e se pertencer ao utilizador especificado.
     *
     * @param email Email do utilizador que deseja alterar a visibilidade da playlist.
     * @param nomePlaylist Nome da playlist cuja visibilidade será alterada.
     * @return O novo estado de visibilidade da playlist: {@code true} se passou a ser pública, {@code false} se passou a ser privada.
     * @throws PlaylistInexistenteException Se a playlist não existir.
     * @throws UtilizadorNaoTemPermissoesException Se o utilizador não for o dono da playlist.
     */
    public boolean alterarVisibilidadePlaylist(String email, String nomePlaylist) throws PlaylistInexistenteException, UtilizadorNaoTemPermissoesException {
        Playlist p = model.getPlaylistPorNome(nomePlaylist, email);

        if (p == null) throw new PlaylistInexistenteException(nomePlaylist);
        if (!p.getUtilizador().getEmail().equals(email)) {
            throw new UtilizadorNaoTemPermissoesException("A playlist não pertence ao utilizador.");
        }

        p.setPublica(!p.getPublica()); // altera o estado atual
        return p.getPublica();         // retorna o novo estado
    }

    /**
     * Guarda o estado atual do sistema para ficheiro.
     *
     * @throws IOException Se ocorrer erro de escrita.
     */
    public void guardarEstado() throws IOException {
        GestorFicheiros gestorFicheiros = new GestorFicheiros();
        gestorFicheiros.guardarEstado(model, GestorFicheiros.SPOTIFUMFILE);
    }

    /**
     * Carrega o estado do sistema a partir de ficheiro.
     *
     * @throws IOException Se ocorrer erro de leitura.
     * @throws ClassNotFoundException Se a classe lida for inválida.
     */
    public void carregarEstado() throws IOException, ClassNotFoundException {
        GestorFicheiros gestorFicheiros = new GestorFicheiros();
        this.model = gestorFicheiros.carregarEstado(GestorFicheiros.SPOTIFUMFILE);
    }

    /**
     * Carrega um script de comandos a partir de ficheiro e executa-os no sistema.
     *
     * @throws IOException Se ocorrer erro de leitura do ficheiro.
     */
    public void carregarScript() throws IOException {
        GestorFicheiros gf = new GestorFicheiros();
        gf.carregarScript(this.model);
    }
}