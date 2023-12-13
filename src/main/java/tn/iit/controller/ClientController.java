package tn.iit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;
import tn.iit.entity.CompteBancaire;
import tn.iit.exception.ClientNotFoundException;
import tn.iit.services.CompteBancaireService;

@RestController
@CrossOrigin("*")
public class ClientController {
	@Autowired

	private CompteBancaireService compteBancaireService;

	// le transfert entre les objet et dto se fait a traver la couch serrvice
	/*@GetMapping("/clients")
	public List<ClientDto> clientsList() {
		// List<Client> listClients = compteBancaireService.listClients();
		// model.addAttribute("listClients", listClients);
		return compteBancaireService.listClients();
		// return "users";
	}*/

	@GetMapping("/clients/{id}")
	public ClientDto getClient(@PathVariable(name = "id") Integer idClient) throws ClientNotFoundException {
		return compteBancaireService.getClient(idClient);

	}

	@PostMapping("/clients")
	public ClientDto saveClient(@RequestBody ClientDto clientDto) {

		return compteBancaireService.saveClient(clientDto);
	}

	
	@PutMapping("/clients/{id}")
	public ClientDto updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto) {
	    clientDto.setId(id);
	    return compteBancaireService.updateClient(clientDto);
	}


	@DeleteMapping("/clients/{Id}")
	public void deleteClient(@PathVariable Integer Id) {
		compteBancaireService.deleteClient(Id);
	}
	@GetMapping("/clients")
	public List<ClientDto> clientsList() {
		
		return compteBancaireService.listClients();
	}
	@GetMapping("/clients/search")
    public List<ClientDto> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return compteBancaireService.searchClients("%"+keyword+"%");
    }
	

	

}
