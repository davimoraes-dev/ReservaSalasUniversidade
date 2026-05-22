package com.exemplo.reservas.exception;

public class NegocioException extends Exception {
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}