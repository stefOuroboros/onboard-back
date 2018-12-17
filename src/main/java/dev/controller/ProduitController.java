package dev.controller;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ProduitVM;
import dev.model.Produit;
import dev.repository.ProduitRepo;
import services.ProduitServices;

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
	
	@GetMapping
	public List<Produit> findByCriteria(@RequestParam String nom, @RequestParam String marque, @RequestParam String discipline,
			@RequestParam String reference, @RequestParam String category,
			@RequestParam double prixMin, @RequestParam double prixMax, @RequestParam String sort,
			@RequestParam int pageNbr, @RequestParam int nbrByPage) {
		return produitService.findByNameCatPriceOrd(nom, reference, marque, discipline, prixMax, prixMin, sort, pageNbr, nbrByPage).stream()
				.map(ProduitVM::new)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/count")
	public long getResultNumberByCriteria(@RequestParam String name, @RequestParam String category,
			@RequestParam double priceMin, @RequestParam double priceMax) {
		return productService.findByNameCatPriceOrd(name, category, priceMin, priceMax, "asc", 1, Integer.MAX_VALUE).stream().count();
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
