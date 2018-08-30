package com.dmore.memorize.service;

import com.dmore.memorize.model.User;
import com.dmore.memorize.model.UserWord;
import com.dmore.memorize.model.Word;
import com.dmore.memorize.model.dto.UserWordDTO;
import com.dmore.memorize.model.dto.WordDTO;
import com.dmore.memorize.model.request.BaseRequest;
import com.dmore.memorize.model.request.UserWordCreateRequest;
import com.dmore.memorize.model.request.UserWordListRequest;
import com.dmore.memorize.repository.UserRepository;
import com.dmore.memorize.repository.UserWordRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordService.class);

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWordRepository userWordRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional
    public Page<UserWordDTO> getUserWordList(UserWordListRequest listRequest) {
        Page<UserWord> page = userWordRepository.findAll(
                UserWordRepository.Specifications.findUserWords(listRequest),
                listRequest.getPage()
        );
        List<UserWordDTO> content = page.getContent().stream()
                .map(pw -> mapperFacade.map(pw, UserWordDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(page.getNumber(), page.getSize()), page.getTotalElements());
    }

    @Transactional
    public UserWordCreateRequest bindWord(UserWordCreateRequest wordBindRequest) {
        WordDTO wordDTO = wordBindRequest.getWord();
        Long userId = wordBindRequest.getUserId();
        if (wordDTO == null || userId == null) {
            LOGGER.error("Request {} is invalid", wordBindRequest);
            wordBindRequest.markError(BaseRequest.CommonError.INVALID_REQUEST);
            return wordBindRequest;
        }
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            LOGGER.error("Such user {} doesn't exist", userId);
            wordBindRequest.markError(BaseRequest.CommonError.NO_SUCH_USER);
            return wordBindRequest;
        }

        User user = userOptional.get();
        Word newWord = mapperFacade.map(wordDTO, Word.class);
        Optional<Word> existWordOpt = wordRepository.findByOriginal(wordDTO.getOriginal());

        if (existWordOpt.isPresent()) {

            if (isLinked(user, existWordOpt.get())) {
                LOGGER.warn("Such word {} has been already added to collection", wordDTO);
                wordBindRequest.markWarn("Such word has been already added to collection");
                return wordBindRequest;
            } else {
                newWord = existWordOpt.get();
            }

        } else {
            newWord = wordRepository.save(newWord);
        }

        UserWord userWord = linkWord(user, newWord);
        wordBindRequest.setPayload(mapperFacade.map(userWord, UserWordDTO.class));
        return wordBindRequest;
    }

    private UserWord linkWord(User user, Word word) {
        UserWord userWord = new UserWord();
        userWord.setWord(word);
        userWord.setUser(user);
        user.getWords().add(userWord);
        userRepository.save(user);
        return userWord;
    }

    private boolean isLinked(User user, Word word) {
        return user.getWords().stream()
                .map(userWord -> userWord.getWord().getOriginal())
                .anyMatch(original -> original.equals(word.getOriginal()));
    }

    public WordDTO saveWord(WordDTO wordDTO) {
        if (wordDTO == null) {
            return null;
        }
        LOGGER.info("Saving word: {}", wordDTO);

        Word word = mapperFacade.map(wordDTO, Word.class);
        word = wordRepository.save(word);

        return mapperFacade.map(word, WordDTO.class);
    }
}
