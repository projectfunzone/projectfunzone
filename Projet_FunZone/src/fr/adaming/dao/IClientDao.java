package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;


/**
 * Interface de la couche DAO qui définit les méthodes CRUD d'un client
 * @author Camille
 *
 */
@Local
public interface IClientDao {
	
	/**
	 * Permet de générer la liste de tous les clients de l'application. Retourne une list de Client
	 */
	public List<Client> getAllClient ();
	
	/**
	 * Permet de rechercher un client par son id. Génère un objet Client
	 */
	public Client getClientById(Client cl);
	
	/**
	 * Permet de rechercher un client par son nom. Genere une liste de Client
	 * @return
	 */
	public List<Client> getClientByIdNom(Client cl);
	

	/**
	 * Permet de créer un client dans la base de donnée
	 * @return
	 */
	public Client addClient(Client cl);
	
	
	/**
	 * Permet de supprimer un client dans la base de donnée
	 * retourne 1 si supprimé, retourne 0 si non supprimé
	 * @return
	 */
	public int deleteClient(Client cl);
	
	
	
}
