package Classes;

import Classes.Musicas.*;
import Classes.Playlists.Playlist;
import Classes.Playlists.PlaylistAleatoria;
import SpotifUM.SpotifUM;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável pela gestão de ficheiros no model SpotifUM.
 * Permite guardar e carregar o estado da aplicação, bem como ler scripts
 * de comandos para popular automaticamente o model com dados.
 */
public class GestorFicheiros {
    /**
     * Caminho para o ficheiro onde o estado da aplicação SpotifUM é guardado.
     */
    public static final String SPOTIFUMFILE = "src/Ficheiros/SpotifUM.dat";
    /**
     * Caminho para o ficheiro de script de exemplo usado para carregar dados automaticamente.
     */
    public static final String SCRIPTFILE = "src/Ficheiros/scriptExemplo.txt";

    /**
     * Guarda o estado atual do model SpotifUM num ficheiro binário.
     *
     * @param spotifUM Instância do model a guardar.
     * @param nomeFicheiro Caminho do ficheiro onde guardar.
     * @throws FileNotFoundException Se o ficheiro não for encontrado.
     * @throws IOException Se ocorrer um erro de escrita.
     */
    public void guardarEstado(SpotifUM spotifUM, String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(spotifUM);
        oos.flush();
        oos.close();
    }

    /**
     * Carrega o estado do model a partir de um ficheiro binário.
     *
     * @param nomeFicheiro Caminho do ficheiro a carregar.
     * @return Instância do model carregada.
     * @throws FileNotFoundException Se o ficheiro não for encontrado.
     * @throws IOException Se ocorrer um erro de leitura.
     * @throws ClassNotFoundException Se a classe do objeto lido não for encontrada.
     */
    public SpotifUM carregarEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        SpotifUM spotifUM = (SpotifUM) ois.readObject();
        ois.close();
        return spotifUM;
    }

    /**
     * Carrega comandos de um ficheiro de script e executa-os no model.
     *
     * @param model Instância do model SpotifUM onde os comandos serão aplicados.
     * @throws IOException Se ocorrer erro ao ler o ficheiro.
     */
    public void carregarScript(SpotifUM model) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(SCRIPTFILE));
        String linha;

        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty() || linha.startsWith("#")) continue;

            try {
                processarLinha(model, linha);
            } catch (Exception e) {
                System.out.println("Erro ao processar linha: " + linha);
                System.out.println("Motivo: " + e.getMessage());
            }
        }
        br.close();
    }

    /**
     * Processa uma linha de comando do script e encaminha para o respetivo tratamento.
     *
     * @param model Instância do model.
     * @param linha Linha lida do ficheiro.
     */
    private void processarLinha(SpotifUM model, String linha) {
        String[] partes = linha.split(",");
        if (partes.length < 2) return;

        String utilizador = partes[0].trim();
        String comando = partes[1].trim();

        if (utilizador.equals("admin")) {
            processarComandoAdmin(model, comando);
        }
        // Futuramente: processarComandoUser
    }

    /**
     * Processa comandos do administrador presentes no script.
     *
     * @param model Instância do model.
     * @param comando Linha de comando a ser interpretada.
     */
    private void processarComandoAdmin(SpotifUM model, String comando) {
        if (comando.startsWith("artista create")) {
            String[] dados = comando.split("<");
            String nome = dados[1].trim();
            String pais = dados[2].trim();
            model.adicionarArtista(new Artista(nome, pais));

        } else if (comando.startsWith("musica create")) {
            String[] dados = comando.split("<");
            String nome = dados[1].trim();
            String nomeArtista = dados[2].trim();
            String editora = dados[3].trim();
            List<String> letra = Arrays.asList(dados[4].trim().split("\\|"));
            List<String> melodia = Arrays.asList(dados[5].trim().split("\\|"));
            String generoStr = dados[6].trim();
            int duracao = Integer.parseInt(dados[7].trim());

            Artista artista = model.getArtistaPorNome(nomeArtista);
            Genero genero = Genero.valueOf(generoStr.toUpperCase());

            Musica m = new Musica(nome, artista, editora, letra, melodia, genero, duracao, 0);
            model.adicionarMusica(m);

        } else if (comando.startsWith("musicaExplicita create")) {
        String[] dados = comando.split("<");
        String nome = dados[1].trim();
        String nomeArtista = dados[2].trim();
        String editora = dados[3].trim();
        List<String> letra = Arrays.asList(dados[4].trim().split("\\|"));
        List<String> melodia = Arrays.asList(dados[5].trim().split("\\|"));
        String generoStr = dados[6].trim();
        int duracao = Integer.parseInt(dados[7].trim());
        String aviso = dados[8].trim();
        int idadeMinima = Integer.parseInt(dados[9].trim());

        Artista artista = model.getArtistaPorNome(nomeArtista);
        Genero genero = Genero.valueOf(generoStr.toUpperCase());

        MusicaExplicita m = new MusicaExplicita(nome, artista, editora, letra, melodia, genero, duracao, 0, aviso, idadeMinima);
        model.adicionarMusica(m);

        } else if (comando.startsWith("musicaMultimedia create")) {
            String[] dados = comando.split("<");
            String nome = dados[1].trim();
            String nomeArtista = dados[2].trim();
            String editora = dados[3].trim();
            List<String> letra = Arrays.asList(dados[4].trim().split("\\|"));
            List<String> melodia = Arrays.asList(dados[5].trim().split("\\|"));
            String generoStr = dados[6].trim();
            int duracao = Integer.parseInt(dados[7].trim());
            String nomeVideo = dados[8].trim();
            String formato = dados[9].trim();

            Artista artista = model.getArtistaPorNome(nomeArtista);
            Genero genero = Genero.valueOf(generoStr.toUpperCase());

            MusicaMultimedia m = new MusicaMultimedia(nome, artista, editora, letra, melodia, genero, duracao, 0, nomeVideo, formato);
            model.adicionarMusica(m);

        }
        else if (comando.startsWith("album create")) {
            String[] dados = comando.split("<");
            String nomeAlbum = dados[1].trim();
            String nomeArtista = dados[2].trim();
            String dataStr = dados[3].trim();
            LocalDate dataLancamento = LocalDate.parse(dataStr);

            List<String> musicas = new ArrayList<>();
            for (int i = 4; i < dados.length; i++) {
                musicas.add(dados[i].trim());
            }

            Artista artista = model.getArtistaPorNome(nomeArtista);
            Album album = new Album(nomeAlbum, dataLancamento, artista, model.musicasToArray(musicas));
            model.adicionarAlbum(album);

        } else if (comando.startsWith("user register")) {
            String[] dados = comando.split("<");
            String email = dados[1].trim();
            String nome = dados[2].trim();
            String morada = dados[3].trim();
            String password = dados[4].trim();
            String plano = dados[5].trim();
            String cargo = dados[6].trim();

            model.registarUtilizadorPorScript(email, nome, morada, password, plano, cargo);

        } else if (comando.startsWith("playlistAleatoria create")) {
            String[] dados = comando.split("<");
            String nome = dados[1].trim();             // Nome da playlist
            String email = dados[2].trim();            // Email do utilizador
            int numeroMusicas = Integer.parseInt(dados[3].trim()); // Número de músicas

            Utilizador utilizador = model.getUtilizadorPorEmail(email);
            if (utilizador == null) {
                System.out.println("Utilizador com email " + email + " não encontrado.");
                return;
            }

            Playlist playlist = new PlaylistAleatoria(
                    nome,
                    utilizador,
                    LocalDate.now(),
                    true, // pública
                    model.criaArrayMusicasAleatorio(numeroMusicas)
            );

            model.adicionarPlaylist(playlist);
        }
    }
}