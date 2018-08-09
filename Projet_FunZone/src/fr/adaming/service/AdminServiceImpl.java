package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

/**
 * Class qui implement l'interface IAdminService pour red�finir les m�thodes
 * m�tier en lien avec Admin
 * 
 * @author Adaming
 *
 */
@Stateful
public class AdminServiceImpl implements IAdminService {
	
	@EJB
	private IAdminDao adDao;

	@Override
	public Admin connectionAdmin(Admin admin) {
		
		return adDao.connectionAdmin(admin);
	}

}
