package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	@Override
	public List<LigneCommande> getAllLigneCommandes() {

		String req = "SELECT lc FROM LigneCommande as lc";

		/*
		 * Créer la query pour envoyer la requete
		 */
		Query query = em.createQuery(req);

		List<LigneCommande> listeQuery = query.getResultList();

		return listeQuery;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		em.persist(lc);

		return lc;
	}

	@Override
	public int updateLigneCommande(LigneCommande lc) {
		String req = "UPDATE LigneCommande as lc SET lc.quantite =:pQte, lc.prix=:pPrix WHERE lc.idLigneCommande =:pIdLC";

		Query query = em.createQuery(req);

		query.setParameter("pQte", lc.getQuantite());
		query.setParameter("pPrix", lc.getPrix());
		query.setParameter("pIdLC", lc.getIdLigneCommande());

		return query.executeUpdate();
	}

	@Override
	public int deleteLigneCommande(LigneCommande lc) {

		String req = "DELETE FROM LigneCommande lc WHERE lc.idLigneCommande=:pIdLC";

		// recuperer la query
		Query query = em.createQuery(req);

		// assignation des params
		query.setParameter("pIdLC", lc.getIdLigneCommande());

		return query.executeUpdate();
	}

	@Override
	public LigneCommande getLigneCommandebyId(LigneCommande lc) {
		String req = "SELECT lc FROM LigneCommande as lc WHERE lc.idLigneCommande=:pIdLC";

		Query query = em.createQuery(req);

		query.setParameter("pIdLC", lc.getIdLigneCommande());

		return (LigneCommande) query.getSingleResult();
	}

}
