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

	@Override
	public int updateClient(Client cl) {
		String req="UPDATE Client AS cl SET cl.nomClient=:pNom, cl.adresse=:pAdresse, cl.email=:pEmail, cl.tel=:pTel, cl.mdpClient=:pMdp WHERE cl.idClient=:pId";
		
		Query query=em.createQuery(req);
		
		query.setParameter("pNom", cl.getNomClient());
		query.setParameter("pAdresse", cl.getAdresse());
		query.setParameter("pEmail", cl.getEmail());
		query.setParameter("pTel", cl.getTel());
		query.setParameter("pMdp", cl.getMdpClient());
		query.setParameter("pId", cl.getIdClient());
		
		return query.executeUpdate();
	}

}
