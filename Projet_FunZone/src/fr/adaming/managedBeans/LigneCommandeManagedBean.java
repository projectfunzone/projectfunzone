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

import fr.adaming.model.LigneCommande;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	/**
	 * Declaration des attributs du ManagedBean Categorie
	 */
	HttpSession maSession;
	private LigneCommande ligneCommande;
	private boolean indice;
	private List<LigneCommande> listeLC;
	
	/**
	 * Transformation de l'association UML en java
	 */
	@EJB
	private ILigneCommandeService lcService;

	/**
	 * Constructeur vide du ManagedBean
	 */
	public LigneCommandeManagedBean() {
		super();
		this.ligneCommande = new LigneCommande();
		this.indice = false;
	}

	@PostConstruct
	public void init(){
		this.listeLC = lcService.getAllLigneCommande();
	}
	
	/**
	 * Declaration des getteurs et setteurs
	 */

	/**
	 * @return the categorie
	 */
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
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
	public List<LigneCommande> getListeLC() {
		return listeLC;
	}

	/**
	 * @param listeCategorie the listeCategorie to set
	 */
	public void setListeCategorie(List<LigneCommande> listeLC) {
		this.listeLC = listeLC;
	}

	/**
	 * ajouter une nouvelle ligne au site
	 */
//	public String addLigneCommande() {
//
//		
//		//on teste ici l'existence de cet ajout
//		if (lcService.addLigneCommande(this.ligneCommande).getIdLigneCommande()!= 0) {
//
//			//envoie vers la page XHTML accueil de l'administrateur
//			return "";
//
//		} else {
//
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Entr�e invalide"));
//			//renvoie vers la page XHTML d'ajout d'une categorie
//			return "";
//		}
//	}

	/**
	 * modifier une ligne du site
	 */
	public String updateLigneCommande() {

		if (lcService.updateLigneCommande(this.ligneCommande) != 0) {

			//envoie vers la page XHTML accueil de l'administrateur
			return "";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification invalide"));
			
			//renvoie vers la page XHTML d'ajout d'une ligne de commande
			return "";
		}
	}

	/**
	 * supprimer une ligne du site
	 */
	public String deleteLigneCommande() {

		if (lcService.deleteLigneCommande(this.ligneCommande) != 0) {

			
			//envoie vers la page XHTML accueil de l'administrateur
			return "";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression invalide"));
			// renvoie vers la page XHTML d'ajout d'une ligne de commande
			return "";
		}
	}
	
	/**
	 * rechercher une ligne du site par son id
	 */
	public String searchLigneCommandebyId() {

		//recherche et stockage de la ligne de commande recherch�e
		LigneCommande lcSearch = lcService.getLigneCommandebyId(ligneCommande);

		// On test le bon r�sultat de la recherche
		if (lcSearch != null) {

			//on stocke la recherche dans l'attribut du ManagedBean
			this.indice = true;
			this.ligneCommande = lcSearch;

		} else {

			this.indice = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supression invalide"));

		}
		//envoie vers la page XHTML recherche mise � jour avec le resultat de la recherche
		return "";
	}


}
