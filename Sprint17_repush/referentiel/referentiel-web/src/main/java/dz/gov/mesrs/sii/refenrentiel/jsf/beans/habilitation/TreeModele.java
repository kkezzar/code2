/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.domaine.TreeModele.java] 
 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:11:05
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation;


/**
 * @author MAKERRI Sofiane  on : 24 f�vr. 2014  16:11:05
 */
public class TreeModele {
	
	private Integer id;
	private Integer idDomaine;
	private String identifiantDomaine;
	private String identifiant;
	private String nom;
	private Integer rang;
	private String url;
	private boolean parent;
	private Integer idModule;
	private String idfModule;
	private String idfFonction;
	

	/**
	 * [TreeModele.TreeModele()] Constructor
	 * @author MAKERRI Sofiane  on : 24 f�vr. 2014  16:11:05	
	 */
	public TreeModele() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * [TreeModele.TreeModele()] Constructor
	 * @author MAKERRI Sofiane  on : 24 f�vr. 2014  16:17:56
	 * @param id
	 * @param identifiant
	 * @param nom
	 * @param rang
	 * @param parent	
	 */
	public TreeModele(int id, String identifiant, String nom, int rang, String identifiantDomaine, boolean parent) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.rang = rang;
		this.parent = parent;
		this.identifiantDomaine = identifiantDomaine;
	}
	
	/**
	 * [TreeModele.TreeModele()] Constructor
	 * @author MAKERRI Sofiane  on : 19 mars 2014  14:50:10
	 * @param id
	 * @param identifiant
	 * @param nom
	 * @param url
	 * @param rang
	 * @param idfModule
	 * @param parent	
	 */
	public TreeModele(int id, String identifiant, String nom, String url, int rang, String idfModule, boolean parent) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.rang = rang;
		this.idfModule = idfModule;
		this.parent = parent;
		this.url = url;
	}
	
	public TreeModele(String identifiant, String nom, String url, int rang, String idfFonction, int id, boolean parent) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.url = url; 
		this.rang = rang;
		this.idfFonction = idfFonction;
		this.parent = parent;
	}


	/**
	 * [TreeModele.id] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [TreeModele.id] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [TreeModele.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}


	/**
	 * [TreeModele.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}


	/**
	 * [TreeModele.identifiantDomaine] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the identifiantDomaine
	 */
	public String getIdentifiantDomaine() {
		return identifiantDomaine;
	}


	/**
	 * [TreeModele.identifiantDomaine] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param identifiantDomaine the identifiantDomaine to set
	 */
	public void setIdentifiantDomaine(String identifiantDomaine) {
		this.identifiantDomaine = identifiantDomaine;
	}


	/**
	 * [TreeModele.identifiant] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}


	/**
	 * [TreeModele.identifiant] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	/**
	 * [TreeModele.nom] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * [TreeModele.nom] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * [TreeModele.rang] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}


	/**
	 * [TreeModele.rang] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}


	/**
	 * [TreeModele.parent] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @return the parent
	 */
	public boolean isParent() {
		return parent;
	}


	/**
	 * [TreeModele.parent] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  16:12:14
	 * @param parent the parent to set
	 */
	public void setParent(boolean parent) {
		this.parent = parent;
	}

	/**
	 * [TreeModele.url] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  16:35:48
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * [TreeModele.url] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  16:35:48
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * [TreeModele.idModule] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  17:27:55
	 * @return the idModule
	 */
	public Integer getIdModule() {
		return idModule;
	}

	/**
	 * [TreeModele.idModule] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  17:27:55
	 * @param idModule the idModule to set
	 */
	public void setIdModule(Integer idModule) {
		this.idModule = idModule;
	}

	/**
	 * [TreeModele.idfModule] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  17:39:11
	 * @return the idfModule
	 */
	public String getIdfModule() {
		return idfModule;
	}

	/**
	 * [TreeModele.idfModule] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  17:39:11
	 * @param idfModule the idfModule to set
	 */
	public void setIdfModule(String idfModule) {
		this.idfModule = idfModule;
	}

	/**
	 * [TreeModele.idfFonction] Getter 
	 * @author MAKERRI Sofiane on : 19 mars 2014  14:54:25
	 * @return the idfFonction
	 */
	public String getIdfFonction() {
		return idfFonction;
	}

	/**
	 * [TreeModele.idfFonction] Setter 
	 * @author MAKERRI Sofiane on : 19 mars 2014  14:54:25
	 * @param idfFonction the idfFonction to set
	 */
	public void setIdfFonction(String idfFonction) {
		this.idfFonction = idfFonction;
	}

	
	

}
