package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

/**
 * 
 * ManagedBean de l'admin
 * 
 * @author Camille
 *
 */

@ManagedBean(name = "adMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

	/**
	 * Transformation de l'association UML en java, avec instanciation EJB
	 */
	@EJB
	private IAdminService adService;

	private Admin admin;

	/**
	 * 
	 */
	public AdminManagedBean() {
		super();
		this.admin = new Admin();
	}

	// Méthode

	/**
	 * @return the admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String seConnecter() {
		Admin adOut = adService.connectionAdmin(this.admin);

		if (adOut != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adOut);

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur s'est produite, la connexion a échouée"));
			
		}
		return "";
	}

}
