package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;

public class PieceConstitutiveDto implements Serializable {

	private static final long serialVersionUID = 561174392531930643L;

	private int id;
	private Date dateFourniture;
	private RefTypePieceConstitutiveDto typePiece;
	private DossierDto dossierDto;
	private boolean fournie;
	private boolean obligatoire;

	public PieceConstitutiveDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFourniture() {
		return dateFourniture;
	}

	public void setDateFourniture(Date dateFourniture) {
		this.dateFourniture = dateFourniture;
	}

	public RefTypePieceConstitutiveDto getTypePiece() {
		return typePiece;
	}

	public void setTypePiece(RefTypePieceConstitutiveDto typePiece) {
		this.typePiece = typePiece;
	}

	public DossierDto getDossierDto() {
		return dossierDto;
	}

	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	public boolean isFournie() {
		return fournie;
	}

	public void setFournie(boolean fournie) {
		this.fournie = fournie;
	}

	public boolean isObligatoire() {
		return obligatoire;
	}

	public void setObligatoire(boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFourniture == null) ? 0 : dateFourniture.hashCode());
		result = prime * result + (fournie ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (obligatoire ? 1231 : 1237);
		result = prime * result + ((typePiece == null) ? 0 : typePiece.hashCode());
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
		PieceConstitutiveDto other = (PieceConstitutiveDto) obj;
		if (dateFourniture == null) {
			if (other.dateFourniture != null)
				return false;
		} else if (!dateFourniture.equals(other.dateFourniture))
			return false;
		if (dossierDto == null) {
			if (other.dossierDto != null)
				return false;
		} else if (!dossierDto.equals(other.dossierDto))
			return false;
		if (fournie != other.fournie)
			return false;
		if (id != other.id)
			return false;
		if (obligatoire != other.obligatoire)
			return false;
		if (typePiece == null) {
			if (other.typePiece != null)
				return false;
		} else if (!typePiece.equals(other.typePiece))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PieceConstitutiveDto [id=" + id + ", dateFourniture=" + dateFourniture + ", typePiece=" + typePiece
				+ ", fournie=" + fournie + ", obligatoire=" + obligatoire + "]";
	}

}
