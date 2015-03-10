package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class FinActiviteDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private NomenclatureDto nomenclature;
	private DossierEmployeDto employe;
	private Date dateDemande;
	private String objet;
	private Date dateDepart;
	private Boolean accepte;
	private String motifRefus;
	private Date dateDepartAcceptee;
	private String motifCessation;
	private Date dateCessationFinale;
	private Date dateDecision;
	private NomenclatureDto typeDepartRetraite;
	private Set<PieceDossierRetraiteDto> pieceDossierRetraites = new HashSet<PieceDossierRetraiteDto>(
			0);
	private SituationEntiteDto situationDto;
	//utilisé dans le cas de l'admission à la retraite
	private DossierDto dossierDto;

	public FinActiviteDto() {
	}

	public FinActiviteDto(Long id) {
		this.id = id;
	}

	public FinActiviteDto(Long id, NomenclatureDto nomenclature,
			DossierEmployeDto employe, Date dateDemande, String objet, Date dateDepart,
			Boolean accepte, String motifRefus, Date dateDepartAcceptee,
			String motifCessation, Date dateCessationFinale,
			Set<PieceDossierRetraiteDto> pieceDossierRetraites) {
		this.id = id;
		this.nomenclature = nomenclature;
		this.employe = employe;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.dateDepart = dateDepart;
		this.accepte = accepte;
		this.motifRefus = motifRefus;
		this.dateDepartAcceptee = dateDepartAcceptee;
		this.motifCessation = motifCessation;
		this.dateCessationFinale = dateCessationFinale;
		this.pieceDossierRetraites = pieceDossierRetraites;
	}
	
	

	public FinActiviteDto(NomenclatureDto nomenclature) {
		super();
		this.nomenclature = nomenclature;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public NomenclatureDto getNomenclature() {
		return nomenclature;
	}


	public void setNomenclature(NomenclatureDto nomenclature) {
		this.nomenclature = nomenclature;
	}

	public DossierEmployeDto getEmploye() {
		return employe;
	}


	public void setEmploye(DossierEmployeDto employe) {
		this.employe = employe;
	}


	public Date getDateDemande() {
		return dateDemande;
	}


	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getObjet() {
		return objet;
	}

	
	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Date getDateDepart() {
		return dateDepart;
	}


	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}


	public Boolean getAccepte() {
		return accepte;
	}


	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}


	public String getMotifRefus() {
		return motifRefus;
	}


	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}


	public Date getDateDepartAcceptee() {
		return dateDepartAcceptee;
	}


	public void setDateDepartAcceptee(Date dateDepartAcceptee) {
		this.dateDepartAcceptee = dateDepartAcceptee;
	}


	public String getMotifCessation() {
		return motifCessation;
	}


	public void setMotifCessation(String motifCessation) {
		this.motifCessation = motifCessation;
	}


	public Date getDateCessationFinale() {
		return dateCessationFinale;
	}


	public void setDateCessationFinale(Date dateCessationFinale) {
		this.dateCessationFinale = dateCessationFinale;
	}


	public Set<PieceDossierRetraiteDto> getPieceDossierRetraites() {
		return pieceDossierRetraites;
	}


	public void setPieceDossierRetraites(
			Set<PieceDossierRetraiteDto> pieceDossierRetraites) {
		this.pieceDossierRetraites = pieceDossierRetraites;
	}
	
	public SituationEntiteDto getSituationDto() {
		return situationDto;
	}
	
	public void setSituationDto(SituationEntiteDto situationDto) {
		this.situationDto = situationDto;
	}
	
	public Date getDateDecision() {
		return dateDecision;
	}
	
	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public NomenclatureDto getTypeDepartRetraite() {
		return typeDepartRetraite;
	}

	public void setTypeDepartRetraite(NomenclatureDto typeDepartRetraite) {
		this.typeDepartRetraite = typeDepartRetraite;
	}
	
	public DossierDto getDossierDto() {
		return dossierDto;
	}
	
	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accepte == null) ? 0 : accepte.hashCode());
		result = prime * result + ((dateCessationFinale == null) ? 0 : dateCessationFinale.hashCode());
		result = prime * result + ((dateDecision == null) ? 0 : dateDecision.hashCode());
		result = prime * result + ((dateDemande == null) ? 0 : dateDemande.hashCode());
		result = prime * result + ((dateDepart == null) ? 0 : dateDepart.hashCode());
		result = prime * result + ((dateDepartAcceptee == null) ? 0 : dateDepartAcceptee.hashCode());
		result = prime * result + ((dossierDto == null) ? 0 : dossierDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motifCessation == null) ? 0 : motifCessation.hashCode());
		result = prime * result + ((motifRefus == null) ? 0 : motifRefus.hashCode());
		result = prime * result + ((nomenclature == null) ? 0 : nomenclature.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((pieceDossierRetraites == null) ? 0 : pieceDossierRetraites.hashCode());
		result = prime * result + ((situationDto == null) ? 0 : situationDto.hashCode());
		result = prime * result + ((typeDepartRetraite == null) ? 0 : typeDepartRetraite.hashCode());
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
		FinActiviteDto other = (FinActiviteDto) obj;
		if (accepte == null) {
			if (other.accepte != null)
				return false;
		} else if (!accepte.equals(other.accepte))
			return false;
		if (dateCessationFinale == null) {
			if (other.dateCessationFinale != null)
				return false;
		} else if (!dateCessationFinale.equals(other.dateCessationFinale))
			return false;
		if (dateDecision == null) {
			if (other.dateDecision != null)
				return false;
		} else if (!dateDecision.equals(other.dateDecision))
			return false;
		if (dateDemande == null) {
			if (other.dateDemande != null)
				return false;
		} else if (!dateDemande.equals(other.dateDemande))
			return false;
		if (dateDepart == null) {
			if (other.dateDepart != null)
				return false;
		} else if (!dateDepart.equals(other.dateDepart))
			return false;
		if (dateDepartAcceptee == null) {
			if (other.dateDepartAcceptee != null)
				return false;
		} else if (!dateDepartAcceptee.equals(other.dateDepartAcceptee))
			return false;
		if (dossierDto == null) {
			if (other.dossierDto != null)
				return false;
		} else if (!dossierDto.equals(other.dossierDto))
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motifCessation == null) {
			if (other.motifCessation != null)
				return false;
		} else if (!motifCessation.equals(other.motifCessation))
			return false;
		if (motifRefus == null) {
			if (other.motifRefus != null)
				return false;
		} else if (!motifRefus.equals(other.motifRefus))
			return false;
		if (nomenclature == null) {
			if (other.nomenclature != null)
				return false;
		} else if (!nomenclature.equals(other.nomenclature))
			return false;
		if (objet == null) {
			if (other.objet != null)
				return false;
		} else if (!objet.equals(other.objet))
			return false;
		if (pieceDossierRetraites == null) {
			if (other.pieceDossierRetraites != null)
				return false;
		} else if (!pieceDossierRetraites.equals(other.pieceDossierRetraites))
			return false;
		if (situationDto == null) {
			if (other.situationDto != null)
				return false;
		} else if (!situationDto.equals(other.situationDto))
			return false;
		if (typeDepartRetraite == null) {
			if (other.typeDepartRetraite != null)
				return false;
		} else if (!typeDepartRetraite.equals(other.typeDepartRetraite))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinActiviteDto [id=" + id + ", nomenclature=" + nomenclature + ", employe=" + employe.getMatricule()
				+ ", dateDemande=" + dateDemande + ", objet=" + objet + ", dateDepart=" + dateDepart + ", accepte="
				+ accepte + ", motifRefus=" + motifRefus + ", dateDepartAcceptee=" + dateDepartAcceptee
				+ ", motifCessation=" + motifCessation + ", dateCessationFinale=" + dateCessationFinale
				+ ", dateDecision=" + dateDecision + ", typeDepartRetraite=" + typeDepartRetraite
				+ ", pieceDossierRetraites=" + pieceDossierRetraites + ", situationDto=" + situationDto
				+ ", dossierDto=" + dossierDto + "]";
	}

	

	
}
