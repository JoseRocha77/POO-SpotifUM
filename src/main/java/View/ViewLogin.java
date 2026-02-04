package View;

import Controller.Controller;
import Exceptions.EmailExistenteException;

import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe responsável por gerir a interface inicial de login e registo no sistema SpotifUM.
 * Permite autenticação, registo de novos utilizadores, execução de scripts de exemplo e visualização de estatísticas.
 */
public class ViewLogin {
    private Controller controller;
    private Scanner scanner;

    /**
     * Construtor da classe ViewLogin.
     *
     * @param scanner Scanner para entrada do utilizador.
     * @param controller Controlador responsável pela lógica da aplicação.
     */
    public ViewLogin(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    /**
     * Retorna o controlador associado.
     *
     * @return Controller da aplicação.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Retorna o scanner associado.
     *
     * @return Scanner de entrada.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Inicia o menu principal do sistema, permitindo login, registo, carregamento de script
     * e visualização de estatísticas.
     */
    public void start() {
        boolean running = true;

        // Tenta carregar estado do sistema
        try {
            controller.carregarEstado();
        } catch (EOFException e) {
            // Ficheiro vazio, não fazer nada
        } catch (IOException e) {
            System.out.println("Erro de IO ao carregar estado: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada ao carregar estado: " + e.getMessage());
        }

        while (running) {
            System.out.println("=== Bem-vindo ao SpotifUM ===");
            System.out.println("1. Login");
            System.out.println("2. Registar novo utilizador");
            System.out.println("3. Stats do Programa");
            System.out.println("4. Carregar script de exemplo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    login();
                    break;
                case "2":
                    registar();
                    break;
                case "3":
                    stats();
                    break;
                case "4":
                    try {
                        controller.carregarScript();
                        System.out.println("Script executado com sucesso.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Obrigado por utilizar o SpotifUM!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        // Guarda estado antes de sair
        try {
            controller.guardarEstado();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permite ao utilizador fazer login no sistema.
     * Caso o utilizador seja admin, direciona para o menu de administração.
     * Caso contrário, abre o menu de utilizador.
     */
    private void login() {
        System.out.println("=== Login ===");
        System.out.print("Email do Utilizador: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (controller.login(email, password)) {
            System.out.println("Login efetuado com sucesso!");
            if (controller.isAdmin(email)) {
                ViewAdmin viewAdmin = new ViewAdmin(scanner, controller);
                viewAdmin.menuAdmin();
            } else {
                ViewUtilizador viewUtilizador = new ViewUtilizador(scanner, controller);
                viewUtilizador.menuUtilizador(email);
            }
        } else {
            System.out.println("Login falhou. Verifique os dados inseridos.");
        }
    }

    /**
     * Permite o registo de um novo utilizador, solicitando dados como nome, email, password,
     * morada, plano de subscrição e cargo (utilizador ou admin).
     */
    private void registar() {
        System.out.println("=== Registar Novo Utilizador ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Morada: ");
        String morada = scanner.nextLine();

        System.out.println("Escolha o plano de subscrição:");
        System.out.println("1. Free");
        System.out.println("2. Premium Base");
        System.out.println("3. Premium Top");
        System.out.print("Opção: ");
        String planoOpcao = scanner.nextLine();

        System.out.println("Escolha o cargo:");
        System.out.println("1. Utilizador");
        System.out.println("2. Admin");
        System.out.print("Opção: ");
        String cargoOpcao = scanner.nextLine();

        try {
            controller.registar(nome, email, morada, password, planoOpcao, cargoOpcao);
            System.out.println("Utilizador registado com sucesso!");
        } catch (EmailExistenteException e) {
            System.out.println("Erro: Já existe um utilizador com o email: " + e.getMessage());
        }
    }

    /**
     * Abre o menu de estatísticas da aplicação.
     */
    private void stats() {
        ViewStats viewStats = new ViewStats(scanner, controller);
        viewStats.menuStats();
    }
}