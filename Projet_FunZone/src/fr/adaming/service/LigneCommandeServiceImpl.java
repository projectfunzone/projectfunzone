package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

/**
 *  Implementation de l'interface LigneCommandeService pour redéfinir les méthodes de LigneCommande
 *  @author Thibault
 */
@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	/**
	 * transformation de l'association UML en java et injection dépendance (EJB)
	 */
	@EJB
	private ILigneCommandeDao lcDao;

	@Override
	public List<LigneCommande> getAllLigneCommande() {

		return lcDao.getAllLigneCommandes();
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc, Commande cmd) {
		lc.setCommande(cmd);
		return lcDao.addLigneCommande(lc);
	}

	@Override
	public int updateLigneCommande(LigneCommande lc) {

		return lcDao.updateLigneCommande(lc);
	}

	@Override
	public int deleteLigneCommande(LigneCommande lc) {
		
		if (lc != null) {
			return lcDao.deleteLigneCommande(lc);
		} else {
			return 0;
		}
	}

	@Override
	public LigneCommande getLigneCommandebyId(LigneCommande lc) {

		return lcDao.getLigneCommandebyId(lc);
	}

	



	@Override
	public List<LigneCommande> getLigneCommandeByIdCommande(LigneCommande lc, Commande cmd) {
		
		lc.setCommande(cmd);

		return lcDao.getLigneCommandeByIdCommande(lc);
	}
	
	
	
	
}
