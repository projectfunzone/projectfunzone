package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

/**
 * ManagedBean du client
 * 
 * @author Camille
 *
 */
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

}
