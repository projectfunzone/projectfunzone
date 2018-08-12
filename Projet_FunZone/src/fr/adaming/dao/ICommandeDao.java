package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;

/**
 * Interface de la couche DAO qui permet de définir le Crud de Commande
 * @author Camille
 *
 */
@Local
public interface ICommandeDao {
	
	/**
	 * Methode qui permet de charger toute la liste des commandes enregistrée dans la base de donnée
	 * Retourne une liste de commande
	 * @return
	 */
	public List<Commande> getAllCommande();
	
	/**
	 * Méthode qui permet de rechercher une commande particulière par son id.
	 * Retourne la commande recherché
	 * @param cmd
	 * @return
	 */
	public Commande getCommandeById(Commande cmd);
	
	/**
	 * Méthode qui permet de rechercher un client par son id ou par l'id du client associé
	 * @param cmd
	 * @return
	 */
	public List<Commande> getCommandeByIdOrClient(Commande cmd);
	
	
	/**
	 * Méthode qui permet de créer une commande
	 * @param cmd
	 * @return
	 */
	public Commande addCommande(Commande cmd);
	
	/**
	 * Méthode qui permet de supprimer une commande
	 * @param cmd
	 * @return
	 */
	public int deleteCommande (Commande cmd);
	
	
}
