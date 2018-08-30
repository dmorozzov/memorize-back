package com.dmore.memorize.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
public class UserWordListRequest {
    private Integer userId;
    private Integer pageNumber = 0;
    private Integer pageSize = 20;

    public Pageable getPage() {
        return PageRequest.of(pageNumber, pageSize);
    }

}
