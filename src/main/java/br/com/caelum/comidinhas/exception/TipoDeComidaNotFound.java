package br.com.caelum.comidinhas.exception;

public class TipoDeComidaNotFound extends RuntimeException{

    public TipoDeComidaNotFound() {
        super();
    }

    public TipoDeComidaNotFound(String message) {
        super(message);
    }
}
