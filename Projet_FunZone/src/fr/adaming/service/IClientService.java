package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

/**
 * Interface de la couche Service qui permet d'utiliser le CRUD et d�finit les m�thodes m�tiers 
 * @author Camille
 *
 */
@Local
public interface IClientService {
	
	/**
	 * Permet de g�n�rer la liste de tous les clients de l'application. Retourne une list de Client
	 */
	public List<Client> getAllClient ();
	
	/**
	 * Permet de rechercher un client par son id. G�n�re un objet Client
	 */
	public Client getClientById(Client cl);
	
	/**
	 * Permet de rechercher un client par son nom. Genere une liste de Client
	 * @return
	 */
	public List<Client> getClientByIdNom(Client cl);
	

}
