package com.dmore.memorize.model.mapper;

import com.dmore.memorize.model.User;
import com.dmore.memorize.model.UserWord;
import com.dmore.memorize.model.Word;
import com.dmore.memorize.model.dto.UserDTO;
import com.dmore.memorize.model.dto.UserWordDTO;
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

        factory.classMap(User.class, UserDTO.class)
                .byDefault()
                .register();

        factory.classMap(UserWord.class, UserWordDTO.class)
                .byDefault()
                .customize(new UserWordToDTOMapper())
                .register();
    }

}
