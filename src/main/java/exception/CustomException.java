package exception;

import java.sql.SQLException;

public class CustomException extends SQLException {

    private static final long serialVersionUID = -6454564965566059988L;

    public CustomException(String mensagem) {
        super(mensagem);
    }

}
