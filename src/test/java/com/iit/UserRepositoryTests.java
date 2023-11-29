package com.iit;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.entity.User;
import tn.iit.entity.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;

	@Test
	public void testAddNew() {
		User user = new User();
		user.setEmail("alex.stevenson@gmail.com");
		user.setPassword("alex123456");
		user.setFirsttName("Alex");
		user.setLastName("Stevenson");

		User savedUser = repo.save(user);

		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAll() {
		Iterable<User> users = repo.findAll();
		Assertions.assertThat(users).hasSizeGreaterThan(0);
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void testGet() {
		Integer userId = 2;
		Optional<User> optionalUser = repo.findById(userId);
		Assertions.assertThat(optionalUser).isPresent();
		System.out.println(optionalUser.get());

	}
	 @Test
	    public void testDelete() {
	        Integer userId = 2;
	        repo.deleteById(userId);

	        Optional<User> optionalUser = repo.findById(userId);
	        Assertions.assertThat(optionalUser).isNotPresent();
	    }
	

}
