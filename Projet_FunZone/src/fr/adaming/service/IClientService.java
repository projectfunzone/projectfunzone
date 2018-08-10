package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

/**
 * Interface de la couche Service qui permet d'utiliser le CRUD et définit les
 * méthodes métiers
 * 
 * @author Camille
 *
 */
@Local
public interface IClientService {

	/**
	 * Permet de générer la liste de tous les clients de l'application. Retourne
	 * une list de Client
	 */
	public List<Client> getAllClient();

	/**
	 * Permet de rechercher un client par son id. Génère un objet Client
	 */
	public Client getClientById(Client cl);

	/**
	 * Permet de rechercher un client par son nom. Genere une liste de Client
	 * 
	 * @return
	 */
	public List<Client> getClientByIdNom(Client cl);

	/**
	 * Permet d'ajouter un client. On compare l'id du client en entrée
	 * (normalement =0) avec l'id du client en retour (normalement =! de zéro
	 * après la création) pour vérifier sa création
	 */
	public Client addClient(Client cl);
	
	/**
	 * Permet de supprimer un client. On compare le client en sortie
	 * s'il est =! null, c'est que la suppression n'a pas fonctionné, donc retourne 0
	 * s'il est = null, c'est que la suppression a fonctionné donc retourne 1
	 */
	public int deleteClient(Client cl);

	
	/**
	 * Permet de modifier un compte client dans la base de donnée
	 * retourne 1 si modifié, retourne 0 si non modifié
	 * @return
	 */
	public int updateClient(Client cl);
	
	/**
	 * Permet de modifier le mdp d'un client  dans la base de donnée
	 * retourne 1 si modifié, retourne 0 si non modifié
	 * @return
	 */
	public int updateClientMdp(Client cl);
	
	public int connectionClient(Client cl);
}
