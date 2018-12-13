package dev.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reference;
	private String nom;
	private double prix;
	private String[] photos;
	private Integer quantite;
	@OneToOne
	private Caracteristiques caracteristiques;
	@Enumerated(EnumType.STRING)
	private Marque marque;
	@Enumerated(EnumType.STRING)
	private Discipline discipline;
	private int[] flex;
	private String description;
	private Boolean actif;
	@ManyToMany(mappedBy="articles")
	private List<Commande> achats;
	
	public Produit() {}

	
	public Produit(String reference, String nom, double prix,
			String[] photos, int quantite, Caracteristiques caracteristiques,
			Marque marque, Discipline discipline, int[] flex) {
	    this.reference = reference;
	    this.nom = nom;
	    this.prix = prix;
	    this.photos = photos;
	    this.quantite = quantite;
	    this.caracteristiques = caracteristiques;
	    this.marque = marque;
	    this.discipline = discipline;
	    this.flex = flex;
	    }
	
	
    public Caracteristiques getCaracteristiques() {
		return caracteristiques;
	}


	public void setCaracteristiques(Caracteristiques caracteristiques) {
		this.caracteristiques = caracteristiques;
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


	public int[] getFlex() {
		return flex;
	}


	public void setFlex(int[] flex) {
		this.flex = flex;
	}


	public Boolean getActif() {
		return actif;
	}


	/**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return String return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return Float return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return String[] return the photos
     */
    public String[] getPhotos() {
        return photos;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    /**
     * @return Integer return the quantite
     */
    public Integer getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    /**
     * @return Caracteristiques return the carateristiques
     */
    public Caracteristiques getCarateristiques() {
        return caracteristiques;
    }

    /**
     * @param carateristiques the carateristiques to set
     */
    public void setCarateristiques(Caracteristiques carateristiques) {
        this.caracteristiques = carateristiques;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Boolean return the actif
     */
    public Boolean isActif() {
        return actif;
    }

    /**
     * @param actif the actif to set
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    /**
     * @return Commande[] return the achats
     */
    public List<Commande> getAchats() {
        return achats;
    }

    /**
     * @param achats the achats to set
     */
    public void setAchats(List<Commande> achats) {
        this.achats = achats;
    }

}
