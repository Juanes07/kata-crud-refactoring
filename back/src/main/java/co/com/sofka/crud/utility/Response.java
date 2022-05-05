package co.com.sofka.crud.utility;

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
