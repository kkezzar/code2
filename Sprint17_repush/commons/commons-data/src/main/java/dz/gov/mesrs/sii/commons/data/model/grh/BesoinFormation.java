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
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "besoin_formation", schema = "grh")
public class BesoinFormation implements java.io.Serializable ,
Identifiable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SituationEntite situationEntite;
	private Nomenclature nomenclature;
	private RefEtablissement refEtablissement;
	private RefGroupe refGroupe;
	private RefStructure refStructure;
	private DossierEmploye dossierEmploye;
	private Date dateDebut;
	private Date dateFin;
	private String objectif;
	private Date dateEvaluation;
	private String objectifAtteint;
	private Set<DetailBesoinFormation> detailBesoinFormations = new HashSet<DetailBesoinFormation>(0);

	public BesoinFormation() {
	}

	public BesoinFormation(Integer id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name = "besoin_formation_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "besoin_formation_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation")
	public SituationEntite getSituationEntite() {
		return this.situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_besoin")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etablissement")
	public RefEtablissement getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupe")
	public RefGroupe getRefGroupe() {
		return this.refGroupe;
	}

	public void setRefGroupe(RefGroupe refGroupe) {
		this.refGroupe = refGroupe;
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
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 13)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "objectif")
	public String getObjectif() {
		return this.objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_evaluation", length = 13)
	public Date getDateEvaluation() {
		return this.dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	@Column(name = "objectif_atteint")
	public String getObjectifAtteint() {
		return this.objectifAtteint;
	}

	public void setObjectifAtteint(String objectifAtteint) {
		this.objectifAtteint = objectifAtteint;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "besoinFormation", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<DetailBesoinFormation> getDetailBesoinFormations() {
		return this.detailBesoinFormations;
	}

	public void setDetailBesoinFormations(Set<DetailBesoinFormation> detailBesoinFormations) {
		this.detailBesoinFormations = detailBesoinFormations;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
