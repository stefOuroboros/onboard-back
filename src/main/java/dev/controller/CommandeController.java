package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CommandeVM;
import dev.model.Commande;
import dev.repository.CommandeRepo;

@CrossOrigin
@RestController
@RequestMapping("/commandes")
/**
 * WEB API d'authentification.
 *
 * Elle permet de récupérer les informations de commandes.
 */
public class CommandeController extends AbstractController {

	@Autowired
	private CommandeRepo commandeRepo;
	
	@GetMapping
	public List<CommandeVM> getCommandes() {
		List<Commande> commandes = this.commandeRepo.findAll();
		List<CommandeVM> commandesVM = new ArrayList<>();
		
		for (Commande comm: commandes) {
			commandesVM.add(new CommandeVM(comm));
		}
		
		return commandesVM;
	}
}
