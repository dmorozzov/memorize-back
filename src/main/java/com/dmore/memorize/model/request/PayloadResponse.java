package com.dmore.memorize.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayloadResponse<T> extends BaseApiResponse {
    private T payload;
}
