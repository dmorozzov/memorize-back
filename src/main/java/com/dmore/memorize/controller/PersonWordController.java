package com.dmore.memorize.controller;

import com.dmore.memorize.model.dto.PersonDTO;
import com.dmore.memorize.model.dto.PersonWordDTO;
import com.dmore.memorize.model.request.PersonWordAddRequest;
import com.dmore.memorize.model.request.PersonWordListRequest;
import com.dmore.memorize.service.PersonService;
import com.dmore.memorize.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class PersonWordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonWordController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private WordService wordService;

    @RequestMapping(value = "person/word-list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PersonWordDTO> getPersonWordList(@RequestBody PersonWordListRequest personWordListRequest) {
        LOGGER.info("Search words for {} request.", personWordListRequest);
        return wordService.getPersonWordList(personWordListRequest);
    }

    @RequestMapping(value = "/word/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonWordAddRequest appendWord(@Valid @RequestBody PersonWordAddRequest personWordAddRequest) {
        LOGGER.info("Save word \'{}\' for person #{}.", personWordAddRequest.getWord().getOriginal(), personWordAddRequest.getPersonId());
        return wordService.appendWord(personWordAddRequest);
    }

    @RequestMapping(value = "person/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO save(PersonDTO personDTO) {
        return personService.savePerson(personDTO);
    }

}
