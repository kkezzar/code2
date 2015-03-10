package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class ConcoursDto implements Serializable {

    private static final long serialVersionUID = 3048122528678906718L;
    private Long id;
    private String identifiant;
    private String intituleLatin;
    private String intituleArabe;
    private Date dateDebut;
    private Date dateFin;
    private Boolean national;
    private Date datePublicationConcours;
    private Date datePublicationResultats;
    private Date datePublicationListeComplementaire;
    private Date dateLimite;
    private Date dateCreation;
    private Integer noteBase;
    private Integer typeConcoursId;
    private String typeConcoursCode;
    private Integer anneeAcademiqueId;
    private String libelleAnneeAcademique1;
    private String libelleAnneeAcademique2;
    private String libelleEtablissementLatin;
    private String libelleDomaine;
    private Integer etablissementId;
    private Integer domaineId;
    private Integer situationId;
    private Integer filiereId;
    private Integer specialiteId;
    private Integer structureId;
    private List<EtablissementAdmissionDto> etablissementAdmissionDtos;
    private List<ExamenConcoursDto> examenConcoursDtos;
    private List<PieceConcoursDto> pieceConstitutiveDtos;
    private RefFiliereLmdDto filiereLmdDto;
    private RefSpecialiteLmdDto specialiteLmdDto;
    private OuvertureOffreFormationDto ouvertureOffreFormationDto;
    private RefStructureDto refStructureDto;
    private SituationEntiteDto situationEntiteDto;

    public ConcoursDto() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getIdentifiant() {
	return identifiant;
    }

    public void setIdentifiant(String identifiant) {
	this.identifiant = identifiant;
    }

    public String getIntituleLatin() {
	return intituleLatin;
    }

    public void setIntituleLatin(String intituleLatin) {
	this.intituleLatin = intituleLatin;
    }

    public String getIntituleArabe() {
	return intituleArabe;
    }

    public void setIntituleArabe(String intituleArabe) {
	this.intituleArabe = intituleArabe;
    }

    public Date getDateDebut() {
	return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
	return dateFin;
    }

    public void setDateFin(Date dateFin) {
	this.dateFin = dateFin;
    }

    public Boolean getNational() {
	return national;
    }

    public void setNational(Boolean national) {
	this.national = national;
    }

    public Date getDatePublicationConcours() {
	return datePublicationConcours;
    }

    public void setDatePublicationConcours(Date datePublicationConcours) {
	this.datePublicationConcours = datePublicationConcours;
    }

    public Date getDatePublicationResultats() {
	return datePublicationResultats;
    }

    public void setDatePublicationResultats(Date datePublicationResultats) {
	this.datePublicationResultats = datePublicationResultats;
    }

    public Date getDatePublicationListeComplementaire() {
	return datePublicationListeComplementaire;
    }

    public void setDatePublicationListeComplementaire(Date datePublicationListeComplementaire) {
	this.datePublicationListeComplementaire = datePublicationListeComplementaire;
    }

    public Date getDateLimite() {
	return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
	this.dateLimite = dateLimite;
    }

    public Integer getNoteBase() {
	return noteBase;
    }

    public void setNoteBase(Integer noteBase) {
	this.noteBase = noteBase;
    }

    public Integer getTypeConcoursId() {
	return typeConcoursId;
    }

    public void setTypeConcoursId(Integer typeConcoursId) {
	this.typeConcoursId = typeConcoursId;
    }

    public String getTypeConcoursCode() {
	return typeConcoursCode;
    }

    public void setTypeConcoursCode(String typeConcoursCode) {
	this.typeConcoursCode = typeConcoursCode;
    }

    public Integer getAnneeAcademiqueId() {
	return anneeAcademiqueId;
    }

    public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
	this.anneeAcademiqueId = anneeAcademiqueId;
    }

    public Integer getEtablissementId() {
	return etablissementId;
    }

    public void setEtablissementId(Integer etablissementId) {
	this.etablissementId = etablissementId;
    }

    public Integer getDomaineId() {
	return domaineId;
    }

    public void setDomaineId(Integer domaineId) {
	this.domaineId = domaineId;
    }

    public Integer getSituationId() {
	return situationId;
    }

    public void setSituationId(Integer situationId) {
	this.situationId = situationId;
    }

    public List<EtablissementAdmissionDto> getEtablissementAdmissionDtos() {
	return etablissementAdmissionDtos;
    }

    public void setEtablissementAdmissionDtos(List<EtablissementAdmissionDto> etablissementAdmissionDtos) {
	this.etablissementAdmissionDtos = etablissementAdmissionDtos;
    }

    public void addEtablissementAdmissionDto(EtablissementAdmissionDto etablissementAdmissionDto) {
	if (this.etablissementAdmissionDtos == null) {
	    this.etablissementAdmissionDtos = new ArrayList<>();
	}
	// etablissementAdmissionDto.setConcoursId(this.id);
	etablissementAdmissionDto.setConcoursDto(this);
	this.etablissementAdmissionDtos.add(etablissementAdmissionDto);
    }

    public void removeEtablissementAdmissionDto(EtablissementAdmissionDto etablissementAdmissionDto) {
	if (this.etablissementAdmissionDtos == null
		|| !this.etablissementAdmissionDtos.contains(etablissementAdmissionDto)) {
	    return;
	} else {
	    this.etablissementAdmissionDtos.remove(etablissementAdmissionDto);
	    etablissementAdmissionDto.setConcoursId(null);
	}

    }

    public List<ExamenConcoursDto> getExamenConcoursDtos() {
	return examenConcoursDtos;
    }

    public void setExamenConcoursDtos(List<ExamenConcoursDto> examenConcoursDtos) {
	this.examenConcoursDtos = examenConcoursDtos;
    }

    public void addExamenConcoursDto(ExamenConcoursDto examenConcoursDto) {
	if (this.examenConcoursDtos == null) {
	    this.examenConcoursDtos = new ArrayList<>();
	}
	examenConcoursDto.setConcoursDto(this);
	this.examenConcoursDtos.add(examenConcoursDto);
    }

    public void removeExamenConcoursDto(ExamenConcoursDto examenConcoursDto) {
	if (this.examenConcoursDtos == null || !this.examenConcoursDtos.contains(examenConcoursDto)) {
	    return;
	} else {
	    this.examenConcoursDtos.remove(examenConcoursDto);
	    examenConcoursDto.setConcoursId(null);
	}

    }

    public List<PieceConcoursDto> getPieceConstitutiveDtos() {
	return pieceConstitutiveDtos;
    }

    public void setPieceConstitutiveDtos(List<PieceConcoursDto> pieceConstitutiveDtos) {
	this.pieceConstitutiveDtos = pieceConstitutiveDtos;
    }

    public void addPieceConstitutive(PieceConcoursDto dto) {
	if (this.pieceConstitutiveDtos == null) {
	    this.pieceConstitutiveDtos = new ArrayList<>();
	}
	dto.setConcoursDto(this);
	this.pieceConstitutiveDtos.add(dto);
	dto.setRang(this.pieceConstitutiveDtos.indexOf(dto)+1);
    }

    public void removePieceConstitutive(PieceConcoursDto dto) {
	if (this.pieceConstitutiveDtos == null || !this.pieceConstitutiveDtos.contains(dto)) {
	    return;
	}
	dto.setConcoursId(null);
	this.pieceConstitutiveDtos.remove(dto);
    }

    public String getLibelleEtablissementLatin() {
	return libelleEtablissementLatin;
    }

    public void setLibelleEtablissementLatin(String libelleEtablissementLatin) {
	this.libelleEtablissementLatin = libelleEtablissementLatin;
    }

    public String getLibelleAnneeAcademique1() {
	return libelleAnneeAcademique1;
    }

    public void setLibelleAnneeAcademique1(String libelleAnneeAcademique1) {
	this.libelleAnneeAcademique1 = libelleAnneeAcademique1;
    }

    public String getLibelleAnneeAcademique2() {
	return libelleAnneeAcademique2;
    }

    public void setLibelleAnneeAcademique2(String libelleAnneeAcademique2) {
	this.libelleAnneeAcademique2 = libelleAnneeAcademique2;
    }

    public RefFiliereLmdDto getFiliereLmdDto() {
	return filiereLmdDto;
    }

    public void setFiliereLmdDto(RefFiliereLmdDto filiereLmdDto) {
	this.filiereLmdDto = filiereLmdDto;
    }

    public RefSpecialiteLmdDto getSpecialiteLmdDto() {
	return specialiteLmdDto;
    }

    public void setSpecialiteLmdDto(RefSpecialiteLmdDto specialiteLmdDto) {
	this.specialiteLmdDto = specialiteLmdDto;
    }

    public OuvertureOffreFormationDto getOuvertureOffreFormationDto() {
	return ouvertureOffreFormationDto;
    }

    public void setOuvertureOffreFormationDto(OuvertureOffreFormationDto ouvertureOffreFormationDto) {
	this.ouvertureOffreFormationDto = ouvertureOffreFormationDto;
    }

    public RefStructureDto getRefStructureDto() {
	return refStructureDto;
    }

    public void setRefStructureDto(RefStructureDto refStructureDto) {
	this.refStructureDto = refStructureDto;
    }

    public Integer getFiliereId() {
	return filiereId;
    }

    public void setFiliereId(Integer filiereId) {
	this.filiereId = filiereId;
    }

    public Integer getSpecialiteId() {
	return specialiteId;
    }

    public void setSpecialiteId(Integer specialiteId) {
	this.specialiteId = specialiteId;
    }

    public Integer getStructureId() {
	return structureId;
    }

    public void setStructureId(Integer structureId) {
	this.structureId = structureId;
    }

    public String getLibelleDomaine() {
	return libelleDomaine;
    }

    public void setLibelleDomaine(String libelleDomaine) {
	this.libelleDomaine = libelleDomaine;
    }

    public SituationEntiteDto getSituationEntiteDto() {
	return situationEntiteDto;
    }

    public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
	this.situationEntiteDto = situationEntiteDto;
    }

    public Date getDateCreation() {
	return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((anneeAcademiqueId == null) ? 0 : anneeAcademiqueId.hashCode());
	result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
	result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
	result = prime * result + ((dateLimite == null) ? 0 : dateLimite.hashCode());
	result = prime * result + ((datePublicationConcours == null) ? 0 : datePublicationConcours.hashCode());
	result = prime * result
		+ ((datePublicationListeComplementaire == null) ? 0 : datePublicationListeComplementaire.hashCode());
	result = prime * result + ((datePublicationResultats == null) ? 0 : datePublicationResultats.hashCode());
	result = prime * result + ((domaineId == null) ? 0 : domaineId.hashCode());
	result = prime * result + ((etablissementAdmissionDtos == null) ? 0 : etablissementAdmissionDtos.hashCode());
	result = prime * result + ((etablissementId == null) ? 0 : etablissementId.hashCode());
	result = prime * result + ((examenConcoursDtos == null) ? 0 : examenConcoursDtos.hashCode());
	result = prime * result + ((filiereId == null) ? 0 : filiereId.hashCode());
	result = prime * result + ((filiereLmdDto == null) ? 0 : filiereLmdDto.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((identifiant == null) ? 0 : identifiant.hashCode());
	result = prime * result + ((intituleArabe == null) ? 0 : intituleArabe.hashCode());
	result = prime * result + ((intituleLatin == null) ? 0 : intituleLatin.hashCode());
	result = prime * result + ((libelleAnneeAcademique1 == null) ? 0 : libelleAnneeAcademique1.hashCode());
	result = prime * result + ((libelleAnneeAcademique2 == null) ? 0 : libelleAnneeAcademique2.hashCode());
	result = prime * result + ((libelleEtablissementLatin == null) ? 0 : libelleEtablissementLatin.hashCode());
	result = prime * result + ((national == null) ? 0 : national.hashCode());
	result = prime * result + ((noteBase == null) ? 0 : noteBase.hashCode());
	result = prime * result + ((ouvertureOffreFormationDto == null) ? 0 : ouvertureOffreFormationDto.hashCode());
	result = prime * result + ((refStructureDto == null) ? 0 : refStructureDto.hashCode());
	result = prime * result + ((situationId == null) ? 0 : situationId.hashCode());
	result = prime * result + ((specialiteId == null) ? 0 : specialiteId.hashCode());
	result = prime * result + ((specialiteLmdDto == null) ? 0 : specialiteLmdDto.hashCode());
	result = prime * result + ((structureId == null) ? 0 : structureId.hashCode());
	result = prime * result + ((typeConcoursCode == null) ? 0 : typeConcoursCode.hashCode());
	result = prime * result + ((typeConcoursId == null) ? 0 : typeConcoursId.hashCode());
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
	ConcoursDto other = (ConcoursDto) obj;
	if (anneeAcademiqueId == null) {
	    if (other.anneeAcademiqueId != null)
		return false;
	} else if (!anneeAcademiqueId.equals(other.anneeAcademiqueId))
	    return false;
	if (dateDebut == null) {
	    if (other.dateDebut != null)
		return false;
	} else if (!dateDebut.equals(other.dateDebut))
	    return false;
	if (dateFin == null) {
	    if (other.dateFin != null)
		return false;
	} else if (!dateFin.equals(other.dateFin))
	    return false;
	if (dateLimite == null) {
	    if (other.dateLimite != null)
		return false;
	} else if (!dateLimite.equals(other.dateLimite))
	    return false;
	if (datePublicationConcours == null) {
	    if (other.datePublicationConcours != null)
		return false;
	} else if (!datePublicationConcours.equals(other.datePublicationConcours))
	    return false;
	if (datePublicationListeComplementaire == null) {
	    if (other.datePublicationListeComplementaire != null)
		return false;
	} else if (!datePublicationListeComplementaire.equals(other.datePublicationListeComplementaire))
	    return false;
	if (datePublicationResultats == null) {
	    if (other.datePublicationResultats != null)
		return false;
	} else if (!datePublicationResultats.equals(other.datePublicationResultats))
	    return false;
	if (domaineId == null) {
	    if (other.domaineId != null)
		return false;
	} else if (!domaineId.equals(other.domaineId))
	    return false;
	if (etablissementAdmissionDtos == null) {
	    if (other.etablissementAdmissionDtos != null)
		return false;
	} else if (!etablissementAdmissionDtos.equals(other.etablissementAdmissionDtos))
	    return false;
	if (etablissementId == null) {
	    if (other.etablissementId != null)
		return false;
	} else if (!etablissementId.equals(other.etablissementId))
	    return false;
	if (examenConcoursDtos == null) {
	    if (other.examenConcoursDtos != null)
		return false;
	} else if (!examenConcoursDtos.equals(other.examenConcoursDtos))
	    return false;
	if (filiereId == null) {
	    if (other.filiereId != null)
		return false;
	} else if (!filiereId.equals(other.filiereId))
	    return false;
	if (filiereLmdDto == null) {
	    if (other.filiereLmdDto != null)
		return false;
	} else if (!filiereLmdDto.equals(other.filiereLmdDto))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (identifiant == null) {
	    if (other.identifiant != null)
		return false;
	} else if (!identifiant.equals(other.identifiant))
	    return false;
	if (intituleArabe == null) {
	    if (other.intituleArabe != null)
		return false;
	} else if (!intituleArabe.equals(other.intituleArabe))
	    return false;
	if (intituleLatin == null) {
	    if (other.intituleLatin != null)
		return false;
	} else if (!intituleLatin.equals(other.intituleLatin))
	    return false;
	if (libelleAnneeAcademique1 == null) {
	    if (other.libelleAnneeAcademique1 != null)
		return false;
	} else if (!libelleAnneeAcademique1.equals(other.libelleAnneeAcademique1))
	    return false;
	if (libelleAnneeAcademique2 == null) {
	    if (other.libelleAnneeAcademique2 != null)
		return false;
	} else if (!libelleAnneeAcademique2.equals(other.libelleAnneeAcademique2))
	    return false;
	if (libelleEtablissementLatin == null) {
	    if (other.libelleEtablissementLatin != null)
		return false;
	} else if (!libelleEtablissementLatin.equals(other.libelleEtablissementLatin))
	    return false;
	if (national == null) {
	    if (other.national != null)
		return false;
	} else if (!national.equals(other.national))
	    return false;
	if (noteBase == null) {
	    if (other.noteBase != null)
		return false;
	} else if (!noteBase.equals(other.noteBase))
	    return false;
	if (ouvertureOffreFormationDto == null) {
	    if (other.ouvertureOffreFormationDto != null)
		return false;
	} else if (!ouvertureOffreFormationDto.equals(other.ouvertureOffreFormationDto))
	    return false;
	if (refStructureDto == null) {
	    if (other.refStructureDto != null)
		return false;
	} else if (!refStructureDto.equals(other.refStructureDto))
	    return false;
	if (situationId == null) {
	    if (other.situationId != null)
		return false;
	} else if (!situationId.equals(other.situationId))
	    return false;
	if (specialiteId == null) {
	    if (other.specialiteId != null)
		return false;
	} else if (!specialiteId.equals(other.specialiteId))
	    return false;
	if (specialiteLmdDto == null) {
	    if (other.specialiteLmdDto != null)
		return false;
	} else if (!specialiteLmdDto.equals(other.specialiteLmdDto))
	    return false;
	if (structureId == null) {
	    if (other.structureId != null)
		return false;
	} else if (!structureId.equals(other.structureId))
	    return false;
	if (typeConcoursCode == null) {
	    if (other.typeConcoursCode != null)
		return false;
	} else if (!typeConcoursCode.equals(other.typeConcoursCode))
	    return false;
	if (typeConcoursId == null) {
	    if (other.typeConcoursId != null)
		return false;
	} else if (!typeConcoursId.equals(other.typeConcoursId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConcoursDto [id=" + id + ", identifiant=" + identifiant + ", intituleLatin=" + intituleLatin
		+ ", intituleArabe=" + intituleArabe + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
		+ ", national=" + national + ", datePublicationConcours=" + datePublicationConcours
		+ ", datePublicationResultats=" + datePublicationResultats + ", datePublicationListeComplementaire="
		+ datePublicationListeComplementaire + ", dateLimite=" + dateLimite + ", noteBase=" + noteBase
		+ ", typeConcoursId=" + typeConcoursId + ", typeConcoursCode=" + typeConcoursCode
		+ ", anneeAcademiqueId=" + anneeAcademiqueId + ", libelleAnneeAcademique1=" + libelleAnneeAcademique1
		+ ", libelleAnneeAcademique2=" + libelleAnneeAcademique2 + ", libelleEtablissementLatin="
		+ libelleEtablissementLatin + ", etablissementId=" + etablissementId + ", domaineId=" + domaineId
		+ ", situationId=" + situationId + ", filiereId=" + filiereId + ", specialiteId=" + specialiteId
		+ ", structureId=" + structureId + ", etablissementAdmissionDtos=" + etablissementAdmissionDtos
		+ ", examenConcoursDtos=" + examenConcoursDtos + ", filiereLmdDto=" + filiereLmdDto
		+ ", specialiteLmdDto=" + specialiteLmdDto + ", ouvertureOffreFormationDto="
		+ ouvertureOffreFormationDto + ", refStructureDto=" + refStructureDto + "]";
    }

}
