package Exceptions;

/**
 * Exceção lançada quando se tenta fazer o registo de um utlizador
 * com um email já em uso
 */
public class EmailExistenteException extends Exception{
    public EmailExistenteException(String msg){
        super(msg);
    }
}
