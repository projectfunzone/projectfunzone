package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@SuppressWarnings("serial")
@ManagedBean(name="cmdMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	@EJB
	private ICommandeService cmdService;
	
	private Commande cmd;
	
	private List<Commande> listeAllCommande;

	/**
	 * Constructeur vide 
	 */
	public CommandeManagedBean() {
		super();
		this.cmd=new Commande();
	}
	
	
	
	
	
	
	
	/**
	 * @return the cmd
	 */
	public Commande getCmd() {
		return cmd;
	}







	/**
	 * @param cmd the cmd to set
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
	 * @param listeAllCommande the listeAllCommande to set
	 */
	public void setListeAllCommande(List<Commande> listeAllCommande) {
		this.listeAllCommande = listeAllCommande;
	}







	/**
	 * méthode qui permet de charger la liste au moment de l'instanciation du managedBean
	 */
	@PostConstruct
	public void init () {
		this.listeAllCommande=cmdService.getAllCommande();
		
	}
	
	
}
