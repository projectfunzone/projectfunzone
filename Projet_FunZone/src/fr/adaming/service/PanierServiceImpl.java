package fr.adaming.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
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

			Client clOut = clService.getClientById(cl);
			
			Date dateFacture=new Date();
			String objetMail="Votre Facture Funzone du "+dateFacture;
			String corpsMail="Bonjour Monsieur/Madame "+cl.getNomClient()+", ci-joint votre facture du "+dateFacture+".";
			
			String nomPDF="Facture Funzone";
			String corpsPDF="Bonjour, ci-joint, ci-joint votre facture du "+dateFacture+".\nVotre commande est composé de :\n";
			
			this.email(clOut, objetMail, corpsMail, nomPDF, corpsPDF);

			return 1;
		}

		return 0;
	}

	public void email(Client cl, String objetMail, String corpsMail, String nomPDF, String corpsPDF) {
		final String username = "projectfunzone@gmail.com";
		final String password = "12root34";

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
			textBodyPart.setText(corpsMail);

			// now write the PDF content to the output stream
			outputStream = new ByteArrayOutputStream();
			writePdf(outputStream, corpsPDF);
			byte[] bytes = outputStream.toByteArray();

			// construct the pdf body part
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName(nomPDF+".pdf");

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
			mimeMessage.setSubject(objetMail);
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
	public void writePdf(OutputStream outputStream, String corpsPDF) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		document.open();

		document.addTitle("Facture FUN ZONE");
		document.addSubject("Votre facture FunZone");
		document.addKeywords("Commande, Funzone");
		document.addAuthor("FunZone");
		document.addCreator("FunZone");

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk(corpsPDF));
		document.add(paragraph);

		document.close();
	}

}
