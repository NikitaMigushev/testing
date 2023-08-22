package com.manning.javapersistence.testing.service;

import com.manning.javapersistence.testing.model.AppUser;
import com.manning.javapersistence.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveTransactionally(AppUser appUser) {
        userRepository.save(appUser);
    }
}
