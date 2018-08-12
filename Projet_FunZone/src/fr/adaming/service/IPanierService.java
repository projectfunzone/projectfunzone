package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;

@Local
public interface IPanierService {

	/**
	 * Méthode qui permet de créer une commande à partir du panier. Cette
	 * méthode crée des lignes de commande dans la base de donnée à partie des
	 * lignes de commande du panier, et crée une commande pour relier le tout
	 * 
	 * @return
	 */
	public int créerCommande(List<LigneCommande> listePanierCommande, Client cl);

}
