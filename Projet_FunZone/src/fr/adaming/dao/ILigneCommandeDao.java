package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeDao {
	
	/*
	 * methode permettant de recuperer la liste des categories de produits disponibles sur le site
	 */
	public List<LigneCommande> getAllLigneCommandes();
	
	/*
	 * methode permettant d'ajouter une categorie de produit
	 */
	public LigneCommande addLigneCommande(LigneCommande lc);
	
	/*
	 * methode permettant de modifier une categorie de produit
	 */
	public int updateLigneCommande(LigneCommande lc);
	
	/*
	 * methode permettant de supprimer une categorie de produit
	 */
	public int deleteLigneCommande(LigneCommande lc);
	
	/*
	 * methode permettant de rechercher une categorie de produit par son id
	 */
	public LigneCommande getLigneCommandebyId (LigneCommande lc);

}
