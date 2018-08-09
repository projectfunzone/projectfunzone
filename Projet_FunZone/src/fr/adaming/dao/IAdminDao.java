package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Admin;

/**
 * Interface de la couche Dao qui permettent de d�finir les m�thodes de la class
 * Admin en rapport avec la base de donn�e
 * 
 * @author Camille
 *
 */
@Local
public interface IAdminDao {
	
	public Admin connectionAdmin(Admin admin);

}
