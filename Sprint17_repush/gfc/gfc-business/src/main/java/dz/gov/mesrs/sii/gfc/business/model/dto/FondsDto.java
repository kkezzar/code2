package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class FondsDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:11
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefStructureDto structure;
	private RefEtablissementDto etablissement;
	private SituationEntiteDto situation;
	private String abreviation;
	private String intituleFr;
	private String intituleAr;
	private Date dateCreation;
	private Date dateCloture;
	private String observation;
	private List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles = new ArrayList<AffectationEtabChapitreArticleDto>(
			0);

	public FondsDto() {
		etablissement = new RefEtablissementDto();
		structure = new RefStructureDto();
		situation = new SituationEntiteDto();
	}

	public FondsDto(Integer id, RefStructureDto structure, RefEtablissementDto etablissement,
			SituationEntiteDto situation, String intituleFr, String intituleAr) {
		this.id = id;
		this.structure = structure;
		this.etablissement = etablissement;
		this.situation = situation;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public FondsDto(Integer id, RefStructureDto structure, RefEtablissementDto etablissement,
			SituationEntiteDto situation, String abreviation, String intituleFr, String intituleAr, Date dateCreation,
			Date dateCloture, String observation,
			List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles) {
		this.id = id;
		this.structure = structure;
		this.etablissement = etablissement;
		this.situation = situation;
		this.abreviation = abreviation;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.dateCreation = dateCreation;
		this.dateCloture = dateCloture;
		this.observation = observation;
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RefStructureDto getStructure() {
		return structure;
	}

	public void setStructure(RefStructureDto structure) {
		this.structure = structure;
	}

	public RefEtablissementDto getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public SituationEntiteDto getSituation() {
		return situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public String getAbreviation() {
		return this.abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<AffectationEtabChapitreArticleDto> getAffectationEtabChapitreArticles() {
		return this.affectationEtabChapitreArticles;
	}

	public void setAffectationEtabChapitreArticles(
			List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles) {
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreviation == null) ? 0 : abreviation.hashCode());
		result = prime * result
				+ ((affectationEtabChapitreArticles == null) ? 0 : affectationEtabChapitreArticles.hashCode());
		result = prime * result + ((dateCloture == null) ? 0 : dateCloture.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((structure == null) ? 0 : structure.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
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
		FondsDto other = (FondsDto) obj;
		if (abreviation == null) {
			if (other.abreviation != null)
				return false;
		} else if (!abreviation.equals(other.abreviation))
			return false;
		if (affectationEtabChapitreArticles == null) {
			if (other.affectationEtabChapitreArticles != null)
				return false;
		} else if (!affectationEtabChapitreArticles.equals(other.affectationEtabChapitreArticles))
			return false;
		if (dateCloture == null) {
			if (other.dateCloture != null)
				return false;
		} else if (!dateCloture.equals(other.dateCloture))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (id != other.id)
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (etablissement == null) {
			if (other.etablissement != null)
				return false;
		} else if (!etablissement.equals(other.etablissement))
			return false;
		if (structure == null) {
			if (other.structure != null)
				return false;
		} else if (!structure.equals(other.structure))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		return true;
	}

}
