package com.dmore.memorize.model.mapper;

import com.dmore.memorize.model.Person;
import com.dmore.memorize.model.PersonWord;
import com.dmore.memorize.model.Word;
import com.dmore.memorize.model.dto.PersonDTO;
import com.dmore.memorize.model.dto.PersonWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperConfigurer extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(Word.class, WordDTO.class)
                .byDefault()
                .register();

        factory.classMap(Person.class, PersonDTO.class)
                .byDefault()
                .register();

        factory.classMap(PersonWord.class, PersonWordDTO.class)
                .byDefault()
                .customize(new PersonWordToDTOMapper())
                .register();
    }

}
