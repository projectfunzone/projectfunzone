package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

/*
 * @author Thibault
 * Manager des Categories
 */
@SuppressWarnings("serial")
@ManagedBean(name = "CatMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	/*
	 * Declaration des attributs du ManagedBean Categorie
	 */
	HttpSession maSession;
	private Categorie categorie;
	private boolean indice;

	/*
	 * Transformation de l'association UML en java
	 */
	@EJB
	private ICategorieService cService;

	/*
	 * Constructeur vide du ManagedBean
	 */
	public CategorieManagedBean() {
		super();
		this.categorie = new Categorie();
		this.indice = false;
	}

	/*
	 * Declaration des getteurs et setteurs
	 */

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the indice
	 */
	public boolean isIndice() {
		return indice;
	}

	/**
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/*
	 * ajouter une nouvelle categorie au site
	 */
	public String addCategorie() {

		/*
		 * on teste ici l'existence de cet ajout
		 */
		if (cService.addCategorie(this.categorie).getIdCategorie() != 0) {

			/*
			 * mettre à jour la liste
			 */
			List<Categorie> listeCategories = cService.getAllCategories();

			/*
			 * ajouter la liste des categories dans la session
			 */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoriesListe",
					listeCategories);

			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueilAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Entrée invalide"));
			/*
			 * renvoie vers la page XHTML d'ajout d'une categorie
			 */
			return "addCategorie";
		}
	}

	public String updateCategorie() {

		if (cService.updateCategorie(this.categorie) != 0) {

			/*
			 * mettre à jour la liste
			 */
			List<Categorie> listeCategories = cService.getAllCategories();

			/*
			 * ajouter la liste des categories dans la session
			 */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoriesListe",
					listeCategories);

			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueilAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification invalide"));
			/*
			 * renvoie vers la page XHTML d'ajout d'une categorie
			 */
			return "updateCategorie";
		}
	}

	public String deleteCategorie() {

		if (cService.deleteCategorie(this.categorie) != 0) {

			/*
			 * mettre à jour la liste
			 */
			List<Categorie> listeCategories = cService.getAllCategories();

			/*
			 * ajouter la liste des categories dans la session
			 */
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoriesListe",
					listeCategories);

			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueilAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supression invalide"));
			/*
			 * renvoie vers la page XHTML d'ajout d'une categorie
			 */
			return "deleteCategorie";
		}
	}

	public String searchCategoriebyNomCategorie() {

		/*
		 * recherche et stockage de la categorie recherchée
		 */
		Categorie cSearch = cService.getCategoriebyNomCategorie(this.categorie);

		/*
		 * On test le bon résultat de la recherche
		 */
		if (cSearch != null) {

			/*
			 * on stocke la recherche dans l'attribut du ManagedBean
			 */
			this.indice = true;
			this.categorie = cSearch;

		} else {

			this.indice = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supression invalide"));

		}
		/*
		 * envoie vers la page XHTML recherche mise à jour avec le resultat de la recherche
		 */
		return "rechercheCategorie";
	}

}
