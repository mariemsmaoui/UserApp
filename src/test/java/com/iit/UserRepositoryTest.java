package com.iit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import tn.iit.entity.User;
import tn.iit.entity.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase (replace =AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)

public class UserRepositoryTest {


	@MockBean

    @Autowired
    private UserRepository repository;


    @Test
    public void testAddNew() {
        // Arrange
        User user = new User();
        user.setEmail("maryam10@gmail.com");
        user.setPassword("123456");
        user.setFirsttName("mariem");
        user.setLastName("smaoui");

     // Avant l'insertion
        System.out.println("Avant insertion");
        User savedUser = repository.save(user);

        // Après l'insertion
        System.out.println("Après insertion");


        // Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
