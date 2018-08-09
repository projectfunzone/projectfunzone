package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Thibault
 * Methode interface Dao pour les categories de produits
 */
@Local
public interface ICategorieDao {
	
	/*
	 * methode permettant de recuperer la liste des categories de produits disponibles sur le site
	 */
	public List<Categorie> getAllCategories();
	
	/*
	 * methode permettant d'ajouter une categorie de produit
	 */
	public Categorie addCategorie(Categorie c);
	
	/*
	 * methode permettant de modifier une categorie de produit
	 */
	public int updateCategorie(Categorie c);
	
	/*
	 * methode permettant de supprimer une categorie de produit
	 */
	public int deleteCategorie(Categorie c);
	
	/*
	 * methode permettant de rechercher une categorie de produit
	 */
	public Categorie getCategoriebyNomCategorie (Categorie c);
	
}
