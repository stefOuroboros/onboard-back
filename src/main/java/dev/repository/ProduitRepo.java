package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.model.Produit;

public interface ProduitRepo extends JpaRepository<Produit, Long>{

}
