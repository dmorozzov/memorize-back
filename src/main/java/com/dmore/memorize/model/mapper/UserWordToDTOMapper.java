package com.dmore.memorize.model.mapper;

import com.dmore.memorize.model.UserWord;
import com.dmore.memorize.model.dto.UserDTO;
import com.dmore.memorize.model.dto.UserWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class UserWordToDTOMapper extends CustomMapper<UserWord, UserWordDTO> {

    @Override
    public void mapAtoB(UserWord source, UserWordDTO dest, MappingContext context) {
        super.mapAtoB(source, dest, context);
        dest.setWordDTO(mapperFacade.map(source.getWord(), WordDTO.class));
        dest.setUserDTO(mapperFacade.map(source.getUser(), UserDTO.class));
    }
}
