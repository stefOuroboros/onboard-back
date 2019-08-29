package dev.controller.vm;

import dev.model.Discipline;
import dev.model.Marque;
import dev.model.Produit;

public class ProduitVM {

	private String reference;
	private String nom;
	private double prix;
	private String photos;
	private int quantite;

	private int longueur;
	private int largeur;
	private int poids;
	private int largeurRoues;
	private double empatement;
	private Marque marque;
	private Discipline discipline;
	private String description;
	private boolean actif;

    public ProduitVM(Produit prod) {
        this.reference = prod.getReference();
        this.nom = prod.getNom();
        this.prix = prod.getPrix();
        this.photos = prod.getPhotos();
        this.quantite = prod.getQuantite();
        this.longueur = prod.getLongueur();
        this.largeur = prod.getLargeur();
        this.poids = prod.getPoids();
        this.largeurRoues = prod.getLargeurRoues();
        this.empatement = prod.getEmpatement();
        this.marque = prod.getMarque();
        this.discipline = prod.getDiscipline();
        this.description = prod.getDescription();
        this.actif = prod.getActif();
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

	public double getEmpatement() {
		return empatement;
	}

	public void setEmpatement(double empatement) {
		this.empatement = empatement;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque_name(Marque marque) {
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
