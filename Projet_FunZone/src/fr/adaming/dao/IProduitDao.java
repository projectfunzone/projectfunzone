package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Produit;

/**
 * @author Thibault
 * Methode interface Dao pour les produits
 *
 */
public interface IProduitDao {
	
	/*
	 * methode permettant de recuperer la liste des produits disponibles
	 */
	public List<Produit> getAllProduits();
	

}
