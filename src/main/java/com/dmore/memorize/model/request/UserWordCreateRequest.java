package com.dmore.memorize.model.request;

import com.dmore.memorize.model.dto.UserWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWordCreateRequest extends BaseRequestResponse<UserWordDTO> {
    private WordDTO word;
    private Long userId;
}
