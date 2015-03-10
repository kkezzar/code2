package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<EnfantDto> enfantDtos;
	private List<DistinctionDto> distinctionDtos;
	private List<ConjointDto> conjointDtos;
	private String photo;
	private List<DiplomeEmployeDto> diplomeEmployeDtos;
	private List<StageDto> stageDtos;
	private Boolean titularise;
	private CandidatEmployeDto candidatEmployeDto;
	private SituationEntiteDto situationEntiteDto;
	private List<FinActiviteDto> finActiviteDtos;
	private List<CarriereDto> carriereDtos;
	private List<ChangementPositionDto> positionDtos;
	private Date dateInstallation;
	private CarriereDto carriereCouranteDto;
	private ChangementPositionDto positionCouranteDto;
	private List<HabileteEmployeDto> habileteDtos;
	private List<ConnaissanceEmployeDto> connaissanceDtos;
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

	public List<EnfantDto> getEnfantDtos() {
		return enfantDtos;
	}

	public void setEnfantDtos(List<EnfantDto> enfantDtos) {
		this.enfantDtos = enfantDtos;
	}

	public List<DistinctionDto> getDistinctionDtos() {
		return distinctionDtos;
	}

	public void setDistinctionDtos(List<DistinctionDto> distinctionDtos) {
		this.distinctionDtos = distinctionDtos;
	}

	public List<ConjointDto> getConjointDtos() {
		return conjointDtos;
	}

	public void setConjointDtos(List<ConjointDto> conjointDtos) {
		this.conjointDtos = conjointDtos;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<DiplomeEmployeDto> getDiplomeEmployeDtos() {
		return diplomeEmployeDtos;
	}

	public void setDiplomeEmployeDtos(List<DiplomeEmployeDto> diplomeEmployeDtos) {
		this.diplomeEmployeDtos = diplomeEmployeDtos;
	}

	public List<StageDto> getStageDtos() {
		return stageDtos;
	}

	public void setStageDtos(List<StageDto> stageDtos) {
		this.stageDtos = stageDtos;
	}

	public Boolean getTitularise() {
		return titularise;
	}

	public void setTitularise(Boolean titularise) {
		this.titularise = titularise;
	}

	public CandidatEmployeDto getCandidatEmployeDto() {
		return candidatEmployeDto;
	}

	public void setCandidatEmployeDto(CandidatEmployeDto candidatEmployeDto) {
		this.candidatEmployeDto = candidatEmployeDto;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public List<FinActiviteDto> getFinActiviteDtos() {
		if (finActiviteDtos == null) {
			finActiviteDtos = new ArrayList<>();
		}
		return finActiviteDtos;
	}

	public void setFinActiviteDtos(List<FinActiviteDto> finActiviteDtos) {
		this.finActiviteDtos = finActiviteDtos;
	}

	public void addFinActiviteDto(FinActiviteDto finActiviteDto) {
		getFinActiviteDtos().add(finActiviteDto);
		finActiviteDto.setEmploye(this);
	}

	public void removeFinActiviteDto(FinActiviteDto finActiviteDto) {
		getFinActiviteDtos().remove(finActiviteDto);
		finActiviteDto.setEmploye(null);
	}

	public List<CarriereDto> getCarriereDtos() {
		return carriereDtos;
	}

	public void setCarriereDtos(List<CarriereDto> carriereDtos) {
		this.carriereDtos = carriereDtos;
	}
	
	public List<ChangementPositionDto> getPositionDtos() {
		return positionDtos;
	}
	
	public void setPositionDtos(List<ChangementPositionDto> positionDtos) {
		this.positionDtos = positionDtos;
	}

	public Date getDateInstallation() {
		return dateInstallation;
	}

	public void setDateInstallation(Date dateInstallation) {
		this.dateInstallation = dateInstallation;
	}

	public CarriereDto getCarriereCouranteDto() {
		return carriereCouranteDto;
	}

	public void setCarriereCouranteDto(CarriereDto carriereCouranteDto) {
		this.carriereCouranteDto = carriereCouranteDto;
	}
	
	public ChangementPositionDto getPositionCouranteDto() {
		return positionCouranteDto;
	}
	
	public void setPositionCouranteDto(ChangementPositionDto positionCouranteDto) {
		this.positionCouranteDto = positionCouranteDto;
	}
	
	public List<HabileteEmployeDto> getHabileteDtos() {
		if (habileteDtos == null) {
			habileteDtos = new ArrayList<>();
		}
		return habileteDtos;
	}

	public void setHabileteDtos(List<HabileteEmployeDto> habileteDtos) {
		this.habileteDtos = habileteDtos;
	}

	public void addHabileteDto(HabileteEmployeDto habileteEmployeDto) {
		this.getHabileteDtos().add(habileteEmployeDto);
		habileteEmployeDto.setEmployeDto(this);
	}

	public void removeHabileteDto(HabileteEmployeDto habileteEmployeDto) {
		this.getHabileteDtos().remove(habileteEmployeDto);
		habileteEmployeDto.setEmployeDto(null);
	}

	public List<ConnaissanceEmployeDto> getConnaissanceDtos() {
		if (this.connaissanceDtos == null) {
			connaissanceDtos = new ArrayList<>();
		}
		return connaissanceDtos;
	}

	public void setConnaissanceDtos(List<ConnaissanceEmployeDto> connaissanceDtos) {
		this.connaissanceDtos = connaissanceDtos;
	}

	public void addConnaissanceDto(ConnaissanceEmployeDto connaissanceEmployeDto) {
		this.getConnaissanceDtos().add(connaissanceEmployeDto);
		connaissanceEmployeDto.setEmployeDto(this);
	}

	public void removeConnaissanceDto(ConnaissanceEmployeDto connaissanceEmployeDto) {
		this.getConnaissanceDtos().remove(connaissanceEmployeDto);
		connaissanceEmployeDto.setEmployeDto(null);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidatEmployeDto == null) ? 0 : candidatEmployeDto.hashCode());
		result = prime * result + ((carriereCouranteDto == null) ? 0 : carriereCouranteDto.hashCode());
		result = prime * result + ((carriereDtos == null) ? 0 : carriereDtos.hashCode());
		result = prime * result + ((conjointDtos == null) ? 0 : conjointDtos.hashCode());
		result = prime * result + ((dateAffiliation == null) ? 0 : dateAffiliation.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((dateInstallation == null) ? 0 : dateInstallation.hashCode());
		result = prime * result + ((dateObtention == null) ? 0 : dateObtention.hashCode());
		result = prime * result + ((dateTitularisation == null) ? 0 : dateTitularisation.hashCode());
		result = prime * result + ((diplomeEmployeDtos == null) ? 0 : diplomeEmployeDtos.hashCode());
		result = prime * result + ((distinction == null) ? 0 : distinction.hashCode());
		result = prime * result + ((distinctionDtos == null) ? 0 : distinctionDtos.hashCode());
		result = prime * result + ((enfantDtos == null) ? 0 : enfantDtos.hashCode());
		result = prime * result + ((finActiviteDtos == null) ? 0 : finActiviteDtos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricule == null) ? 0 : matricule.hashCode());
		result = prime * result + ((nomenclatureByTypeCompteDto == null) ? 0 : nomenclatureByTypeCompteDto.hashCode());
		result = prime * result + ((nomenclatureByTypePermisDto == null) ? 0 : nomenclatureByTypePermisDto.hashCode());
		result = prime * result + ((numeroCompte == null) ? 0 : numeroCompte.hashCode());
		result = prime * result + ((numeross == null) ? 0 : numeross.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((refEtablissementDto == null) ? 0 : refEtablissementDto.hashCode());
		result = prime * result + ((refIndividuDto == null) ? 0 : refIndividuDto.hashCode());
		result = prime * result + ((refStructureDto == null) ? 0 : refStructureDto.hashCode());
		result = prime * result + ((situationEntiteDto == null) ? 0 : situationEntiteDto.hashCode());
		result = prime * result + ((stageDtos == null) ? 0 : stageDtos.hashCode());
		result = prime * result + ((titularise == null) ? 0 : titularise.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DossierEmployeDto other = (DossierEmployeDto) obj;
		if (candidatEmployeDto == null) {
			if (other.candidatEmployeDto != null)
				return false;
		} else if (!candidatEmployeDto.equals(other.candidatEmployeDto))
			return false;
		if (carriereCouranteDto == null) {
			if (other.carriereCouranteDto != null)
				return false;
		} else if (!carriereCouranteDto.equals(other.carriereCouranteDto))
			return false;
		if (carriereDtos == null) {
			if (other.carriereDtos != null)
				return false;
		} else if (!carriereDtos.equals(other.carriereDtos))
			return false;
		if (conjointDtos == null) {
			if (other.conjointDtos != null)
				return false;
		} else if (!conjointDtos.equals(other.conjointDtos))
			return false;
		if (dateAffiliation == null) {
			if (other.dateAffiliation != null)
				return false;
		} else if (!dateAffiliation.equals(other.dateAffiliation))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateInstallation == null) {
			if (other.dateInstallation != null)
				return false;
		} else if (!dateInstallation.equals(other.dateInstallation))
			return false;
		if (dateObtention == null) {
			if (other.dateObtention != null)
				return false;
		} else if (!dateObtention.equals(other.dateObtention))
			return false;
		if (dateTitularisation == null) {
			if (other.dateTitularisation != null)
				return false;
		} else if (!dateTitularisation.equals(other.dateTitularisation))
			return false;
		if (diplomeEmployeDtos == null) {
			if (other.diplomeEmployeDtos != null)
				return false;
		} else if (!diplomeEmployeDtos.equals(other.diplomeEmployeDtos))
			return false;
		if (distinction == null) {
			if (other.distinction != null)
				return false;
		} else if (!distinction.equals(other.distinction))
			return false;
		if (distinctionDtos == null) {
			if (other.distinctionDtos != null)
				return false;
		} else if (!distinctionDtos.equals(other.distinctionDtos))
			return false;
		if (enfantDtos == null) {
			if (other.enfantDtos != null)
				return false;
		} else if (!enfantDtos.equals(other.enfantDtos))
			return false;
		if (finActiviteDtos == null) {
			if (other.finActiviteDtos != null)
				return false;
		} else if (!finActiviteDtos.equals(other.finActiviteDtos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricule == null) {
			if (other.matricule != null)
				return false;
		} else if (!matricule.equals(other.matricule))
			return false;
		if (nomenclatureByTypeCompteDto == null) {
			if (other.nomenclatureByTypeCompteDto != null)
				return false;
		} else if (!nomenclatureByTypeCompteDto.equals(other.nomenclatureByTypeCompteDto))
			return false;
		if (nomenclatureByTypePermisDto == null) {
			if (other.nomenclatureByTypePermisDto != null)
				return false;
		} else if (!nomenclatureByTypePermisDto.equals(other.nomenclatureByTypePermisDto))
			return false;
		if (numeroCompte == null) {
			if (other.numeroCompte != null)
				return false;
		} else if (!numeroCompte.equals(other.numeroCompte))
			return false;
		if (numeross == null) {
			if (other.numeross != null)
				return false;
		} else if (!numeross.equals(other.numeross))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (refEtablissementDto == null) {
			if (other.refEtablissementDto != null)
				return false;
		} else if (!refEtablissementDto.equals(other.refEtablissementDto))
			return false;
		if (refIndividuDto == null) {
			if (other.refIndividuDto != null)
				return false;
		} else if (!refIndividuDto.equals(other.refIndividuDto))
			return false;
		if (refStructureDto == null) {
			if (other.refStructureDto != null)
				return false;
		} else if (!refStructureDto.equals(other.refStructureDto))
			return false;
		if (situationEntiteDto == null) {
			if (other.situationEntiteDto != null)
				return false;
		} else if (!situationEntiteDto.equals(other.situationEntiteDto))
			return false;
		if (stageDtos == null) {
			if (other.stageDtos != null)
				return false;
		} else if (!stageDtos.equals(other.stageDtos))
			return false;
		if (titularise == null) {
			if (other.titularise != null)
				return false;
		} else if (!titularise.equals(other.titularise))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DossierEmployeDto [id=" + id + ", refIndividuDto=" + refIndividuDto + ", refEtablissementDto="
				+ refEtablissementDto + ", refStructureDto=" + refStructureDto + ", nomenclatureByTypePermisDto="
				+ nomenclatureByTypePermisDto + ", nomenclatureByTypeCompteDto=" + nomenclatureByTypeCompteDto
				+ ", matricule=" + matricule + ", numeross=" + numeross + ", dateAffiliation=" + dateAffiliation
				+ ", numeroCompte=" + numeroCompte + ", dateObtention=" + dateObtention + ", distinction="
				+ distinction + ", dateTitularisation=" + dateTitularisation + ", dateCreation=" + dateCreation
				+ ", enfantDtos=" + enfantDtos + ", distinctionDtos=" + distinctionDtos + ", conjointDtos="
				+ conjointDtos + ", photo=" + photo + ", diplomeEmployeDtos=" + diplomeEmployeDtos + ", stageDtos="
				+ stageDtos + ", titularise=" + titularise + ", candidatEmployeDto=" + candidatEmployeDto
				+ ", situationEntiteDto=" + situationEntiteDto + ", finActiviteDtos=" + finActiviteDtos
				+ ", carriereDtos=" + carriereDtos + ", dateInstallation=" + dateInstallation
				+ ", carriereCouranteDto=" + carriereCouranteDto + "]";
	}

}
