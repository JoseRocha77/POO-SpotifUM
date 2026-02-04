package View;

import Controller.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe responsável por apresentar estatísticas relacionadas com a utilização
 * da aplicação SpotifUM. Permite ao utilizador consultar dados como música mais reproduzida,
 * utilizador com mais pontos, artista mais escutado, entre outros.
 */
public class ViewStats {
    private Scanner scanner;
    private Controller controller;

    /**
     * Construtor da classe ViewStats.
     *
     * @param scanner    Scanner para input do utilizador.
     * @param controller Controller responsável pela ligação à camada de modelo.
     */
    public ViewStats(Scanner scanner, Controller controller){
        this.scanner = scanner;
        this.controller = controller;
    }

    /**
     * Apresenta o menu principal de estatísticas e processa as opções selecionadas pelo utilizador.
     */
    public void menuStats(){
        boolean loggedIn = true;

        while (loggedIn){
            System.out.println("\n=== Menu Principal - Stats ===");
            System.out.println("1. Música mais reproduzida");
            System.out.println("2. Utilizador com mais músicas ouvidas num período");
            System.out.println("3. Artista mais escutado");
            System.out.println("4. Utilizador com mais pontos");
            System.out.println("5. Género com mais reproduções");
            System.out.println("6. Número de playlists públicas");
            System.out.println("7. Utilizador com mais playlists");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": musicaMaisReproduzida(); break;
                case "2": utilizadorMaisMusicas(); break;
                case "3": artistaMaisEscutado(); break;
                case "4": utilizadorMaisPontos(); break;
                case "5": generoComMaisReproducoes(); break;
                case "6": quantasPlaylistsPublicas(); break;
                case "7": ultilizadorComMaisPLaylist(); break;
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
     * Mostra a música mais reproduzida.
     */
    private void musicaMaisReproduzida(){
        System.out.println("Calcular música com mais reproduções...");
        String nome = controller.countMusicas();
        System.out.println(nome);
    }

    /**
     * Mostra o utilizador com mais pontos.
     */
    private void utilizadorMaisPontos(){
        System.out.println("Calcular utilizador com mais pontos...");
        String nome = controller.countUtilizador();
        if (nome.equals(".")) System.out.println("Erro: Não existem utilizadores.");
        else System.out.println(nome);
    }

    /**
     * Mostra o género musical com mais reproduções.
     */
    private void generoComMaisReproducoes(){
        System.out.println("Calcular género musical com mais reproduções...");
        String nome = controller.generoMaisReproduzido();
        System.out.println(nome);
    }

    /**
     * Mostra a quantidade de playlists públicas existentes.
     */
    private void quantasPlaylistsPublicas(){
        System.out.println("Calcular quantas playlists são públicas...");
        Integer n = controller.playlistPublicas();
        System.out.println("A quantidade de playlists públicas é: " + n);
    }

    /**
     * Mostra o utilizador com mais playlists criadas.
     */
    private void ultilizadorComMaisPLaylist(){
        System.out.println("Calcular utilizador com mais playlists...");
        String nome = controller.utilizadorMaisPlaylists();
        System.out.println(nome);
    }

    /**
     * Mostra o utilizador que mais ouviu músicas, com possibilidade de filtrar por intervalo de tempo.
     */
    private void utilizadorMaisMusicas(){
        System.out.println("Deseja escolher o período de tempo? (yes/no)");
        String opcao = scanner.nextLine();

        LocalDateTime inicio = LocalDateTime.of(1000,1,1,0,0,0);
        LocalDateTime fim = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (opcao.equalsIgnoreCase("yes")){
            boolean dataValida = false;
            while (!dataValida) {
                try {
                    System.out.print("Data de Início (yyyy-MM-dd HH:mm): ");
                    inicio = LocalDateTime.parse(scanner.nextLine(), formatter);
                    System.out.print("Data de Fim (yyyy-MM-dd HH:mm): ");
                    fim = LocalDateTime.parse(scanner.nextLine(), formatter);
                    dataValida = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Use o formato: yyyy-MM-dd HH:mm.");
                }
            }
        }

        String nome = controller.obterUtilizadorComMaisReproducoes(inicio, fim);

        if (opcao.equalsIgnoreCase("no")){
            System.out.println("O utilizador que mais ouviu músicas desde sempre foi: " + nome);
        } else {
            System.out.println("O utilizador que mais ouviu músicas entre (" + inicio.format(formatter) + ") e (" + fim.format(formatter) + ") foi: " + nome);
        }
    }

    /**
     * Mostra o artista mais escutado.
     */
    private void artistaMaisEscutado(){
        System.out.println("Calcular artista mais escutado...");
        String nome = controller.calcArtistaMaisEscutado();
        System.out.println("Este é o artista mais escutado: " + nome);
    }
}