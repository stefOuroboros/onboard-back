/**
 * 
 */
package dev.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Role;
import dev.domain.RoleUtilisateur;
import dev.domain.Utilisateur;
import dev.exception.FunctionalException;
import dev.repository.RolesRepo;
import dev.repository.UtilisateurRepo;

/**
 * @author Alex
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * CRUD du utilisateur
	 */
	
	@Autowired
	private UtilisateurRepo utilisateurRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RolesRepo rolesRepo;
	
	/**
	 * Lister tous les users
	 * 
	 * */
	@GetMapping
	public List<Utilisateur> getProduits() {
		return this.utilisateurRepo.findAll();
	}
	
	@GetMapping("/{nom}")
	public Utilisateur findByNom(@PathVariable String nom) {
		
		return utilisateurRepo.findByNom(nom);
	}
	
	@PostMapping("/utilisateur")
	public ResponseEntity<String> ajouterUnUser(@RequestBody UserForm userForm) throws FunctionalException {
		Utilisateur utils = new Utilisateur();
		utils.setNom(userForm.getNom());
		utils.setPrenom(userForm.getPrenom());
		utils.setEmail(userForm.getEmail());
		utils.setMotDePasse(passwordEncoder.encode(userForm.getMot_de_passe()));
		utils.setRoles(Arrays.asList(new RoleUtilisateur(utils, Role.ROLE_UTILISATEUR)));


		
		if (utilisateurRepo.findByNom(utils.getNom())!=null) { 
			 throw new FunctionalException("Un nom existe déjà :"+utils.getNom()); 
		}
		 
		utilisateurRepo.save(utils);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	//@Secured("ROLE_ADMINISTRATEUR")
	@DeleteMapping("/{nom}")
	public void deleteUnUser(@PathVariable String nom) {
		//Suppresion du role avant la suppresion de utilisateur
		rolesRepo.delete(this.rolesRepo.findByUtilisateur(this.utilisateurRepo.findByNom(nom)));
		utilisateurRepo.delete(this.utilisateurRepo.findByNom(nom));
	}
	
	//@Secured("ROLE_ADMINISTRATEUR")
	//@Secured("ROLE_UTILISATEUR")
	@PostMapping("/modifier/{nom}")
	public ResponseEntity<String> modifierUnUser(@PathVariable String nom, @RequestBody Utilisateur util) {
		Utilisateur utilisateur = utilisateurRepo.findByNom(nom);
		if (utilisateur!=null) {
			utilisateur.setNom(util.getNom());
			utilisateur.setPrenom(util.getPrenom());
			utilisateur.setEmail(util.getEmail());
			utilisateur.setMotDePasse(passwordEncoder.encode(util.getMotDePasse()));
			utilisateurRepo.save(util);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
