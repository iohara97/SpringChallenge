package com.br.meli.exception;

import java.sql.SQLException;

public class CustomException extends SQLException {

    private static final long serialVersionUID = -6476845811839837202L;

    public CustomException(String mensagem) {
        super(mensagem);
    }
}
