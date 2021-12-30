package exception;

import java.io.Serializable;
import java.sql.SQLException;

public class CustomException extends SQLException implements Serializable {

    private static final long serialVersionUID = -6454564965566059988L;

    public CustomException(String mensagem) {
        super(mensagem);
    }

}
