package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeService {
	
	/*
	 * methode de selection de l'ensemble des categorie disponible sur le site
	 */
	public List<LigneCommande> getAllLigneCommande();
	
	/*
	 * methode d'ajout d'une categorie
	 */
	public LigneCommande addLigneCommande(LigneCommande lc);
	
	/*
	 * methode de modification d'une categorie
	 */
	public int updateLigneCommande(LigneCommande lc);
	
	/*
	 * methode de supression d'une categorie
	 */
	public int deleteLigneCommande(LigneCommande lc);
	
	/*
	 * methode de recherche d'une categorie en fonction de son id
	 */
	public LigneCommande getLigneCommandebyId(LigneCommande lc);
}
