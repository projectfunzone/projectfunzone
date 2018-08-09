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

	/**
	 * permet de r�cup�rer la liste des clients au moment de l'instanciation du managedBean
	 */
	@PostConstruct
	public void init () {
		this.listeAllClient=clService.getAllClient();
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
	 * @param listeAllClient
	 *            the listeAllClient to set
	 */
	public void setListeAllClient(List<Client> listeAllClient) {
		this.listeAllClient = listeAllClient;
	}

	
	//autre m�thode
	/**
	 * Permet de r�cup�rer un client par son id. retourne la m�me page xhtml, afin d'afficher les informations sur la page
	 * On v�rifie que le client r�cup�rer est diff�rent de null, pour confirmer qu'il existe dans la base de donn�e.
	 * @return
	 */
	public String getClientById () {
		
		Client clOut=clService.getClientById(this.cl);
		
		if (clOut != null ) {
			this.cl=clOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Il n'y a pas de client associ� � cet id"));
		}
		
		return "";
		
	}

}
