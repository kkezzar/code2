package dz.gov.mesrs.sii.commons.data.model.grh;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "visite_medicale", schema = "grh")
public class VisiteMedicale implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private DossierEmploye dossierEmploye;
	private Date dateDemande;
	private String objet;
	private Date dateRdv;
	private Date heureRdv;
	private Date dateConvenue;
	private Date heureConvenue;
	private Date dateVisite;
	private Date heureVisite;
	private String dignostic;
	private Boolean resultat;
	private String motifRefus;
	private Boolean avecExamen;
	private Boolean finVisite;
	private Boolean finExamen;
	private Set<Medicament> medicaments = new HashSet<Medicament>(0);
	private Set<ExamenMedical> examenMedicals = new HashSet<ExamenMedical>(0);

	public VisiteMedicale() {
	}

	public VisiteMedicale(Long id) {
		this.id = id;
	}

	public VisiteMedicale(Long id, DossierEmploye dossierEmploye, Date dateDemande, String objet, Date dateRdv,
			Date heureRdv, Date dateConvenue, Date heureConvenue, Date dateVisite, Date heureVisite, String dignostic,
			Boolean resultat, String motifRefus, Set<Medicament> medicaments, Set<ExamenMedical> examenMedicals) {
		this.id = id;
		this.dossierEmploye = dossierEmploye;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.dateRdv = dateRdv;
		this.heureRdv = heureRdv;
		this.dateConvenue = dateConvenue;
		this.heureConvenue = heureConvenue;
		this.dateVisite = dateVisite;
		this.heureVisite = heureVisite;
		this.dignostic = dignostic;
		this.resultat = resultat;
		this.motifRefus = motifRefus;
		this.medicaments = medicaments;
		this.examenMedicals = examenMedicals;
	}

	@Id
	@SequenceGenerator(name = "grh_visite_medicale_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_visite_medicale_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande", length = 13)
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Column(name = "objet")
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_rdv", length = 13)
	public Date getDateRdv() {
		return this.dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_rdv", length = 15)
	public Date getHeureRdv() {
		return this.heureRdv;
	}

	public void setHeureRdv(Date heureRdv) {
		this.heureRdv = heureRdv;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_convenue", length = 13)
	public Date getDateConvenue() {
		return this.dateConvenue;
	}

	public void setDateConvenue(Date dateConvenue) {
		this.dateConvenue = dateConvenue;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_convenue", length = 15)
	public Date getHeureConvenue() {
		return this.heureConvenue;
	}

	public void setHeureConvenue(Date heureConvenue) {
		this.heureConvenue = heureConvenue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_visite", length = 13)
	public Date getDateVisite() {
		return this.dateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_visite", length = 15)
	public Date getHeureVisite() {
		return this.heureVisite;
	}

	public void setHeureVisite(Date heureVisite) {
		this.heureVisite = heureVisite;
	}

	@Column(name = "dignostic")
	public String getDignostic() {
		return this.dignostic;
	}

	public void setDignostic(String dignostic) {
		this.dignostic = dignostic;
	}

	@Column(name = "resultat")
	public Boolean getResultat() {
		return this.resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	@Column(name = "motif_refus")
	public String getMotifRefus() {
		return this.motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}
	
	
	
	@Column(name = "avecExamen")
	public Boolean getAvecExamen() {
		return avecExamen;
	}

	public void setAvecExamen(Boolean avecExamen) {
		this.avecExamen = avecExamen;
	}
	@Column(name = "finVisite")
	public Boolean getFinVisite() {
		return finVisite;
	}

	public void setFinVisite(Boolean finVisite) {
		this.finVisite = finVisite;
	}
	@Column(name = "finExamen")
	public Boolean getFinExamen() {
		return finExamen;
	}

	public void setFinExamen(Boolean finExamen) {
		this.finExamen = finExamen;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visiteMedicale", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Medicament> getMedicaments() {
		return this.medicaments;
	}

	public void setMedicaments(Set<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visiteMedicale", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<ExamenMedical> getExamenMedicals() {
		return this.examenMedicals;
	}

	public void setExamenMedicals(Set<ExamenMedical> examenMedicals) {
		this.examenMedicals = examenMedicals;
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
		result = prime * result
				+ ((dateConvenue == null) ? 0 : dateConvenue.hashCode());
		result = prime * result
				+ ((dateDemande == null) ? 0 : dateDemande.hashCode());
		result = prime * result + ((dateRdv == null) ? 0 : dateRdv.hashCode());
		result = prime * result
				+ ((dateVisite == null) ? 0 : dateVisite.hashCode());
		result = prime * result
				+ ((dignostic == null) ? 0 : dignostic.hashCode());
		result = prime * result
				+ ((dossierEmploye == null) ? 0 : dossierEmploye.hashCode());
		result = prime * result
				+ ((examenMedicals == null) ? 0 : examenMedicals.hashCode());
		result = prime * result
				+ ((heureConvenue == null) ? 0 : heureConvenue.hashCode());
		result = prime * result
				+ ((heureRdv == null) ? 0 : heureRdv.hashCode());
		result = prime * result
				+ ((heureVisite == null) ? 0 : heureVisite.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((medicaments == null) ? 0 : medicaments.hashCode());
		result = prime * result
				+ ((motifRefus == null) ? 0 : motifRefus.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result
				+ ((resultat == null) ? 0 : resultat.hashCode());
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
		VisiteMedicale other = (VisiteMedicale) obj;
		if (dateConvenue == null) {
			if (other.dateConvenue != null)
				return false;
		} else if (!dateConvenue.equals(other.dateConvenue))
			return false;
		if (dateDemande == null) {
			if (other.dateDemande != null)
				return false;
		} else if (!dateDemande.equals(other.dateDemande))
			return false;
		if (dateRdv == null) {
			if (other.dateRdv != null)
				return false;
		} else if (!dateRdv.equals(other.dateRdv))
			return false;
		if (dateVisite == null) {
			if (other.dateVisite != null)
				return false;
		} else if (!dateVisite.equals(other.dateVisite))
			return false;
		if (dignostic == null) {
			if (other.dignostic != null)
				return false;
		} else if (!dignostic.equals(other.dignostic))
			return false;
		if (dossierEmploye == null) {
			if (other.dossierEmploye != null)
				return false;
		} else if (!dossierEmploye.equals(other.dossierEmploye))
			return false;
		if (examenMedicals == null) {
			if (other.examenMedicals != null)
				return false;
		} else if (!examenMedicals.equals(other.examenMedicals))
			return false;
		if (heureConvenue == null) {
			if (other.heureConvenue != null)
				return false;
		} else if (!heureConvenue.equals(other.heureConvenue))
			return false;
		if (heureRdv == null) {
			if (other.heureRdv != null)
				return false;
		} else if (!heureRdv.equals(other.heureRdv))
			return false;
		if (heureVisite == null) {
			if (other.heureVisite != null)
				return false;
		} else if (!heureVisite.equals(other.heureVisite))
			return false;
		if (id != other.id)
			return false;
		if (medicaments == null) {
			if (other.medicaments != null)
				return false;
		} else if (!medicaments.equals(other.medicaments))
			return false;
		if (motifRefus == null) {
			if (other.motifRefus != null)
				return false;
		} else if (!motifRefus.equals(other.motifRefus))
			return false;
		if (objet == null) {
			if (other.objet != null)
				return false;
		} else if (!objet.equals(other.objet))
			return false;
		if (resultat == null) {
			if (other.resultat != null)
				return false;
		} else if (!resultat.equals(other.resultat))
			return false;
		return true;
	}


	
	
	

}
