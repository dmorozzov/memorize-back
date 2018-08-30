package com.dmore.memorize.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserWordDTO {
    private UserDTO userDTO;
    private WordDTO wordDTO;
    private LocalDateTime createDate;
}
