/**
 * 
 */
package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * Interface défininissant les méthodes de Produit
 * @author Thibault
 *
 */
public interface IProduitService {

	/*
	 * methode permettant de recuperer la liste des produits
	 * disponibles sur le site
	 */
	public List<Produit> getAllProduits();

	/*
	 * methode permettant d'ajouter un produit
	 */
	public Produit addProduit(Produit pr, Categorie c);

	/*
	 * methode permettant de modifier un produit
	 */
	public int updateProduit(Produit pr, Categorie c);

	/*
	 * methode permettant de supprimer un produit
	 */
	public int deleteProduit(Produit pr);

	/*
	 * methode permettant de rechercher un produit par son id
	 */
	public Produit getProduitbyId(Produit pr);

}
