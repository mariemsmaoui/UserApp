package tn.iit.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.iit.dto.ClientDto;
import tn.iit.dto.CompteBancaireDto;
import tn.iit.dto.CompteCourantDto;
import tn.iit.dto.CompteEpargneDto;
import tn.iit.dto.OperationCompteDto;
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

@Service
@Transactional
public class CompteBancaireServiceImpl implements CompteBancaireService {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteBancaireRepository compteBancaireRepository;
	@Autowired
	private OperationCompteRepository operationCompteRepository;
	@Autowired

	private ClientConverter clientConverterDto;

	@Override
	/**
	 * Récupère la liste des clients depuis le repository, les convertit en objets
	 * ClientDto à l'aide du clientConverterDto, puis retourne la liste résultante.
	 *
	 * @return Une liste de ClientDto représentant les clients récupérés.
	 */
	public List<ClientDto> listClients() {
		// Récupérer la liste des clients depuis le repository
		List<Client> clients = clientRepository.findAll();

		// Initialiser une liste pour stocker les clients convertis en ClientDto
		List<ClientDto> clientsDto = new ArrayList<>();

		// Parcourir la liste des clients et les convertir un par un en ClientDto
		for (Client client : clients) {
			// Utiliser le clientConverterDto pour convertir un client en ClientDto
			ClientDto clientDto = clientConverterDto.fromClient(client);

			// Ajouter le ClientDto à la liste résultante
			clientsDto.add(clientDto);
		}

		// Retourner la liste finale des clients convertis en ClientDto
		return clientsDto;
	}

	@Override
	public ClientDto getClient(Integer idClient) throws ClientNotFoundException {
		Client client = clientRepository.findById(idClient)
				.orElseThrow(() -> new ClientNotFoundException("Customer Not found"));
		return clientConverterDto.fromClient(client);
	}

	@Override
	public ClientDto saveClient(ClientDto clientDto) {
		Client client = clientConverterDto.fromClientDto(clientDto);
		Client savedClients = clientRepository.save(client);
		return clientConverterDto.fromClient(savedClients);

		// return clientRepository.save(client);
	}

	@Override
	public ClientDto updateClient(ClientDto clientDto) {
		Client client = clientConverterDto.fromClientDto(clientDto);
		Client savedClients = clientRepository.save(client);
		return clientConverterDto.fromClient(savedClients);

	}

	@Override
	public CompteCourantDto saveCompteCourant(double solde, double decouvertAutorise, Integer idClient)
			throws ClientNotFoundException {
		Optional<Client> clientOptional = clientRepository.findById(idClient);
		if (!clientOptional.isPresent()) {
			throw new ClientNotFoundException("Le client n'existe pas !");
		}
		Client client = clientOptional.get();

		CompteCourant compteCourant = new CompteCourant();
		compteCourant.setRib(UUID.randomUUID().toString());
		compteCourant.setSolde(solde); // Utilisez le solde passé en paramètre
		compteCourant.setCreatedAt(new Date());
		compteCourant.setEtatCompte(EtatCompte.CREE);
		compteCourant.setClient(clientOptional.get()); // Vous pouvez le décommenter si vous souhaitez définir le
														// client, mais il n'est pas nécessaire ici
		compteCourant.setDecouvertAutorise(decouvertAutorise); // Utilisez le decouvertAutorise passé en paramètre

		CompteCourant savedCompteCourant = compteBancaireRepository.save(compteCourant);
		// return savedCompteCourant;
		return clientConverterDto.fromCompteCourant(savedCompteCourant);

	}

	@Override
	public CompteEpargneDto saveCompteEpargne(double solde, double tauxInteret, Integer idClient)
			throws ClientNotFoundException {
		Optional<Client> client = clientRepository.findById(idClient);
		if (!client.isPresent()) {
			throw new ClientNotFoundException("Le client n'existe pas !");
		}
		Client clients = client.get();

		CompteEpargne compteEpargne = new CompteEpargne();
		compteEpargne.setRib(UUID.randomUUID().toString());
		compteEpargne.setSolde(solde); // Utilisez le solde passé en paramètre
		compteEpargne.setCreatedAt(new Date());
		compteEpargne.setEtatCompte(EtatCompte.CREE);
		compteEpargne.setClient(client.get()); // Vous pouvez le décommenter si vous souhaitez définir le client, mais
												// il n'est pas nécessaire ici
		compteEpargne.setTauxInteret(tauxInteret); // Utilisez le tauxInteret passé en paramètre

		CompteEpargne savedCompteEpargne = compteBancaireRepository.save(compteEpargne);
		// return savedCompteEpargne;
		return clientConverterDto.fromCompteEpargne(savedCompteEpargne);
	}

	@Override
	public CompteBancaireDto getCompteBancaire(String compteId) throws CompteBancaireNotFoundException {

		CompteBancaire compteBancaire = compteBancaireRepository.findById(compteId)
				.orElseThrow(() -> new CompteBancaireNotFoundException("Le compte n existe pas "));

		if (compteBancaire instanceof CompteCourant) {
			CompteCourant savedCompteCourant = (CompteCourant) compteBancaire;
			return clientConverterDto.fromCompteCourant(savedCompteCourant);

		} else {

			CompteEpargne savedCompteEpargne = (CompteEpargne) compteBancaire;
			return clientConverterDto.fromCompteEpargne(savedCompteEpargne);
		}

	}

	@Override
	public void debit(String compteId, double montant, String description)
			throws CompteBancaireNotFoundException, SoldeNotSufficientException {
		CompteBancaire compteBancaire = compteBancaireRepository.findById(compteId)
				.orElseThrow(() -> new CompteBancaireNotFoundException("Le compte n existe pas "));
		if (compteBancaire.getSolde() < montant)
			throw new SoldeNotSufficientException("Balance not sufficient");
		OperationCompte operationCompte = new OperationCompte();
		operationCompte.setType(TypeOperation.DEBIT);
		operationCompte.setMontant(montant);
		operationCompte.setDescription(description);
		operationCompte.setDateOperation(new Date());
		operationCompte.setCompteBancaire(compteBancaire);
		operationCompteRepository.save(operationCompte);
		compteBancaire.setSolde(compteBancaire.getSolde() - montant);
		compteBancaireRepository.save(compteBancaire);
	}

	@Override
	public void retrait(String compteId, double montant, String description) throws CompteBancaireNotFoundException {

		CompteBancaire compteBancaire = compteBancaireRepository.findById(compteId)
				.orElseThrow(() -> new CompteBancaireNotFoundException("Le compte n existe pas "));

		OperationCompte operationCompte = new OperationCompte();
		operationCompte.setType(TypeOperation.CREDIT);
		operationCompte.setMontant(montant);
		operationCompte.setDescription(description);
		operationCompte.setDateOperation(new Date());
		operationCompte.setCompteBancaire(compteBancaire);
		operationCompteRepository.save(operationCompte);
		compteBancaire.setSolde(compteBancaire.getSolde() + montant);
		compteBancaireRepository.save(compteBancaire);

	}

	@Override
	public void transfert(String compteSourceId, String compteDestintationId, double montant)
			throws CompteBancaireNotFoundException, SoldeNotSufficientException {

		debit(compteSourceId, montant, "Transferer POUR le compte destinatire : " + compteDestintationId);

		retrait(compteDestintationId, montant, "Transferer D'APRES  le compte " + compteSourceId);
	}

	@Override
	public List<CompteBancaireDto> CompteBancaireList() {
		List<CompteBancaire> compteBancaires = compteBancaireRepository.findAll();
		List<CompteBancaireDto> bankAccountDTOS = compteBancaires.stream().map(compteBancaire -> {
			if (compteBancaire instanceof CompteCourant) {
				CompteCourant savedCompteCourant = (CompteCourant) compteBancaire;
				return clientConverterDto.fromCompteCourant(savedCompteCourant);

			} else {

				CompteEpargne savedCompteEpargne = (CompteEpargne) compteBancaire;
				return clientConverterDto.fromCompteEpargne(savedCompteEpargne);
			}
		}).collect(Collectors.toList());
		return bankAccountDTOS;
	}

	@Override
	public void deleteClient(Integer idClient) {
		clientRepository.deleteById(idClient);
	}

	@Override
	public List<OperationCompteDto> comptesHistory(String rib) {
	    List<OperationCompte> operationComptes = operationCompteRepository.findByCompteBancaireRib(rib);
	    return operationComptes.stream().map(op -> clientConverterDto.fromOperationCompte(op))
	            .collect(Collectors.toList());
	}

	@Override
	public List<ClientDto> searchClients(String keyword) {
		 List<Client> clients=clientRepository.searchClients(keyword);
	        List<ClientDto> clientDtos = clients.stream().map(clt -> clientConverterDto.fromClient(clt))
	        		.collect(Collectors.toList());
	        return clientDtos;
	}



}
