package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

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

		List<Categorie> listeQuery = query.getResultList();
		
		/*
		 * pour la lire la photo en la transformant le byte en String
		 */
		for (Categorie cat : listeQuery) {
			cat.setImage("data:image/png);base64,"+Base64.encodeBase64String(cat.getPhoto()));
		}
		
		return listeQuery;
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

		String req = "UPDATE Categorie as cat SET cat.nomCategorie =:pNomCat, cat.description =:pDescription, cat.photo=:pPhotoC WHERE cat.idCategorie =:pIdC";

		Query query = em.createQuery(req);

		query.setParameter("pNomCat", c.getNomCategorie());
		query.setParameter("pDescription", c.getDescription());
		query.setParameter("pPhotoC", c.getPhoto());
		query.setParameter("pIdC", c.getIdCategorie());

		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.adaming.dao.ICategorieDao#deleteCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public int deleteCategorie(Categorie c) {

		 String req = "DELETE FROM Categorie cat WHERE cat.nomCategorie=:pNomCategorie";
		
		 // recuperer la query
		 Query query = em.createQuery(req);
		
		 // assignation des params
		 query.setParameter("pNomCategorie", c.getNomCategorie());

		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ICategorieDao#getCategoriebyId(fr.adaming.model.Categorie)
	 */
	@Override
	public Categorie getCategoriebyNomCategorie(Categorie c) {

		String req = "SELECT cat FROM Categorie as cat WHERE cat.nomCategorie=:pNomCategorie";
		
		Query query = em.createQuery(req);
		
		query.setParameter("pNomCategorie", c.getNomCategorie());
		
		return (Categorie) query.getSingleResult();
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ICategorieDao#getCategoriebyNomCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public Categorie getCategoriebyId(Categorie c) {

		String req = "SELECT cat FROM Categorie as cat WHERE cat.idCategorie=:pIdC";
		
		Query query = em.createQuery(req);
		
		query.setParameter("pIdC", c.getIdCategorie());
		
		return (Categorie) query.getSingleResult();
	}

}
