package fr.adaming.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
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

	@Override
	public Commande getCommandeById(Commande cmd) {
		
		return cmdDao.getCommandeById(cmd);
	}

	@Override
	public List<Commande> getCommandeByIdOrClient(Commande cmd,Client cl) {
		cmd.setCl(cl);
		return cmdDao.getCommandeByIdOrClient(cmd);
	}

	@Override
	public Commande addCommande(Commande cmd,Client cl) {
		Date dateCrea=new Date();
		cmd.setDateCommande(dateCrea);
		cmd.setCl(cl);
		return cmdDao.addCommande(cmd);
	}

}
