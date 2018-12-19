package dev.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import dev.domain.Utilisateur;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long numero;
    @OneToOne
    private Utilisateur client;
    @ManyToMany
    /*@JoinTable(
		name="CommandeProduit",
		joinColumns={@JoinColumn(name="fk_commande", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="fk_produit", referencedColumnName="id")})
    @MapKey(name = "quantite")
    private Map<Produit,Integer> articles = new HashMap<Produit,Integer>();*/
    private List<Produit> articles;
    private Date achatDate;

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
     * @return Long return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Long numero) {
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
     * @return Produit[] return the articles
     */
    public List<Produit> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Produit> articles) {
        this.articles = articles;
    }

    /**
     * @return Date return the achatDate
     */
    public Date getAchatDate() {
        return achatDate;
    }

    /**
     * @param achatDate the achatDate to set
     */
    public void setAchatDate(Date achatDate) {
        this.achatDate = achatDate;
    }

}
