package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OrderBy;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

@Entity
@Table(name = "dossier_employe", schema = "grh")
public class DossierEmploye implements java.io.Serializable, Identifiable<Long> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private RefIndividu refIndividu;
	private RefEtablissement refEtablissement;
	private RefStructure refStructure;
	private Nomenclature nomenclatureByTypePermis;
	private Nomenclature nomenclatureByTypeCompte;
	private String matricule;
	private String numeross;
	private Date dateAffiliation;
	private String numeroCompte;
	private Date dateObtention;
	private Date dateTitularisation;
	private Date dateCreation;
	private Set<Enfant> enfants = new HashSet<Enfant>(0);
	private Set<Distinction> distinctions = new HashSet<Distinction>(0);
	private Set<Conjoint> conjoints = new HashSet<Conjoint>(0);
	private String photo;
	private Set<DiplomeEmploye> diplomeEmployes = new HashSet<DiplomeEmploye>(0);
	private Set<Stage> stages = new HashSet<Stage>(0);
	private Boolean titularise;
	private CandidatEmploye candidatEmploye;
	private SituationEntite situationEntite;
	private List<FinActivite> finActivites = new ArrayList<>();
	private Set<Carriere> carrieres = new HashSet<Carriere>(0);
	private Date dateInstallation;
	private Carriere carriereCourante;
	private List<ChangementPosition> changementPositions = new ArrayList<>(0);
	private ChangementPosition currentPosition;
	private List<ConnaissanceEmploye> connaissances = new ArrayList<>();
	private List<HabileteEmploye> habiletes = new ArrayList<>();
	private Nomenclature niveauCompetence;
	private Nomenclature niveauQualification;

	public DossierEmploye() {
	}

	public DossierEmploye(Long id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "grh_dossier_employe_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_dossier_employe_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "matricule", nullable = false)
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	@Column(name = "numeross")
	public String getNumeross() {
		return this.numeross;
	}

	public void setNumeross(String numeross) {
		this.numeross = numeross;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_affiliation", length = 13)
	public Date getDateAffiliation() {
		return this.dateAffiliation;
	}

	public void setDateAffiliation(Date dateAffiliation) {
		this.dateAffiliation = dateAffiliation;
	}

	@Column(name = "numero_compte")
	public String getNumeroCompte() {
		return this.numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_obtention", length = 13)
	public Date getDateObtention() {
		return this.dateObtention;
	}

	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_titularisation", length = 13)
	public Date getDateTitularisation() {
		return this.dateTitularisation;
	}

	public void setDateTitularisation(Date dateTitularisation) {
		this.dateTitularisation = dateTitularisation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individu", nullable = false)
	public RefIndividu getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividu refIndividu) {
		this.refIndividu = refIndividu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etablissement", nullable = false)
	public RefEtablissement getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structure")
	public RefStructure getRefStructure() {
		return this.refStructure;
	}

	public void setRefStructure(RefStructure refStructure) {
		this.refStructure = refStructure;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_permis")
	public Nomenclature getNomenclatureByTypePermis() {
		return this.nomenclatureByTypePermis;
	}

	public void setNomenclatureByTypePermis(Nomenclature nomenclatureByTypePermis) {
		this.nomenclatureByTypePermis = nomenclatureByTypePermis;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_compte")
	public Nomenclature getNomenclatureByTypeCompte() {
		return this.nomenclatureByTypeCompte;
	}

	public void setNomenclatureByTypeCompte(Nomenclature nomenclatureByTypeCompte) {
		this.nomenclatureByTypeCompte = nomenclatureByTypeCompte;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	public Set<Enfant> getEnfants() {
		return this.enfants;
	}

	public void setEnfants(Set<Enfant> enfants) {
		this.enfants = enfants;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	public Set<Distinction> getDistinctions() {
		return distinctions;
	}

	public void setDistinctions(Set<Distinction> distinctions) {
		this.distinctions = distinctions;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	public Set<Conjoint> getConjoints() {
		return conjoints;
	}

	public void setConjoints(Set<Conjoint> conjoints) {
		this.conjoints = conjoints;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	public Set<DiplomeEmploye> getDiplomeEmployes() {
		return diplomeEmployes;
	}

	public void setDiplomeEmployes(Set<DiplomeEmploye> diplomeEmployes) {
		this.diplomeEmployes = diplomeEmployes;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation")
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	public Set<Stage> getStages() {
		return stages;
	}

	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}

	@Column(name = "titularise")
	public Boolean getTitularise() {
		return this.titularise;
	}

	public void setTitularise(Boolean titularise) {
		this.titularise = titularise;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidat")
	public CandidatEmploye getCandidatEmploye() {
		return candidatEmploye;
	}

	public void setCandidatEmploye(CandidatEmploye candidatEmploye) {
		this.candidatEmploye = candidatEmploye;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation")
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employe")
	@OrderBy(clause = "dateDemande DESC")
	public List<FinActivite> getFinActivites() {
		return finActivites;
	}

	public void setFinActivites(List<FinActivite> finActivites) {
		this.finActivites = finActivites;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	public Set<Carriere> getCarrieres() {
		return carrieres;
	}

	public void setCarrieres(Set<Carriere> carrieres) {
		this.carrieres = carrieres;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dossierEmploye")
	@OrderBy(clause = "dateDemande DESC")
	public List<ChangementPosition> getChangementPositions() {
		return changementPositions;
	}

	public void setChangementPositions(List<ChangementPosition> changementPositions) {
		this.changementPositions = changementPositions;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_installation", length = 13)
	public Date getDateInstallation() {
		return dateInstallation;
	}

	public void setDateInstallation(Date dateInstallation) {
		this.dateInstallation = dateInstallation;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carriere_courante")
	public Carriere getCarriereCourante() {
		return carriereCourante;
	}

	public void setCarriereCourante(Carriere carriereCourante) {
		this.carriereCourante = carriereCourante;
	}

	// TODO remove nullable=true
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_courante")
	public ChangementPosition getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(ChangementPosition currentPosition) {
		this.currentPosition = currentPosition;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employe")
	public List<ConnaissanceEmploye> getConnaissances() {
		if (connaissances == null) {
			connaissances = new ArrayList<>();
		}
		return connaissances;
	}

	public void setConnaissances(List<ConnaissanceEmploye> connaissances) {
		this.connaissances = connaissances;
	}

	@OneToMany(mappedBy = "employe", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<HabileteEmploye> getHabiletes() {
		if(habiletes == null){
			habiletes =  new ArrayList<HabileteEmploye>();
		}
		return habiletes;
	}

	public void setHabiletes(List<HabileteEmploye> habiletes) {
		this.habiletes = habiletes;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "niveau_competence")
	public Nomenclature getNiveauCompetence() {
		return niveauCompetence;
	}

	public void setNiveauCompetence(Nomenclature niveauCompetence) {
		this.niveauCompetence = niveauCompetence;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "niveau_qualification")
	public Nomenclature getNiveauQualification() {
		return niveauQualification;
	}

	public void setNiveauQualification(Nomenclature niveauQualification) {
		this.niveauQualification = niveauQualification;
	}

	public void addConnaissance(ConnaissanceEmploye connaissanceEmploye) {
		this.getConnaissances().add(connaissanceEmploye);
		connaissanceEmploye.setEmploye(this);
	}

	public void addHabilete(HabileteEmploye habileteEmploye) {
		this.getHabiletes().add(habileteEmploye);
		habileteEmploye.setEmploye(this);

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
		result = prime * result + ((candidatEmploye == null) ? 0 : candidatEmploye.hashCode());
		result = prime * result + ((carriereCourante == null) ? 0 : carriereCourante.hashCode());
		result = prime * result + ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result + ((dateAffiliation == null) ? 0 : dateAffiliation.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((dateInstallation == null) ? 0 : dateInstallation.hashCode());
		result = prime * result + ((dateObtention == null) ? 0 : dateObtention.hashCode());
		result = prime * result + ((dateTitularisation == null) ? 0 : dateTitularisation.hashCode());
		result = prime * result + ((diplomeEmployes == null) ? 0 : diplomeEmployes.hashCode());
		result = prime * result + ((enfants == null) ? 0 : enfants.hashCode());
		result = prime * result + ((finActivites == null) ? 0 : finActivites.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricule == null) ? 0 : matricule.hashCode());
		result = prime * result + ((nomenclatureByTypeCompte == null) ? 0 : nomenclatureByTypeCompte.hashCode());
		result = prime * result + ((nomenclatureByTypePermis == null) ? 0 : nomenclatureByTypePermis.hashCode());
		result = prime * result + ((numeroCompte == null) ? 0 : numeroCompte.hashCode());
		result = prime * result + ((numeross == null) ? 0 : numeross.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((refEtablissement == null) ? 0 : refEtablissement.hashCode());
		result = prime * result + ((refIndividu == null) ? 0 : refIndividu.hashCode());
		result = prime * result + ((refStructure == null) ? 0 : refStructure.hashCode());
		result = prime * result + ((situationEntite == null) ? 0 : situationEntite.hashCode());
		result = prime * result + ((titularise == null) ? 0 : titularise.hashCode());
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
		DossierEmploye other = (DossierEmploye) obj;
		if (candidatEmploye == null) {
			if (other.candidatEmploye != null)
				return false;
		} else if (!candidatEmploye.equals(other.candidatEmploye))
			return false;
		if (carriereCourante == null) {
			if (other.carriereCourante != null)
				return false;
		} else if (!carriereCourante.equals(other.carriereCourante))
			return false;
		if (currentPosition == null) {
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
			return false;
		if (dateAffiliation == null) {
			if (other.dateAffiliation != null)
				return false;
		} else if (!dateAffiliation.equals(other.dateAffiliation))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateInstallation == null) {
			if (other.dateInstallation != null)
				return false;
		} else if (!dateInstallation.equals(other.dateInstallation))
			return false;
		if (dateObtention == null) {
			if (other.dateObtention != null)
				return false;
		} else if (!dateObtention.equals(other.dateObtention))
			return false;
		if (dateTitularisation == null) {
			if (other.dateTitularisation != null)
				return false;
		} else if (!dateTitularisation.equals(other.dateTitularisation))
			return false;
		if (diplomeEmployes == null) {
			if (other.diplomeEmployes != null)
				return false;
		} else if (!diplomeEmployes.equals(other.diplomeEmployes))
			return false;
		if (enfants == null) {
			if (other.enfants != null)
				return false;
		} else if (!enfants.equals(other.enfants))
			return false;
		if (finActivites == null) {
			if (other.finActivites != null)
				return false;
		} else if (!finActivites.equals(other.finActivites))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricule == null) {
			if (other.matricule != null)
				return false;
		} else if (!matricule.equals(other.matricule))
			return false;
		if (nomenclatureByTypeCompte == null) {
			if (other.nomenclatureByTypeCompte != null)
				return false;
		} else if (!nomenclatureByTypeCompte.equals(other.nomenclatureByTypeCompte))
			return false;
		if (nomenclatureByTypePermis == null) {
			if (other.nomenclatureByTypePermis != null)
				return false;
		} else if (!nomenclatureByTypePermis.equals(other.nomenclatureByTypePermis))
			return false;
		if (numeroCompte == null) {
			if (other.numeroCompte != null)
				return false;
		} else if (!numeroCompte.equals(other.numeroCompte))
			return false;
		if (numeross == null) {
			if (other.numeross != null)
				return false;
		} else if (!numeross.equals(other.numeross))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (refEtablissement == null) {
			if (other.refEtablissement != null)
				return false;
		} else if (!refEtablissement.equals(other.refEtablissement))
			return false;
		if (refIndividu == null) {
			if (other.refIndividu != null)
				return false;
		} else if (!refIndividu.equals(other.refIndividu))
			return false;
		if (refStructure == null) {
			if (other.refStructure != null)
				return false;
		} else if (!refStructure.equals(other.refStructure))
			return false;
		if (situationEntite == null) {
			if (other.situationEntite != null)
				return false;
		} else if (!situationEntite.equals(other.situationEntite))
			return false;
		if (titularise == null) {
			if (other.titularise != null)
				return false;
		} else if (!titularise.equals(other.titularise))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DossierEmploye [id=" + id + ", refIndividu=" + refIndividu + ", refEtablissement=" + refEtablissement
				+ ", refStructure=" + refStructure + ", nomenclatureByTypePermis=" + nomenclatureByTypePermis
				+ ", nomenclatureByTypeCompte=" + nomenclatureByTypeCompte + ", matricule=" + matricule + ", numeross="
				+ numeross + ", dateAffiliation=" + dateAffiliation + ", numeroCompte=" + numeroCompte
				+ ", dateObtention=" + dateObtention + ", dateTitularisation=" + dateTitularisation + ", dateCreation="
				+ dateCreation + ", enfants=" + enfants + ", photo=" + photo + ", titularise=" + titularise
				+ ", candidatEmploye=" + candidatEmploye + ", situationEntite=" + situationEntite
				+ ", dateInstallation=" + dateInstallation + ", carriereCourante=" + carriereCourante
				+ ", currentPosition=" + currentPosition + "]";
	}

}
