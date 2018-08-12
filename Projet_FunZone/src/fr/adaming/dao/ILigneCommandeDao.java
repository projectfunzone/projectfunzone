package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

/*
 *  Interface Dao définisant les méthodes de Categorie
 *  @author Thibault
 */
@Local
public interface ILigneCommandeDao {
	
	/*
	 * methode permettant de recuperer la liste des commandes effectuées
	 */
	public List<LigneCommande> getAllLigneCommandes();
	
	/*
	 * methode permettant d'ajouter une ligne de commande
	 */
	public LigneCommande addLigneCommande(LigneCommande lc);
	
	/*
	 * methode permettant de modifier une ligne de commande
	 */
	public int updateLigneCommande(LigneCommande lc);
	
	/*
	 * methode permettant de supprimer une ligne de commande
	 */
	public int deleteLigneCommande(LigneCommande lc);
	
	/*
	 * methode permettant de rechercher une ligne de commande
	 */
	public LigneCommande getLigneCommandebyId (LigneCommande lc);
	
	public List<LigneCommande> getLigneCommandeByIdCommande(LigneCommande lc);

}
