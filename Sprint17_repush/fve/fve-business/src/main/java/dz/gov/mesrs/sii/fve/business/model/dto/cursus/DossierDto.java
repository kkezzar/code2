package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;

public class DossierDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 20 mai 2014 18:07:06
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	// private SituationEntite situationEntite;
	private String typeDossier;
	private Date dateCreation;

	private Integer situationId;
	private String situationLibelleFr;
	private String situationLibelleAr;

	// private DossierInscriptionAdminDto dossierInscriptionAdmin;
	//
	// private DossierEtudiantDto dossierEtudiant;
	// private PieceConstitutiveDto pieceConstitutive;

	public DossierDto() {
	}

	public DossierDto(int id) {
		this.id = id;
	}

	/**
	 * [DossierDto.id] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:48:43
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [DossierDto.id] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:48:43
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [DossierDto.typeDossier] Getter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:48:43
	 * @return the typeDossier
	 */
	public String getTypeDossier() {
		return typeDossier;
	}

	/**
	 * [DossierDto.typeDossier] Setter
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 08:48:43
	 * @param typeDossier
	 *            the typeDossier to set
	 */
	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	/**
	 * [DossierDto.dateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:26:15
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [DossierDto.dateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:26:15
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Integer getSituationId() {
		return situationId;
	}

	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}

	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}

	public String getSituationLibelleAr() {
		return situationLibelleAr;
	}

	public void setSituationLibelleAr(String situationLibelleAr) {
		this.situationLibelleAr = situationLibelleAr;
	}

}
