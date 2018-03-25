package com.dmore.memorize.model.mapper;

import com.dmore.memorize.model.PersonWord;
import com.dmore.memorize.model.dto.PersonDTO;
import com.dmore.memorize.model.dto.PersonWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PersonWordToDTOMapper extends CustomMapper<PersonWord, PersonWordDTO> {

    @Override
    public void mapAtoB(PersonWord source, PersonWordDTO dest, MappingContext context) {
        super.mapAtoB(source, dest, context);
        dest.setWordDTO(mapperFacade.map(source.getWord(), WordDTO.class));
        dest.setPersonDTO(mapperFacade.map(source.getPerson(), PersonDTO.class));
    }
}
