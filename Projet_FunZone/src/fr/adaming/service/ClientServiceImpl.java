package fr.adaming.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import sun.java2d.cmm.lcms.LcmsServiceProvider;

/**
 * Class qui implemente l'interface IClientService pour récupérer les méthodes
 * du CRUD et développer les méthodes méthiers
 * 
 * @author Camille
 *
 */
@Stateful
public class ClientServiceImpl implements IClientService {

	/**
	 * Injection dépendance de Dao
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Client> getAllClient() {

		return clDao.getAllClient();
	}

	/**
	 * Permet de récupérer un client par son id.
	 */
	@Override
	public Client getClientById(Client cl) {

		return clDao.getClientById(cl);
	}

	@Override
	public List<Client> getClientByIdNom(Client cl) {

		return clDao.getClientByIdNom(cl);
	}

	@Override
	public Client addClient(Client cl) {

		Client clOut = clDao.addClient(cl);

		// on compare l'id du client en sortie, car si il est différent de 0,
		// c'est que le client a été créé
		if (clOut.getIdClient() != 0) {
			return clOut;
		}
		return null;
	}

	@Override
	public int deleteClient(Client cl) {

		return clDao.deleteClient(cl);
	}

	@Override
	public int updateClient(Client cl) {

		return clDao.updateClient(cl);
	}

	@Override
	public int updateClientMdp(Client cl) {

		return clDao.updateClientMdp(cl);
	}

	/**
	 * On récupère un client via son email pour ensuite comparer le mdp du
	 * client récupérer (du coup dans clOut) avec le mdp renseigné sur la vue
	 * (dans cl). Retourne un int avec des valeurs différentes selon la
	 * situation. 0 si le client n'existe pas, 1 si le client existe et le mdp
	 * est bon 2 si le mot de passe renseigné ne correspond pas au mdp dans la
	 * base de donnée
	 */
	@Override
	public int connectionClient(Client cl) {
		// on récupère une liste avec normalement 1 client qui correspond au
		// mail unique
		List<Client> listeRecupMail = clDao.getClientByIdNom(cl);
		// on verifie qu'il n'y a qu'un client avec cet adresse mail, car sinon,
		// cela veut dire qu'il y a 2 comptes clients avec la même adresse mail
		if (listeRecupMail.size() == 1) {
			// On récupère le client dans la liste dans un clOut pour ne pas
			// écraser le mdp rentré dans cl
			Client clOut = listeRecupMail.get(0);

			// On compare les 2 mots de passe, s'ils sont différents, c'est que
			// ce n'est pas le bon mdp rentré
			if (clOut.getMdpClient().contentEquals(cl.getMdpClient())) {
				return 1;
			}

			return 2;

		}
		return 0;
	}

	/**
	 *  Le produit choisi par le client ainsi que sa quantité sont
	 * en parametre. On crée une ligne de commande et on vérifié que la quantité
	 * demandée est disponible Si c’est le cas alors on ajoute à la ligne de
	 * commande. On stocke le produit, la quantité et le prix dans la ligne de
	 * commande En sortie on récupère lc.
	 */
	@Override
	public LigneCommande ajoutProdPanier(Produit pr, int q) {

		LigneCommande lc = new LigneCommande();
		

		if (q <= pr.getQuantite()) {
			
			
			lc.setProduit(pr);
			lc.setQuantite(q);
			lc.setPrix(pr.getPrix() * q);
			
			return lc;
		} else {
			
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.adaming.service.IClientService#sendMail(fr.adaming.model.Client)
	 * La methode ne retourne rien. Elle permet d'envoyer un mail de
	 * confirmation suite à la commande. Le client doit être connecté pour
	 * valider sa commande et ainsi recevoir le mail depuis l'addresse gmail
	 * précisée (+password) La deuxième partie de la methode créé le corps du message du mail
	 * et fait la liaison avec le pdf à ajouter en pièce jointe
	 */
	@Override
	public void sendMail(Client cl) {
		final String username = "projectfunzone@gmail.com";
		final String password = "funzone44";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		ByteArrayOutputStream outputStream = null;

		try {
			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("hello world");

			// now write the PDF content to the output stream
			outputStream = new ByteArrayOutputStream();
			writePdf(outputStream);
			byte[] bytes = outputStream.toByteArray();

			// construct the pdf body part
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("test.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			// create the sender/recipient addresses
			InternetAddress iaSender = new InternetAddress(username);
			InternetAddress iaRecipient = new InternetAddress(cl.getEmail());

			// construct the mime message
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setSender(iaSender);
			mimeMessage.setSubject("FunZone : Votre facture");
			mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
			mimeMessage.setContent(mimeMultipart);

			// send off the email
			Transport.send(mimeMessage);
			
			System.out.println("mail envoyé");
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// clean off
			if (null != outputStream) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception ex) {
				}
			}
		}
	}

	/**
	 * Writes the content of a PDF file (using iText API) to the
	 * {@link OutputStream}.
	 * 
	 * @param outputStream
	 *            {@link OutputStream}.
	 * @throws Exception
	 */
	public void writePdf(OutputStream outputStream) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		document.open();

		document.addTitle("Facture FUN ZONE");
		document.addSubject("Votre facture FunZone");
		document.addKeywords("Commande, Funzone");
		document.addAuthor("FunZone");
		document.addCreator("FunZone");

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("hello!"));
		document.add(paragraph);

		document.close();
	}
}