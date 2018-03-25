package com.dmore.memorize.service;

import com.dmore.memorize.model.Person;
import com.dmore.memorize.model.dto.PersonDTO;
import com.dmore.memorize.repository.PersonRepository;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional
    public PersonDTO savePerson(PersonDTO personDTO) {
        if (personDTO == null) {
            return null;
        }
        LOGGER.info("Saving person: {}", personDTO);

        Person person = mapperFacade.map(personDTO, Person.class);
        person.setCreateDate(LocalDate.now());
        person = personRepository.save(person);

        return mapperFacade.map(person, PersonDTO.class);
    }

}
