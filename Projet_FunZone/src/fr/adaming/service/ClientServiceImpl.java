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
 * Class qui implemente l'interface IClientService pour r�cup�rer les m�thodes
 * du CRUD et d�velopper les m�thodes m�thiers
 * 
 * @author Camille
 *
 */
@Stateful
public class ClientServiceImpl implements IClientService {

	/**
	 * Injection d�pendance de Dao
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Client> getAllClient() {

		return clDao.getAllClient();
	}

	/**
	 * Permet de r�cup�rer un client par son id.
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

		// on compare l'id du client en sortie, car si il est diff�rent de 0,
		// c'est que le client a �t� cr��
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
	 * On r�cup�re un client via son email pour ensuite comparer le mdp du
	 * client r�cup�rer (du coup dans clOut) avec le mdp renseign� sur la vue
	 * (dans cl). Retourne un int avec des valeurs diff�rentes selon la
	 * situation. 0 si le client n'existe pas, 1 si le client existe et le mdp
	 * est bon 2 si le mot de passe renseign� ne correspond pas au mdp dans la
	 * base de donn�e
	 */
	@Override
	public int connectionClient(Client cl) {
		// on r�cup�re une liste avec normalement 1 client qui correspond au
		// mail unique
		List<Client> listeRecupMail = clDao.getClientByIdNom(cl);
		// on verifie qu'il n'y a qu'un client avec cet adresse mail, car sinon,
		// cela veut dire qu'il y a 2 comptes clients avec la m�me adresse mail
		if (listeRecupMail.size() == 1) {
			// On r�cup�re le client dans la liste dans un clOut pour ne pas
			// �craser le mdp rentr� dans cl
			Client clOut = listeRecupMail.get(0);

			// On compare les 2 mots de passe, s'ils sont diff�rents, c'est que
			// ce n'est pas le bon mdp rentr�
			if (clOut.getMdpClient().contentEquals(cl.getMdpClient())) {
				return 1;
			}

			return 2;

		}
		return 0;
	}

	/**
	 *  Le produit choisi par le client ainsi que sa quantit� sont
	 * en parametre. On cr�e une ligne de commande et on v�rifi� que la quantit�
	 * demand�e est disponible Si c�est le cas alors on ajoute � la ligne de
	 * commande. On stocke le produit, la quantit� et le prix dans la ligne de
	 * commande En sortie on r�cup�re lc.
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
	 * confirmation suite � la commande. Le client doit �tre connect� pour
	 * valider sa commande et ainsi recevoir le mail depuis l'addresse gmail
	 * pr�cis�e (+password) La deuxi�me partie de la methode cr�� le corps du message du mail
	 * et fait la liaison avec le pdf � ajouter en pi�ce jointe
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
			
			System.out.println("mail envoy�");
		
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