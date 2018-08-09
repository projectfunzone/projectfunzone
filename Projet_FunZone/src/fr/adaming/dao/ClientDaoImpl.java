package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

/**
 * Classe qui implemente l'interface IClientDao pour définir les méthodes du
 * CRUD
 * 
 * @author Camille
 */
@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient() {

		String req = "SELECT cl FROM Client cl";

		Query query = em.createQuery(req);

		return query.getResultList();
	}

	@Override
	public Client getClientById(Client cl) {

		return em.find(Client.class, cl.getIdClient());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getClientByIdNom(Client cl) {

		// requete JPQL
		String req = "SELECT cl FROM Client cl WHERE cl.idClient=:pId OR cl.nomClient=:pNom";

		// Récupérer le query
		Query query = em.createQuery(req);

		// ajout des paramètres
		query.setParameter("pId", cl.getIdClient());
		query.setParameter("pNom", cl.getNomClient());

		//retourner la liste de résultat
		return query.getResultList();
	}

	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	@Override
	public int deleteClient(Client cl) {
		
		try {
			Client clOut=em.find(Client.class, cl.getIdClient());
			em.remove(clOut);
			return 1;
		} catch (Exception exce) {
			exce.printStackTrace();
		
		}
		return 0;
	}

}
