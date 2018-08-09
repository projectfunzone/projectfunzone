/**
 * 
 */
package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

/**
 * @author Thibault Definition des methodes services pour la classe Categorie
 *
 */
public class CategorieServiceImpl implements ICategorieService {

	/*
	 * transformation de l'association UML en java et injection dépendance (EJB)
	 */
	@EJB
	private ICategorieDao cDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.adaming.service.ICategorieService#getAllCategories()
	 */
	@Override
	public List<Categorie> getAllCategories() {

		return cDao.getAllCategories();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.adaming.service.ICategorieService#addCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public Categorie addCategorie(Categorie c) {

		return cDao.addCategorie(c);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.adaming.service.ICategorieService#updateCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public int updateCategorie(Categorie c) {

 
		return cDao.updateCategorie(c);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.service.ICategorieService#deleteCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public int deleteCategorie(Categorie c) {

		if (c != null) {
			return cDao.deleteCategorie(c);
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.adaming.service.ICategorieService#getCategoriebyNomCategorie(fr.adaming.model.Categorie)
	 */
	@Override
	public Categorie getCategoriebyNomCategorie(Categorie c) {
		return cDao.getCategoriebyNomCategorie(c);
	}
	
	

}
