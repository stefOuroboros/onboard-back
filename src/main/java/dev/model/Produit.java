package dev.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reference;
	private String nom;
	private double prix;
	private String photos;
	private Integer quantite;
	int longueur;
	int largeur;
	int poids;
	double empatement;
	int largeurRoues;
	@Enumerated(EnumType.STRING)
	private Marque marque;
	@Enumerated(EnumType.STRING)
	private Discipline discipline;
	//private int[] flex;
	private String description;
	private Boolean actif;
	
	public Produit() {}

	public Produit(String reference, String nom, double prix, String photos, Integer quantite, int longueur,
			int largeur, int poids, double empatement, int largeurRoues, Marque marque, Discipline discipline,
			String description, Boolean actif, List<Commande> achats) {
		super();
		this.reference = reference;
		this.nom = nom;
		this.prix = prix;
		this.photos = photos;
		this.quantite = quantite;
		this.longueur = longueur;
		this.largeur = largeur;
		this.poids = poids;
		this.empatement = empatement;
		this.largeurRoues = largeurRoues;
		this.marque = marque;
		this.discipline = discipline;
		this.description = description;
		this.actif = actif;
	}

	public Produit(String reference, String nom, double prix, String photos, Integer quantite, int longueur,
			int largeur, int poids, double empatement, int largeurRoues, Marque marque, Discipline discipline, Boolean actif) {
		super();
		this.reference = reference;
		this.nom = nom;
		this.prix = prix;
		this.photos = photos;
		this.quantite = quantite;
		this.longueur = longueur;
		this.largeur = largeur;
		this.poids = poids;
		this.empatement = empatement;
		this.largeurRoues = largeurRoues;
		this.marque = marque;
		this.discipline = discipline;
		this.actif = actif;
	}
	
	public Long getId() {
		return id;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
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

	public double getEmpatement() {
		return empatement;
	}

	public void setEmpatement(double empatement) {
		this.empatement = empatement;
	}

	public int getLargeurRoues() {
		return largeurRoues;
	}

	public void setLargeurRoues(int largeurRoues) {
		this.largeurRoues = largeurRoues;
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

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}


}
