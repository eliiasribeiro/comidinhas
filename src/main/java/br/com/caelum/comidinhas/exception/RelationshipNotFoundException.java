package br.com.caelum.comidinhas.exception;

public class RelationshipNotFoundException extends RuntimeException{

    public RelationshipNotFoundException() {
        super();
    }

    public RelationshipNotFoundException(String message) {
        super(message);
    }
}
