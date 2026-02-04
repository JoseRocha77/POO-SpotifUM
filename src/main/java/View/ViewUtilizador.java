package View;

import Controller.Controller;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela interface do utilizador comum da aplicação SpotifUM.
 * Permite ao utilizador realizar ações como ouvir músicas, criar playlists,
 * visualizar biblioteca, entre outros, de acordo com as permissões do seu plano.
 */
public class ViewUtilizador {

    private Scanner scanner;
    private Controller controller;

    /**
     * Construtor da classe ViewUtilizador.
     *
     * @param scanner     Scanner utilizado para input do utilizador.
     * @param controller  Controller que faz a ligação à camada de lógica (model).
     */
    public ViewUtilizador(Scanner scanner, Controller controller){
        this.scanner = scanner;
        this.controller = controller;
    }

    /**
     * Menu principal apresentado ao utilizador comum após login.
     *
     * @param email Email do utilizador autenticado.
     */
    public void menuUtilizador(String email) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Ouvir Música");
            System.out.println("2. Listar Músicas");
            System.out.println("3. Criar playlist (se permitido)");
            System.out.println("4. Ver playlists disponíveis");
            System.out.println("5. Ouvir playlists");
            System.out.println("6. Listar Biblioteca");
            System.out.println("7. Adicionar Playlist/Álbum à Biblioteca");
            System.out.println("8. Mudar visibilidade Playlist");
            System.out.println("9. Trocar Plano");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome da música que deseja ouvir: ");
                    String nomeMusica = scanner.nextLine();
                    try {
                        System.out.println(controller.ouvirMusica(email, nomeMusica));
                    } catch (MusicaInexistenteException e) {
                        System.out.println("Erro: Música inexistente: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println(controller.listarMusicas());
                    break;
                case "3":
                    if (!controller.podeFazerPlaylist(email)) {
                        ViewPlaylist viewPlaylist = new ViewPlaylist(scanner, controller);
                        viewPlaylist.menuPlaylist(email, "1");
                    } else {
                        System.out.println("Apenas utilizadores com planos Premium podem criar playlists.");
                    }
                    break;
                case "4":
                    System.out.println("== Lista de Playlists SpotifUM ==");
                    System.out.println(controller.listarPlaylist(email));
                    break;
                case "5":
                    ViewPlaylist viewPlaylist = new ViewPlaylist(scanner, controller);
                    viewPlaylist.menuPlaylist(email, "2");
                    break;
                case "6":
                    try {
                        System.out.println(controller.listarBiblioteca(email));
                    } catch (UtilizadorFreeNaoPossuiBibliotecaException e) {
                        System.out.println("Plano Free não permite biblioteca. Utilizador: " + e.getMessage());
                    }
                    break;
                case "7":
                    System.out.print("O que deseja adicionar à biblioteca? (1) Playlist  (2) Álbum: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Qual é o nome da Playlist/Álbum? ");
                    String nome = scanner.nextLine();

                    try {
                        if (tipo.equals("1")) {
                            try {
                                controller.adicionarBibliotecaPlaylist(email, nome);
                            } catch (PlaylistJaNaBibliotecaException e) {
                                System.out.println("A biblioteca já contém a playlist: " + nome);
                            } catch (PlaylistInexistenteException e) {
                                System.out.println("Playlist inexistente: " + nome);
                            }
                        } else if (tipo.equals("2")) {
                            try {
                                controller.adicionarBibliotecaAlbum(email, nome);
                            } catch (AlbumJaNaBibliotecaException e) {
                                System.out.println("A biblioteca já contém o álbum: " + nome);
                            } catch (AlbumInexistenteException e) {
                                System.out.println("Álbum inexistente: " + nome);
                            }
                        } else {
                            System.out.println("Tipo inválido.");
                        }
                    } catch (UtilizadorFreeNaoPossuiBibliotecaException e) {
                        System.out.println("Plano Free não permite biblioteca. Utilizador: " + e.getMessage());
                    }
                    break;
                case "8":
                    alterarVisibilidadePlaylist(email);
                    break;
                case "9":
                    if (controller.utilizadorIsPremiumTop(email)) {
                        System.out.println("Já possui o plano mais elevado!");
                    } else {
                        boolean plano = controller.utilizadorIsPremiumTop(email);
                        upgradePlano(email, plano);
                    }
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
     * Permite ao utilizador tentar melhorar o seu plano atual, caso tenha pontos suficientes.
     *
     * @param email Email do utilizador.
     * @param plano Indica se o utilizador já está em plano PremiumTop.
     */
    public void upgradePlano(String email, boolean plano){
        if(!plano) {
            System.out.println("Para melhorar de plano precisa de 100 pontos!");
            System.out.println("Deseja gastar os seus pontos? (y/n)");
            String escolha = scanner.nextLine();

            if (escolha.equals("y")) {
                try {
                    controller.melhoraPlano(email);
                } catch (UtilizadorFaltaPontosException e) {
                    System.out.println("Utilizador " + e.getMessage() + " não tem pontos suficientes.");
                }
            } else {
                System.out.println("Operação cancelada. Plano não foi alterado.");
            }
        } else {
            System.out.println("Já tem o melhor plano disponível.");
        }
    }

    /**
     * Menu para o utilizador alterar a visibilidade de uma das suas playlists.
     *
     * Solicita o nome da playlist e tenta inverter a sua visibilidade (de pública para privada ou vice-versa).
     * Informa o utilizador do novo estado ou exibe mensagens de erro apropriadas.
     *
     * @param email Email do utilizador que está autenticado.
     */
    private void alterarVisibilidadePlaylist(String email) {
        System.out.print("Nome da playlist a alterar: ");
        String nome = scanner.nextLine();

        try {
            boolean novaVisibilidade = controller.alterarVisibilidadePlaylist(email, nome);
            System.out.println("Playlist \"" + nome + "\" agora é " + (novaVisibilidade ? "pública." : "privada."));
        } catch (PlaylistInexistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (UtilizadorNaoTemPermissoesException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}