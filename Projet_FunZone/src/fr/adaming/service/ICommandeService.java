package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

/**
 * Interface de la couche Service qui permet de définir les méthodes métiers de Commande
 * @author Adaming
 *
 */
@Local
public interface ICommandeService {
	
	/**
	 * Méthode qui permet de charger la liste des commandes enregistrée dans la base de donnée
	 * @return
	 */
	public List<Commande> getAllCommande();
	
	/**
	 * Méthode qui permet de rechercher une commande par son id
	 * @param cmd
	 * @return
	 */
	public Commande getCommandeById(Commande cmd);
	
	
	/**
	 * Méthode qui permet de rechercher un client par son id ou par l'id du client associé
	 * @param cmd
	 * @return
	 */
	public List<Commande> getCommandeByIdOrClient(Commande cmd,Client cl);
	
	
	/**
	 * Méthode qui permet de créer une commande
	 * @param cmd
	 * @return
	 */
	public Commande addCommande(Commande cmd,Client cl);
	

}
