package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.exception.FunctionalException;
import dev.model.Discipline;
import dev.model.Marque;
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
	public List<Produit> recherche(@RequestParam String nom, @RequestParam Marque marque, @RequestParam Discipline discipline, @RequestParam String reference) {

		// RECHERCHE SI SEULEMENT LE NOM EST PRESENT
		
		if (!nom.isEmpty() && marque == null && discipline == null && reference.isEmpty()){
			List<Produit> produits = produitRepo.findAll(ProduitServices.nomContains(nom));
			return produits;
		} 
		
		// RECHERCHE SI SEULEMENT LA REFERENCE EST PRESENTE
		
		else if (nom.isEmpty() && marque == null && discipline == null && !reference.isEmpty()){
			List<Produit> produits = produitRepo.findAll(ProduitServices.refContains(reference));
			return produits;
		}
		
		// RECHERCHE SI SEULEMENT LA MARQUE EST PRESENTE
		
		else if (nom.isEmpty() && reference.isEmpty() && discipline == null && marque != null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque));
			return produits;
		} 
		
		// RECHERCHE SI SEULEMENT LA DISCIPLINE EST PRESENTE
		
		else if (nom.isEmpty() && reference.isEmpty() && discipline != null && marque == null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline));
			return produits;
		}
		
		// RECHERCHE SI NOM ET MARQUE EST PRESENT
		
		else if (!nom.isEmpty() && reference.isEmpty() && discipline == null && marque != null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque).and(ProduitServices.nomContains(nom)));
			return produits;
		}
		
		// RECHERCHE SI NOM ET DISCIPLINE EST PRESENT
			
		else if (!nom.isEmpty() && reference.isEmpty() && discipline != null && marque == null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline).and(ProduitServices.nomContains(nom)));
			return produits;
		}
		
		// RECHERCHE SI NOM ET REF EST PRESENT
		
		else if (!nom.isEmpty() && !reference.isEmpty() && discipline == null && marque == null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.refContains(reference).and(ProduitServices.nomContains(nom)));
			return produits;
		}
		
		// RECHERCHE SI MARQUE ET DISCIPLINE EST PRESENT
			
		else if (nom.isEmpty() && reference.isEmpty() && discipline != null && marque != null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline).and(ProduitServices.hasMarque(marque)));
			return produits;
		}
		
		// RECHERCHE SI MARQUE ET REF EST PRESENT
		
		else if (nom.isEmpty() && !reference.isEmpty() && discipline == null && marque != null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.refContains(reference).and(ProduitServices.hasMarque(marque)));
			return produits;
		}	
		
		// RECHERCHE SI REF ET DISCIPLINE EST PRESENT
			
		else if (nom.isEmpty() && !reference.isEmpty() && discipline != null && marque == null) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline).and(ProduitServices.refContains(reference)));
			return produits;
		}
		
		// RECHERCHE SI TOUS LES CRITERES SONT PRESENTS :
		
		else if (!nom.isEmpty() && marque != null && discipline != null && !reference.isEmpty()) {
				List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque)
						.and(ProduitServices.byDiscipline(discipline)
						.and(ProduitServices.refContains(reference))));
				return produits;
			}
		
		// SI AUCUN CRITERE :
		
		else {
			List<Produit> produits = produitRepo.findAll();
			return produits;
		}
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
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
//	@GetMapping("/{marque}")
//	public List<Produit> findByMarque(@PathVariable String marque) {
//		List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque));
//		return produits;
//	}
}
