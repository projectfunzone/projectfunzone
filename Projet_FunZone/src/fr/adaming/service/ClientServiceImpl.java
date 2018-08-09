package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

/**
 * Class qui implemente l'interface IClientService pour récupérer les méthodes
 * du CRUD et développer les méthodes méthiers
 * 
 * @author Camille
 *
 */
@Stateful
public class ClientServiceImpl implements IClientService {

	/**
	 * Injection dépendance de Dao
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Client> getAllClient() {

		return clDao.getAllClient();
	}

	/**
	 * Permet de récupérer un client par son id.
	 */
	@Override
	public Client getClientById(Client cl) {

		return clDao.getClientById(cl);
	}

	@Override
	public List<Client> getClientByIdNom(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
