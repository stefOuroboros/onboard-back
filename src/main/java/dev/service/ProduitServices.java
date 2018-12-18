package dev.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import dev.model.Produit;
@Service
@Transactional
public class ProduitServices {

	@PersistenceContext
	private EntityManager em;

	public List<Produit> findByNameCatPriceOrd(String nom, String reference, String marque, String discipline, double prixMax, double prixMin, String sort, int pageNbr, int nbrByPage) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteriaQuery = criteriaBuilder.createQuery(Produit.class);
		Root<Produit> productRoot = criteriaQuery.from(Produit.class);

		if(StringUtils.isEmpty(marque) && StringUtils.isEmpty(discipline)) {
			
			Predicate namePredicate = criteriaBuilder.like(productRoot.get("nom"), "%" + nom + "%");
			Predicate referPrecidate = criteriaBuilder.like(productRoot.get("reference"), "%" + reference + "%");
			Predicate maxPredicate = criteriaBuilder.lessThanOrEqualTo(productRoot.get("prix"), prixMax);
			Predicate minPredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("prix"), prixMin);
			criteriaQuery.where(namePredicate, referPrecidate, minPredicate, maxPredicate);

		} else if (StringUtils.isEmpty(marque)) {
			
			Predicate namePredicate = criteriaBuilder.like(productRoot.get("nom"), "%" + nom + "%");
			Predicate referPrecidate = criteriaBuilder.like(productRoot.get("reference"), "%" + reference + "%");
			Predicate discPrecidate = criteriaBuilder.like(productRoot.get("discipline"), "%" + discipline + "%");
			Predicate maxPredicate = criteriaBuilder.lessThanOrEqualTo(productRoot.get("prix"), prixMax);
			Predicate minPredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("prix"), prixMin);
			criteriaQuery.where(namePredicate, referPrecidate, discPrecidate, minPredicate, maxPredicate);
		} else if (StringUtils.isEmpty(discipline)) {
			
			Predicate namePredicate = criteriaBuilder.like(productRoot.get("nom"), "%" + nom + "%");
			Predicate referPrecidate = criteriaBuilder.like(productRoot.get("reference"), "%" + reference + "%");
			Predicate discPrecidate = criteriaBuilder.like(productRoot.get("marque"), "%" + marque + "%");
			Predicate maxPredicate = criteriaBuilder.lessThanOrEqualTo(productRoot.get("prix"), prixMax);
			Predicate minPredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("prix"), prixMin);
			criteriaQuery.where(namePredicate, referPrecidate, discPrecidate, minPredicate, maxPredicate);
		
		} else {
		
		Predicate namePredicate = criteriaBuilder.like(productRoot.get("nom"), "%" + nom + "%");
		Predicate referPrecidate = criteriaBuilder.like(productRoot.get("reference"), "%" + reference + "%");
		Predicate marquePrecidate = criteriaBuilder.like(productRoot.get("marque"), "%" + marque + "%");
		Predicate discPrecidate = criteriaBuilder.like(productRoot.get("discipline"), "%" + discipline + "%");
		Predicate maxPredicate = criteriaBuilder.lessThanOrEqualTo(productRoot.get("prix"), prixMax);
		Predicate minPredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("prix"), prixMin);
		criteriaQuery.where(namePredicate, referPrecidate, marquePrecidate, discPrecidate, minPredicate, maxPredicate);
		
		}

		if (sort.equals("asc")) {
			criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get("prix")));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get("prix")));
		}

		return em.createQuery(criteriaQuery).setFirstResult((pageNbr - 1) * nbrByPage).setMaxResults(nbrByPage)
				.getResultList();
	}
//	
//	
//	
//
//	public List<Produit> findByTypeLimited(String category, int number) {
//		
//		//List<ProduitVM> listProd = (Math.random() * (listProd.size() - 1));
//		
//		TypedQuery<Produit> products = em.createQuery("SELECT p FROM Produit AS p WHERE p.category = :category", Produit.class);
//		products.setParameter("category", category);
//		return products.getResultList();
//		
//	}
}
