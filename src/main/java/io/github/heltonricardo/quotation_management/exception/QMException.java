package io.github.heltonricardo.quotation_management.exception;

public abstract class QMException extends Exception {

    public QMException(String message) {
        super("Error: " + message + ".");
    }
}
