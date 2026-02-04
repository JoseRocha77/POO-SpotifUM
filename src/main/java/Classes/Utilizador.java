package Classes;

import Classes.Planos.PlanoPremiumBase;
import Classes.Planos.PlanoPremiumTop;
import Classes.Planos.PlanoSubscricao;
import Classes.Playlists.Playlist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa um Utilizador do sistema SpotifUM.
 * Contém dados pessoais, plano de subscrição, cargo e a biblioteca (caso aplicável).
 * Permite acumular pontos com base na reprodução de músicas.
 */
public class Utilizador implements Serializable {
    private String nome;
    private String email;
    private String morada;
    private String password;
    private int pontos;
    private PlanoSubscricao planoSubscricao;
    private Cargo cargo;
    private Biblioteca biblioteca;

    /**
     * Construtor por omissão.
     * Inicializa todos os campos com valores padrão.
     */
    public Utilizador(){
        this.nome = "";
        this.email = "";
        this.morada = "";
        this.password = "";
        this.pontos = 0;
        this.planoSubscricao = null;
        this.cargo = Cargo.USER;
        this.biblioteca = new Biblioteca();
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome              Nome do utilizador.
     * @param email             Email do utilizador.
     * @param morada            Morada do utilizador.
     * @param password          Password do utilizador.
     * @param pontos            Pontos acumulados.
     * @param planoSubscricao   Plano de subscrição do utilizador.
     * @param cargo             Cargo (USER ou ADMIN).
     * @param biblioteca        Biblioteca associada ao utilizador (apenas para premium).
     */
    public Utilizador(String nome, String email, String morada, String password, int pontos, PlanoSubscricao planoSubscricao,
                      Cargo cargo, Biblioteca biblioteca){
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.password = password;
        this.pontos = pontos;
        this.planoSubscricao = planoSubscricao;
        this.cargo = cargo;
        this.biblioteca = new Biblioteca(biblioteca);
    }

    /**
     * Construtor de cópia.
     *
     * @param utilizador Utilizador a copiar.
     */
    public Utilizador(Utilizador utilizador){
        this.nome = utilizador.getNome();
        this.email = utilizador.getEmail();
        this.morada = utilizador.getMorada();
        this.password = utilizador.getPassword();
        this.pontos = utilizador.getPontos();
        this.planoSubscricao = utilizador.getPlanoSubscricao();
        this.cargo = utilizador.getCargo();
        this.biblioteca = utilizador.getBiblioteca();
    }

    /**
     * Obtém o nome do utilizador.
     *
     * @return Nome do utilizador.
     */
    public String getNome() { return this.nome; }

    /**
     * Obtém o email do utilizador.
     *
     * @return Email associado ao utilizador.
     */
    public String getEmail() { return this.email; }

    /**
     * Obtém a morada do utilizador.
     *
     * @return Morada do utilizador.
     */
    public String getMorada() { return this.morada; }

    /**
     * Obtém a password do utilizador.
     *
     * @return Palavra-passe do utilizador.
     */
    public String getPassword() { return this.password; }

    /**
     * Obtém o número de pontos acumulados pelo utilizador.
     *
     * @return Pontos do utilizador.
     */
    public int getPontos() { return this.pontos; }

    /**
     * Obtém o plano de subscrição atual do utilizador.
     *
     * @return Objeto que representa o plano de subscrição.
     */
    public PlanoSubscricao getPlanoSubscricao() { return this.planoSubscricao; }

    /**
     * Obtém o cargo (admin ou utilizador normal) do utilizador.
     *
     * @return Cargo do utilizador.
     */
    public Cargo getCargo() { return this.cargo; }

    /**
     * Obtém a biblioteca pessoal do utilizador.
     * Pode ser {@code null} se o utilizador estiver num plano Free.
     *
     * @return Biblioteca do utilizador.
     */
    public Biblioteca getBiblioteca() { return this.biblioteca; }

    /**
     * Define o nome do utilizador.
     *
     * @param nome Novo nome a ser definido.
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * Define o email do utilizador.
     *
     * @param email Novo endereço de email.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Define a morada do utilizador.
     *
     * @param morada Nova morada.
     */
    public void setMorada(String morada) { this.morada = morada; }

    /**
     * Define a password do utilizador.
     *
     * @param password Nova palavra-passe.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Atualiza os pontos acumulados do utilizador.
     *
     * @param pontos Novo total de pontos.
     */
    public void setPontos(int pontos) { this.pontos = pontos; }


    /**
     * Define o plano de subscrição.
     * Caso seja PlanoPremiumTop, o utilizador começa com 100 pontos.
     *
     * @param planoSubscricao Novo plano.
     */
    public void setPlanoSubscricao(PlanoSubscricao planoSubscricao) {
        this.planoSubscricao = planoSubscricao;
        if(planoSubscricao instanceof PlanoPremiumTop){
            this.pontos = 100;
        }
    }

    /** @param cargo Novo cargo do utilizador. */
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    /**
     * Define a biblioteca, apenas para utilizadores premium.
     *
     * @param biblioteca Nova biblioteca.
     * @throws UnsupportedOperationException se o utilizador for Free.
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        if (planoSubscricao instanceof PlanoPremiumBase || planoSubscricao instanceof PlanoPremiumTop) {
            this.biblioteca = biblioteca.clone();
        } else {
            throw new UnsupportedOperationException("Utilizadores Free não podem ter biblioteca.");
        }
    }

    /**
     * Representação textual do utilizador.
     *
     * @return String formatada com os dados do utilizador.
     */
    public String toString() {
        return "Utilizador{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", morada='" + morada + '\'' +
                ", password='" + password + '\'' +
                ", pontos=" + pontos +
                ", planoSubscricao=" + planoSubscricao.getNomePlano() +
                ", cargo=" + cargo +
                ", biblioteca=" + biblioteca.toString() +
                '}';
    }

    /**
     * Verifica se dois utilizadores são iguais.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilizador u = (Utilizador) o;
        return (this.nome.equals(u.nome)) && (this.email.equals(u.email)) && (this.morada.equals(u.morada))
                && (this.password.equals(u.getPassword())) && (this.pontos == u.pontos)
                && (this.planoSubscricao.equals(u.planoSubscricao)) && (this.cargo == u.getCargo())
                && (this.biblioteca.equals(u.getBiblioteca()));
    }

    /**
     * Cria uma cópia deste utilizador.
     *
     * @return Novo objeto Utilizador com os mesmos dados.
     */
    public Utilizador clone(){
        return new Utilizador(this);
    }

    /**
     * Atualiza os pontos do utilizador conforme o seu plano de subscrição
     * e se a música já foi ouvida ou não.
     *
     * @param jaOuviu true se a música já foi ouvida antes.
     */
    public void ganharPontos(boolean jaOuviu){
        if (this.planoSubscricao != null) {
            int pontosGanhos = this.planoSubscricao.pontosPorMusica(this.pontos, jaOuviu);
            System.out.println("Pontos Ganhos: " + pontosGanhos);
            this.pontos += pontosGanhos;
        }
    }

    /**
     * Verifica se o utilizador tem um plano premium (Base ou Top).
     *
     * @return true se for premium, false caso contrário.
     */
    public boolean isPremium(){
        return (planoSubscricao instanceof PlanoPremiumBase) || (planoSubscricao instanceof PlanoPremiumTop);
    }

    /**
     * Adiciona uma playlist à biblioteca.
     *
     * @param playlist Playlist a adicionar.
     */
    public void adicionarPlaylist(Playlist playlist){
        this.biblioteca.adicionarPlaylist(playlist);
    }

    /**
     * Adiciona um álbum à biblioteca.
     *
     * @param a Álbum a adicionar.
     */
    public void adicionarAlbum(Album a){
        this.biblioteca.adicionarAlbum(a);
    }
}
