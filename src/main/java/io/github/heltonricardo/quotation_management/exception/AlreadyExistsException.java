package io.github.heltonricardo.quotation_management.exception;

public class AlreadyExistsException extends QMException {

    public AlreadyExistsException() {
        super("Already exists");
    }
}
