package Exceptions;

/**
 * Exceção lançada quando um utilizador tenta fazer alguma ação que o seu plano
 * de subscrição não permite
 */
public class UtilizadorNaoTemPermissoesException extends RuntimeException {
    public UtilizadorNaoTemPermissoesException(String message) {
        super(message);
    }
}
