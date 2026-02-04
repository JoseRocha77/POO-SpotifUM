package Exceptions;

/**
 * Exceção lançada quando um utilizador tenta criar uma playlist baseada no seu
 * género favorito mas nunca ouviu uma música
 */
public class SemReproducoesException extends RuntimeException {
    public SemReproducoesException(String message) {
        super(message);
    }
}
