package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "panMB")
@RequestScoped
public class PanierManagedBean implements Serializable {

	/*
	 * Les attributs utilis�s dans le panier
	 */
	private Panier pan;
	private Produit pr;
	private int q;
	private LigneCommande lc;

	private List<LigneCommande> listePanier=new ArrayList<>();

	/*
	 * Transformation de l'association UML en JAVA
	 */
	@EJB
	private IProduitService pService;
	@EJB
	private IClientService clService;

	/**
	 * Constructeur vide
	 */
	public PanierManagedBean() {
		super();
		this.pan = new Panier();
		this.pr = new Produit();
		this.lc = new LigneCommande();
	}

	@PostConstruct
	public void init() {
		
		Panier panSession = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("panierClient");

				// on r�cupere la session du panier et on verifie qu'elle ne soit pas
		// vide
		if (panSession != null) {

			// on recupere la liste de commande du panier et on verifie qu'elle
			// ne soit pas vide
			if (panSession.getListeCommande() != null) {

								// on stocke la nouvelle liste dans la nouvelle
				for (LigneCommande elem : panSession.getListeCommande()) {
					this.listePanier.add(elem);
				}

			}
		}
	}

	/**
	 * @return the pan
	 */
	public Panier getPan() {
		return pan;
	}

	/**
	 * @param pan
	 *            the pan to set
	 */
	public void setPan(Panier pan) {
		this.pan = pan;
	}

	/**
	 * @return the pr
	 */
	public Produit getPr() {
		return pr;
	}

	/**
	 * @param pr
	 *            the pr to set
	 */
	public void setPr(Produit pr) {
		this.pr = pr;
	}

	/**
	 * @return the q
	 */
	public int getQ() {
		return q;
	}

	/**
	 * @param q
	 *            the q to set
	 */
	public void setQ(int q) {
		this.q = q;
	}

	/**
	 * @return the lc
	 */
	public LigneCommande getLc() {
		return lc;
	}

	/**
	 * @param lc
	 *            the lc to set
	 */
	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}

	/**
	 * @return the listePanier
	 */
	public List<LigneCommande> getListePanier() {
		return listePanier;
	}

	/**
	 * @param listePanier
	 *            the listePanier to set
	 */
	public void setListePanier(List<LigneCommande> listePanier) {
		this.listePanier = listePanier;
	}

	/*
	 * Methode permettant d'ajouter un produit dans son panier en passant par
	 * une ligne de commande Ce panier n'est pas stock� dans la base de donn�e
	 * (transient) mais uniquement dans la session cr��e pour l'occasion
	 */
	public String addProdPanier() {

//		// on cr�� une session pour le panier
//		Panier panSession = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
//				.get("panierClient");
//
//		// on cr�� une ligne de commande pour le produit
//		List<LigneCommande> newList = new ArrayList<LigneCommande>();
//
//		// on r�cupere la session du panier et on verifie qu'elle ne soit pas
//		// vide
//		if (panSession != null) {
//
//			// on recupere la liste de commande du panier et on verifie qu'elle
//			// ne soit pas vide
//			if (panSession.getListeCommande() != null) {
//
//				// on recupere l'ancienne ligne de commande par la nouvelle
//				List<LigneCommande> oldlist = panSession.getListeCommande();
//
//				// on stocke la nouvelle liste dans la nouvelle
//				for (LigneCommande elem : oldlist) {
//					newList.add(elem);
//				}
//
//			}
//		}

		// on r�cupere le produit de la base de donn�e.
		Produit prOut = pService.getProduitbyId(this.pr);

		
		// on cr�er alors la ligne de commande associ�e
		LigneCommande lcOut = clService.ajoutProdPanier(prOut, q);

		// on v�rifie que lc ne soit pas vide
		if (lcOut != null) {

			System.out.println(lcOut);
			
			System.out.println(this.listePanier);
			// on ajoute � la liste de ligne de commande cette nouvelle nouvelle
			// ligne de commande
			this.listePanier.add(lcOut);

			// on ajoute au panier la liste de commande
			pan.setListeCommande(this.listePanier);

			// on ajoute � la session PanierClient la nouveau panier
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierClient", pan);

			// on renvoie au panier
			return "panierClient";

		} else {

			// Message d'erreur suite � la tentative d'ajout de produit au
			// panier
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));

			return "ajoutPanier";
		}

	}

}
