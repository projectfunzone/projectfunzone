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
 *Implementation de l'interface ProduitService pour red�finir les m�thodes de Produit
 * @author Adaming
 *
 */
@Stateful
public class ProduitServiceImpl implements IProduitService {

	/**
	 * transformation de l'association UML en java et injection d�pendance (EJB)
	 */
	@EJB
	private IProduitDao pDao;

	/**
	 * @see fr.adaming.service.ICategorieService#getAllCategories()
	 */
	@Override
	public List<Produit> getAllProduits() {

		return pDao.getAllProduits();
	}

	/**
	 * @see fr.adaming.service.ICategorieService#addCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public Produit addProduit(Produit pr, Categorie c) {

		// lier les objets java
		pr.setCategorie(c);

		return pDao.addProduit(pr);

	}

	/**
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

	/**
	 * @see
	 * fr.adaming.service.ICategorieService#deleteCategorie(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public int deleteProduit(Produit pr) {

		// chercher l'�tudiant avec son id
		Produit pDel = this.getProduitbyId(pr);

		// verifier si l'�tudiant existe et appartient au formateur
		if (pDel != null) {

			return pDao.deleteProduit(pDel);

		} else {

			return 0;

		}
	}

	/**
	 * @see
	 * fr.adaming.service.ICategorieService#getCategoriebyId(fr.adaming.model.
	 * Categorie)
	 */
	@Override
	public Produit getProduitbyId(Produit pr) {

		System.out.println(pr.getIdProduit());
		return pDao.getProduitbyId(pr);
	}

}
