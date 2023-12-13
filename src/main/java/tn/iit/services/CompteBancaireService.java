package tn.iit.services;

import java.util.List;

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
import tn.iit.exception.ClientNotFoundException;
import tn.iit.exception.CompteBancaireNotFoundException;
import tn.iit.exception.SoldeNotSufficientException;

public interface CompteBancaireService {
	
	public List<ClientDto> listClients();

	
	ClientDto getClient(Integer idClient) throws ClientNotFoundException;

	ClientDto saveClient(ClientDto clientDto);

	CompteCourantDto saveCompteCourant(double solde, double tauxInteret, Integer idClient) throws ClientNotFoundException;

	CompteEpargneDto saveCompteEpargne(double solde, double decouvertAutorise, Integer idClient) throws ClientNotFoundException;

    List<CompteBancaireDto> CompteBancaireList();

	CompteBancaireDto getCompteBancaire(String compteId) throws CompteBancaireNotFoundException;

	void debit(String compteId,double montant , String description) throws CompteBancaireNotFoundException, SoldeNotSufficientException;

	void retrait(String compteId,double montant , String description) throws CompteBancaireNotFoundException;

	void transfert(String compteIdSource, String compteIdDestintation, double montant) throws CompteBancaireNotFoundException, SoldeNotSufficientException;


	void deleteClient(Integer idClient);


	ClientDto updateClient(ClientDto clientDto);


	List<OperationCompteDto> comptesHistory(String rib);


    List<ClientDto> searchClients(String keyword);

}
