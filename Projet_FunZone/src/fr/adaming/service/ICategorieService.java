/**
 * 
 */
package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Thibault
 * Liste des methodes services pour la classe Categorie
 *
 */
@Local
public interface ICategorieService {
	
	/*
	 * methode de selection de l'ensemble des categorie disponible sur le site
	 */
	public List<Categorie> getAllCategories();
	
	/*
	 * methode d'ajout d'une categorie
	 */
	public Categorie addCategorie(Categorie c);
	
	/*
	 * methode de modification d'une categorie
	 */
	public int updateCategorie(Categorie c);
	
	/*
	 * methode de supression d'une categorie
	 */
	public int deleteCategorie(Categorie c);
	
	/*
	 * methode de recherche d'une categorie en fonction de son nom
	 */
	public Categorie getCategoriebyNomCategorie(Categorie c);
}
