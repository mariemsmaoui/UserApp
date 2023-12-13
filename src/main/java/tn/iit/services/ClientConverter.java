package tn.iit.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import tn.iit.dto.ClientDto;
import tn.iit.dto.CompteCourantDto;
import tn.iit.dto.CompteEpargneDto;
import tn.iit.dto.OperationCompteDto;
import tn.iit.entity.Client;
import tn.iit.entity.CompteCourant;
import tn.iit.entity.CompteEpargne;
import tn.iit.entity.OperationCompte;

@Service
public class ClientConverter {

    public static ClientDto fromClient(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setNom(client.getNom());
        clientDto.setPrenom(client.getPrenom());
        clientDto.setEmail(client.getEmail());
        clientDto.setMotdepasse(client.getMotdepasse()); // Correction ici

        return clientDto;
    }

    public static Client fromClientDto(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
    }

    public CompteEpargneDto fromCompteEpargne(CompteEpargne compteEpargne) {
        CompteEpargneDto compteEpargneDto = new CompteEpargneDto();
        BeanUtils.copyProperties(compteEpargne, compteEpargneDto);
        compteEpargneDto.setClientDto(fromClient(compteEpargne.getClient()));
        compteEpargneDto.setType(compteEpargne.getClass().getSimpleName());
        return compteEpargneDto;
    }

    public CompteEpargne fromSavingBankAccountDTO(CompteEpargneDto compteEpargneDto) {
        CompteEpargne compteEpargne = new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDto, compteEpargne);
        compteEpargne.setClient(fromClientDto(compteEpargneDto.getClientDto()));
        return compteEpargne;
    }

    public CompteCourantDto fromCompteCourant(CompteCourant compteCourant) {
        CompteCourantDto compteCourantDto = new CompteCourantDto();
        BeanUtils.copyProperties(compteCourant, compteCourantDto);
        compteCourantDto.setClientDto(fromClient(compteCourant.getClient()));
        compteCourantDto.setType(compteCourant.getClass().getSimpleName());
        return compteCourantDto;
    }

    public CompteCourant fromCompteCourantDTO(CompteCourantDto compteCourantDto) {
        CompteCourant compteCourant = new CompteCourant();
        BeanUtils.copyProperties(compteCourantDto, compteCourant);
        compteCourant.setClient(fromClientDto(compteCourantDto.getClientDto()));
        return compteCourant;
    }

    public OperationCompteDto fromOperationCompte(OperationCompte operationCompte) {
        OperationCompteDto operationCompteDTO = new OperationCompteDto();
        BeanUtils.copyProperties(operationCompte, operationCompteDTO);
        return operationCompteDTO;
    }

    public OperationCompte fromOperationCompteDto(OperationCompteDto operationCompteDto) {
        OperationCompte operationCompte = new OperationCompte(); // Correction ici
        BeanUtils.copyProperties(operationCompteDto, operationCompte);
        return operationCompte;
    }
}
