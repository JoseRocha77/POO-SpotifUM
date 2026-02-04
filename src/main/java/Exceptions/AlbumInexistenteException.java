package Exceptions;

/**
 * Exceção lançada quando se tenta aceder a um álbum que não existe
 */
public class AlbumInexistenteException extends RuntimeException {
    public AlbumInexistenteException(String message) {
        super(message);
    }
}
