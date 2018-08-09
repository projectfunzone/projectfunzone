package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Admin;

/**
 * Interface de la couche Service qui permet de d�finir les m�thodes m�tier de
 * la class Admin
 * 
 * @author Camille
 *
 */
@Local
public interface IAdminService {
	
	public Admin connectionAdmin(Admin admin);

}
