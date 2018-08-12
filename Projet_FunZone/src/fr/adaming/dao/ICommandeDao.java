package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;

/**
 * Interface de la couche DAO qui permet de d�finir le Crud de Commande
 * @author Camille
 *
 */
@Local
public interface ICommandeDao {
	
	/**
	 * Methode qui permet de charger toute la liste des commandes enregistr�e dans la base de donn�e
	 * Retourne une liste de commande
	 * @return
	 */
	public List<Commande> getAllCommande();
	
	/**
	 * M�thode qui permet de rechercher une commande particuli�re par son id.
	 * Retourne la commande recherch�
	 * @param cmd
	 * @return
	 */
	public Commande getCommandeById(Commande cmd);
	
	/**
	 * M�thode qui permet de rechercher un client par son id ou par l'id du client associ�
	 * @param cmd
	 * @return
	 */
	public List<Commande> getCommandeByIdOrClient(Commande cmd);
	
	
	/**
	 * M�thode qui permet de cr�er une commande
	 * @param cmd
	 * @return
	 */
	public Commande addCommande(Commande cmd);
	
	/**
	 * M�thode qui permet de supprimer une commande
	 * @param cmd
	 * @return
	 */
	public int deleteCommande (Commande cmd);
	
	
}
