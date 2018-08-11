package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

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

		return clDao.getClientByIdNom(cl);
	}

	@Override
	public Client addClient(Client cl) {

		Client clOut = clDao.addClient(cl);

		// on compare l'id du client en sortie, car si il est diff�rent de 0,
		// c'est que le client a �t� cr��
		if (clOut.getIdClient() != 0) {
			return clOut;
		}
		return null;
	}

	@Override
	public int deleteClient(Client cl) {

		return clDao.deleteClient(cl);
	}

	@Override
	public int updateClient(Client cl) {

		return clDao.updateClient(cl);
	}

	@Override
	public int updateClientMdp(Client cl) {

		return clDao.updateClientMdp(cl);
	}

	/**
	 * On r�cup�re un client via son email pour ensuite comparer le mdp du
	 * client r�cup�rer (du coup dans clOut) avec le mdp renseign� sur la vue
	 * (dans cl). Retourne un int avec des valeurs diff�rentes selon la
	 * situation. 0 si le client n'existe pas, 1 si le client existe et le mdp
	 * est bon 2 si le mot de passe renseign� ne correspond pas au mdp dans la
	 * base de donn�e
	 */
	@Override
	public int connectionClient(Client cl) {
		// on r�cup�re une liste avec normalement 1 client qui correspond au
		// mail unique
		List<Client> listeRecupMail = clDao.getClientByIdNom(cl);
		// on verifie qu'il n'y a qu'un client avec cet adresse mail, car sinon,
		// cela veut dire qu'il y a 2 comptes clients avec la m�me adresse mail
		if (listeRecupMail.size() == 1) {
			// On r�cup�re le client dans la liste dans un clOut pour ne pas
			// �craser le mdp rentr� dans cl
			Client clOut = listeRecupMail.get(0);

			// On compare les 2 mots de passe, s'ils sont diff�rents, c'est que
			// ce n'est pas le bon mdp rentr�
			if (clOut.getMdpClient().contentEquals(cl.getMdpClient())) {
				return 1;
			}

			return 2;

		}
		return 0;
	}

	/*
	 * @see fr.adaming.service.IClientService#ajoutProdPanier(fr.adaming.model.
	 * Produit, int) 
	 * Le produit choisi par le client ainsi que sa quantit� sont en parametre. 
	 * On cr�e une ligne de commande et on v�rifi� que la quantit� demand�e est disponible 
	 * Si c�est le cas alors on ajoute � la ligne de commande.
	 * On stocke le produit, la quantit� et le prix dans la ligne de commande
	 * En sortie on r�cup�re lc.
	 */
	@Override
	public LigneCommande ajoutProdPanier(Produit pr, int q) {

		LigneCommande lc = new LigneCommande();

		if (q <= pr.getQuantite()) {
			lc.setProduit(pr);
			lc.setQuantite(q);
			lc.setPrix(pr.getPrix() * q);
			return lc;
		} else {
			return null;
		}

	}

}
