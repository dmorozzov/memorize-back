package com.dmore.memorize.model.request;

import com.dmore.memorize.model.dto.PersonWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonWordAddRequest extends BaseRequestResponse<PersonWordDTO> {
    private WordDTO word;
    private Long personId;
}
