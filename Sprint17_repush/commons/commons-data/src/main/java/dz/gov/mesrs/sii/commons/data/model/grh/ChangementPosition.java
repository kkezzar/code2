package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OrderBy;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

@Entity
@Table(name = "changement_position", schema = "grh")
public class ChangementPosition implements java.io.Serializable, Identifiable<Long> {

	private static final long serialVersionUID = 5951216485197026306L;
	private Long id;
	private Nomenclature nomenclatureByMotif;
	private DossierEmploye dossierEmploye;
	private Nomenclature nomenclatureByPosition;
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
	private SituationEntite situation;
	private RefStructure structure;
	private Boolean droit;

	public ChangementPosition() {
		super();
	}



	@Id
	@SequenceGenerator(name = "changement_position_id_changement_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "changement_position_id_changement_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motif")
	public Nomenclature getNomenclatureByMotif() {
		return this.nomenclatureByMotif;
	}

	public void setNomenclatureByMotif(Nomenclature nomenclatureByMotif) {
		this.nomenclatureByMotif = nomenclatureByMotif;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position" , nullable=false)
	public Nomenclature getNomenclatureByPosition() {
		return this.nomenclatureByPosition;
	}

	public void setNomenclatureByPosition(Nomenclature nomenclatureByPosition) {
		this.nomenclatureByPosition = nomenclatureByPosition;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande", length = 13, nullable = false)
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Column(name = "objet", length = 256)
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13, nullable = false)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 13, nullable = false)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "prolongation")
	public Boolean getProlongation() {
		return this.prolongation;
	}

	public void setProlongation(Boolean prolongation) {
		this.prolongation = prolongation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_resultat", length = 13)
	public Date getDateResultat() {
		return this.dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	@Column(name = "acceptee")
	public Boolean getAcceptee() {
		return this.acceptee;
	}

	public void setAcceptee(Boolean acceptee) {
		this.acceptee = acceptee;
	}

	@Column(name = "motif_refus", length = 256)
	public String getMotifRefus() {
		return this.motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	@Column(name = "ordre_rappel_sn")
	public String getOrdreRappelSn() {
		return this.ordreRappelSn;
	}

	public void setOrdreRappelSn(String ordreRappelSn) {
		this.ordreRappelSn = ordreRappelSn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_reintegration", length = 13)
	public Date getDateReintegration() {
		return this.dateReintegration;
	}

	public void setDateReintegration(Date dateReintegration) {
		this.dateReintegration = dateReintegration;
	}

	@Column(name = "motif_reintegration")
	public String getMotifReintegration() {
		return this.motifReintegration;
	}

	public void setMotifReintegration(String motifReintegration) {
		this.motifReintegration = motifReintegration;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ref_structure", nullable = true)
	public RefStructure getStructure() {
		return structure;
	}

	public void setStructure(RefStructure structure) {
		this.structure = structure;
	}
	
	
	@Column(name = "droit")
	public Boolean isDroit() {
		return droit;
	}

	public void setDroit(Boolean droit) {
		this.droit = droit;
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motifRefus == null) ? 0 : motifRefus.hashCode());
		result = prime * result + ((motifReintegration == null) ? 0 : motifReintegration.hashCode());
		result = prime * result + ((nomenclatureByMotif == null) ? 0 : nomenclatureByMotif.hashCode());
		result = prime * result + ((nomenclatureByPosition == null) ? 0 : nomenclatureByPosition.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((ordreRappelSn == null) ? 0 : ordreRappelSn.hashCode());
		result = prime * result + ((prolongation == null) ? 0 : prolongation.hashCode());
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
		ChangementPosition other = (ChangementPosition) obj;
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
		if (dossierEmploye == null) {
			if (other.dossierEmploye != null)
				return false;
		} else if (!dossierEmploye.equals(other.dossierEmploye))
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
		if (nomenclatureByMotif == null) {
			if (other.nomenclatureByMotif != null)
				return false;
		} else if (!nomenclatureByMotif.equals(other.nomenclatureByMotif))
			return false;
		if (nomenclatureByPosition == null) {
			if (other.nomenclatureByPosition != null)
				return false;
		} else if (!nomenclatureByPosition.equals(other.nomenclatureByPosition))
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
		return true;
	}

	@Override
	public String toString() {
		return "ChangementPosition [id=" + id + ", nomenclatureByMotif=" + nomenclatureByMotif
				+ ", nomenclatureByPosition=" + nomenclatureByPosition + ", dateDemande=" + dateDemande + ", objet="
				+ objet + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prolongation=" + prolongation
				+ ", dateResultat=" + dateResultat + ", acceptee=" + acceptee + ", motifRefus=" + motifRefus
				+ ", ordreRappelSn=" + ordreRappelSn + ", dateReintegration=" + dateReintegration
				+ ", motifReintegration=" + motifReintegration + "]";
	}

}
