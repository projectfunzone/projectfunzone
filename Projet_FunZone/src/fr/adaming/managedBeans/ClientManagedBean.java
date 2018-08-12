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

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

/**
 * ManagedBean du client
 * 
 * @author Camille
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	/**
	 * Transformation de l'association UML en java, avec instanciation EJB
	 */
	@EJB
	private IClientService clService;

	private Client cl;

	private List<Client> listeAllClient;

	private List<Client> listeGetClient;

	private HttpSession maSession;

	/**
	 * permet de récupérer la liste des clients au moment de l'instanciation du
	 * managedBean
	 */
	@PostConstruct
	public void init() {
		this.listeAllClient = clService.getAllClient();
	}

	/**
	 * Constructeur vide avec instanciation d'un client
	 */
	public ClientManagedBean() {
		super();
		this.cl = new Client();
	}

	/**
	 * @return the cl
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param cl
	 *            the cl to set
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return the listeAllClient
	 */
	public List<Client> getListeAllClient() {
		return listeAllClient;
	}

	/**
	 * @return the listeGetClient
	 */
	public List<Client> getListeGetClient() {
		return listeGetClient;
	}

	/**
	 * @param listeGetClient
	 *            the listeGetClient to set
	 */
	public void setListeGetClient(List<Client> listeGetClient) {
		this.listeGetClient = listeGetClient;
	}

	/**
	 * @param listeAllClient
	 *            the listeAllClient to set
	 */
	public void setListeAllClient(List<Client> listeAllClient) {
		this.listeAllClient = listeAllClient;
	}

	// autre méthode
	/**
	 * Permet de récupérer un client par son id. retourne la même page xhtml,
	 * afin d'afficher les informations sur la page On vérifie que le client
	 * récupérer est différent de null, pour confirmer qu'il existe dans la base
	 * de donnée.
	 * 
	 * @return
	 */
	public String getClientById() {

		Client clOut = clService.getClientById(this.cl);

		if (clOut != null) {
			this.cl = clOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Il n'y a pas de client associé à cet id"));
		}

		return "";

	}

	/**
	 * Permet de récupérer un client par son id ou par son nom. retourne la même
	 * page xhtml, afin d'afficher les informations sur la page On vérifie que
	 * la taille de la liste de résultat est >0, pour confirmer qu'il y a bien
	 * des correspondances dans la base de donnée.
	 * 
	 * @return
	 */
	public String getClientByIdNom() {
		this.listeGetClient = clService.getClientByIdNom(this.cl);

		if (this.listeGetClient.size() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Il n'y a pas de client correspondant à ce nom ou cet id dans la base de donnée"));
		}

		return "";

	}

	/**
	 * Permet de créer un client. Met la liste des clients à jour dans la page
	 * "listeAllClient.xhtml"
	 * 
	 * @return
	 */
	public String addClient() {

		Client clOut = clService.addClient(this.cl);

		if (clOut != null) {
			this.listeAllClient = clService.getAllClient();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le compte a bien été crée"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produit"));
		}

		return "";
	}

	/**
	 * Permet de supprimer un client. Met la liste des clients à jour dans la
	 * page "listeAllClient.xhtml" si le retour de la méthode est =! 0, la
	 * suppression a fonctionné sinon, si ==0 c'est que la suppression n'a pas
	 * fonctionner
	 * 
	 * @return
	 */
	public String deleteClient() {
		int verif = clService.deleteClient(this.cl);

		if (verif != 0) {
			this.listeAllClient = clService.getAllClient();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le compte a bien été supprimé"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produit"));
		}

		return "";

	}

	/**
	 * Permet de modifier un client. Met la liste des clients à jour dans la
	 * page "listeAllClient.xhtml" si le retour de la méthode est =! 0, la modif
	 * a fonctionné sinon, si ==0 c'est que la modif n'a pas fonctionner
	 * 
	 * @return
	 */
	public String updateClient() {
		int verif = clService.updateClient(this.cl);

		if (verif != 0) {
			this.listeAllClient = clService.getAllClient();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modif a été prise en compte"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produit"));
		}

		return "";
	}

	/**
	 * Permet de modifier le mdp d'un client. si le retour de la méthode est =!
	 * 0, la modif a fonctionné sinon, si ==0 c'est que la modif n'a pas
	 * fonctionner
	 * 
	 * @return
	 */
	public String updateClientMdp() {
		int verif = clService.updateClientMdp(this.cl);

		if (verif != 0) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le mot de passe a été modifié"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produit"));
		}

		return "";
	}

	/**
	 * Méthode qui permet au client de se connecter à n'importe quel moment dans
	 * le site pour récupérer les informations de son compte
	 * 
	 * @return
	 */
	public String seConnecter() {

		// Vérifie si le client est déjà connecté => on vérifie s'il y a déjà un
		// client dans la session
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession") != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous êtes déjà connecté"));
			return "";
		} else {
			// si le client n'est pas connecter, on appelle la méthode pour
			// vérifier le mail et le mdp
			int verif = clService.connectionClient(this.cl);

			switch (verif) {
			// si le retour de la méthode = 0, le mail n'existe pas => pas de
			// compte client dans la DB
			case 0:
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Il n'y a pas de compte client associé à cette adresse email"));
				return "";
			// si le retour de la méthode = 1, la connexion a réussi et on
			// ajoute le client dans la session
			case 1:
				this.cl = (Client) clService.getClientByIdNom(cl).get(0);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", this.cl);

				return "accueil";
			case 2:
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le mot de passe est erroné"));
				return "";
			default:
				return "";

			}
		}
	}

	public String seDeconnecter() {

		// vérifié qu'un client est déjà connecté
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession") != null) {

			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

			if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession") != null) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La déconnexion n'a pas fonctionné"));

			} else {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous n'êtes plus connecté"));

			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'êtes pas connecté à un compte client"));
		}

		return "";

	}
	
	public String payerCommande (){
		
		clService.sendMail(this.cl);
		
		return "accueil";
	}
}
