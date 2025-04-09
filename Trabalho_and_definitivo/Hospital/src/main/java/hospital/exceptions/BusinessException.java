package hospital.exceptions;

import jakarta.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(name = "BusinessException")
public class BusinessException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }
}
