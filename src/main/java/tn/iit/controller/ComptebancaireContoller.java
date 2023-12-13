package tn.iit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.iit.dto.CompteBancaireDto;
import tn.iit.dto.OperationCompteDto;
import tn.iit.exception.CompteBancaireNotFoundException;
import tn.iit.services.CompteBancaireService;

@RestController
@CrossOrigin("*")

public class ComptebancaireContoller {
	@Autowired
	public CompteBancaireService compteBancaireService;
	public ComptebancaireContoller(CompteBancaireService compteBancaireService) {
		this.compteBancaireService = compteBancaireService;
	}
	@GetMapping("/comptes/{id}")
	public CompteBancaireDto getCompteBancaire(@PathVariable String idCompte) throws CompteBancaireNotFoundException {
	    return compteBancaireService.getCompteBancaire(idCompte);
	}

	@GetMapping("/comptes")
	public List<CompteBancaireDto> listerComptes() {
	    return compteBancaireService.CompteBancaireList();
	}
	

	@GetMapping("/comptes/{id}/operations")
	public List<OperationCompteDto> historyOperations(@PathVariable String rib){
	    return compteBancaireService.comptesHistory(rib);
	}


}
