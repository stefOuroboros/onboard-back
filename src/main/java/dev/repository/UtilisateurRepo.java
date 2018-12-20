package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.domain.Utilisateur;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long>,JpaSpecificationExecutor<Utilisateur> {

    Optional<Utilisateur> findByEmail(String email);

	Utilisateur findByNom(String nom);
}
