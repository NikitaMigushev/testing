package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SaveRetrieveUserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveRetrieve() {
        userRepository.save(new AppUser("User1"));
        List<AppUser> users = userRepository.findAll();
        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("User1", users.get(0).getName())
        );
    }
}