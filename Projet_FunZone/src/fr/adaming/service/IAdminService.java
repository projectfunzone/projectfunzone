package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Admin;

/**
 * Interface de la couche Service qui permet de définir les méthodes métier de
 * la class Admin
 * 
 * @author Camille
 *
 */
@Local
public interface IAdminService {
	
	public Admin connectionAdmin(Admin admin);

}
