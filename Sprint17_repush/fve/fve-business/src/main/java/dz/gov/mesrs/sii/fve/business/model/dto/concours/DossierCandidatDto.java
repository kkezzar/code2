package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public class DossierCandidatDto implements Serializable {

    private static final long serialVersionUID = -3181591712182374141L;

    private Integer id;
    private Long concoursId;
    private Integer anneeAcademiqueId;
    private RefIndividuDto individu;
    private Date dateDepot;
    private Boolean etudeDossier;
    private String motifEtude;
    private Boolean accepte;
    private String motifRejet;
    private Boolean admis;
    private Date dateAdmission;
    private Double moyenneObtenue;
    private Integer classement;
    private Boolean listeComplementaire;
    private Boolean desistement;
    private Date dateDesistement;
    private String motifDesistement;
    private List<PieceFournieDto> pieceFournieDtos;
    private List<ResultatExamenDto> resultatExamenDtos;
    private DossierInscriptionAdministrativeDto dossierInscriptionAdministrative;
    private EtablissementAdmissionDto etablissementAdmissionDto;
    private String adresse;
    private String codePostal;
    private String numeroTelephone;
    private String email;

    public DossierCandidatDto() {
	super();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Long getConcoursId() {
	return concoursId;
    }

    public void setConcoursId(Long concoursId) {
	this.concoursId = concoursId;
    }

    public Integer getAnneeAcademiqueId() {
	return anneeAcademiqueId;
    }

    public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
	this.anneeAcademiqueId = anneeAcademiqueId;
    }

    public RefIndividuDto getIndividu() {
	return individu;
    }

    public void setIndividu(RefIndividuDto individu) {
	this.individu = individu;
    }

    public Date getDateDepot() {
	return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
	this.dateDepot = dateDepot;
    }

    public Boolean getEtudeDossier() {
	return etudeDossier;
    }

    public void setEtudeDossier(Boolean etudeDossier) {
	this.etudeDossier = etudeDossier;
    }

    public String getMotifEtude() {
	return motifEtude;
    }

    public void setMotifEtude(String motifEtude) {
	this.motifEtude = motifEtude;
    }

    public Boolean getAccepte() {
	return accepte;
    }

    public void setAccepte(Boolean accepte) {
	this.accepte = accepte;
    }

    public String getMotifRejet() {
	return motifRejet;
    }

    public void setMotifRejet(String motifRejet) {
	this.motifRejet = motifRejet;
    }

    public Boolean getAdmis() {
	return admis;
    }

    public void setAdmis(Boolean admis) {
	this.admis = admis;
    }

    public Date getDateAdmission() {
	return dateAdmission;
    }

    public void setDateAdmission(Date dateAdmission) {
	this.dateAdmission = dateAdmission;
    }

    public Double getMoyenneObtenue() {
	return moyenneObtenue;
    }

    public void setMoyenneObtenue(Double moyenneObtenue) {
	this.moyenneObtenue = moyenneObtenue;
    }

    public Integer getClassement() {
	return classement;
    }

    public void setClassement(Integer classement) {
	this.classement = classement;
    }

    public Boolean getListeComplementaire() {
	return listeComplementaire;
    }

    public void setListeComplementaire(Boolean listeComplementaire) {
	this.listeComplementaire = listeComplementaire;
    }

    public Boolean getDesistement() {
	return desistement;
    }

    public void setDesistement(Boolean desistement) {
	this.desistement = desistement;
    }

    public Date getDateDesistement() {
	return dateDesistement;
    }

    public void setDateDesistement(Date dateDesistement) {
	this.dateDesistement = dateDesistement;
    }

    public String getMotifDesistement() {
	return motifDesistement;
    }

    public void setMotifDesistement(String motifDesistement) {
	this.motifDesistement = motifDesistement;
    }

    public void setPieceFournieDtos(List<PieceFournieDto> pieceFournieDtos) {
	this.pieceFournieDtos = pieceFournieDtos;
    }

    public List<PieceFournieDto> getPieceFournieDtos() {
	return pieceFournieDtos;
    }

    public void addPieceFournieDto(PieceFournieDto dto) {
	if (pieceFournieDtos == null) {
	    pieceFournieDtos = new ArrayList<>();
	}
	pieceFournieDtos.add(dto);
	dto.setDossierCandidatDto(this);
    }

    public void removePieceFournieDto(PieceFournieDto dto) {
	if (pieceFournieDtos == null || !(pieceFournieDtos.size() > 0)) {
	    return;
	}
	for (PieceFournieDto pieceFournieDto : pieceFournieDtos) {
	    if (pieceFournieDto.getPieceConcoursDto().getId().equals(dto.getPieceConcoursDto().getId())) {
		dto = pieceFournieDto;
		break;
	    }
	}

	pieceFournieDtos.remove(dto);
	dto.setDossierCandidatDto(null);

    }

    public void setResultatExamenDtos(List<ResultatExamenDto> resultatExamenDtos) {
	this.resultatExamenDtos = resultatExamenDtos;
    }

    public List<ResultatExamenDto> getResultatExamenDtos() {
	return resultatExamenDtos;
    }

    public void addResultatExamenDto(ResultatExamenDto dto) {
	if (resultatExamenDtos == null) {
	    resultatExamenDtos = new ArrayList<>();
	}
	resultatExamenDtos.add(dto);
	dto.setDossierCandidatDto(this);
    }

    public void removeResultatExamenDto(ResultatExamenDto dto) {
	if (resultatExamenDtos == null || !resultatExamenDtos.contains(dto)) {
	    return;
	}

	resultatExamenDtos.remove(dto);
	dto.setDossierCandidatDto(null);

    }

    public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrative() {
	return dossierInscriptionAdministrative;
    }

    public void setDossierInscriptionAdministrative(DossierInscriptionAdministrativeDto dossierInscriptionAdministrative) {
	this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
    }

    public EtablissementAdmissionDto getEtablissementAdmissionDto() {
	return etablissementAdmissionDto;
    }

    public void setEtablissementAdmissionDto(EtablissementAdmissionDto etablissementAdmissionDto) {
	this.etablissementAdmissionDto = etablissementAdmissionDto;
    }

    public String getAdresse() {
	return adresse;
    }

    public void setAdresse(String adresse) {
	this.adresse = adresse;
    }

    public String getNumeroTelephone() {
	return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
	this.numeroTelephone = numeroTelephone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getCodePostal() {
	return codePostal;
    }

    public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((accepte == null) ? 0 : accepte.hashCode());
	result = prime * result + ((admis == null) ? 0 : admis.hashCode());
	result = prime * result + ((anneeAcademiqueId == null) ? 0 : anneeAcademiqueId.hashCode());
	result = prime * result + ((classement == null) ? 0 : classement.hashCode());
	result = prime * result + ((concoursId == null) ? 0 : concoursId.hashCode());
	result = prime * result + ((dateAdmission == null) ? 0 : dateAdmission.hashCode());
	result = prime * result + ((dateDepot == null) ? 0 : dateDepot.hashCode());
	result = prime * result + ((dateDesistement == null) ? 0 : dateDesistement.hashCode());
	result = prime * result + ((desistement == null) ? 0 : desistement.hashCode());
	result = prime * result + ((etudeDossier == null) ? 0 : etudeDossier.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((individu == null) ? 0 : individu.hashCode());
	result = prime * result + ((listeComplementaire == null) ? 0 : listeComplementaire.hashCode());
	result = prime * result + ((motifDesistement == null) ? 0 : motifDesistement.hashCode());
	result = prime * result + ((motifEtude == null) ? 0 : motifEtude.hashCode());
	result = prime * result + ((motifRejet == null) ? 0 : motifRejet.hashCode());
	result = prime * result + ((moyenneObtenue == null) ? 0 : moyenneObtenue.hashCode());
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
	DossierCandidatDto other = (DossierCandidatDto) obj;
	if (accepte == null) {
	    if (other.accepte != null)
		return false;
	} else if (!accepte.equals(other.accepte))
	    return false;
	if (admis == null) {
	    if (other.admis != null)
		return false;
	} else if (!admis.equals(other.admis))
	    return false;
	if (anneeAcademiqueId == null) {
	    if (other.anneeAcademiqueId != null)
		return false;
	} else if (!anneeAcademiqueId.equals(other.anneeAcademiqueId))
	    return false;
	if (classement == null) {
	    if (other.classement != null)
		return false;
	} else if (!classement.equals(other.classement))
	    return false;
	if (concoursId == null) {
	    if (other.concoursId != null)
		return false;
	} else if (!concoursId.equals(other.concoursId))
	    return false;
	if (dateAdmission == null) {
	    if (other.dateAdmission != null)
		return false;
	} else if (!dateAdmission.equals(other.dateAdmission))
	    return false;
	if (dateDepot == null) {
	    if (other.dateDepot != null)
		return false;
	} else if (!dateDepot.equals(other.dateDepot))
	    return false;
	if (dateDesistement == null) {
	    if (other.dateDesistement != null)
		return false;
	} else if (!dateDesistement.equals(other.dateDesistement))
	    return false;
	if (desistement == null) {
	    if (other.desistement != null)
		return false;
	} else if (!desistement.equals(other.desistement))
	    return false;
	if (etudeDossier == null) {
	    if (other.etudeDossier != null)
		return false;
	} else if (!etudeDossier.equals(other.etudeDossier))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (individu == null) {
	    if (other.individu != null)
		return false;
	} else if (!individu.equals(other.individu))
	    return false;
	if (listeComplementaire == null) {
	    if (other.listeComplementaire != null)
		return false;
	} else if (!listeComplementaire.equals(other.listeComplementaire))
	    return false;
	if (motifDesistement == null) {
	    if (other.motifDesistement != null)
		return false;
	} else if (!motifDesistement.equals(other.motifDesistement))
	    return false;
	if (motifEtude == null) {
	    if (other.motifEtude != null)
		return false;
	} else if (!motifEtude.equals(other.motifEtude))
	    return false;
	if (motifRejet == null) {
	    if (other.motifRejet != null)
		return false;
	} else if (!motifRejet.equals(other.motifRejet))
	    return false;
	if (moyenneObtenue == null) {
	    if (other.moyenneObtenue != null)
		return false;
	} else if (!moyenneObtenue.equals(other.moyenneObtenue))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DossierCandidatDto [id=" + id + ", concoursId=" + concoursId + ", anneeAcademiqueId="
		+ anneeAcademiqueId + ", individu=" + individu + ", dateDepot=" + dateDepot + ", etudeDossier="
		+ etudeDossier + ", motifEtude=" + motifEtude + ", accepte=" + accepte + ", motifRejet=" + motifRejet
		+ ", admis=" + admis + ", dateAdmission=" + dateAdmission + ", moyenneObtenue=" + moyenneObtenue
		+ ", classement=" + classement + ", listeComplementaire=" + listeComplementaire + ", desistement="
		+ desistement + ", dateDesistement=" + dateDesistement + ", motifDesistement=" + motifDesistement + "]";
    }

    public void addPieceConcours(PieceConcoursDto pieceConcours) {
	boolean exists = false;
	for (PieceFournieDto pieceFournieDto : this.getPieceFournieDtos()) {
	    if (pieceFournieDto.getPieceConcoursDto().getId().equals(pieceConcours.getId())) {
		exists = true;
		break;
	    }
	}
	if (!exists) {
	    PieceFournieDto pieceFournieDto = new PieceFournieDto();
	    pieceFournieDto.setPieceConcoursDto(pieceConcours);
	    pieceFournieDto.setDossierCandidatDto(this);
	    this.getPieceFournieDtos().add(pieceFournieDto);
	}

    }

    public void removePieceConcours(PieceConcoursDto pieceConcours) {
	int index = -1;
	for (PieceFournieDto pieceFournieDto : this.getPieceFournieDtos()) {
	    if (pieceFournieDto.getPieceConcoursDto().getId().equals(pieceConcours.getId())) {
		index = this.pieceFournieDtos.indexOf(pieceFournieDto);
		break;
	    }
	}

	if (index >= 0) {
	    this.pieceFournieDtos.remove(index);
	}
    }

}
