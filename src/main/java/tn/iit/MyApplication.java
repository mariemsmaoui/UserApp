package tn.iit;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import tn.iit.dto.ClientDto;
import tn.iit.dto.CompteBancaireDto;
import tn.iit.dto.CompteCourantDto;
import tn.iit.dto.CompteEpargneDto;
import tn.iit.entity.Client;
import tn.iit.entity.CompteBancaire;
import tn.iit.entity.CompteCourant;
import tn.iit.entity.CompteEpargne;
import tn.iit.entity.OperationCompte;
import tn.iit.enums.EtatCompte;
import tn.iit.enums.TypeOperation;
import tn.iit.exception.ClientNotFoundException;
import tn.iit.exception.CompteBancaireNotFoundException;
import tn.iit.exception.SoldeNotSufficientException;
import tn.iit.repository.ClientRepository;
import tn.iit.repository.CompteBancaireRepository;
import tn.iit.repository.OperationCompteRepository;
import tn.iit.services.CompteBancaireService;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition
@EntityScan("tn.iit.entity")
@ComponentScan("tn.iit")

public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);

	}

	@Bean
	CommandLineRunner start(CompteBancaireService compteBancaireService) {
		return args -> {
			Stream.of("hana", "eya", "amine").forEach(name -> {
				ClientDto client = new ClientDto();
				client.setNom(name);
				client.setEmail(name);
				client.setPrenom(name);

				client.setEmail(name + "@gmail.com");
				client.setMotdepasse("123456");

				compteBancaireService.saveClient(client);
				
			});
	
			compteBancaireService.listClients().forEach(client -> {
			    try {
			        compteBancaireService.saveCompteCourant(Math.random() * 90000, 9000, client.getId());
			        compteBancaireService.saveCompteEpargne(Math.random() * 120000, 5.5, client.getId());
			    } catch (ClientNotFoundException e) {
			        e.printStackTrace();
			    }
			});

			List<CompteBancaireDto> compteBancaires = compteBancaireService.CompteBancaireList();
			for (CompteBancaireDto compteBancaire : compteBancaires) {
				for (int i = 0; i < 10; i++) {
					String compteId;
					if (compteBancaire instanceof CompteEpargneDto) {
						compteId = ((CompteEpargneDto) compteBancaire).getRib();
					} else {
						compteId = ((CompteCourantDto) compteBancaire).getRib();
					}
					try {
						compteBancaireService.retrait(compteId, 10000 + Math.random() * 120000, "Credit");
						compteBancaireService.debit(compteId, 1000 + Math.random() * 9000, "Debit");

					} catch (CompteBancaireNotFoundException | SoldeNotSufficientException e) {
						e.printStackTrace();

					}
				}
			}
		};


	}
	/*CommandLineRunner start(ClientRepository clientRepository, CompteBancaireRepository compteBancaireRepository,
			OperationCompteRepository operationCompteRepository) {
		return args -> {
			Stream.of("heni", "sana", "josh").forEach(name -> {
				Client client = new Client();
				CompteBancaire compteBancaire = new CompteBancaire();

				client.setPrenom(name);

				client.setNom(name);
				client.setEmail(name + "@gmail.com");
				client.setMotdepasse("123456");
				//compteBancaire.setClient(client);
				client.getListcompteBancaires();
				clientRepository.save(client);

				clientRepository.save(client);
			});
			clientRepository.findAll().forEach(client -> {
				CompteCourant compteCourant = new CompteCourant();
				compteCourant.setRib(UUID.randomUUID().toString());
				compteCourant.setSolde(Math.random() * 50000);
				compteCourant.setCreatedAt(new Date());
				compteCourant.setEtatCompte(EtatCompte.CREE);
				//compteCourant.setClient(client);
				compteCourant.setDecouvertAutorise(5000);
				compteBancaireRepository.save(compteCourant);

				CompteEpargne compteEpargne = new CompteEpargne();
				compteEpargne.setRib(UUID.randomUUID().toString());
				compteEpargne.setSolde(Math.random() * 50000);
				compteEpargne.setCreatedAt(new Date());
				compteEpargne.setEtatCompte(EtatCompte.CREE);
				//compteEpargne.setClient(client);
				compteEpargne.setTauxInteret(4.5);
				compteBancaireRepository.save(compteEpargne);

			});
			compteBancaireRepository.findAll().forEach(compte -> {
				for (int i = 0; i < 10; i++) {
					OperationCompte operationCompte = new OperationCompte();
					operationCompte.setDateOperation(new Date());
					operationCompte.setMontant(Math.random() * 10000);
					operationCompte.setType(Math.random() > 0.5 ? TypeOperation.DEBIT : TypeOperation.CREDIT);
					operationCompte.setCompteBancaire(compte);
					operationCompteRepository.save(operationCompte);
				}

			});

		};

	}*/
}
