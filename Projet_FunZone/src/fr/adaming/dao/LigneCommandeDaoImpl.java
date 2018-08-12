package fr.adaming.dao;

/*
 * Implementation de l'interface LigneCommandeDao pour redéfinir les méthodes de LigneCommande
 * @author Thibault
 */
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ILigneCommandeDao#getAllLigneCommandes()
	 */
	@Override
	public List<LigneCommande> getAllLigneCommandes() {

		String req = "SELECT lc FROM LigneCommande as lc";
		
		Query query = em.createQuery(req);

		List<LigneCommande> listeQuery = query.getResultList();

		return listeQuery;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ILigneCommandeDao#addLigneCommande(fr.adaming.model.LigneCommande)
	 */
	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		em.persist(lc);

		return lc;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ILigneCommandeDao#updateLigneCommande(fr.adaming.model.LigneCommande)
	 */
	@Override
	public int updateLigneCommande(LigneCommande lc) {
		String req = "UPDATE LigneCommande as lc SET lc.quantite =:pQte, lc.prix=:pPrix WHERE lc.idLigneCommande =:pIdLC";

		Query query = em.createQuery(req);

		query.setParameter("pQte", lc.getQuantite());
		query.setParameter("pPrix", lc.getPrix());
		query.setParameter("pIdLC", lc.getIdLigneCommande());

		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ILigneCommandeDao#deleteLigneCommande(fr.adaming.model.LigneCommande)
	 */
	@Override
	public int deleteLigneCommande(LigneCommande lc) {

		String req = "DELETE FROM LigneCommande lc WHERE lc.idLigneCommande=:pIdLC";

		// recuperer la query
		Query query = em.createQuery(req);

		// assignation des params
		query.setParameter("pIdLC", lc.getIdLigneCommande());

		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.dao.ILigneCommandeDao#getLigneCommandebyId(fr.adaming.model.LigneCommande)
	 */
	@Override
	public LigneCommande getLigneCommandebyId(LigneCommande lc) {
		String req = "SELECT lc FROM LigneCommande as lc WHERE lc.idLigneCommande=:pIdLC";

		Query query = em.createQuery(req);

		query.setParameter("pIdLC", lc.getIdLigneCommande());

		return (LigneCommande) query.getSingleResult();
	}

	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<LigneCommande> getLigneCommandeByIdCommande(LigneCommande lc) {
		String req = "SELECT lc FROM LigneCommande AS lc WHERE lc.commande.idCommande=:pIdCmd";
		
		Query query=em.createQuery(req);
		
		query.setParameter("pIdCmd", lc.getCommande().getIdCommande());
		
		return query.getResultList();
	}
}
