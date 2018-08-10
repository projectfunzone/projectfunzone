package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

/**
 * Class qui implement ICommandeService et qui permet de redéfinir les méthodes métier de commande
 * @author Adaming
 *
 */
@Stateful
public class CommandeServiceImpl implements ICommandeService {
	
	@EJB
	private ICommandeDao cmdDao;

	@Override
	public List<Commande> getAllCommande() {
		
		return cmdDao.getAllCommande();
	}

}
