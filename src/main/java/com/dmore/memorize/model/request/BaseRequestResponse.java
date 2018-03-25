package com.dmore.memorize.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseRequestResponse<T> extends BaseRequest {
    private T payload;
}
