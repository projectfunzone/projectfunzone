package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

/*
 * @author Thibault
 * Definition des methodes de Categories
 */
@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	/*
	 * Instanciation du Entity Manager
	 */
	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.adaming.dao.ICategorieDao#getAllCategories()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> getAllCategories() {

		/*
		 * Créer la requete de recuperation de la methode
		 */
		String req = "SELECT cat FROM Categorie as cat";

		/*
		 * Créer la query pour envoyer la requete
		 */
		Query query = em.createQuery(req);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.adaming.dao.ICategorieDao#addCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public Categorie addCategorie(Categorie c) {

		em.persist(c);

		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.adaming.dao.ICategorieDao#updateCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public int updateCategorie(Categorie c) {

		String req = "UPDATE Categorie as cat SET cat.nomCategorie =:pNomCat, cat.description =:pDescription, cat.photo=:pPhotoC cat.WHERE cat.idCategorie =:pIdC";

		Query query = em.createQuery(req);

		query.setParameter("pNomCat", c.getNomCategorie());
		query.setParameter("pDescription", c.getDescription());
		query.setParameter("pPhotoC", c.getPhoto());

		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.adaming.dao.ICategorieDao#deleteCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public int deleteCategorie(Categorie c) {

		 String req = "DELETE FROM Categorie cat WHERE cat.id=:pIdC";
		
		 // recuperer la query
		 Query query = em.createQuery(req);
		
		 // assignation des params
		 query.setParameter("pIdC", c.getIdCategorie());

		return query.executeUpdate();
	}

}
