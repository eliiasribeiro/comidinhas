package br.com.caelum.comidinhas.infra.validation;

import java.util.*;

class ValidationErrorsOutputDto {

    private final List<String> globalErrorMessages = new ArrayList<>();
    private final List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    void addError(String message) {
        globalErrorMessages.add(message);
    }

    void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErrors.size();
    }
}
