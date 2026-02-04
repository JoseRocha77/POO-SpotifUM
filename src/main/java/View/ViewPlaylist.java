package View;

import Controller.Controller;
import Exceptions.MusicaInexistenteException;
import Exceptions.PlaylistInexistenteException;
import Exceptions.PlaylistIsNotAleatoriaException;
import Exceptions.SemReproducoesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela interação com o utilizador no que diz respeito à criação
 * e reprodução de playlists, tanto por utilizadores comuns como por administradores.
 */
public class ViewPlaylist {
    private Scanner scanner;
    private Controller controller;

    /**
     * Construtor da classe ViewPlaylist.
     *
     * @param scanner Scanner para leitura de input do utilizador.
     * @param controller Controlador da aplicação que interage com o modelo.
     */
    public ViewPlaylist(Scanner scanner, Controller controller){
        this.scanner = scanner;
        this.controller = controller;
    }

    /**
     * Menu principal para operações relacionadas com playlists.
     *
     * @param email Email do utilizador atual.
     * @param opcao Tipo de operação: 1 - Criar, 2 - Reproduzir, 3 - Criar como admin.
     */
    public void menuPlaylist(String email, String opcao){
        switch(opcao){
            case "1":
                criarPlaylist(email);
                break;
            case "2":
                try {
                    reproduzPlaylist(email);
                } catch (PlaylistInexistenteException e) {
                    System.out.println("Erro: Playlist inexistente: " + e.getMessage());
                } catch (PlaylistIsNotAleatoriaException e){
                    System.out.println("Erro: Playlist não aleatória: " + e.getMessage());
                }
                break;
            case "3":
                try {
                    criarPlaylistAdmin();
                } catch (MusicaInexistenteException e){
                    System.out.println("Erro: Música inexistente: " + e.getMessage());
                }
                break;
        }
    }

    /**
     * Lê múltiplos nomes de músicas do utilizador.
     *
     * @return Lista com os nomes das músicas.
     */
    private List<String> criaListaMusicas (){
        List<String> musicas = new ArrayList<>();
        int num;

        while (true) {
            System.out.print("Quantas músicas quer adicionar à playlist? ");
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
     * Permite ao utilizador criar uma playlist de diferentes tipos.
     *
     * @param email Email do utilizador.
     */
    private void criarPlaylist(String email) {
        System.out.println("Qual o tipo de playlist");
        System.out.println("--1. Playlist Normal");
        System.out.println("--2. Playlist Favoritos");
        System.out.println("--3. Playlist Género Musical Tempo");
        System.out.println("--4. Playlist Favoritos - Explícita");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        System.out.print("Nome da Playlist: ");
        String nome = scanner.nextLine();

        switch(opcao){
            case "1":
                try {
                    criaPlaylistNormal(email, nome);
                } catch (MusicaInexistenteException e){
                    System.out.println("Erro: Musica não existe: " + e.getMessage());
                }
                break;
            case "2":
                try {
                    controller.adicionarPlaylistFavorita(nome, email);
                } catch(SemReproducoesException e){
                    System.out.println("Erro: Sem reproduções para criar favoritos: " + e.getMessage());
                }
                break;
            case "3":
                System.out.print("Tempo máximo da playlist (em segundos): ");
                try {
                    int timemax = Integer.parseInt(scanner.nextLine());
                    controller.adicionarPlaylistFavoritaGenero(nome, email, timemax);
                } catch(NumberFormatException e){
                    System.out.println("Erro: tempo inválido.");
                } catch(SemReproducoesException e){
                    System.out.println("Erro: Sem reproduções: " + e.getMessage());
                }
                break;
            case "4":
                try {
                    controller.adicionarPlaylistExplicita(nome, email);
                } catch(SemReproducoesException e){
                    System.out.println("Erro: Sem reproduções explícitas: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Tipo de playlist inválido.");
        }
    }

    /**
     * Cria uma playlist normal com músicas indicadas pelo utilizador.
     *
     * @param email Email do utilizador.
     * @param nome Nome da playlist.
     */
    private void criaPlaylistNormal (String email, String nome){
        System.out.print("Vai ser pública a Playlist (true/false): ");
        boolean publica = scanner.nextBoolean();
        scanner.nextLine();

        List<String> lista = criaListaMusicas();

        controller.adicionarPlaylistConstruida(nome, email, publica, lista);
    }

    /**
     * Permite a reprodução de uma playlist, com controlo de navegação se for premium.
     *
     * @param email Email do utilizador.
     */
    private void reproduzPlaylist(String email) {
        boolean premium = controller.utilizadorIsPremium(email);
        String nomePlaylist, modoReproducao = "S";

        if (premium){
            System.out.print("Nome da Playlist que deseja ouvir: ");
            nomePlaylist = scanner.nextLine();
            System.out.println("Modo de Reprodução: (S) Sequencial | (A) Aleatório");
            modoReproducao = scanner.nextLine();
        } else {
            System.out.println("Nome da Playlist (tem que ser aleatória): ");
            nomePlaylist = scanner.nextLine();
        }

        List<String> lista = controller.playlistToArrayMusicas(email, nomePlaylist, premium, modoReproducao);

        int index = 0;
        while (index >= 0 && index < lista.size()) {
            String nomeMusica = lista.get(index);
            System.out.println("Vai ouvir: " + nomeMusica);

            controller.ouvirMusica(email, nomeMusica);

            if (!premium) {
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine();
                index++;
            } else {
                System.out.println("Deseja (a) Avançar | (b) Voltar | (s) Sair ?");
                String opcao = scanner.nextLine().toLowerCase();

                switch (opcao) {
                    case "a": index++; break;
                    case "b":
                        if (index > 0) index--;
                        else System.out.println("Já está na primeira música.");
                        break;
                    case "s": index = -1; break;
                    default:
                        System.out.println("Opção inválida. Avançar por defeito.");
                        index++;
                }
            }
        }

        System.out.println("Acabou a playlist " + nomePlaylist);
    }

    /**
     * Permite ao administrador criar playlists aleatórias ou construídas manualmente.
     */
    private void criarPlaylistAdmin () {
        System.out.print("Nome da Playlist: ");
        String nome = scanner.nextLine();

        System.out.print("Email do Utilizador: ");
        String email = scanner.nextLine();

        System.out.print("Queres fazer uma playlist Aleatória (true/false): ");
        boolean aleatoria = scanner.nextBoolean();
        scanner.nextLine();

        if (aleatoria){
            int num;
            while (true) {
                System.out.print("Quantas músicas quer adicionar à playlist? ");
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

            controller.adicionarPlaylistAleatoria(nome, email, num);
        } else {
            System.out.print("Vai ser pública a Playlist (true/false): ");
            boolean publica = scanner.nextBoolean();
            scanner.nextLine();

            List<String> musicas = criaListaMusicas();
            controller.adicionarPlaylistConstruida(nome, email, publica, musicas);
        }
    }
}