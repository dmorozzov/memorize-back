package com.dmore.memorize.service;

import com.dmore.memorize.model.Person;
import com.dmore.memorize.model.PersonWord;
import com.dmore.memorize.model.Word;
import com.dmore.memorize.model.dto.PersonWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import com.dmore.memorize.model.request.BaseRequest;
import com.dmore.memorize.model.request.PersonWordAddRequest;
import com.dmore.memorize.model.request.PersonWordListRequest;
import com.dmore.memorize.repository.PersonRepository;
import com.dmore.memorize.repository.PersonWordRepository;
import com.dmore.memorize.repository.WordRepository;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordService.class);

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonWordRepository personWordRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional
    public Page<PersonWordDTO> getPersonWordList(PersonWordListRequest listRequest) {
        Page<PersonWord> page = personWordRepository.findAll(
                PersonWordRepository.Specifications.findPersonWords(listRequest),
                listRequest.getPage()
        );
        List<PersonWordDTO> content = page.getContent().stream()
                .map(pw -> mapperFacade.map(pw, PersonWordDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(page.getNumber(), page.getSize()), page.getTotalElements());
    }

    @Transactional
    public PersonWordAddRequest appendWord(PersonWordAddRequest wordAddRequest) {
        WordDTO wordDTO = wordAddRequest.getWord();
        Long userId = wordAddRequest.getPersonId();
        if (wordDTO == null || userId == null) {
            LOGGER.error("Request {} is invalid", wordAddRequest);
            wordAddRequest.markError(BaseRequest.CommonError.INVALID_REQUEST);
            return wordAddRequest;
        }
        Optional<Person> userOptional = personRepository.findById(userId);
        if (!userOptional.isPresent()) {
            LOGGER.error("Such person {} doesn't exist", userId);
            wordAddRequest.markError(BaseRequest.CommonError.NO_SUCH_USER);
            return wordAddRequest;
        }

        Person person = userOptional.get();
        Word newWord = mapperFacade.map(wordDTO, Word.class);
        Optional<Word> existWordOpt = wordRepository.findByOriginal(wordDTO.getOriginal());

        if (existWordOpt.isPresent()) {

            if (isLinked(person, existWordOpt.get())) {
                LOGGER.warn("Such word {} has been already added to collection", wordDTO);
                wordAddRequest.markWarn("Such word has been already added to collection");
                return wordAddRequest;
            } else {
                newWord = existWordOpt.get();
            }

        } else {
            newWord.setCreateDate(LocalDate.now());
            newWord = wordRepository.save(newWord);
        }

        PersonWord personWord = linkWord(person, newWord);
        wordAddRequest.setPayload(mapperFacade.map(personWord, PersonWordDTO.class));
        return wordAddRequest;
    }

    private PersonWord linkWord(Person person, Word word) {
        PersonWord personWord = new PersonWord();
        personWord.setWord(word);
        personWord.setPerson(person);
        person.getWords().add(personWord);
        personRepository.save(person);
        return personWord;
    }

    private boolean isLinked(Person person, Word word) {
        return person.getWords().stream()
                .map(personWord -> personWord.getWord().getOriginal())
                .anyMatch(original -> original.equals(word.getOriginal()));
    }

    public WordDTO saveWord(WordDTO wordDTO) {
        if (wordDTO == null) {
            return null;
        }
        LOGGER.info("Saving word: {}", wordDTO);

        Word word = mapperFacade.map(wordDTO, Word.class);
        word.setCreateDate(LocalDate.now());
        word = wordRepository.save(word);

        return mapperFacade.map(word, WordDTO.class);
    }
}
