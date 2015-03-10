package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class PosteEmploiDto implements Serializable {

	private static final long serialVersionUID = -3122506209193725832L;

	private Long id;
	private String code;
	private String resultat;
	private Date dateCreation;
	private Boolean statut;
	private NomenclatureDto niveauQualificationDto;
	private NomenclatureDto niveauCompetenceDto;
	private List<ConnaissancePosteDto> connaissanceDtos;
	private List<HabiletePosteDto> habileteDtos;
	private RefEtablissementDto etablissementDto;
	private String objet;
	private AffectationPosteDto affectationDto;

	public PosteEmploiDto() {
		super();
	}

	public PosteEmploiDto(NomenclatureDto niveauQualificationDto, NomenclatureDto niveauCompetenceDto,
			RefEtablissementDto etablissementDto) {
		super();
		this.niveauQualificationDto = niveauQualificationDto;
		this.niveauCompetenceDto = niveauCompetenceDto;
		this.etablissementDto = etablissementDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Boolean getStatut() {
		return statut;
	}

	public void setStatut(Boolean statut) {
		this.statut = statut;
	}

	public NomenclatureDto getNiveauQualificationDto() {
		return niveauQualificationDto;
	}

	public void setNiveauQualificationDto(NomenclatureDto niveauQualificationDto) {
		this.niveauQualificationDto = niveauQualificationDto;
	}

	public NomenclatureDto getNiveauCompetenceDto() {
		return niveauCompetenceDto;
	}

	public void setNiveauCompetenceDto(NomenclatureDto niveauCompetenceDto) {
		this.niveauCompetenceDto = niveauCompetenceDto;
	}

	public List<ConnaissancePosteDto> getConnaissanceDtos() {
		if (connaissanceDtos == null) {
			connaissanceDtos = new ArrayList<>();
		}
		return connaissanceDtos;
	}

	public void setConnaissanceDtos(List<ConnaissancePosteDto> connaissanceDtos) {
		this.connaissanceDtos = connaissanceDtos;
	}

	public List<HabiletePosteDto> getHabileteDtos() {
		if (habileteDtos == null) {
			habileteDtos = new ArrayList<>();
		}
		return habileteDtos;
	}

	public void setHabileteDtos(List<HabiletePosteDto> habileteDtos) {
		this.habileteDtos = habileteDtos;
	}

	public void addHabileteDto(HabiletePosteDto dto) {
		this.getHabileteDtos().add(dto);
		dto.setPosteDto(this);
	}

	public void removeHabileteDto(HabiletePosteDto dto) {
		this.getHabileteDtos().remove(dto);
		dto.setPosteDto(null);
	}

	public void addConnaissanceDto(ConnaissancePosteDto dto) {
		this.getConnaissanceDtos().add(dto);
		dto.setPosteDto(this);
	}

	public void removeConnaissanceDto(ConnaissancePosteDto dto) {
		this.getConnaissanceDtos().remove(dto);
		dto.setPosteDto(null);
	}

	public RefEtablissementDto getEtablissementDto() {
		return etablissementDto;
	}

	public void setEtablissementDto(RefEtablissementDto etablissementDto) {
		this.etablissementDto = etablissementDto;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public void setAffectationDto(AffectationPosteDto affectationDto) {
		this.affectationDto = affectationDto;
	}

	public AffectationPosteDto getAffectationDto() {
		return affectationDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((niveauCompetenceDto == null) ? 0 : niveauCompetenceDto.hashCode());
		result = prime * result + ((niveauQualificationDto == null) ? 0 : niveauQualificationDto.hashCode());
		result = prime * result + ((resultat == null) ? 0 : resultat.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
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
		PosteEmploiDto other = (PosteEmploiDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (niveauCompetenceDto == null) {
			if (other.niveauCompetenceDto != null)
				return false;
		} else if (!niveauCompetenceDto.equals(other.niveauCompetenceDto))
			return false;
		if (niveauQualificationDto == null) {
			if (other.niveauQualificationDto != null)
				return false;
		} else if (!niveauQualificationDto.equals(other.niveauQualificationDto))
			return false;
		if (resultat == null) {
			if (other.resultat != null)
				return false;
		} else if (!resultat.equals(other.resultat))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PosteEmploiDto [id=" + id + ", code=" + code + ", resultat=" + resultat + ", dateCreation="
				+ dateCreation + ", statut=" + statut + ", niveauQualificationDto=" + niveauQualificationDto
				+ ", niveauCompetenceDto=" + niveauCompetenceDto + "]";
	}

}
