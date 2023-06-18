package com.ibm.shop.services;

import com.ibm.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    /**
     * Quando injetamos através do construtor, isso obrigatóriamente
     * torna esse campo obrigatório, gerando problemas caso não
     * seja injetado. Ao injetar como parâmetros, como o @Autowired, a
     * injeção só vai ocorrer quando necessário, o que pode ocasionar um
     * NullPointerException
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one person by name " + username + "!");

        var user = this.userRepository.findByUserName(username);

        if (user != null) {
            return user;

        } else {
            throw new UsernameNotFoundException("Username" + username + "not found");
        }
    }
}

