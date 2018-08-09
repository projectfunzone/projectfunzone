package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

/**
 * Class qui implemente l'interface IClientService pour r�cup�rer les m�thodes
 * du CRUD et d�velopper les m�thodes m�thiers
 * 
 * @author Camille
 *
 */
@Stateful
public class ClientServiceImpl implements IClientService {

	/**
	 * Injection d�pendance de Dao
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Client> getAllClient() {

		return clDao.getAllClient();
	}

	/**
	 * Permet de r�cup�rer un client par son id.
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
