package dev.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import dev.model.Discipline;
import dev.model.Marque;

@CrossOrigin
public class ProduitForm {
	private String reference;
	private String nom;
	private int prix;
	private String photos;
	private int quantite;
	private int longueur;
	private int largeur;
	private int poids;
	private int largeurRoues;
	private int empatement;
	private Marque marque;
	private Discipline discipline;
	private String description;
	private boolean actif;
	
	
	public ProduitForm(String reference, String nom, int prix, String photos, int quantite, int longueur, int largeur,
			int poids, int largeurRoues, int empatement, Marque marque, Discipline discipline, String description,
			boolean actif) {
		super();
		this.reference = reference;
		this.nom = nom;
		this.prix = prix;
		this.photos = photos;
		this.quantite = quantite;
		this.longueur = longueur;
		this.largeur = largeur;
		this.poids = poids;
		this.largeurRoues = largeurRoues;
		this.empatement = empatement;
		this.marque = marque;
		this.discipline = discipline;
		this.description = description;
		this.actif = actif;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getPrix() {
		return prix;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public int getLongueur() {
		return longueur;
	}


	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}


	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public int getPoids() {
		return poids;
	}


	public void setPoids(int poids) {
		this.poids = poids;
	}


	public int getLargeurRoues() {
		return largeurRoues;
	}


	public void setLargeurRoues(int largeurRoues) {
		this.largeurRoues = largeurRoues;
	}


	public int getEmpatement() {
		return empatement;
	}


	public void setEmpatement(int empatement) {
		this.empatement = empatement;
	}


	public Marque getMarque() {
		return marque;
	}


	public void setMarque(Marque marque) {
		this.marque = marque;
	}


	public Discipline getDiscipline() {
		return discipline;
	}


	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	
	
	
	
}
