package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;

/**
 * Interface de la couche Service qui permet de définir les méthodes métiers de Commande
 * @author Adaming
 *
 */
@Local
public interface ICommandeService {
	
	public List<Commande> getAllCommande();

}
