package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DossierDto implements Serializable{

	private static final long serialVersionUID = 4427092206724499703L;
	private int id;
	private List<PieceConstitutiveDto> pieceConstitutiveDtos;
	
	public DossierDto(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<PieceConstitutiveDto> getPieceConstitutiveDtos() {
		if(pieceConstitutiveDtos==null){
			pieceConstitutiveDtos = new ArrayList<>();
		}
		return pieceConstitutiveDtos;
	}

	public void setPieceConstitutiveDtos(List<PieceConstitutiveDto> pieceConstitutiveDtos) {
		this.pieceConstitutiveDtos = pieceConstitutiveDtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((pieceConstitutiveDtos == null) ? 0 : pieceConstitutiveDtos.hashCode());
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
		DossierDto other = (DossierDto) obj;
		if (id != other.id)
			return false;
		if (pieceConstitutiveDtos == null) {
			if (other.pieceConstitutiveDtos != null)
				return false;
		} else if (!pieceConstitutiveDtos.equals(other.pieceConstitutiveDtos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DossierDto [id=" + id + ", pieceConstitutiveDtos=" + pieceConstitutiveDtos + "]";
	}
	
	
	

	
		
	
	
}
