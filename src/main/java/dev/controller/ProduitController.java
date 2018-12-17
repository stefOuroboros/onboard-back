package dev.controller;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.model.Produit;
import dev.repository.ProduitRepo;
import services.ProduitServices;

@CrossOrigin
@RestController()
@RequestMapping("/produits")
public class ProduitController {
	
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
				.map(Produit::new)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/count")
	public long getResultNumberByCriteria(@RequestParam String name, @RequestParam String category,
			@RequestParam double priceMin, @RequestParam double priceMax) {
		return productService.findByNameCatPriceOrd(name, category, priceMin, priceMax, "asc", 1, Integer.MAX_VALUE).stream().count();
	}
}
