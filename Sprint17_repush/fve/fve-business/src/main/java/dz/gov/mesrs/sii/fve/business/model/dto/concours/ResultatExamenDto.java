package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;

public class ResultatExamenDto implements Serializable {

    private static final long serialVersionUID = 4312392537405495841L;
    private Long id;
    private Boolean admis;
    private Double note;
    private DossierCandidatDto dossierCandidatDto;
    private ExamenConcoursDto examenConcoursDto;

    public ResultatExamenDto() {
	super();
    }

    public ResultatExamenDto(ExamenConcoursDto examenConcoursDto) {
	super();
	this.examenConcoursDto = examenConcoursDto;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Boolean getAdmis() {
	return admis;
    }

    public void setAdmis(Boolean admis) {
	this.admis = admis;
    }

    public Double getNote() {
	return note;
    }

    public void setNote(Double note) {
	this.note = note;
    }

    public DossierCandidatDto getDossierCandidatDto() {
	return dossierCandidatDto;
    }

    public void setDossierCandidatDto(DossierCandidatDto dossierCandidatDto) {
	this.dossierCandidatDto = dossierCandidatDto;
    }

    public ExamenConcoursDto getExamenConcoursDto() {
	return examenConcoursDto;
    }

    public void setExamenConcoursDto(ExamenConcoursDto examenConcoursDto) {
	this.examenConcoursDto = examenConcoursDto;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((admis == null) ? 0 : admis.hashCode());
	result = prime * result + ((dossierCandidatDto == null) ? 0 : dossierCandidatDto.hashCode());
	result = prime * result + ((examenConcoursDto == null) ? 0 : examenConcoursDto.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((note == null) ? 0 : note.hashCode());
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
	ResultatExamenDto other = (ResultatExamenDto) obj;
	if (admis == null) {
	    if (other.admis != null)
		return false;
	} else if (!admis.equals(other.admis))
	    return false;
	if (dossierCandidatDto == null) {
	    if (other.dossierCandidatDto != null)
		return false;
	} else if (!dossierCandidatDto.equals(other.dossierCandidatDto))
	    return false;
	if (examenConcoursDto == null) {
	    if (other.examenConcoursDto != null)
		return false;
	} else if (!examenConcoursDto.equals(other.examenConcoursDto))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (note == null) {
	    if (other.note != null)
		return false;
	} else if (!note.equals(other.note))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ResultatExamenDto [id=" + id + ", admis=" + admis + ", note=" + note + ", examenConcoursDto="
		+ examenConcoursDto + "]";
    }

}
