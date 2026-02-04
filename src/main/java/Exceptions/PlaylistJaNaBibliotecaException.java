package Exceptions;

/**
 * Exceção lançada quando um utilizador tenta adicionar à sua biblioteca
 * uma playlist que já se encontra presente nela.
 */
public class PlaylistJaNaBibliotecaException extends RuntimeException {
    public PlaylistJaNaBibliotecaException(String message) {
        super(message);
    }
}
