package com.br.meli.exception;

import java.sql.SQLException;

public class CustomException extends SQLException {

    private static final long serialVersionUID = -5289430065774575207L;

    public CustomException(String mensagem) {
        super(mensagem);
    }
}
