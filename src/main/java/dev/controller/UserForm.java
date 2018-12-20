/**
 * 
 */
package dev.controller;

/**
 * @author Alex
 * Création d'un formulaire utilisateur qu'on récupère depuis le front
 *
 */
public class UserForm {

	private String email;
	private String nom;
	private String prenom;
	private String mot_de_passe;
	
	
	public UserForm(String email, String nom, String prenom, String mot_de_passe) {
		super();
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.mot_de_passe = mot_de_passe;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getMot_de_passe() {
		return mot_de_passe;
	}


	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	
	
	
	
}
