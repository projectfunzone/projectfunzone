package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateful
public class PanierServiceImpl implements IPanierService {

	@EJB
	ICommandeService cmdService;

	@EJB
	IClientService clService;

	@EJB
	ILigneCommandeService lcService;

	@Override
	public int créerCommande(List<LigneCommande> listePanierCommande, Client cl) {

		Commande cmd = new Commande();

		cmd = cmdService.addCommande(cmd, cl);

		if (cmd != null) {

			for (LigneCommande lc : listePanierCommande) {

				LigneCommande lcOut = lcService.addLigneCommande(lc, cmd);
				
				if (lcOut == null) {
					return 2;
				}

			}
			return 1;
		}
		
		return 0;
	}

}
