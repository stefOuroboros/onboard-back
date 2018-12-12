package dev.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.model.Marques;
import dev.model.Produit;
import dev.repository.ProduitRepo;
import services.ProduitServices;

@RestController
@RequestMapping("/produits")
public class ProduitController {

	private ProduitServices produitService;
	private ProduitRepo produitRepo;
	
	public ProduitController(ProduitRepo produitRepo) {
		this.produitRepo = produitRepo;
	}
	
	@GetMapping
	public List<Produit> getProduits() {
		return this.produitRepo.findAll();
	}
	
	@GetMapping("/{nom}")
	public List<Produit> findOne(@PathVariable String nom, Marques marques) {
		List<Produit> produits = produitService.findProduitsByMarquesAndNom(nom, marques);
		return produits;
	}
}
