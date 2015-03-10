package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;

public class EtablissementAdmissionDto implements Serializable {

    private static final long serialVersionUID = -4125319821610811876L;
    private Long id;
    private Long concoursId;
    private Integer nombreAdmettre;
    private Integer etablissementId;
    private String etablissementLibelleFr;
    private ConcoursDto concoursDto;
    private Integer nombreAdmis;
    private Integer nombreListeComplementaire;

    public EtablissementAdmissionDto() {
	super();
    }

    public EtablissementAdmissionDto(int i) {
	super();
	this.nombreAdmettre = Integer.valueOf(i);
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

    public Integer getNombreAdmettre() {
	return nombreAdmettre;
    }

    public void setNombreAdmettre(Integer nombreAdmettre) {
	this.nombreAdmettre = nombreAdmettre;
    }

    public Integer getEtablissementId() {
	return etablissementId;
    }

    public void setEtablissementId(Integer etablissementId) {
	this.etablissementId = etablissementId;
    }

    public String getEtablissementLibelleFr() {
	return etablissementLibelleFr;
    }

    public void setEtablissementLibelleFr(String etablissementLibelleFr) {
	this.etablissementLibelleFr = etablissementLibelleFr;
    }

    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }

    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    public Integer getNombreAdmis() {
	return nombreAdmis;
    }

    public void setNombreAdmis(Integer nombreAdmis) {
	this.nombreAdmis = nombreAdmis;
    }

    public Integer getNombreListeComplementaire() {
	return nombreListeComplementaire;
    }

    public void setNombreListeComplementaire(Integer nombreListeComplementaire) {
	this.nombreListeComplementaire = nombreListeComplementaire;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((concoursId == null) ? 0 : concoursId.hashCode());
	result = prime * result + ((etablissementId == null) ? 0 : etablissementId.hashCode());
	result = prime * result + ((etablissementLibelleFr == null) ? 0 : etablissementLibelleFr.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nombreAdmettre == null) ? 0 : nombreAdmettre.hashCode());
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
	EtablissementAdmissionDto other = (EtablissementAdmissionDto) obj;
	if (concoursId == null) {
	    if (other.concoursId != null)
		return false;
	} else if (!concoursId.equals(other.concoursId))
	    return false;
	if (etablissementId == null) {
	    if (other.etablissementId != null)
		return false;
	} else if (!etablissementId.equals(other.etablissementId))
	    return false;
	if (etablissementLibelleFr == null) {
	    if (other.etablissementLibelleFr != null)
		return false;
	} else if (!etablissementLibelleFr.equals(other.etablissementLibelleFr))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (nombreAdmettre == null) {
	    if (other.nombreAdmettre != null)
		return false;
	} else if (!nombreAdmettre.equals(other.nombreAdmettre))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "EtablissementAdmissionDto [id=" + id + ", concoursId=" + concoursId + ", nombreAdmettre="
		+ nombreAdmettre + ", etablissementId=" + etablissementId + ", etablissementLibelleFr="
		+ etablissementLibelleFr + "]";
    }

}
