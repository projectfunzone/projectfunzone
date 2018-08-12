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
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@SuppressWarnings("serial")
@ManagedBean(name = "cmdMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	@EJB
	private ICommandeService cmdService;

	private Commande cmd;

	private Client cl;

	private List<Commande> listeAllCommande;

	private List<Commande> listeGetCommande;

	/**
	 * Constructeur vide
	 */
	public CommandeManagedBean() {
		super();
		this.cmd = new Commande();
	}

	/**
	 * @return the cmd
	 */
	public Commande getCmd() {
		return cmd;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCmd(Commande cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the listeAllCommande
	 */
	public List<Commande> getListeAllCommande() {
		return listeAllCommande;
	}

	/**
	 * @param listeAllCommande
	 *            the listeAllCommande to set
	 */
	public void setListeAllCommande(List<Commande> listeAllCommande) {
		this.listeAllCommande = listeAllCommande;
	}

	/**
	 * m�thode qui permet de charger la liste au moment de l'instanciation du
	 * managedBean
	 */
	@PostConstruct
	public void init() {
		this.listeAllCommande = cmdService.getAllCommande();

	}

	/**
	 * @return the listeGetCommande
	 */
	public List<Commande> getListeGetCommande() {
		return listeGetCommande;
	}

	/**
	 * @param listeGetCommande
	 *            the listeGetCommande to set
	 */
	public void setListeGetCommande(List<Commande> listeGetCommande) {
		this.listeGetCommande = listeGetCommande;
	}

	/**
	 * M�thode qui permet de rechercher une commande � partir de son id
	 * 
	 * @return
	 */
	public String getCommandeById() {

		Commande cmdOut = cmdService.getCommandeById(this.cmd);

		if (cmdOut != null) {
			this.cmd = cmdOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande recherch�e n'existe pas"));
		}

		return "";
	}

	/**
	 * Si connect� en temps que client : permet de rechercher la liste de ses
	 * commandes si connect� en temps qu'admin : permet de rechercher une
	 * commande par son id si pas connect� : demande de se connecter
	 * 
	 * @return
	 */
	public String getCommandeByIdOrClient() {

		// On v�rifie si un client est connect� pour faire la recherche par son
		// id client
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession") != null) {
			// on charge les informations du client de la session dans le cl du
			// managedBean
			this.cl = (Client) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession");

			// On rechercher des commande par leur id ou par l'id du client
			// connect�
			this.listeGetCommande = cmdService.getCommandeByIdOrClient(this.cmd, this.cl);

			if (this.listeGetCommande.size() <= 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Il n'y a pas de commande associ� � cette id ou a votre compte client"));
			}

			// Si l'admin est connect� on peut faire la rechercher seulement par
			// id
		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adminSession") != null) {

			Commande cmdOut = cmdService.getCommandeById(this.cmd);

			if (cmdOut != null) {
				this.cmd = cmdOut;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Il n'y a pas de commande associ� � cette id"));
			}

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Veuillez vous connecter pour consulter vos commandes"));
		}

		return "";
	}

	/**
	 * M�thode qui permet de cr�er une commande par un client connect�
	 * 
	 * @return
	 */
	public String addCommande() {

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession") != null) {

			this.cl = (Client) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession");
			Commande cmdOut = cmdService.addCommande(this.cmd, this.cl);

			if (cmdOut != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La commande a �t� cr�e avec succ�s"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"une erreur s'est produite lors du passage de la commande, consulter un admin"));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Veuillez vous connecter pour passer commandes"));
		}
		return "";
	}

	public String deleteCommande() {
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession") != null) {

			this.cl = (Client) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clSession");

			Commande cmdOut = cmdService.getCommandeById(this.cmd);

			if (cmdOut != null) {
				if (cmdOut.getCl().getIdClient() != this.cl.getIdClient()) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Cette commande n'existe pas dans votre liste de commande"));
				} else {

					int verif = cmdService.deleteCommande(this.cmd, this.cl);

					if (verif != 0) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("votre commande a bien �t� supprim�"));
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
								"une erreur s'est produite lors de la suppresion de la commande, consulter un admin"));
					}
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("la commande que vous voulez supprimer n'existe pas"));
			}
			
			
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("vous n'�tes pas connect� � votre espace Client"));
		}

		return "";
	}

}
