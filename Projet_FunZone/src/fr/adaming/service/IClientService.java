package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

/**
 * Interface de la couche Service qui permet d'utiliser le CRUD et d�finit les
 * m�thodes m�tiers
 * 
 * @author Camille
 *
 */
@Local
public interface IClientService {

	/**
	 * Permet de g�n�rer la liste de tous les clients de l'application. Retourne
	 * une list de Client
	 */
	public List<Client> getAllClient();

	/**
	 * Permet de rechercher un client par son id. G�n�re un objet Client
	 */
	public Client getClientById(Client cl);

	/**
	 * Permet de rechercher un client par son nom. Genere une liste de Client
	 * 
	 * @return
	 */
	public List<Client> getClientByIdNom(Client cl);

	/**
	 * Permet d'ajouter un client. On compare l'id du client en entr�e
	 * (normalement =0) avec l'id du client en retour (normalement =! de z�ro
	 * apr�s la cr�ation) pour v�rifier sa cr�ation
	 */
	public Client addClient(Client cl);
	
	/**
	 * Permet de supprimer un client. On compare le client en sortie
	 * s'il est =! null, c'est que la suppression n'a pas fonctionn�, donc retourne 0
	 * s'il est = null, c'est que la suppression a fonctionn� donc retourne 1
	 */
	public int deleteClient(Client cl);

	
	/**
	 * Permet de modifier un compte client dans la base de donn�e
	 * retourne 1 si modifi�, retourne 0 si non modifi�
	 * @return
	 */
	public int updateClient(Client cl);
	
	/**
	 * Permet de modifier le mdp d'un client  dans la base de donn�e
	 * retourne 1 si modifi�, retourne 0 si non modifi�
	 * @return
	 */
	public int updateClientMdp(Client cl);
	
	public int connectionClient(Client cl);
}
