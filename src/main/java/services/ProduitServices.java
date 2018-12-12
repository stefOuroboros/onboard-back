package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.model.Marques;
import dev.model.Produit;

@Service
@Transactional
public class ProduitServices {

	@Autowired
	EntityManager em;
	 
    // constructor
 
    public List<Produit> findProduitsByMarquesAndNom(String nom, Marques marques) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Produit> cq = cb.createQuery(Produit.class);
 
        Root<Produit> produit = cq.from(Produit.class);
        Predicate marquesPredicate = cb.equal(produit.get("marques"), marques);
        Predicate nomPredicate = cb.like(produit.get("nom"), "%" + nom + "%");
        cq.where(marquesPredicate, nomPredicate);
 
        TypedQuery<Produit> query = em.createQuery(cq);
        return query.getResultList();
    }
	
}
