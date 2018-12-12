package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.model.Produit;
import dev.repository.ProduitRepo;

@RestController
public class ProduitController {

	private ProduitRepo produitRepo;
	
	public ProduitController(ProduitRepo produitRepo) {
		this.produitRepo = produitRepo;
	}
	
	@GetMapping("/produits")
	public List<Produit> getProduits() {
		return this.produitRepo.findAll();
	}
}
