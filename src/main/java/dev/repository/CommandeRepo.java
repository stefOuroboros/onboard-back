package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.model.Commande;

public interface CommandeRepo extends JpaRepository<Commande, Long>{

}
