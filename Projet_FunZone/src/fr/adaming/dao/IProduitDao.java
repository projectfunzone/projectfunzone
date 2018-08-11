package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

/**
 * Interface Dao définissant les méthodes de Produit
 * @author Thibault
 *
 */
@Local
public interface IProduitDao {
	
	/*
	 * methode permettant de recuperer la liste des produits disponibles sur le site
	 */
	public List<Produit> getAllProduits();
	
	/*
	 * methode permettant d'ajouter un produit
	 */
	public Produit addProduit(Produit pr);
	
	/*
	 * methode permettant de modifier un produit
	 */
	public int updateProduit(Produit pr);
	
	/*
	 * methode permettant de supprimer un produit
	 */
	public int deleteProduit(Produit pr);
	
	/*
	 * methode permettant de rechercher un produit
	 */
	public Produit getProduitbyId (Produit pr);
	
}