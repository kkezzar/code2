package dz.gov.mesrs.sii.fve.business.model.dto.concours;

import java.io.Serializable;

public class PieceFournieDto implements Serializable{

    private static final long serialVersionUID = -4830654529566467264L;

    private Long id;
    
    private PieceConcoursDto pieceConcoursDto;
    
    private DossierCandidatDto dossierCandidatDto;
    
    public PieceFournieDto(){
	super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PieceConcoursDto getPieceConcoursDto() {
        return pieceConcoursDto;
    }

    public void setPieceConcoursDto(PieceConcoursDto pieceConcoursDto) {
        this.pieceConcoursDto = pieceConcoursDto;
    }

    public DossierCandidatDto getDossierCandidatDto() {
        return dossierCandidatDto;
    }

    public void setDossierCandidatDto(DossierCandidatDto dossierCandidatDto) {
        this.dossierCandidatDto = dossierCandidatDto;
    }

    @Override
    public String toString() {
	return "PieceFournieDto [id=" + id + ", pieceConcoursDto=" + pieceConcoursDto + ", dossierCandidatDto="
		+ dossierCandidatDto + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dossierCandidatDto == null) ? 0 : dossierCandidatDto.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((pieceConcoursDto == null) ? 0 : pieceConcoursDto.hashCode());
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
	PieceFournieDto other = (PieceFournieDto) obj;
	if (dossierCandidatDto == null) {
	    if (other.dossierCandidatDto != null)
		return false;
	} else if (!dossierCandidatDto.equals(other.dossierCandidatDto))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (pieceConcoursDto == null) {
	    if (other.pieceConcoursDto != null)
		return false;
	} else if (!pieceConcoursDto.equals(other.pieceConcoursDto))
	    return false;
	return true;
    }
    
    
}
