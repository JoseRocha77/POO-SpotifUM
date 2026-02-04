package Exceptions;

/**
 * Exceção lançada quando se tenta reproduzir uma música não existente
 * no programa
 */
public class MusicaInexistenteException extends RuntimeException {
    public MusicaInexistenteException(String message) {super(message);
    }
}
