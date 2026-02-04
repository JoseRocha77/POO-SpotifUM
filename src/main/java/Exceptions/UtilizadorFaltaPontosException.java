package Exceptions;

/**
 * Exceção lançada quando um utilizador tenta melhorar o seu plano de subscrição
 * mas não tem pontos suficientes
 */
public class UtilizadorFaltaPontosException extends RuntimeException {
    public UtilizadorFaltaPontosException(String message) {
        super(message);
    }
}
