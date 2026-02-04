package Classes.Planos;


/**
 * Interface que representa um plano de subscrição no sistema SpotifUM.
 * Cada plano define a forma como os utilizadores ganham pontos por música,
 * bem como as permissões associadas ao mesmo.
 */
public interface PlanoSubscricao {

    /**
     * Calcula os pontos a atribuir ao utilizador por ouvir uma música.
     *
     * @param pontosAtuais Pontuação atual do utilizador.
     * @param musicaNova   {@code true} se for a primeira vez que o utilizador ouve a música, {@code false} caso contrário.
     * @return Número de pontos a atribuir.
     */
    int pontosPorMusica(int pontosAtuais, boolean musicaNova);

    /**
     * Verifica se o plano permite ao utilizador criar playlists personalizadas.
     *
     * @return {@code true} se o plano permite criar playlists, {@code false} caso contrário.
     */
    boolean podeCriarPlaylists();

    /**
     * Verifica se o plano permite ao utilizador aceder às playlists de favoritos.
     *
     * @return {@code true} se o plano permite aceder a favoritos, {@code false} caso contrário.
     */
    boolean podeAcederAFavoritos();

    /**
     * Retorna o nome do plano de subscrição.
     *
     * @return Nome do plano.
     */
    String getNomePlano();
}