package Exceptions;

/**
 * Exceção lançada quando um utilizador free tenta adicionar um àlbum/playlist
 * à sua biblioteca
 */
public class UtilizadorFreeNaoPossuiBibliotecaException extends RuntimeException {
    public UtilizadorFreeNaoPossuiBibliotecaException(String message) {
        super(message);
    }
}
