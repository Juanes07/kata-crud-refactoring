package co.com.sofka.crud.utility;

/**
 * GroupList Rest Controller
 *
 * @author Julian lasso
 * @version 1.0.0
 * @since 1.0.0
 */

public class Response {

    public boolean error;

    public String message;

    public Object data;

    public Response() {
        error = false;
        message = "";
        data = null;
    }

    public void restart() {
        error = false;
        message = "";
        data = null;
    }

}
