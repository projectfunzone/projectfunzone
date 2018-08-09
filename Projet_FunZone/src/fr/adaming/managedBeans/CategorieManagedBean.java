package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

/*
 * @author Thibault
 * Manager des Categories
 */
@SuppressWarnings("serial")
@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	/*
	 * Declaration des attributs du ManagedBean Categorie
	 */
	HttpSession maSession;
	private Categorie categorie;
	private boolean indice;
	private List<Categorie> listeCategorie;
	private UploadedFile file;
	
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

	@PostConstruct
	public void init(){
		this.listeCategorie = cService.getAllCategories();
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
	
	/**
	 * @return the listeCategorie
	 */
	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	/**
	 * @param listeCategorie the listeCategorie to set
	 */
	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}
	
	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param the file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/*
	 * ajouter une nouvelle categorie au site
	 */
	public String addCategorie() {

		/*
		 * on charge le dossier dans l'attribut photo de la classe Categorie
		 */
		this.categorie.setPhoto(file.getContents());
		
		/*
		 * on teste ici l'existence de cet ajout
		 */
		if (cService.addCategorie(this.categorie).getIdCategorie() != 0) {

			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueil";

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
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueil";

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
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueil";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression invalide"));
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
		return "searchCategorie";
	}
	
	public String searchCategoriebyId() {

		/*
		 * recherche et stockage de la categorie recherchée
		 */
		Categorie cSearch = cService.getCategoriebyId(this.categorie);

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
		return "searchCategorie";
	}

}
