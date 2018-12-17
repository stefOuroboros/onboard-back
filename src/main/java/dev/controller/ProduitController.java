package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.model.Produit;
import dev.repository.ProduitRepo;
import services.ProduitServices;
import services.StringUtils;

@CrossOrigin
@RestController()
@RequestMapping("/produits")
public class ProduitController {
	
	@Autowired
	ProduitRepo produitRepo;
	
	
	@GetMapping
	public List<Produit> getProduits() {
		return this.produitRepo.findAll();
	}
	
	@GetMapping("/search")
	public List<Produit> recherche(@RequestParam String nom, @RequestParam String marque, @RequestParam String discipline, @RequestParam String reference) {

		// RECHERCHE SI SEULEMENT LE NOM EST PRESENT
		
		if (!StringUtils.isEmpty(nom) && StringUtils.isEmpty(marque) && StringUtils.isEmpty(discipline) && StringUtils.isEmpty(reference)){
			List<Produit> produits = produitRepo.findAll(ProduitServices.nomContains(nom));
			return produits;
		} 
		
		// RECHERCHE SI SEULEMENT LA REFERENCE EST PRESENTE
		
		else if (StringUtils.isEmpty(nom) && StringUtils.isEmpty(marque) && StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(reference)){
			List<Produit> produits = produitRepo.findAll(ProduitServices.refContains(reference));
			return produits;
		}
		
		// RECHERCHE SI SEULEMENT LA MARQUE EST PRESENTE
		
		else if (StringUtils.isEmpty(nom) && StringUtils.isEmpty(reference) && StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque));
			return produits;
		} 
		
		// RECHERCHE SI SEULEMENT LA DISCIPLINE EST PRESENTE
		
		else if (StringUtils.isEmpty(nom) && StringUtils.isEmpty(reference) && !StringUtils.isEmpty(discipline) && StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline));
			return produits;
		}
		
		// RECHERCHE SI NOM ET MARQUE EST PRESENT
		
		else if (!StringUtils.isEmpty(nom) && StringUtils.isEmpty(reference) && StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque).and(ProduitServices.nomContains(nom)));
			return produits;
		}
		
		// RECHERCHE SI NOM ET DISCIPLINE EST PRESENT
			
		else if (!StringUtils.isEmpty(nom) && StringUtils.isEmpty(reference) && !StringUtils.isEmpty(discipline) && StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline).and(ProduitServices.nomContains(nom)));
			return produits;
		}
		
		// RECHERCHE SI NOM ET REF EST PRESENT
		
		else if (!StringUtils.isEmpty(nom) && !StringUtils.isEmpty(reference) && StringUtils.isEmpty(discipline) && StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.refContains(reference).and(ProduitServices.nomContains(nom)));
			return produits;
		}
		
		// RECHERCHE SI MARQUE ET DISCIPLINE EST PRESENT
			
		else if (StringUtils.isEmpty(nom) && StringUtils.isEmpty(reference) && !StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline).and(ProduitServices.hasMarque(marque)));
			return produits;
		}
		
		// RECHERCHE SI MARQUE ET REF EST PRESENT
		
		else if (StringUtils.isEmpty(nom) && !StringUtils.isEmpty(reference) && StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.refContains(reference).and(ProduitServices.hasMarque(marque)));
			return produits;
		}	
		
		// RECHERCHE SI REF ET DISCIPLINE EST PRESENT
			
		else if (StringUtils.isEmpty(nom) && !StringUtils.isEmpty(reference) && !StringUtils.isEmpty(discipline) && StringUtils.isEmpty(marque)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.byDiscipline(discipline).and(ProduitServices.refContains(reference)));
			return produits;
		}
		
		// RECHERCHE SI NOM, REF ET DISCIPLINE SONT PRESENTS :
		
		else if (!StringUtils.isEmpty(nom) && StringUtils.isEmpty(marque) && !StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(reference)) {
				List<Produit> produits = produitRepo.findAll(ProduitServices.nomContains(nom)
						.and(ProduitServices.byDiscipline(discipline)
						.and(ProduitServices.refContains(reference))));
				return produits;
			}
		
		// RECHERCHE SI NOM, REF ET MARQUE SONT PRESENTS :
		
		else if (!StringUtils.isEmpty(nom) && !StringUtils.isEmpty(marque) && StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(reference)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque)
					.and(ProduitServices.nomContains(nom)
					.and(ProduitServices.refContains(reference))));
			return produits;
		}
		
		// RECHERCHE SI REF MARQUE ET DISCIPLINE SONT PRESENT
		
		else if (StringUtils.isEmpty(nom) && !StringUtils.isEmpty(marque) && !StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(reference)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque)
					.and(ProduitServices.byDiscipline(discipline)
					.and(ProduitServices.refContains(reference))));
			return produits;
		}
		
		// RECHERCHE SI TOUS LES CRITERES SONT PRESENTS :
		
		else if (!StringUtils.isEmpty(nom) && !StringUtils.isEmpty(marque) && !StringUtils.isEmpty(discipline) && !StringUtils.isEmpty(reference)) {
			List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque)
					.and(ProduitServices.byDiscipline(discipline)
					.and(ProduitServices.refContains(reference))
					.and(ProduitServices.nomContains(nom))));
			return produits;
		}
		
		// SI AUCUN CRITERE :
		
		else {
			List<Produit> produits = produitRepo.findAll();
			return produits;
		}
	}
}
