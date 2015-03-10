package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class InscriptionSessionFormationDto implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SessionFormationDto sessionFormationDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDemande;
	private Date dateInscription;
	private Boolean resultat;
	private String motif;
	private Boolean present;

	public InscriptionSessionFormationDto() {
	}

	public InscriptionSessionFormationDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SessionFormationDto getSessionFormationDto() {
		return sessionFormationDto;
	}

	public void setSessionFormationDto(SessionFormationDto sessionFormationDto) {
		this.sessionFormationDto = sessionFormationDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Boolean getResultat() {
		return resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Boolean getPresent() {
		return present;
	}

	public void setPresent(Boolean present) {
		this.present = present;
	}

	

	

}
