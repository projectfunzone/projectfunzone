package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;

/**
 * Interface de la couche DAO qui permet de définir le Crud de Commande
 * @author Camille
 *
 */
@Local
public interface ICommandeDao {
	
	public List<Commande> getAllCommande();

}
