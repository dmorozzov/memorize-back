package com.dmore.memorize.controller;

import com.dmore.memorize.model.dto.UserWordDTO;
import com.dmore.memorize.model.request.UserWordCreateRequest;
import com.dmore.memorize.model.request.UserWordListRequest;
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
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserWordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWordController.class);

    @Autowired
    private WordService wordService;

    @RequestMapping(value = "/user/words", method = RequestMethod.POST)
    public Page<UserWordDTO> getUserWordList(@RequestBody UserWordListRequest userWordListRequest) {
        LOGGER.info("Search words for {} request.", userWordListRequest);
        return wordService.getUserWordList(userWordListRequest);
    }

    @RequestMapping(value = "/word/save", method = RequestMethod.POST)
    public UserWordCreateRequest appendWord(@Valid @RequestBody UserWordCreateRequest userWordCreateRequest) {
        LOGGER.info("Save word \'{}\' for user #{}.", userWordCreateRequest.getWord().getOriginal(), userWordCreateRequest.getUserId());
        return wordService.bindWord(userWordCreateRequest);
    }

}
