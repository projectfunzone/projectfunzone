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
 * @author Camille
 */
@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient() {

		String req="SELECT cl FROM Client cl";
		
		Query query=em.createQuery(req);
		
		return query.getResultList();
	}

	@Override
	public Client getClientById(Client cl) {
		
		return em.find(Client.class, cl.getIdClient());
	}

	@Override
	public List<Client> getClientByIdNom(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
