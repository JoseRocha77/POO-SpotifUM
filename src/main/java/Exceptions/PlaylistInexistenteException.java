package Exceptions;

/**
 * Exceção lançada quando se tenta reproduzir uma playlist não existente
 * no programa
 */
public class PlaylistInexistenteException extends RuntimeException {
    public PlaylistInexistenteException(String message) {
        super(message);
    }
}
