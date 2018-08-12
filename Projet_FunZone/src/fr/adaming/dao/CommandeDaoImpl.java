package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;

/**
 * Class qui implemente ICommandeDAO et qui permet de redéfinir les méthodes du CRUD de commande
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

	@Override
	public Commande getCommandeById(Commande cmd) {
		
		return em.find(Commande.class, cmd.getIdCommande());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List <Commande> getCommandeByIdOrClient(Commande cmd) {
		String req = "SELECT cmd FROM Commande AS cmd WHERE cmd.idCommande=:pIdCmd OR cmd.cl.idClient=:pIdCl";
				
		Query query=em.createQuery(req);
		
		query.setParameter("pIdCmd", cmd.getIdCommande());
		query.setParameter("pIdCl", cmd.getCl().getIdClient());
		
		return query.getResultList();
	}
	
	
	@Override
	public Commande addCommande(Commande cmd) {
		em.persist(cmd);
		return cmd;
	}


	
	
	

}
