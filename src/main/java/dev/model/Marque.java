package dev.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Marque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "marque")
	private List<Produit> produits;

	public Marque() {};


	public Marque(String nom, List<Produit> produits) {
		this.id = id;
		this.nom = nom;
		this.produits = produits;
	}

	public Marque(String nom) {
	}

	public Long getId() { return id; }

	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }

	public List<Produit> getProduits() { return produits; }
	public void setProduits(List<Produit> produits) { this.produits = produits; }
}
