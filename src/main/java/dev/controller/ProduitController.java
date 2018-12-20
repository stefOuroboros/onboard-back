package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ProduitVM;
import dev.exception.FunctionalException;
import dev.model.Produit;
import dev.repository.ProduitRepo;
import dev.service.ProduitServices;

@CrossOrigin
@RestController
@RequestMapping("/produits")
public class ProduitController extends AbstractController {

	@Autowired
	ProduitRepo produitRepo;
	@Autowired
	ProduitServices produitService;

	@GetMapping
	public List<Produit> getProduits() {
		return this.produitRepo.findAll();
	}
	/**
	 * CRUD du produit pour l'administrateur
	 * 
	 * */
	@GetMapping("/search")
	public List<ProduitVM> findByCriteria(@RequestParam String nom, @RequestParam String marque,
			@RequestParam String discipline, @RequestParam String reference, @RequestParam double prixMin,
			@RequestParam double prixMax, @RequestParam String sort, @RequestParam int pageNbr,
			@RequestParam int nbrByPage) {
		return produitService
				.findByNameCatPriceOrd(nom, reference, marque, discipline, prixMax, prixMin, sort, pageNbr, nbrByPage)
				.stream().map(ProduitVM::new).collect(Collectors.toList());
	}

	@GetMapping("/count")
	public long getResultNumberByCriteria(@RequestParam String nom, @RequestParam String marque, @RequestParam String discipline,
			@RequestParam String reference, @RequestParam double prixMin, @RequestParam double prixMax) {
		return produitService.findByNameCatPriceOrd(nom, reference, marque, discipline, prixMax, prixMin, "asc", 1, Integer.MAX_VALUE).stream().count();
	}
	
	@GetMapping("/{nom}")
	public Produit findByNom(@PathVariable String nom) {
		return produitRepo.findByNom(nom);
	}

	@Secured("ROLE_ADMINISTRATEUR")
	@PostMapping("/new")
	public ResponseEntity<String> ajouterUnProduit(@RequestBody ProduitVM produit) throws FunctionalException {
		Produit pro = new Produit();
		pro.setReference(produit.getReference());
		pro.setNom(produit.getNom());
		pro.setPrix(produit.getPrix());
		pro.setPhotos(produit.getPhotos());
		pro.setQuantite(produit.getQuantite());
		pro.setLongueur(produit.getLongueur());
		pro.setLargeur(produit.getLargeur());
		pro.setPoids(produit.getPoids());
		pro.setLargeurRoues(produit.getLargeurRoues());
		pro.setEmpatement(produit.getEmpatement());
		pro.setMarque(produit.getMarque());
		pro.setDiscipline(produit.getDiscipline());
		pro.setDescription(produit.getDescription());
		pro.setActif(produit.isActif());

		
		 if (produitRepo.findByNom(pro.getNom())!=null) { 
			 throw new FunctionalException("Un produit existe déjà avec ce nom:"+pro.getNom()); 
			 
		 }
		 
		produitRepo.save(pro);
		return new ResponseEntity<>(HttpStatus.OK);

	}



	@Secured("ROLE_ADMINISTRATEUR")
	@DeleteMapping("/{reference}")
	public void deleteUnProduit(@PathVariable String reference) {
		produitRepo.delete(this.produitRepo.findByReference(reference));
	}

	@Secured("ROLE_ADMINISTRATEUR")
	@PostMapping("/modifier/{reference}")
	public ResponseEntity<String> modifierUnProduit(@PathVariable String reference, @RequestBody Produit prod) {
		Produit produit = produitRepo.findByReference(reference);
		if (produit!=null) {
			produit.setNom(prod.getNom());
			produit.setLargeur(prod.getLargeur());
			produit.setMarque(prod.getMarque());
			produit.setReference(prod.getReference());
			produit.setPrix(prod.getPrix());
			produit.setPhotos(prod.getPhotos());
			produit.setQuantite(prod.getQuantite());
			produit.setLongueur(prod.getLongueur());
			produit.setPoids(prod.getPoids());
			produit.setLargeurRoues(prod.getLargeurRoues());
			produit.setEmpatement(prod.getEmpatement());
			produit.setDiscipline(prod.getDiscipline());
			produit.setDescription(prod.getDescription());
			produit.setActif(prod.getActif());
			produitRepo.save(produit);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
