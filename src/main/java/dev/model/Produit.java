package dev.model;

import java.util.List;

import javax.persistence.Entity;
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
	private Float prix;
	private String[] photos;
	private Integer quantite;
	@OneToOne
	private Caracteristiques carateristiques;
	private String description;
	private Boolean actif;
	@ManyToMany(mappedBy="articles")
	private List<Commande> achats;

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
    public Float getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(Float prix) {
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
        return carateristiques;
    }

    /**
     * @param carateristiques the carateristiques to set
     */
    public void setCarateristiques(Caracteristiques carateristiques) {
        this.carateristiques = carateristiques;
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
