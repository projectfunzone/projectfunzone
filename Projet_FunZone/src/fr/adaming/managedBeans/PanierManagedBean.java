package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;
import sun.print.PSStreamPrintService;

@ManagedBean(name = "panMB")
public class PanierManagedBean {

	/*
	 * Les attributs utilisés dans le panier
	 */
	private Panier pan;
	private Produit pr;
	private int q;
	private LigneCommande lc;

	/*
	 * Transformation de l'association UML en JAVA
	 */
	private IProduitService pService;
	private IClientService clService;

	/**
	 * Constructeur vide
	 */
	public PanierManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.pan = new Panier();
		this.pr = new Produit();
		this.lc = new LigneCommande();
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

	
	/*
	 * Methode permettant d'ajouter un produit dans son panier en passant par une ligne de commande
	 * Ce panier n'est pas stocké dans la base de donnée (transient) mais uniquement dans la session créée pour l'occasion 
	 */
	public String ajouterProdPanier() {

		// on créé une session pour le panier
		Panier panSession = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("panierClient");

		// on créé une ligne de commande pour le produit
		List<LigneCommande> newList = new ArrayList<LigneCommande>();

		// on récupere la session du panier et on verifie qu'elle ne soit pas
		// vide
		if (panSession != null) {

			// on recupere la liste de commande du panier et on verifie qu'elle
			// ne soit pas vide
			if (panSession.getListeCommande() != null) {

				// on recupere l'ancienne ligne de commande par la nouvelle
				List<LigneCommande> list = panSession.getListeCommande();

				// on stocke la nouvelle liste dans la nouvelle
				for (LigneCommande elem : list) {
					newList.add(elem);
				}

			}
		}

		// on récupere le produit de la base de donnée.
		Produit prOut = pService.getProduitbyId(this.pr);

		// on créer alors la ligne de commande associée
		lc = clService.ajoutProdPanier(prOut, q);

		// on vérifie que lc ne soit pas vide
		if (lc != null) {

			// on ajoute à la liste de ligne de commande cette nouvelle nouvelle
			// ligne de commande
			newList.add(lc);

			// on ajoute au panier la liste de commande
			pan.setListeCommande(newList);

			// on ajoute à la session PanierClient la nouveau panier
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panierClient", pan);

			// on renvoie au panier
			return "panierClient";

		} else {

			// Message d'erreur suite à la tentative d'ajout de produit au
			// panier
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));

			return "ajoutPanier";
		}

	}

}
