package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class RegieDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:34:14
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefStructureDto refStructure;
	private NomenclatureDto typeRegie;
	private OrdonnateurDto ordonnateur;
	private CompteDto compteDepot;
	private AgentComptableDto agentComptable;
	private CompteDto compteCcp;
	private RefEtablissementDto refEtablissement;
	private SituationEntiteDto situationEntite;
	private String objetFr;
	private String objetAr;
	private Date dateOuverture;
	private String observationOuverture;
	private Date dateCloture;
	private String observationCloture;
	private List<RegisseurDto> regisseurs = new ArrayList<RegisseurDto>(0);
	private List<AffectationRegieChapitreArticleDto> affectationRegieChapitreArticles = new ArrayList<AffectationRegieChapitreArticleDto>(
			0);

	public RegieDto() {
		typeRegie = new NomenclatureDto();
		refStructure = new RefStructureDto();
		agentComptable = new AgentComptableDto();
		ordonnateur = new OrdonnateurDto();
		compteDepot = new CompteDto();
		// compteCcp=new CompteDto();
		situationEntite = new SituationEntiteDto();
	}

	public RegieDto(Integer id, RefStructureDto refStructure, NomenclatureDto typeRegie, OrdonnateurDto ordonnateur,
			AgentComptableDto agentComptable, RefEtablissementDto refEtablissement, SituationEntiteDto situationEntite,
			String objetFr, String objetAr) {
		this.id = id;
		this.refStructure = refStructure;
		this.typeRegie = typeRegie;
		this.ordonnateur = ordonnateur;
		this.agentComptable = agentComptable;
		this.refEtablissement = refEtablissement;
		this.situationEntite = situationEntite;
		this.objetFr = objetFr;
		this.objetAr = objetAr;
	}

	public RegieDto(Integer id, RefStructureDto refStructure, NomenclatureDto typeRegie, OrdonnateurDto ordonnateur,
			CompteDto compteDepot, AgentComptableDto agentComptable, CompteDto compteCcp,
			RefEtablissementDto refEtablissement, SituationEntiteDto situationEntite, String objetFr, String objetAr,
			Date dateOuverture, String observationOuverture, Date dateCloture, String observationCloture,
			List<RegisseurDto> regisseurs,

			List<AffectationRegieChapitreArticleDto> affectationRegieChapitreArticles) {
		this.id = id;
		this.refStructure = refStructure;
		this.typeRegie = typeRegie;
		this.ordonnateur = ordonnateur;
		this.compteDepot = compteDepot;
		this.agentComptable = agentComptable;
		this.compteCcp = compteCcp;
		this.refEtablissement = refEtablissement;
		this.situationEntite = situationEntite;
		this.objetFr = objetFr;
		this.objetAr = objetAr;
		this.dateOuverture = dateOuverture;
		this.observationOuverture = observationOuverture;
		this.dateCloture = dateCloture;
		this.observationCloture = observationCloture;
		this.regisseurs = regisseurs;

		this.affectationRegieChapitreArticles = affectationRegieChapitreArticles;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param ordonnateur
	 *            the ordonnateur to set
	 */
	public void setOrdonnateur(OrdonnateurDto ordonnateur) {
		this.ordonnateur = ordonnateur;
	}

	/**
	 * @return the compteDepot
	 */
	public CompteDto getCompteDepot() {
		return compteDepot;
	}

	/**
	 * @param compteDepot
	 *            the compteDepot to set
	 */
	public void setCompteDepot(CompteDto compteDepot) {
		this.compteDepot = compteDepot;
	}

	/**
	 * @return the agentComptable
	 */
	public AgentComptableDto getAgentComptable() {
		return agentComptable;
	}

	/**
	 * @param agentComptable
	 *            the agentComptable to set
	 */
	public void setAgentComptable(AgentComptableDto agentComptable) {
		this.agentComptable = agentComptable;
	}

	/**
	 * @return the compteCcp
	 */
	public CompteDto getCompteCcp() {
		return compteCcp;
	}

	/**
	 * @param compteCcp
	 *            the compteCcp to set
	 */
	public void setCompteCcp(CompteDto compteCcp) {
		this.compteCcp = compteCcp;
	}

	/**
	 * @return the refStructure
	 */
	public RefStructureDto getRefStructure() {
		return refStructure;
	}

	/**
	 * @param refStructure
	 *            the refStructure to set
	 */
	public void setRefStructure(RefStructureDto refStructure) {
		this.refStructure = refStructure;
	}

	/**
	 * @return the typeRegie
	 */
	public NomenclatureDto getTypeRegie() {
		return typeRegie;
	}

	/**
	 * @param typeRegie
	 *            the typeRegie to set
	 */
	public void setTypeRegie(NomenclatureDto typeRegie) {
		this.typeRegie = typeRegie;
	}

	/**
	 * @return the refEtablissement
	 */
	public RefEtablissementDto getRefEtablissement() {
		return refEtablissement;
	}

	/**
	 * @param refEtablissement
	 *            the refEtablissement to set
	 */
	public void setRefEtablissement(RefEtablissementDto refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	/**
	 * @return the situationEntite
	 */
	public SituationEntiteDto getSituationEntite() {
		return situationEntite;
	}

	/**
	 * @param situationEntite
	 *            the situationEntite to set
	 */
	public void setSituationEntite(SituationEntiteDto situationEntite) {
		this.situationEntite = situationEntite;
	}

	/**
	 * @return the ordonnateur
	 */
	public OrdonnateurDto getOrdonnateur() {
		return ordonnateur;
	}

	/**
	 * @return the objetFr
	 */
	public String getObjetFr() {
		return objetFr;
	}

	/**
	 * @param objetFr
	 *            the objetFr to set
	 */
	public void setObjetFr(String objetFr) {
		this.objetFr = objetFr;
	}

	/**
	 * @return the objetAr
	 */
	public String getObjetAr() {
		return objetAr;
	}

	/**
	 * @param objetAr
	 *            the objetAr to set
	 */
	public void setObjetAr(String objetAr) {
		this.objetAr = objetAr;
	}

	/**
	 * @return the dateOuverture
	 */
	public Date getDateOuverture() {
		return dateOuverture;
	}

	/**
	 * @param dateOuverture
	 *            the dateOuverture to set
	 */
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	/**
	 * @return the observationOuverture
	 */
	public String getObservationOuverture() {
		return observationOuverture;
	}

	/**
	 * @param observationOuverture
	 *            the observationOuverture to set
	 */
	public void setObservationOuverture(String observationOuverture) {
		this.observationOuverture = observationOuverture;
	}

	/**
	 * @return the dateCloture
	 */
	public Date getDateCloture() {
		return dateCloture;
	}

	/**
	 * @param dateCloture
	 *            the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * @return the observationCloture
	 */
	public String getObservationCloture() {
		return observationCloture;
	}

	/**
	 * @param observationCloture
	 *            the observationCloture to set
	 */
	public void setObservationCloture(String observationCloture) {
		this.observationCloture = observationCloture;
	}

	/**
	 * @return the regisseurs
	 */
	public List<RegisseurDto> getRegisseurs() {
		return regisseurs;
	}

	/**
	 * @param regisseurs
	 *            the regisseurs to set
	 */
	public void setRegisseurs(List<RegisseurDto> regisseurs) {
		this.regisseurs = regisseurs;
	}

	/**
	 * @return the affectationRegieChapitreArticles
	 */
	public List<AffectationRegieChapitreArticleDto> getAffectationRegieChapitreArticles() {
		return affectationRegieChapitreArticles;
	}

	/**
	 * @param affectationRegieChapitreArticles
	 *            the affectationRegieChapitreArticles to set
	 */
	public void setAffectationRegieChapitreArticles(
			List<AffectationRegieChapitreArticleDto> affectationRegieChapitreArticles) {
		this.affectationRegieChapitreArticles = affectationRegieChapitreArticles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((affectationRegieChapitreArticles == null) ? 0 : affectationRegieChapitreArticles.hashCode());

		result = prime * result + ((agentComptable == null) ? 0 : agentComptable.hashCode());
		result = prime * result + ((compteCcp == null) ? 0 : compteCcp.hashCode());
		result = prime * result + ((compteDepot == null) ? 0 : compteDepot.hashCode());
		result = prime * result + ((dateCloture == null) ? 0 : dateCloture.hashCode());
		result = prime * result + ((dateOuverture == null) ? 0 : dateOuverture.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typeRegie == null) ? 0 : typeRegie.hashCode());
		result = prime * result + ((objetAr == null) ? 0 : objetAr.hashCode());
		result = prime * result + ((objetFr == null) ? 0 : objetFr.hashCode());
		result = prime * result + ((observationCloture == null) ? 0 : observationCloture.hashCode());
		result = prime * result + ((observationOuverture == null) ? 0 : observationOuverture.hashCode());
		result = prime * result + ((ordonnateur == null) ? 0 : ordonnateur.hashCode());
		result = prime * result + ((refEtablissement == null) ? 0 : refEtablissement.hashCode());
		result = prime * result + ((refStructure == null) ? 0 : refStructure.hashCode());
		result = prime * result + ((regisseurs == null) ? 0 : regisseurs.hashCode());
		result = prime * result + ((situationEntite == null) ? 0 : situationEntite.hashCode());
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
		RegieDto other = (RegieDto) obj;
		if (affectationRegieChapitreArticles == null) {
			if (other.affectationRegieChapitreArticles != null)
				return false;
		} else if (!affectationRegieChapitreArticles.equals(other.affectationRegieChapitreArticles))
			return false;
		if (agentComptable == null) {
			if (other.agentComptable != null)
				return false;
		} else if (!agentComptable.equals(other.agentComptable))
			return false;
		if (compteCcp == null) {
			if (other.compteCcp != null)
				return false;
		} else if (!compteCcp.equals(other.compteCcp))
			return false;
		if (compteDepot == null) {
			if (other.compteDepot != null)
				return false;
		} else if (!compteDepot.equals(other.compteDepot))
			return false;
		if (dateCloture == null) {
			if (other.dateCloture != null)
				return false;
		} else if (!dateCloture.equals(other.dateCloture))
			return false;
		if (dateOuverture == null) {
			if (other.dateOuverture != null)
				return false;
		} else if (!dateOuverture.equals(other.dateOuverture))
			return false;
		if (id != other.id)
			return false;
		if (typeRegie == null) {
			if (other.typeRegie != null)
				return false;
		} else if (!typeRegie.equals(other.typeRegie))
			return false;
		if (objetAr == null) {
			if (other.objetAr != null)
				return false;
		} else if (!objetAr.equals(other.objetAr))
			return false;
		if (objetFr == null) {
			if (other.objetFr != null)
				return false;
		} else if (!objetFr.equals(other.objetFr))
			return false;
		if (observationCloture == null) {
			if (other.observationCloture != null)
				return false;
		} else if (!observationCloture.equals(other.observationCloture))
			return false;
		if (observationOuverture == null) {
			if (other.observationOuverture != null)
				return false;
		} else if (!observationOuverture.equals(other.observationOuverture))
			return false;
		if (ordonnateur == null) {
			if (other.ordonnateur != null)
				return false;
		} else if (!ordonnateur.equals(other.ordonnateur))
			return false;
		if (refEtablissement == null) {
			if (other.refEtablissement != null)
				return false;
		} else if (!refEtablissement.equals(other.refEtablissement))
			return false;
		if (refStructure == null) {
			if (other.refStructure != null)
				return false;
		} else if (!refStructure.equals(other.refStructure))
			return false;
		if (regisseurs == null) {
			if (other.regisseurs != null)
				return false;
		} else if (!regisseurs.equals(other.regisseurs))
			return false;
		if (situationEntite == null) {
			if (other.situationEntite != null)
				return false;
		} else if (!situationEntite.equals(other.situationEntite))
			return false;
		return true;
	}

}
