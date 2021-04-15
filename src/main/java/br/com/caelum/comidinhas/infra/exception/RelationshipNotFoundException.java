package br.com.caelum.comidinhas.infra.exception;

public class RelationshipNotFoundException extends RuntimeException{
    public RelationshipNotFoundException(String message) {
        super(message);
    }
}
