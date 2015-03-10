package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class DossierEmployeDto implements Serializable {

	private static final long serialVersionUID = -7943086012529223367L;
	private Long id;
	private RefIndividuDto refIndividuDto;
	private RefEtablissementDto refEtablissementDto;
	private RefStructureDto refStructureDto;
	private NomenclatureDto nomenclatureByTypePermisDto;
	private NomenclatureDto nomenclatureByTypeCompteDto;
	private String matricule;
	private String numeross;
	private Date dateAffiliation;
	private String numeroCompte;
	private Date dateObtention;
	private Integer distinction;
	private Date dateTitularisation;
	private Date dateCreation;

	private String photo;

	private Boolean titularise;

	private SituationEntiteDto situationEntiteDto;

	private Date dateInstallation;
	private NomenclatureDto niveauCompetenceDto;
	private NomenclatureDto niveauQualificationDto;
	private String affectationCourante;

	public DossierEmployeDto() {
		refIndividuDto = new RefIndividuDto();
		refEtablissementDto = new RefEtablissementDto();
		refStructureDto = new RefStructureDto();
		nomenclatureByTypePermisDto = new NomenclatureDto();
		nomenclatureByTypeCompteDto = new NomenclatureDto();
	}

	public DossierEmployeDto(Long id) {
		this.id = id;
	}

	public DossierEmployeDto(String matricule, RefIndividuDto refIndividuDto, RefEtablissementDto refEtablissementDto,
			RefStructureDto refStructureDto) {
		this.matricule = matricule;
		this.refIndividuDto = refIndividuDto;
		this.refEtablissementDto = refEtablissementDto;
		this.refStructureDto = refStructureDto;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNumeross() {
		return numeross;
	}

	public void setNumeross(String numeross) {
		this.numeross = numeross;
	}

	public Date getDateAffiliation() {
		return dateAffiliation;
	}

	public void setDateAffiliation(Date dateAffiliation) {
		this.dateAffiliation = dateAffiliation;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public Date getDateObtention() {
		return dateObtention;
	}

	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	public Integer getDistinction() {
		return distinction;
	}

	public void setDistinction(Integer distinction) {
		this.distinction = distinction;
	}

	public Date getDateTitularisation() {
		return dateTitularisation;
	}

	public void setDateTitularisation(Date dateTitularisation) {
		this.dateTitularisation = dateTitularisation;
	}

	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
	}

	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	public NomenclatureDto getNomenclatureByTypePermisDto() {
		return nomenclatureByTypePermisDto;
	}

	public void setNomenclatureByTypePermisDto(NomenclatureDto nomenclatureByTypePermisDto) {
		this.nomenclatureByTypePermisDto = nomenclatureByTypePermisDto;
	}

	public NomenclatureDto getNomenclatureByTypeCompteDto() {
		return nomenclatureByTypeCompteDto;
	}

	public void setNomenclatureByTypeCompteDto(NomenclatureDto nomenclatureByTypeCompteDto) {
		this.nomenclatureByTypeCompteDto = nomenclatureByTypeCompteDto;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getTitularise() {
		return titularise;
	}

	public void setTitularise(Boolean titularise) {
		this.titularise = titularise;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public Date getDateInstallation() {
		return dateInstallation;
	}

	public void setDateInstallation(Date dateInstallation) {
		this.dateInstallation = dateInstallation;
	}

	public NomenclatureDto getNiveauCompetenceDto() {
		return niveauCompetenceDto;
	}

	public void setNiveauCompetenceDto(NomenclatureDto niveauCompetenceDto) {
		this.niveauCompetenceDto = niveauCompetenceDto;
	}

	public NomenclatureDto getNiveauQualificationDto() {
		return niveauQualificationDto;
	}

	public void setNiveauQualificationDto(NomenclatureDto niveauQualificationDto) {
		this.niveauQualificationDto = niveauQualificationDto;
	}

	public String getAffectationCourante() {
		return affectationCourante;
	}

	public void setAffectationCourante(String affectationCourante) {
		this.affectationCourante = affectationCourante;
	}
}