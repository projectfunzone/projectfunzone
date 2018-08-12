package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

/**
 * Interface de la couche Service qui permet de d�finir les m�thodes m�tiers de Commande
 * @author Adaming
 *
 */
@Local
public interface ICommandeService {
	
	/**
	 * M�thode qui permet de charger la liste des commandes enregistr�e dans la base de donn�e
	 * @return
	 */
	public List<Commande> getAllCommande();
	
	/**
	 * M�thode qui permet de rechercher une commande par son id
	 * @param cmd
	 * @return
	 */
	public Commande getCommandeById(Commande cmd);
	
	
	/**
	 * M�thode qui permet de rechercher un client par son id ou par l'id du client associ�
	 * @param cmd
	 * @return
	 */
	public List<Commande> getCommandeByIdOrClient(Commande cmd,Client cl);
	
	
	/**
	 * M�thode qui permet de cr�er une commande
	 * @param cmd
	 * @return
	 */
	public Commande addCommande(Commande cmd,Client cl);
	

}
