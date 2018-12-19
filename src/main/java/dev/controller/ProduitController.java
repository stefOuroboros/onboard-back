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
import org.springframework.web.bind.annotation.PutMapping;
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
			@RequestParam String reference,
			@RequestParam double prixMin, @RequestParam double prixMax) {
		return produitService.findByNameCatPriceOrd(nom, reference, marque, discipline, prixMax, prixMin, "asc", 1, Integer.MAX_VALUE).stream().count();
	}
	
	@GetMapping("/{nom}")
	public ProduitVM findByReference(@PathVariable String ref) {
		return new ProduitVM(produitRepo.findByReference(ref));
	}
	
	
	@Secured("ROLE_ADMINISTRATEUR")
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

		
		/*if (produitRepo.findByNom(pro.getNom()).length>0) {
			throw new FunctionalException("Un produit existe déjà avec ce nom:"+pro.getNom());
		}
		*/
		produitRepo.save(pro);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
/*	@GetMapping("/{nom}")
	public Produit modifyUnProduit(@PathVariable String nom) {
		Produit[] produit = produitRepo.findByNom(nom);

		return
	}*/
	
	@Secured("ROLE_ADMINISTRATEUR")
	@DeleteMapping("/{reference}") 
	 public void deleteUnProduit (@PathVariable String reference) {
		produitRepo.delete(this.produitRepo.findByReference(reference));
	}
	
	
	@PutMapping("/modify/{reference}")
	public <ModifProduitForm> ResponseEntity<String> modifierUnProduit(@RequestBody ModifProduitForm produitForm, @PathVariable String reference) throws FunctionalException {
		return ;
		
	}
//	@GetMapping("/{marque}")
//	public List<Produit> findByMarque(@PathVariable String marque) {
//		List<Produit> produits = produitRepo.findAll(ProduitServices.hasMarque(marque));
//		return produits;
//	}
}
