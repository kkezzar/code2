package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class FicheBesoinsDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:05
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String identite;
	private ProjetBudgetDto projetBudget;
	private RefEtablissementDto etablissement;
	private NomenclatureDto uniteMonetaire;
	private SituationEntiteDto situation;
	private Date dateEnvoi;
	private Date dateReception;
	private String intituleFr;
	private String intituleAr;
	private String description;
	private Date dateValidation;
	private Date dateVisaCF;
	private String observation;
	private List<ConsolidationBesoinsDto> consolidationBesoins = new ArrayList<ConsolidationBesoinsDto>(0);
	private List<DetailsFicheBesoinsDto> detailsFicheBesoins = new ArrayList<DetailsFicheBesoinsDto>(0);

	public FicheBesoinsDto() {
		projetBudget = new ProjetBudgetDto();
		etablissement = new RefEtablissementDto();
		uniteMonetaire = new NomenclatureDto();
		situation = new SituationEntiteDto();

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentite() {
		return this.identite;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public ProjetBudgetDto getProjetBudget() {
		return this.projetBudget;
	}

	public void setProjetBudget(ProjetBudgetDto projetBudget) {
		this.projetBudget = projetBudget;
	}

	public RefEtablissementDto getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public NomenclatureDto getUniteMonetaire() {
		return this.uniteMonetaire;
	}

	public void setUniteMonetaire(NomenclatureDto uniteMonetaire) {
		this.uniteMonetaire = uniteMonetaire;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public Date getDateEnvoi() {
		return this.dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public Date getDateReception() {
		return this.dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Date getDateVisaCF() {
		return this.dateVisaCF;
	}

	public void setDateVisaCF(Date dateVisaCF) {
		this.dateVisaCF = dateVisaCF;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<ConsolidationBesoinsDto> getConsolidationBesoins() {
		return this.consolidationBesoins;
	}

	public void setConsolidationBesoins(List<ConsolidationBesoinsDto> consolidationBesoins) {
		this.consolidationBesoins = consolidationBesoins;
	}

	public List<DetailsFicheBesoinsDto> getDetailsFicheBesoins() {
		return this.detailsFicheBesoins;
	}

	public void setDetailsFicheBesoins(List<DetailsFicheBesoinsDto> detailsFicheBesoins) {
		this.detailsFicheBesoins = detailsFicheBesoins;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateEnvoi == null) ? 0 : dateEnvoi.hashCode());
		result = prime * result + ((dateReception == null) ? 0 : dateReception.hashCode());
		result = prime * result + ((dateValidation == null) ? 0 : dateValidation.hashCode());
		result = prime * result + ((dateVisaCF == null) ? 0 : dateVisaCF.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identite == null) ? 0 : identite.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((projetBudget == null) ? 0 : projetBudget.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
		result = prime * result + ((uniteMonetaire == null) ? 0 : uniteMonetaire.hashCode());
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
		FicheBesoinsDto other = (FicheBesoinsDto) obj;
		if (consolidationBesoins == null) {
			if (other.consolidationBesoins != null)
				return false;
		} else if (!consolidationBesoins.equals(other.consolidationBesoins))
			return false;
		if (dateEnvoi == null) {
			if (other.dateEnvoi != null)
				return false;
		} else if (!dateEnvoi.equals(other.dateEnvoi))
			return false;
		if (dateReception == null) {
			if (other.dateReception != null)
				return false;
		} else if (!dateReception.equals(other.dateReception))
			return false;
		if (dateValidation == null) {
			if (other.dateValidation != null)
				return false;
		} else if (!dateValidation.equals(other.dateValidation))
			return false;
		if (dateVisaCF == null) {
			if (other.dateVisaCF != null)
				return false;
		} else if (!dateVisaCF.equals(other.dateVisaCF))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (detailsFicheBesoins == null) {
			if (other.detailsFicheBesoins != null)
				return false;
		} else if (!detailsFicheBesoins.equals(other.detailsFicheBesoins))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identite == null) {
			if (other.identite != null)
				return false;
		} else if (!identite.equals(other.identite))
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
		if (projetBudget == null) {
			if (other.projetBudget != null)
				return false;
		} else if (!projetBudget.equals(other.projetBudget))
			return false;
		if (etablissement == null) {
			if (other.etablissement != null)
				return false;
		} else if (!etablissement.equals(other.etablissement))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		if (uniteMonetaire == null) {
			if (other.uniteMonetaire != null)
				return false;
		} else if (!uniteMonetaire.equals(other.uniteMonetaire))
			return false;
		return true;
	}
}
