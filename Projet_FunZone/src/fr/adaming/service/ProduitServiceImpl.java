/**
 * 
 */
package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Adaming
 *
 */
@Stateful
public class ProduitServiceImpl implements IProduitService {

	/*
	 * transformation de l'association UML en java et injection dépendance (EJB)
	 */
	@EJB
	private IProduitDao pDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.adaming.service.ICategorieService#getAllCategories()
	 */
	@Override
	public List<Produit> getAllProduits(Categorie c) {

		return pDao.getAllProduits(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.adaming.service.ICategorieService#addCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public Produit addProduit(Produit pr, Categorie c) {

		// lier les objets java
		pr.setCategorie(c);

		return pDao.addProduit(pr);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.adaming.service.ICategorieService#updateCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public int updateProduit(Produit pr, Categorie c) {

		// lier les objets java
		pr.setCategorie(c);
		return pDao.updateProduit(pr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.adaming.service.ICategorieService#deleteCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public int deleteProduit(Produit pr, Categorie c) {

		// chercher l'étudiant avec son id
		Produit pDel = this.getProduitbyId(pr, c);

		// verifier si l'étudiant existe et appartient au formateur
		if (pDel != null) {

			return pDao.deleteProduit(pDel);

		} else {

			return 0;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.adaming.service.ICategorieService#getCategoriebyId(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public Produit getProduitbyId(Produit pr, Categorie c) {
		// lier les objets java
		pr.setCategorie(c);

		// recuperer l'étudiant
		Produit pOut = pDao.getProduitbyId(pr);

		if (pOut != null) {

			if (pOut.getCategorie().getIdCategorie() == c.getIdCategorie()) { 

				return pOut;

			}
		}

		return null;
	}

}
