package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.RoleUtilisateur;
import dev.domain.Utilisateur;

public interface RolesRepo extends JpaRepository<RoleUtilisateur, Long> {

	RoleUtilisateur findByUtilisateur(Utilisateur utilisateur);
	
}
