package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * Interface Dao définisant les méthodes de Categorie
 * @author Thibault
 */
@Local
public interface ICategorieDao {
	
	/**
	 * Methode permettant de recuperer la liste des categories de produits disponibles sur le site
	 */
	public List<Categorie> getAllCategories();
	
	/**
	 * Methode permettant d'ajouter une categorie de produit
	 */
	public Categorie addCategorie(Categorie c);
	
	/**
	 * Methode permettant de modifier une categorie de produit
	 */
	public int updateCategorie(Categorie c);
	
	/**
	 * methode permettant de supprimer une categorie de produit
	 */
	public int deleteCategorie(Categorie c);
	
	/**
	 * methode permettant de rechercher une categorie de produit par son nom
	 */
	public Categorie getCategoriebyNomCategorie (Categorie c);
	
	
	/**
	 * methode permettant de rechercher une categorie de produit par son id
	 */
	public Categorie getCategoriebyId (Categorie c);
	
}
