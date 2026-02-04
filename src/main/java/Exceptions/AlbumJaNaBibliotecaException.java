package Exceptions;

/**
 * Exceção lançada quando um utilizador tenta adicionar à sua biblioteca
 * um álbum que já se encontra presente nela.
 */
public class AlbumJaNaBibliotecaException extends RuntimeException {
    public AlbumJaNaBibliotecaException(String message) {
        super(message);
    }
}
