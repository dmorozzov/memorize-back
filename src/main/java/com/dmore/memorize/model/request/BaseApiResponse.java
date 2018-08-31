package com.dmore.memorize.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseApiResponse {

    public enum Status {SUCCESS, WARNING, FAILURE}

    private Status status;
    private String message;

    public static BaseApiResponse makeSuccess(String message) {
        return new BaseApiResponse(Status.SUCCESS, message);
    }

    public static BaseApiResponse makeFailure(String message) {
        return new BaseApiResponse(Status.FAILURE, message);
    }

    public static BaseApiResponse makeWarn(String message) {
        return new BaseApiResponse(Status.WARNING, message);
    }

}
