package dev.controller;


import java.util.List;
import java.util.stream.Collectors;

import dev.model.Discipline;
import dev.model.Marque;
import dev.repository.MarqueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RestController()

@RequestMapping("/produits")
public class ProduitController extends AbstractController {
	
	@Autowired
	ProduitRepo produitRepo;
	@Autowired
	ProduitServices produitService;
    @Autowired
    MarqueRepo marqueRepo;
	
	@GetMapping
	public List<Produit> getProduits() {
		return this.produitRepo.findAll();
	}
	
	@GetMapping("/search")
	public List<ProduitVM> findByCriteria(@RequestParam String nom, @RequestParam String marque, @RequestParam String discipline,
			@RequestParam String reference, @RequestParam double prixMin, @RequestParam double prixMax, @RequestParam String sort,
			@RequestParam int pageNbr, @RequestParam int nbrByPage) {
		return produitService.findByNameCatPriceOrd(nom, reference, marque, discipline, prixMax, prixMin, sort, pageNbr, nbrByPage).stream()
				.map(ProduitVM::new)
				.collect(Collectors.toList());
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



	@PostMapping(value = {"/new"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> ajouterUnProduit(@RequestBody ProduitForm produitForm) throws FunctionalException {
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
		pro.setMarque(marqueRepo.findByNom(produitForm.getMarque()));
		pro.setDiscipline(produitForm.getDiscipline());
		pro.setDescription(produitForm.getDescription());
		pro.setActif(produitForm.isActif());
		this.produitRepo.save(pro);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Secured("ROLE_ADMINISTRATEUR")
	@DeleteMapping(path="/{reference}") 
	 public void deleteUnProduit (@PathVariable String reference) {
		produitRepo.delete(this.produitRepo.findByReference(reference));
	}

	@Secured("ROLE_ADMINISTRATEUR")
	@PostMapping(value = {"/edit/{reference}"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> editProduct(@PathVariable String reference, @RequestBody ProduitForm produitForm) throws FunctionalException {
        Produit pro = this.produitRepo.findByReference(reference);
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
        pro.setMarque(marqueRepo.findByNom(produitForm.getMarque()));
		pro.setDiscipline(produitForm.getDiscipline());
		pro.setDescription(produitForm.getDescription());
		pro.setActif(produitForm.isActif());

		produitRepo.save(pro);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
		/* Produit pro = new Produit("1", "TARAB", 459.9, "http://www.hw-shapes.de/bilder/produkte/gross/Loaded-Tarab-Longboard-Komplettboard_b2.jpg", 10, 119, 24, 3900, 81.9, 42, Marque loaded, Discipline.DANCING, true);
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
		pro.setActif(produitForm.isActif()); */


				/*Produit pro = new Produit("1", "TARAB", 459.9, "http://www.hw-shapes.de/bilder/produkte/gross/Loaded-Tarab-Longboard-Komplettboard_b2.jpg", 10, 119, 24, 3900, 81.9, 42, "LOADED", Discipline.DANCING, true);
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
		pro.setActif(produitForm.isActif()); */

//		if (produitRepo.findByNom(pro.getNom()).length>0) {
//
//		if (produitRepo.findByNom(pro.getNom()).length>0) {
//			throw new FunctionalException("Un produit existe déjà avec ce nom:"+pro.getNom());
//		}
//