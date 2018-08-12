package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;

@Local
public interface IPanierService {

	/**
	 * M�thode qui permet de cr�er une commande � partir du panier. Cette
	 * m�thode cr�e des lignes de commande dans la base de donn�e � partie des
	 * lignes de commande du panier, et cr�e une commande pour relier le tout
	 * 
	 * @return
	 */
	public int cr�erCommande(List<LigneCommande> listePanierCommande, Client cl);

}
