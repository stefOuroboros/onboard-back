
package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.model.Produit;

public interface ProduitRepo extends JpaRepository<Produit, Long>, JpaSpecificationExecutor<Produit>{

	Produit findByNom(String nom);

	Produit findByReference(String ref);

	
}

