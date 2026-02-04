package Exceptions;

/**
 * Exceção lançada quando um utilizador free tenta reproduzir
 * uma playlist que não seja uma playlist aleatória
 */
public class PlaylistIsNotAleatoriaException extends RuntimeException {
    public PlaylistIsNotAleatoriaException(String message) {
        super(message);
    }
}
