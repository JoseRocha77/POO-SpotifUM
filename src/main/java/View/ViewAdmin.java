package View;

import Classes.Genero;
import Controller.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exceptions.ArtistaInexistenteException;
import Exceptions.MusicaInexistenteException;

/**
 * Classe responsável pela interface de administração (admin) do sistema SpotifUM.
 * Permite ao administrador adicionar músicas, álbuns, artistas e playlists, assim como consultar as entidades registadas.
 */
public class ViewAdmin {

    private Scanner scanner;
    private Controller controller;

    /**
     * Construtor da classe ViewAdmin.
     *
     * @param scanner Scanner para entrada do utilizador.
     * @param controller Controller que comunica com o modelo de dados.
     */
    public ViewAdmin(Scanner scanner, Controller controller){
        this.scanner = scanner;
        this.controller = controller;
    }

    /**
     * Menu principal para administradores com várias funcionalidades disponíveis.
     */
    public void menuAdmin(){
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== Menu Principal - ADMIN ===");
            System.out.println("1. Adicionar Música");
            System.out.println("2. Criar Álbum");
            System.out.println("3. Adicionar Artista");
            System.out.println("4. Gerar Playlist");
            System.out.println("5. Listar Entidades");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("-- 1. Músicas --");
                    try {
                        escolhaMusica();
                    } catch (ArtistaInexistenteException e) {
                        System.out.println("Erro: Artista não existe : " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("-- 2. Álbuns --");
                    try {
                        adicionarAlbum();
                    } catch (ArtistaInexistenteException e) {
                        System.out.println("Erro: O artista indicado não existe. " + e.getMessage());
                    } catch (MusicaInexistenteException e) {
                        System.out.println("Erro: Uma ou mais músicas indicadas não existem. " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("-- 3. Artistas --");
                    adicionarArtista();
                    break;
                case "4":
                    System.out.println("-- 4. Playlists --");
                    String email = ""; // Para compatibilidade com menuPlaylist
                    ViewPlaylist viewPlaylist = new ViewPlaylist(scanner, controller);
                    viewPlaylist.menuPlaylist(email, "3");
                    break;
                case "5":
                    System.out.println("-- 5. Entidades --");
                    System.out.println("Utilizadores: " + controller.infoUtilizadores());
                    System.out.println("Musicas: " + controller.infoMusica());
                    System.out.println("Albuns: " + controller.infoAlbum());
                    System.out.println("Playlist: " + controller.infoPlaylist());
                    System.out.println("Artista: " + controller.infoArtista());
                    System.out.println("Reproducao: " + controller.infoReproducao());
                    break;
                case "0":
                    loggedIn = false;
                    System.out.println("Logout efetuado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Lê várias linhas do utilizador até receber um "." indicando fim da entrada.
     *
     * @param prompt Mensagem a apresentar ao utilizador.
     * @return Lista de strings introduzidas.
     */
    private List<String> lerMultiplasLinhas(String prompt) {
        List<String> linhas = new ArrayList<>();
        System.out.println(prompt + " (Escreva '.' para terminar):");

        while (true) {
            String linha = scanner.nextLine();
            if (linha.equalsIgnoreCase(".")) break;
            linhas.add(linha);
        }

        return linhas;
    }

    /**
     * Apresenta opções para o tipo de música a adicionar (normal, explícita ou multimédia).
     */
    private void escolhaMusica(){
        System.out.println("1. Musica Normal");
        System.out.println("2. Musica Explicita");
        System.out.println("3. Musica Multimedia");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao){
            case"1":
            case"2":
            case"3":
                adicionarMusica(opcao);
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    /**
     * Adiciona uma música ao sistema com base no tipo escolhido.
     *
     * @param opcao Tipo de música (1: normal, 2: explícita, 3: multimédia).
     */
    private void adicionarMusica(String opcao) {
        System.out.print("Nome da música: ");
        String nome = scanner.nextLine();

        System.out.print("Nome do Artista: ");
        String nomeArtista = scanner.nextLine();

        System.out.print("Editora: ");
        String editora = scanner.nextLine();

        List<String> letra = lerMultiplasLinhas("Letra: ");
        List<String> melodia = lerMultiplasLinhas("Melodia: ");

        Genero genero = null;
        boolean valido = false;

        while (!valido) {
            System.out.print("Género: ");
            String generoInput = scanner.nextLine().toUpperCase();

            try {
                genero = Genero.valueOf(generoInput);
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Género inválido. Tente novamente. Opções válidas:");
                for (Genero g : Genero.values()) {
                    System.out.println("- " + g.name());
                }
            }
        }

        System.out.print("Duração (em segundos): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case "1":
                controller.adicionarMusica(nome, nomeArtista, editora, letra, melodia, genero, duracao);
                break;
            case "2":
                System.out.print("Motivo pela qual é explicita: ");
                String motivo = scanner.nextLine();

                System.out.print("Idade Minima para ouvir: ");
                Integer idademinima = Integer.parseInt(scanner.nextLine());

                controller.adicionarMusicaExplicita(nome, nomeArtista, editora, letra, melodia, genero, duracao, motivo, idademinima);
                break;
            case "3":
                System.out.print("Nome do video: ");
                String nomevideo = scanner.nextLine();

                System.out.print("Formato: ");
                String formato = scanner.nextLine();

                controller.adicionarMusicaMultimedia(nome, nomeArtista, editora, letra, melodia, genero, duracao, nomevideo, formato);
                break;
        }
    }

    /**
     * Adiciona um novo artista ao sistema.
     */
    private void adicionarArtista() {
        System.out.print("Nome do Artista: ");
        String nome = scanner.nextLine();

        System.out.print("País do Artista: ");
        String pais = scanner.nextLine();

        controller.adicionarArtista(nome, pais);
    }

    /**
     * Lê os nomes das músicas que compõem um álbum.
     *
     * @return Lista com os nomes das músicas.
     */
    private List<String> criaListaMusicas (){
        List<String> musicas  = new ArrayList<>();
        int num;
        while (true) {
            System.out.print("Quantas músicas quer adicionar ao álbum? ");
            String input = scanner.nextLine();

            try {
                num = Integer.parseInt(input);
                if (num < 0) {
                    System.out.println("Número inválido. Deve ser positivo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: introduza um número válido.");
            }
        }

        for (int i = 0; i < num; i++) {
            System.out.print("Nome da música " + (i + 1) + ": ");
            String nomeMusica = scanner.nextLine();
            musicas.add(nomeMusica);
        }

        return musicas;
    }

    /**
     * Adiciona um novo álbum ao sistema.
     *
     * @throws ArtistaInexistenteException Se o artista do álbum não existir.
     * @throws MusicaInexistenteException Se alguma das músicas não existir.
     */
    private void adicionarAlbum() {
        System.out.print("Nome do Album: ");
        String nome = scanner.nextLine();

        LocalDate dataLancamento = null;

        while (dataLancamento == null) {
            try {
                System.out.print("Data de Lançamento (YYYY-MM-DD): ");
                dataLancamento = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato da data incorreto. Correto: YYYY-MM-DD");
            }
        }

        System.out.print("Nome do artista: ");
        String nomeArtista = scanner.nextLine();

        List<String> musicas  = criaListaMusicas();

        controller.adicionarAlbum(nome, dataLancamento, nomeArtista, musicas);
    }
}