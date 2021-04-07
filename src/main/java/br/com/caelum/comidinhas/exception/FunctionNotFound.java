package br.com.caelum.comidinhas.exception;

public class FunctionNotFound extends RuntimeException{

    public FunctionNotFound() {
        super();
    }

    public FunctionNotFound(String message) {
        super(message);
    }
}
