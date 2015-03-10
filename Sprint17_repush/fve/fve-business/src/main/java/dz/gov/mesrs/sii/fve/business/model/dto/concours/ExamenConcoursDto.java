package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;
import java.util.Date;

public class ExamenConcoursDto implements Serializable {

    private static final long serialVersionUID = -4212121506895622004L;
    private Long id;
    private Long concoursId;
    private Integer responsableId;
    private String responsableNom;
    private String responsablePrenom;
    private Integer lieuId;
    private Integer typeExamenId;
    private String typeExamenLibelleFr;
    private String epreuveLibelleFr;
    private String lieuLibelleFr;
    private Integer epreuveId;
    private Integer noteBase;
    private Double coefficient;
    private Date dateDebut;
    private Double duree;
    private String intitule;
    private ConcoursDto concoursDto;

    public ExamenConcoursDto() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getConcoursId() {
	return concoursId;
    }

    public void setConcoursId(Long concoursId) {
	this.concoursId = concoursId;
    }

    public Integer getResponsableId() {
	return responsableId;
    }

    public void setResponsableId(Integer responsableId) {
	this.responsableId = responsableId;
    }

    public Integer getLieuId() {
	return lieuId;
    }

    public void setLieuId(Integer lieuId) {
	this.lieuId = lieuId;
    }

    public Integer getTypeExamenId() {
	return typeExamenId;
    }

    public void setTypeExamenId(Integer typeExamenId) {
	this.typeExamenId = typeExamenId;
    }

    public Integer getEpreuveId() {
	return epreuveId;
    }

    public void setEpreuveId(Integer epreuveId) {
	this.epreuveId = epreuveId;
    }

    public Integer getNoteBase() {
	return noteBase;
    }

    public void setNoteBase(Integer noteBase) {
	this.noteBase = noteBase;
    }

    public Double getCoefficient() {
	return coefficient;
    }

    public void setCoefficient(Double coefficient) {
	this.coefficient = coefficient;
    }

    public Date getDateDebut() {
	return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
    }

    public Double getDuree() {
	return duree;
    }

    public void setDuree(Double duree) {
	this.duree = duree;
    }

    public String getIntitule() {
	return intitule;
    }

    public void setIntitule(String intitule) {
	this.intitule = intitule;
    }

    public String getResponsableNom() {
	return responsableNom;
    }

    public void setResponsableNom(String responsableNom) {
	this.responsableNom = responsableNom;
    }

    public String getResponsablePrenom() {
	return responsablePrenom;
    }

    public void setResponsablePrenom(String responsablePrenom) {
	this.responsablePrenom = responsablePrenom;
    }

    public String getTypeExamenLibelleFr() {
	return typeExamenLibelleFr;
    }

    public void setTypeExamenLibelleFr(String typeExamenLibelleFr) {
	this.typeExamenLibelleFr = typeExamenLibelleFr;
    }

    public String getEpreuveLibelleFr() {
	return epreuveLibelleFr;
    }

    public void setEpreuveLibelleFr(String epreuveLibelleFr) {
	this.epreuveLibelleFr = epreuveLibelleFr;
    }

    public String getLieuLibelleFr() {
	return lieuLibelleFr;
    }

    public void setLieuLibelleFr(String lieuLibelleFr) {
	this.lieuLibelleFr = lieuLibelleFr;
    }
    
    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }
    
    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((coefficient == null) ? 0 : coefficient.hashCode());
	result = prime * result + ((concoursDto == null) ? 0 : concoursDto.hashCode());
	result = prime * result + ((concoursId == null) ? 0 : concoursId.hashCode());
	result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
	result = prime * result + ((duree == null) ? 0 : duree.hashCode());
	result = prime * result + ((epreuveId == null) ? 0 : epreuveId.hashCode());
	result = prime * result + ((epreuveLibelleFr == null) ? 0 : epreuveLibelleFr.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((intitule == null) ? 0 : intitule.hashCode());
	result = prime * result + ((lieuId == null) ? 0 : lieuId.hashCode());
	result = prime * result + ((lieuLibelleFr == null) ? 0 : lieuLibelleFr.hashCode());
	result = prime * result + ((noteBase == null) ? 0 : noteBase.hashCode());
	result = prime * result + ((responsableId == null) ? 0 : responsableId.hashCode());
	result = prime * result + ((responsableNom == null) ? 0 : responsableNom.hashCode());
	result = prime * result + ((responsablePrenom == null) ? 0 : responsablePrenom.hashCode());
	result = prime * result + ((typeExamenId == null) ? 0 : typeExamenId.hashCode());
	result = prime * result + ((typeExamenLibelleFr == null) ? 0 : typeExamenLibelleFr.hashCode());
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
	ExamenConcoursDto other = (ExamenConcoursDto) obj;
	if (coefficient == null) {
	    if (other.coefficient != null)
		return false;
	} else if (!coefficient.equals(other.coefficient))
	    return false;
	if (concoursDto == null) {
	    if (other.concoursDto != null)
		return false;
	} else if (!concoursDto.equals(other.concoursDto))
	    return false;
	if (concoursId == null) {
	    if (other.concoursId != null)
		return false;
	} else if (!concoursId.equals(other.concoursId))
	    return false;
	if (dateDebut == null) {
	    if (other.dateDebut != null)
		return false;
	} else if (!dateDebut.equals(other.dateDebut))
	    return false;
	if (duree == null) {
	    if (other.duree != null)
		return false;
	} else if (!duree.equals(other.duree))
	    return false;
	if (epreuveId == null) {
	    if (other.epreuveId != null)
		return false;
	} else if (!epreuveId.equals(other.epreuveId))
	    return false;
	if (epreuveLibelleFr == null) {
	    if (other.epreuveLibelleFr != null)
		return false;
	} else if (!epreuveLibelleFr.equals(other.epreuveLibelleFr))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (intitule == null) {
	    if (other.intitule != null)
		return false;
	} else if (!intitule.equals(other.intitule))
	    return false;
	if (lieuId == null) {
	    if (other.lieuId != null)
		return false;
	} else if (!lieuId.equals(other.lieuId))
	    return false;
	if (lieuLibelleFr == null) {
	    if (other.lieuLibelleFr != null)
		return false;
	} else if (!lieuLibelleFr.equals(other.lieuLibelleFr))
	    return false;
	if (noteBase == null) {
	    if (other.noteBase != null)
		return false;
	} else if (!noteBase.equals(other.noteBase))
	    return false;
	if (responsableId == null) {
	    if (other.responsableId != null)
		return false;
	} else if (!responsableId.equals(other.responsableId))
	    return false;
	if (responsableNom == null) {
	    if (other.responsableNom != null)
		return false;
	} else if (!responsableNom.equals(other.responsableNom))
	    return false;
	if (responsablePrenom == null) {
	    if (other.responsablePrenom != null)
		return false;
	} else if (!responsablePrenom.equals(other.responsablePrenom))
	    return false;
	if (typeExamenId == null) {
	    if (other.typeExamenId != null)
		return false;
	} else if (!typeExamenId.equals(other.typeExamenId))
	    return false;
	if (typeExamenLibelleFr == null) {
	    if (other.typeExamenLibelleFr != null)
		return false;
	} else if (!typeExamenLibelleFr.equals(other.typeExamenLibelleFr))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ExamenConcoursDto [id=" + id + ", concoursId=" + concoursId + ", responsableId=" + responsableId
		+ ", responsableNom=" + responsableNom + ", responsablePrenom=" + responsablePrenom + ", lieuId="
		+ lieuId + ", typeExamenId=" + typeExamenId + ", typeExamenLibelleFr=" + typeExamenLibelleFr
		+ ", epreuveLibelleFr=" + epreuveLibelleFr + ", lieuLibelleFr=" + lieuLibelleFr + ", epreuveId="
		+ epreuveId + ", noteBase=" + noteBase + ", coefficient=" + coefficient + ", dateDebut=" + dateDebut
		+ ", duree=" + duree + ", intitule=" + intitule ;
    }

   

}
