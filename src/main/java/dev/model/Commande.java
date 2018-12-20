package dev.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.domain.Utilisateur;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Integer numero;
    
    @ManyToOne
    private Utilisateur client;
    
    @OneToMany(mappedBy="commande")
    private List<Achat> achats = new ArrayList<Achat>();
    
    private LocalDate achatDate;
    
    public Commande() {}
    
    public Commande (Integer num, Utilisateur client) {
    	this.numero = num;
    	this.client = client;
    	this.achatDate = LocalDate.now();
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Long return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return Utilisateur return the client
     */
    public Utilisateur getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Utilisateur client) {
        this.client = client;
    }

    /**
     * @return Date return the achatDate
     */
    public LocalDate getAchatDate() {
        return achatDate;
    }

    /**
     * @param achatDate the achatDate to set
     */
    public void setAchatDate(LocalDate achatDate) {
        this.achatDate = achatDate;
    }

	/**
	 * @return the achats
	 */
	public List<Achat> getAchats() {
		return achats;
	}

	/**
	 * @param achats the achats to set
	 */
	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

}
