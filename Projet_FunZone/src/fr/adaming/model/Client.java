package fr.adaming.model;


public class Client {

	//Attributs
	private Long idClient;
	private String nomClient;
	private String adresse;
	private String email;
	private String tel;
	private String mdpClient;

	//constructeur
	public Client() {
		super();
	}

	public Client(String nomClient, String adresse, String email, String tel, String mdpClient) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.mdpClient = mdpClient;
	}

	public Client(Long idClient, String nomClient, String adresse, String email, String tel, String mdpClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.mdpClient = mdpClient;
	}

	//getter et setter
	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMdpClient() {
		return mdpClient;
	}

	public void setMdpClient(String mdpClient) {
		this.mdpClient = mdpClient;
	}

}
