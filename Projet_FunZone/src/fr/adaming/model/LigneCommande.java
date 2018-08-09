package fr.adaming.model;

public class LigneCommande {
	
	//Declaration des attributs
	private int quantite;
	private int prix;
	

	//Declaration des constructeurs
	//constructeur vide
	public LigneCommande() {
		super();
	}

	//constructeur complet
	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	//Declaration des getteurs et setteurs
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	
	//methode
	@Override
	public String toString() {
		return "LigneCommande [quantite=" + quantite + ", prix=" + prix + "]";
	}
	
	
	
	

}
