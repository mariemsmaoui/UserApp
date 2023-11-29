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

	// Test adding a new user to the repository
	@Test
	public void testAddNew() {
		// Create a new user
		User user = new User();
		user.setEmail("maryam0@gmail.com");
		user.setPassword("maryam123456");
		user.setFirstName("maryam");
		user.setLastName("Smaoui");

		// Save the user to the repository
		User savedUser = repo.save(user);

		// Check that the saved user is not null and has an ID greater than 0
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}

	// Test retrieving the list of all users
	@Test
	public void testListAll() {
		// Get all users from the repository
		Iterable<User> users = repo.findAll();

		// Check that the list is not empty
		Assertions.assertThat(users).hasSizeGreaterThan(0);

		// Print each user to the console
		for (User user : users) {
			System.out.println(user);
		}
	}

	// Test updating the password of an existing user
	@Test
	public void testUpdate() {
		// Specify the ID of the user to update
		Integer userId = 1;

		// Retrieve the user from the repository by their ID
		Optional<User> optionalUser = repo.findById(userId);

		// Get the user object from the Optional
		User user = optionalUser.get();

		// Update the user's password
		user.setPassword("hello2000");

		// Save the updated user back to the repository
		repo.save(user);

		// Retrieve the user again to check if the password is updated
		User updatedUser = repo.findById(userId).get();

		// Check that the password is now "hello2000"
		Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2000");
	}

	// Test retrieving a user by their ID
	@Test
	public void testGet() {
		// Specify the ID of the user to retrieve
		Integer userId = 2;

		// Get the user from the repository by their ID
		Optional<User> optionalUser = repo.findById(userId);

		// Check that the user is present
		Assertions.assertThat(optionalUser).isPresent();

		// Print the user to the console
		System.out.println(optionalUser.get());
	}

	// Test deleting a user by their ID
	@Test
	public void testDelete() {
		// Specify the ID of the user to delete
		Integer userId = 2;

		// Delete the user from the repository by their ID
		repo.deleteById(userId);

		// Check that the user is no longer present in the repository
		Optional<User> optionalUser = repo.findById(userId);
		Assertions.assertThat(optionalUser).isNotPresent();
	}

	// Test retrieving a user by their ID

}