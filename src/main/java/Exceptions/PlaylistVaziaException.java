package Exceptions;

/**
 * Exceção lançada quando um utilizador tenta reproduzir uma playlist
 * que não contém nenhuma música
 */
public class PlaylistVaziaException extends RuntimeException {
    public PlaylistVaziaException(String message) {
        super(message);
    }
}
