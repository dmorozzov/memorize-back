package com.dmore.memorize.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;

    @NotNull
    @Size(min=1, max=250)
    private String name;

    private LocalDate createDate;
}
