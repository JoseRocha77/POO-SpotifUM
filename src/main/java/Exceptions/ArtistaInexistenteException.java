package Exceptions;

/**
 * Exceção lançada quando é inserido o nome de um artista inexistente
 * ao adicionar uma música
 */
public class ArtistaInexistenteException extends RuntimeException {
    public ArtistaInexistenteException(String message) {
        super(message);
    }
}
