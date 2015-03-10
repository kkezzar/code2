package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * 
 * @author BELDI Jamel on : 25 déc. 2014 10:08:06
 */

public class MutationDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 25 déc. 2014 10:12:54
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SituationEntiteDto situationEntiteDto;
	private RefEtablissementDto etabDemandeDto;
	private DossierEmployeDto dossierEmployeDto;
	private RefEtablissementDto etabOrigineDto;
	private Date dateDemande;
	private String objet;
	private Date dateSouhaite;
	private String observation;
	private Date dateResultat;
	private Boolean accepte;
	private Boolean motif;
	private Date dateAccordEtabOrigine;
	private Date dateAccordEtabAccueil;
	private Date dateEffet;
	private Date dateDecision;
	private Boolean necessiteService;

	public MutationDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public RefEtablissementDto getEtabDemandeDto() {
		return etabDemandeDto;
	}

	public void setEtabDemandeDto(RefEtablissementDto etabDemandeDto) {
		this.etabDemandeDto = etabDemandeDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public RefEtablissementDto getEtabOrigineDto() {
		return etabOrigineDto;
	}

	public void setEtabOrigineDto(RefEtablissementDto etabOrigineDto) {
		this.etabOrigineDto = etabOrigineDto;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Date getDateSouhaite() {
		return dateSouhaite;
	}

	public void setDateSouhaite(Date dateSouhaite) {
		this.dateSouhaite = dateSouhaite;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDateResultat() {
		return dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	public Boolean getAccepte() {
		return accepte;
	}

	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}

	public Boolean getMotif() {
		return motif;
	}

	public void setMotif(Boolean motif) {
		this.motif = motif;
	}

	public Date getDateAccordEtabOrigine() {
		return dateAccordEtabOrigine;
	}

	public void setDateAccordEtabOrigine(Date dateAccordEtabOrigine) {
		this.dateAccordEtabOrigine = dateAccordEtabOrigine;
	}

	public Date getDateAccordEtabAccueil() {
		return dateAccordEtabAccueil;
	}

	public void setDateAccordEtabAccueil(Date dateAccordEtabAccueil) {
		this.dateAccordEtabAccueil = dateAccordEtabAccueil;
	}

	public Date getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	public Date getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public Boolean getNecessiteService() {
		return necessiteService;
	}

	public void setNecessiteService(Boolean necessiteService) {
		this.necessiteService = necessiteService;
	}

}
