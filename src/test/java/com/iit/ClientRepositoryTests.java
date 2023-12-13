package com.iit;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import tn.iit.entity.Client;
import tn.iit.repository.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientRepositoryTests {
	@Autowired
	private ClientRepository repo;

	// Test adding a new Client to the repository
	@Test
	public void testAddNew() {
		// Create a new Client
		Client Client = new Client();
		Client.setEmail("maryam0@gmail.com");
		Client.setMotdepasse("maryam123456");
		Client.setPrenom("maryam");
		Client.setNom("Smaoui");

		// Save the Client to the repository
		Client savedClient = repo.save(Client);

		// Check that the saved Client is not null and has an ID greater than 0
		Assertions.assertThat(savedClient).isNotNull();
		Assertions.assertThat(savedClient.getId());
	}

	// Test retrieving the list of all Clients
	@Test
	public void testListAll() {
		// Get all Clients from the repository
		Iterable<Client> Clients = repo.findAll();

		// Check that the list is not empty
		Assertions.assertThat(Clients).hasSizeGreaterThan(0);

		// Print each Client to the console
		for (Client Client : Clients) {
			System.out.println(Client);
		}
	}

	// Test updating the password of an existing Client
	@Test
	public void testUpdate() {
		// Specify the ID of the Client to update
		Integer ClientId = 1;

		// Retrieve the Client from the repository by their ID
		Optional<Client> optionalClient = repo.findById(ClientId);

		// Get the Client object from the Optional
		Client Client = optionalClient.get();

		// Update the Client's password
		Client.setMotdepasse("hello2000");

		// Save the updated Client back to the repository
		repo.save(Client);

		// Retrieve the Client again to check if the password is updated
		Client updatedClient = repo.findById(ClientId).get();

		// Check that the password is now "hello2000"
		Assertions.assertThat(updatedClient.getMotdepasse()).isEqualTo("hello2000");
	}

	// Test retrieving a Client by their ID
	@Test
	public void testGet() {
		// Specify the ID of the Client to retrieve
		Integer ClientId = 2;

		// Get the Client from the repository by their ID
		Optional<Client> optionalClient = repo.findById(ClientId);

		// Check that the Client is present
		Assertions.assertThat(optionalClient).isPresent();

		// Print the Client to the console
		System.out.println(optionalClient.get());
	}

	// Test deleting a Client by their ID
	@Test
	public void testDelete() {
		// Specify the ID of the Client to delete
		Integer ClientId = 2;

		// Delete the Client from the repository by their ID
		repo.deleteById(ClientId);

		// Check that the Client is no longer present in the repository
		Optional<Client> optionalClient = repo.findById(ClientId);
		Assertions.assertThat(optionalClient).isNotPresent();
	}

	// Test retrieving a Client by their ID

}