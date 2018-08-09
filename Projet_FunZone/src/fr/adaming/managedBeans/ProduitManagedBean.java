package fr.adaming.managedBeans;

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
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

/*
 * @author Thibault
 * Manager des Produits
 */

@ManagedBean(name = "pMB")
@RequestScoped
public class ProduitManagedBean {

	
	/*
	 * Declaration des attributs du ManagedBean Produit
	 */
	HttpSession maSession;
	private Produit produit;
	private Categorie categorie;
	private boolean indice;
	private List<Produit> listeProduit;
	private UploadedFile file;
	
	/*
	 * Transformation de l'association UML en java
	 */
	@EJB
	private IProduitService pService;

	/*
	 * Constructeur vide du ManagedBean
	 */
	public ProduitManagedBean() {
		super();
		this.produit = new Produit();
		this.indice = false;
	}

	@PostConstruct
	public void init(){
		this.listeProduit = pService.getAllProduits(categorie);
	}
	
	/*
	 * Declaration des getteurs et setteurs
	 */

	/**
	 * @return the categorie
	 */
	public Produit getProduit() {
		return produit;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setProduit(Produit produit) {
		this.produit = produit;
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
	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	/**
	 * @param listeCategorie the listeCategorie to set
	 */
	public void setListeCategorie(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
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
	public String addProduit() {

		/*
		 * on charge le dossier dans l'attribut photo de la classe Categorie
		 */
		this.produit.setPhoto(file.getContents());
		
		Produit pAjout = pService.addProduit(this.produit, this.categorie);
		
		/*
		 * on teste ici l'existence de cet ajout
		 */
		if (pAjout.getIdProduit() != 0) {

			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueil";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Entrée invalide"));
			/*
			 * renvoie vers la page XHTML d'ajout d'une categorie
			 */
			return "addProduit";
		}
	}

	public String updateProduit() {

		int verif = pService.updateProduit(this.produit, this.categorie);
		
		if (verif != 0) {

			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueil";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification invalide"));
			/*
			 * renvoie vers la page XHTML d'ajout d'une categorie
			 */
			return "updateProduit";
		}
	}

	public String deleteProduit() {
		
		int verif = pService.deleteProduit(this.produit, this.categorie);

		if (verif != 0) {

			
			/*
			 * envoie vers la page XHTML accueil de l'administrateur
			 */
			return "accueil";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression invalide"));
			/*
			 * renvoie vers la page XHTML d'ajout d'une categorie
			 */
			return "deleteProduit";
		}
	}
	
	public String searchProduitbyId() {

		/*
		 * recherche et stockage de la categorie recherchée
		 */
		Produit pSearch = pService.getProduitbyId(this.produit, this.categorie);

		/*
		 * On test le bon résultat de la recherche
		 */
		if (pSearch != null) {

			/*
			 * on stocke la recherche dans l'attribut du ManagedBean
			 */
			this.indice = true;
			this.produit = pSearch;

		} else {

			this.indice = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supression invalide"));

		}
		/*
		 * envoie vers la page XHTML recherche mise à jour avec le resultat de la recherche
		 */
		return "searchProduit";
	}
	
	
}
