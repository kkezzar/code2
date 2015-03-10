package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class ChangementPositionDto implements Serializable {

	private static final long serialVersionUID = -2582278344677222927L;
	private Long id;
	private NomenclatureDto nomenclatureByMotifDto;
	private DossierEmployeDto dossierEmployeDto;
	private NomenclatureDto nomenclatureByPositionDto;
	private Date dateDemande;
	private String objet;
	private Date dateDebut;
	private Date dateFin;
	private Boolean prolongation;
	private Date dateResultat;
	private Boolean acceptee;
	private String motifRefus;
	private String ordreRappelSn;
	private Date dateReintegration;
	private String motifReintegration;
	private RefStructureDto structureDto;

	public ChangementPositionDto() {
		super();
		this.nomenclatureByPositionDto = new NomenclatureDto();
	}

	public ChangementPositionDto(Long id) {
		this.id = id;
	}

	public ChangementPositionDto(DossierEmployeDto dossierEmploye) {
		this();
		this.dossierEmployeDto = dossierEmploye;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByMotifDto() {
		return nomenclatureByMotifDto;
	}

	public void setNomenclatureByMotifDto(NomenclatureDto nomenclatureByMotifDto) {
		this.nomenclatureByMotifDto = nomenclatureByMotifDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public NomenclatureDto getNomenclatureByPositionDto() {
		return nomenclatureByPositionDto;
	}

	public void setNomenclatureByPositionDto(NomenclatureDto nomenclatureByPositionDto) {
		this.nomenclatureByPositionDto = nomenclatureByPositionDto;
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

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Boolean getProlongation() {
		return prolongation;
	}

	public void setProlongation(Boolean prolongation) {
		this.prolongation = prolongation;
	}

	public Date getDateResultat() {
		return dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	public Boolean getAcceptee() {
		return acceptee;
	}

	public void setAcceptee(Boolean acceptee) {
		this.acceptee = acceptee;
	}

	public String getMotifRefus() {
		return motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	public String getOrdreRappelSn() {
		return ordreRappelSn;
	}

	public void setOrdreRappelSn(String ordreRappelSn) {
		this.ordreRappelSn = ordreRappelSn;
	}

	public Date getDateReintegration() {
		return dateReintegration;
	}

	public void setDateReintegration(Date dateReintegration) {
		this.dateReintegration = dateReintegration;
	}

	public String getMotifReintegration() {
		return motifReintegration;
	}

	public void setMotifReintegration(String motifReintegration) {
		this.motifReintegration = motifReintegration;
	}

	public RefStructureDto getStructureDto() {
		return structureDto;
	}

	public void setStructureDto(RefStructureDto structureDto) {
		this.structureDto = structureDto;
	}

	@Override
	public String toString() {
		return "ChangementPositionDto [id=" + id + ", nomenclatureByMotifDto=" + nomenclatureByMotifDto
				+ ", nomenclatureByPositionDto=" + nomenclatureByPositionDto + ", dateDemande=" + dateDemande
				+ ", objet=" + objet + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prolongation="
				+ prolongation + ", dateResultat=" + dateResultat + ", acceptee=" + acceptee + ", motifRefus="
				+ motifRefus + ", ordreRappelSn=" + ordreRappelSn + ", dateReintegration=" + dateReintegration
				+ ", motifReintegration=" + motifReintegration + ", structureDto=" + structureDto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptee == null) ? 0 : acceptee.hashCode());
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateDemande == null) ? 0 : dateDemande.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((dateReintegration == null) ? 0 : dateReintegration.hashCode());
		result = prime * result + ((dateResultat == null) ? 0 : dateResultat.hashCode());
		result = prime * result + ((dossierEmployeDto == null) ? 0 : dossierEmployeDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motifRefus == null) ? 0 : motifRefus.hashCode());
		result = prime * result + ((motifReintegration == null) ? 0 : motifReintegration.hashCode());
		result = prime * result + ((nomenclatureByMotifDto == null) ? 0 : nomenclatureByMotifDto.hashCode());
		result = prime * result + ((nomenclatureByPositionDto == null) ? 0 : nomenclatureByPositionDto.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((ordreRappelSn == null) ? 0 : ordreRappelSn.hashCode());
		result = prime * result + ((prolongation == null) ? 0 : prolongation.hashCode());
		result = prime * result + ((structureDto == null) ? 0 : structureDto.hashCode());
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
		ChangementPositionDto other = (ChangementPositionDto) obj;
		if (acceptee == null) {
			if (other.acceptee != null)
				return false;
		} else if (!acceptee.equals(other.acceptee))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateDemande == null) {
			if (other.dateDemande != null)
				return false;
		} else if (!dateDemande.equals(other.dateDemande))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (dateReintegration == null) {
			if (other.dateReintegration != null)
				return false;
		} else if (!dateReintegration.equals(other.dateReintegration))
			return false;
		if (dateResultat == null) {
			if (other.dateResultat != null)
				return false;
		} else if (!dateResultat.equals(other.dateResultat))
			return false;
		if (dossierEmployeDto == null) {
			if (other.dossierEmployeDto != null)
				return false;
		} else if (!dossierEmployeDto.equals(other.dossierEmployeDto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motifRefus == null) {
			if (other.motifRefus != null)
				return false;
		} else if (!motifRefus.equals(other.motifRefus))
			return false;
		if (motifReintegration == null) {
			if (other.motifReintegration != null)
				return false;
		} else if (!motifReintegration.equals(other.motifReintegration))
			return false;
		if (nomenclatureByMotifDto == null) {
			if (other.nomenclatureByMotifDto != null)
				return false;
		} else if (!nomenclatureByMotifDto.equals(other.nomenclatureByMotifDto))
			return false;
		if (nomenclatureByPositionDto == null) {
			if (other.nomenclatureByPositionDto != null)
				return false;
		} else if (!nomenclatureByPositionDto.equals(other.nomenclatureByPositionDto))
			return false;
		if (objet == null) {
			if (other.objet != null)
				return false;
		} else if (!objet.equals(other.objet))
			return false;
		if (ordreRappelSn == null) {
			if (other.ordreRappelSn != null)
				return false;
		} else if (!ordreRappelSn.equals(other.ordreRappelSn))
			return false;
		if (prolongation == null) {
			if (other.prolongation != null)
				return false;
		} else if (!prolongation.equals(other.prolongation))
			return false;
		if (structureDto == null) {
			if (other.structureDto != null)
				return false;
		} else if (!structureDto.equals(other.structureDto))
			return false;
		return true;
	}
	
	

}
