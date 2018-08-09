package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;

/**
 * Class qui implement l'interface IAdminDao pour d�finir les m�thodes d'Admin
 * en lien avec la base de donn�e
 * 
 * @author Camille
 *
 */
@Stateless
public class AdminDaoImpl implements IAdminDao {

	@PersistenceContext(unitName = "PFZ")
	private EntityManager em;

	/**
	 * M�thode qui permet de v�rifier l'existence d'un admin dans la base de
	 * donn�e ainsi que son mot de passe pour permettre la connexion
	 * 
	 * @return
	 */
	@Override
	public Admin connectionAdmin(Admin admin) {

		try {
			String req = "SELECT ad FROM Admin ad WHERE ad.idAdmin=:pId AND ad.mdpAdmin=:pMdp";

			Query query = em.createQuery(req);

			query.setParameter("pId", admin.getIdAdmin());
			query.setParameter("pMdp", admin.getMdpAdmin());

			return (Admin) query.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
