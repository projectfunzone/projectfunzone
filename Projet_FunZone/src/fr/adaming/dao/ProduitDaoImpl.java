package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Produit;

/**
 * Implementation de l'interface ProduitDao pour red�finir les m�thodes de Produit
 * @author Thibault
 */
@Stateless
public class ProduitDaoImpl implements IProduitDao {

	/**
	 * Instanciation du Entity Manager
	 */
	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	/**
	 * @see fr.adaming.dao.ICategorieDao#getAllCategories()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getAllProduits() {

		String req = "SELECT pr FROM Produit as pr";
		
		Query query = em.createQuery(req);

		List<Produit> listeQuery = query.getResultList();
		
		//pour la lire la photo en la transformant le byte en String
		for (Produit pr : listeQuery) {
			pr.setImage("data:image/png;base64,"+Base64.encodeBase64String(pr.getPhoto()));
		}
		
		return listeQuery;
	}

	/**
	 * @see
	 * fr.adaming.dao.ICategorieDao#addCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public Produit addProduit(Produit pr) {

		em.persist(pr);

		return pr;
	}

	/**
	 * @see
	 * fr.adaming.dao.ICategorieDao#updateCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public int updateProduit(Produit pr) {

		String req = "UPDATE Produit as pr SET pr.venteFlash =:pFlash, pr.designation =:pDesign, pr.description =:pDescription, pr.prix=:pPrix, pr.quantite=:pQuantite, pr.photo=:pPhotoP WHERE pr.idProduit =:pIdP AND pr.categorie.idCategorie=:pIdC ";

		Query query = em.createQuery(req);

		query.setParameter("pFlash", pr.isVenteFlash());
		query.setParameter("pDesign", pr.getDesignation());
		query.setParameter("pDescription", pr.getDescription());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQuantite", pr.getQuantite());
		
		query.setParameter("pPhotoP", pr.getPhoto());
		query.setParameter("pIdP", pr.getIdProduit());
		query.setParameter("pIdC", pr.getCategorie().getIdCategorie());

		return query.executeUpdate();
	}

	/**
	 * @see
	 * fr.adaming.dao.ICategorieDao#deleteCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public int deleteProduit(Produit pr) {

		 String req = "DELETE FROM Produit pr WHERE pr.idProduit=:pIdP";
		
		 // recuperer la query
		 Query query = em.createQuery(req);
		
		 // assignation des params
		 query.setParameter("pIdP", pr.getIdProduit());

		return query.executeUpdate();
	}

	/**
	 * @see fr.adaming.dao.ICategorieDao#getCategoriebyId(fr.adaming.model.Categorie)
	 */
	@Override
	public Produit getProduitbyId(Produit pr) {

		String req = "SELECT pr FROM Produit as pr WHERE pr.idProduit=:pIdP";
		
		Query query = em.createQuery(req);
		
		query.setParameter("pIdP", pr.getIdProduit());
		
		return (Produit) query.getSingleResult();
	}

}