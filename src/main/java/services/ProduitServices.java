package services;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import dev.model.Produit;

@Service
@Transactional
public interface ProduitServices {

	static Specification<Produit> hasMarque(String marque) {
	    return (produit, cq, cb) -> cb.equal(produit.get("marque"), marque);
	}
	 
	static Specification<Produit> nomContains(String nom) {
	    return (produit, cq, cb) -> cb.like(produit.get("nom"), "%" + nom + "%");
	}
	
}
