package com.dmore.memorize.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {

    public enum Status {SUCCESS, WARN, ERROR}

    public enum CommonError {
        INVALID_REQUEST, NO_SUCH_USER
    }

    private Status status = Status.SUCCESS;

    private String message;

    private void mark(String message, Status status) {
        this.status = status;
        this.message = message;
    }

    public void markWarn(String message) {
        mark(message, Status.WARN);
    }

    public void markError(String message, CommonError error) {
        mark(message, Status.ERROR);
    }

    public void markError(CommonError error) {
        mark(error.name(), Status.ERROR);
    }
}
