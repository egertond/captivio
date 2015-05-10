package model;

import play.i18n.Messages;
import play.mvc.Controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

    Object error;

    String message;

    int status;

    public Response() {
        status = Controller.BAD_REQUEST;
        message = Messages.get("contactUs.error");
    }

    public Object getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
