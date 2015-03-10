package dz.gov.mesrs.sii.referentiel.business.model.dto;


/**
 * @author BELDI Jamel  on : 24 f�vr. 2014  16:58:05
 */
public class RefPlageAdresseDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * @author BELDI Jamel  on : 24 f�vr. 2014  16:57:21
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String nom;
	private String adresseDebut;
	private String adresseFin;
	/********** etablissement****************/
	private Integer idEtablissement;
	

	public RefPlageAdresseDto() {
	}


	/**
	 * [RefPlageAdresseDto.id] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefPlageAdresseDto.id] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [RefPlageAdresseDto.identifiant] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}


	/**
	 * [RefPlageAdresseDto.identifiant] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	/**
	 * [RefPlageAdresseDto.nom] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * [RefPlageAdresseDto.nom] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * [RefPlageAdresseDto.adresseDebut] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @return the adresseDebut
	 */
	public String getAdresseDebut() {
		return adresseDebut;
	}


	/**
	 * [RefPlageAdresseDto.adresseDebut] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @param adresseDebut the adresseDebut to set
	 */
	public void setAdresseDebut(String adresseDebut) {
		this.adresseDebut = adresseDebut;
	}


	/**
	 * [RefPlageAdresseDto.adresseFin] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @return the adresseFin
	 */
	public String getAdresseFin() {
		return adresseFin;
	}


	/**
	 * [RefPlageAdresseDto.adresseFin] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  16:57:47
	 * @param adresseFin the adresseFin to set
	 */
	public void setAdresseFin(String adresseFin) {
		this.adresseFin = adresseFin;
	}


	/**
	 * [RefPlageAdresseDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:34:22
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}


	/**
	 * [RefPlageAdresseDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:34:22
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}


	
	
	

	
}
