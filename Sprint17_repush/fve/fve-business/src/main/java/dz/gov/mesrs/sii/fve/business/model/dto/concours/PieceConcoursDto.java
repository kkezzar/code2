package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;

public class PieceConcoursDto implements Serializable {

    private static final long serialVersionUID = -7476260976262532056L;

    private Long id;
    private int rang;
    private String intitule;
    private String typeLibelleFr;
    private Integer typeId;
    private Boolean obligatoire;
    private ConcoursDto concoursDto;
    private Long concoursId;
    private boolean fournie;

    public PieceConcoursDto() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getRang() {
	return rang;
    }

    public void setRang(int rang) {
	this.rang = rang;
    }

    public String getIntitule() {
	return intitule;
    }

    public void setIntitule(String intitule) {
	this.intitule = intitule;
    }

    public String getTypeLibelleFr() {
	return typeLibelleFr;
    }

    public void setTypeLibelleFr(String typeLibelleFr) {
	this.typeLibelleFr = typeLibelleFr;
    }

    public Integer getTypeId() {
	return typeId;
    }

    public void setTypeId(Integer typeId) {
	this.typeId = typeId;
    }

    public Boolean getObligatoire() {
	return obligatoire;
    }

    public void setObligatoire(Boolean obligatoire) {
	this.obligatoire = obligatoire;
    }

    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }

    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    public void setConcoursId(Long concoursId) {
	this.concoursId = concoursId;
    }

    public Long getConcoursId() {
	return concoursId;
    }


    public boolean isFournie() {
        return fournie;
    }

    public void setFournie(boolean fournie) {
        this.fournie = fournie;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((concoursDto == null) ? 0 : concoursDto.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((intitule == null) ? 0 : intitule.hashCode());
	result = prime * result + ((obligatoire == null) ? 0 : obligatoire.hashCode());
	result = prime * result + rang;
	result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
	result = prime * result + ((typeLibelleFr == null) ? 0 : typeLibelleFr.hashCode());
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
	PieceConcoursDto other = (PieceConcoursDto) obj;
	if (concoursDto == null) {
	    if (other.concoursDto != null)
		return false;
	} else if (!concoursDto.equals(other.concoursDto))
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
	if (obligatoire == null) {
	    if (other.obligatoire != null)
		return false;
	} else if (!obligatoire.equals(other.obligatoire))
	    return false;
	if (rang != other.rang)
	    return false;
	if (typeId == null) {
	    if (other.typeId != null)
		return false;
	} else if (!typeId.equals(other.typeId))
	    return false;
	if (typeLibelleFr == null) {
	    if (other.typeLibelleFr != null)
		return false;
	} else if (!typeLibelleFr.equals(other.typeLibelleFr))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConcoursPieceConstitutiveDto [id=" + id + ", rang=" + rang + ", intitule=" + intitule
		+ ", typeLibelleFr=" + typeLibelleFr + ", typeId=" + typeId + ", obligatoire=" + obligatoire;
    }

}
