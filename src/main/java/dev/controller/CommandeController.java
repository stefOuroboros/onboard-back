package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.model.Commande;
import dev.repository.CommandeRepo;

@RestController
public class CommandeController {

	private CommandeRepo commandeRepo;
	
	public CommandeController(CommandeRepo commandeRepo) {
		this.commandeRepo = commandeRepo;
	}
	
	@GetMapping("/commandes")
	public List<Commande> getProduits() {
		return this.commandeRepo.findAll();
	}
}
