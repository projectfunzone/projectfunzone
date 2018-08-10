package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;

/**
 * Class qui implemente ICommandeDAO et qui permet de red�finir les m�thodes du CRUD de commande
 * @author Adaming
 *
 */
@Stateless
public class CommandeDaoImpl implements ICommandeDao {
	
	@PersistenceContext(name="PFZ")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getAllCommande() {
		String req="SELECT cmd FROM Commande AS cmd";
		
		Query query=em.createQuery(req);
		
		return query.getResultList();
	}

}
