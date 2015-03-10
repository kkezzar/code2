package dz.gov.mesrs.sii.fve.business.model.dto.bac;


public class CorrespondanceDomaineDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 18 juin 2014  14:24:11
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String codeNiveau1;
	private String libelleNiveau1;
	private String codeNiveau2;
	private String libelleNiveau2;
	private Boolean estClassique;
	private Boolean recrutementNational;
	private String etablissement;
	private String nouveauCode;
	private String ancienCode;
	
	public CorrespondanceDomaineDto() {
	}

	/**
	 * [CorrespondanceDomaineDto.id] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [CorrespondanceDomaineDto.id] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [CorrespondanceDomaineDto.codeNiveau1] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the codeNiveau1
	 */
	public String getCodeNiveau1() {
		return codeNiveau1;
	}

	/**
	 * [CorrespondanceDomaineDto.codeNiveau1] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param codeNiveau1 the codeNiveau1 to set
	 */
	public void setCodeNiveau1(String codeNiveau1) {
		this.codeNiveau1 = codeNiveau1;
	}

	/**
	 * [CorrespondanceDomaineDto.libelleNiveau1] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the libelleNiveau1
	 */
	public String getLibelleNiveau1() {
		return libelleNiveau1;
	}

	/**
	 * [CorrespondanceDomaineDto.libelleNiveau1] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param libelleNiveau1 the libelleNiveau1 to set
	 */
	public void setLibelleNiveau1(String libelleNiveau1) {
		this.libelleNiveau1 = libelleNiveau1;
	}

	/**
	 * [CorrespondanceDomaineDto.codeNiveau2] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the codeNiveau2
	 */
	public String getCodeNiveau2() {
		return codeNiveau2;
	}

	/**
	 * [CorrespondanceDomaineDto.codeNiveau2] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param codeNiveau2 the codeNiveau2 to set
	 */
	public void setCodeNiveau2(String codeNiveau2) {
		this.codeNiveau2 = codeNiveau2;
	}

	/**
	 * [CorrespondanceDomaineDto.libelleNiveau2] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the libelleNiveau2
	 */
	public String getLibelleNiveau2() {
		return libelleNiveau2;
	}

	/**
	 * [CorrespondanceDomaineDto.libelleNiveau2] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param libelleNiveau2 the libelleNiveau2 to set
	 */
	public void setLibelleNiveau2(String libelleNiveau2) {
		this.libelleNiveau2 = libelleNiveau2;
	}

	/**
	 * [CorrespondanceDomaineDto.estClassique] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the estClassique
	 */
	public Boolean getEstClassique() {
		return estClassique;
	}

	/**
	 * [CorrespondanceDomaineDto.estClassique] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param estClassique the estClassique to set
	 */
	public void setEstClassique(Boolean estClassique) {
		this.estClassique = estClassique;
	}

	/**
	 * [CorrespondanceDomaineDto.recrutementNational] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the recrutementNational
	 */
	public Boolean getRecrutementNational() {
		return recrutementNational;
	}

	/**
	 * [CorrespondanceDomaineDto.recrutementNational] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param recrutementNational the recrutementNational to set
	 */
	public void setRecrutementNational(Boolean recrutementNational) {
		this.recrutementNational = recrutementNational;
	}

	/**
	 * [CorrespondanceDomaineDto.etablissment] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the etablissment
	 */
	public String getEtablissement() {
		return etablissement;
	}

	/**
	 * [CorrespondanceDomaineDto.etablissment] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param etablissment the etablissment to set
	 */
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * [CorrespondanceDomaineDto.nouveauCode] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the nouveauCode
	 */
	public String getNouveauCode() {
		return nouveauCode;
	}

	/**
	 * [CorrespondanceDomaineDto.nouveauCode] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param nouveauCode the nouveauCode to set
	 */
	public void setNouveauCode(String nouveauCode) {
		this.nouveauCode = nouveauCode;
	}

	/**
	 * [CorrespondanceDomaineDto.ancienCode] Getter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @return the ancienCode
	 */
	public String getAncienCode() {
		return ancienCode;
	}

	/**
	 * [CorrespondanceDomaineDto.ancienCode] Setter 
	 * @author rlaib on : 18 juin 2014  14:24:04
	 * @param ancienCode the ancienCode to set
	 */
	public void setAncienCode(String ancienCode) {
		this.ancienCode = ancienCode;
	}

	
	
}
