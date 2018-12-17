package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.exception.FunctionalException;
import dev.model.Produit;
import dev.repository.ProduitRepo;
import services.ProduitServices;

@CrossOrigin
@RestController
@RequestMapping("/produits")
public class ProduitController extends AbstractController {
	
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
	
	@PostMapping("/new")
	public ResponseEntity<String> ajouterUnProduit(@RequestBody ProduitForm produitForm) throws FunctionalException {
		Produit pro = new Produit();
		pro.setNom(produitForm.getNom());
		pro.setReference(produitForm.getReference());
		pro.setNom(produitForm.getNom());
		pro.setPrix(produitForm.getPrix());
		pro.setPhotos(produitForm.getPhotos());
		pro.setQuantite(produitForm.getQuantite());
		pro.setLongueur(produitForm.getLongueur());
		pro.setLargeur(produitForm.getLargeur());
		pro.setPoids(produitForm.getPoids());
		pro.setLargeurRoues(produitForm.getLargeurRoues());
		pro.setEmpatement(produitForm.getEmpatement());
		pro.setMarque(produitForm.getMarque());
		pro.setDiscipline(produitForm.getDiscipline());
		pro.setDescription(produitForm.getDescription());
		pro.setActif(produitForm.isActif());

		
		if (produitRepo.findAll(ProduitServices.nomContains(pro.getNom())).size()>0) {
			throw new FunctionalException("Un produit existe déjà avec ce nom:"+pro.getNom());
		}
		
		produitRepo.save(pro);
		return ResponseEntity.ok().body("{message:'success'}");
		
	}
//	@GetMapping("/{marque}")
//	public List<Produit> findByMarque(@PathVariable String marque) {
//		List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque));
//		return produits;
//	}
}
