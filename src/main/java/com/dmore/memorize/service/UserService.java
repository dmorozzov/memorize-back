package com.dmore.memorize.service;

import com.dmore.memorize.model.User;
import com.dmore.memorize.model.dto.UserDTO;
import com.dmore.memorize.repository.UserRepository;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        LOGGER.info("Saving user: {}", userDTO);

        User user = mapperFacade.map(userDTO, User.class);
        user = userRepository.save(user);

        return mapperFacade.map(user, UserDTO.class);
    }

}
