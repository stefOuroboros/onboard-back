package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.model.Produit;
import dev.repository.ProduitRepo;
import services.ProduitServices;

@RestController
@RequestMapping("/produits")
public class ProduitController {
	
	@Autowired
	ProduitRepo produitRepo;
	
	
	@GetMapping
	public List<Produit> getProduits() {
		return this.produitRepo.findAll();
	}
	
	@GetMapping("/search")
	public List<Produit> findByNom(@RequestParam String nom,@RequestParam String marque) {
		List<Produit> produits = produitRepo.findAll(ProduitServices.nomContains(nom).or(ProduitServices.hasMarque(marque)));
		return produits;
	}
	
//	@GetMapping("/{marque}")
//	public List<Produit> findByMarque(@PathVariable String marque) {
//		List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque));
//		return produits;
//	}
}
