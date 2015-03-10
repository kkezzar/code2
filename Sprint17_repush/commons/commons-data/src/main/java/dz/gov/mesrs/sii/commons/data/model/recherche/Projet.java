package dz.gov.mesrs.sii.commons.data.model.recherche;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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


/**
 * @author slimane ramdane   on : 02 fev. 2015 15:33:48
 */
@Entity
@Table(name = "projet", schema = "recherche")
public class Projet implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author slimane ramdane  on : 01 fev. 2015 15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private Date dateDebutReel;
	private Date dateFinReel;
	private Date dateDebutPrev;
	private Date dateFinPrev;
	private String objectif;
	private String justification;
	private Date dateCloture;
	private String observation;
	private Date dateIdentification;
	private SituationEntite situation;
	private Nomenclature typeCloture;
	private Programme programe;
	private RefEtablissement etablissement;	
	private List<Motcle> motcles;
	private List<ActiviteProjet> activiteProjets;
	private Theme theme;
	private Groupe groupe;
	private List<IndEvaluation> indEvaluations;
	private List<ProjetPartenaire> projetPartenaires;
	private List<EtapeValidation>  etapeValidations;
	private Structure structure;
	//private Partenaire partenaire;
	public Projet() {
		
	}

	
	@Id
	@SequenceGenerator(name="projet_id_seq_gen", sequenceName="recherche.projet_id_seq")
	@GeneratedValue(generator="projet_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "code",length=50, nullable=false)
	public String getCode() {
		return code;
	}

	
	

	public void setCode(String code) {
		this.code = code;
	}

	
	@Column(name = "intitule_fr",length=250, nullable=false)
	public String getIntituleFr() {
		return intituleFr;
	}

	
	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	
	@Column(name = "intitule_ar",length=250)
	public String getIntituleAr() {
		return intituleAr;
	}

	
	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_reel", nullable=false)
	public Date getDateDebutReel() {
		return dateDebutReel;
	}


	public void setDateDebutReel(Date dateDebutReel) {
		this.dateDebutReel = dateDebutReel;
	}
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_reel", nullable=false)
	public Date getDateFinReel() {
		return dateFinReel;
	}


	public void setDateFinReel(Date dateFinReel) {
		this.dateFinReel = dateFinReel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_prev", nullable=false)
	public Date getDateDebutPrev() {
		return dateDebutPrev;
	}


	public void setDateDebutPrev(Date dateDebutPrev) {
		this.dateDebutPrev = dateDebutPrev;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_prev", nullable=false)
	public Date getDateFinPrev() {
		return dateFinPrev;
	}


	public void setDateFinPrev(Date dateFinPrev) {
		this.dateFinPrev = dateFinPrev;
	}

	@Column(name = "objectif")
	public String getObjectif() {
		return objectif;
	}


	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	@Column(name = "justification")
	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cloture", nullable=false)
	public Date getDateCloture() {
		return dateCloture;
	}


	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	@Column(name = "observation")
	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_identification", nullable=false)
	public Date getDateIdentification() {
		return dateIdentification;
	}


	public void setDateIdentification(Date dateIdentification) {
		this.dateIdentification = dateIdentification;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}


	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_cloture", nullable = false)
	public Nomenclature getTypeCloture() {
		return typeCloture;
	}


	


	public void setTypeCloture(Nomenclature typeCloture) {
		this.typeCloture = typeCloture;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programe", nullable = false)
	public Programme getPrograme() {
		return programe;
	}


	public void setPrograme(Programme programe) {
		this.programe = programe;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "projet")
	public List<Motcle> getMotcles() {
		return motcles;
	}


	public void setMotcles(List<Motcle> motcles) {
		this.motcles = motcles;
	}


	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "projet")
	public List<ActiviteProjet> getActiviteProjets() {
		return activiteProjets;
	}


	public void setActiviteProjets(List<ActiviteProjet> activiteProjets) {
		this.activiteProjets = activiteProjets;
	}

	


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_theme")
	public Theme getTheme() {
		return theme;
	}


	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe")
	public Groupe getGroupe() {
		return groupe;
	}


	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}


	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "projet")
	public List<IndEvaluation> getIndEvaluations() {
		return indEvaluations;
	}


	public void setIndEvaluations(List<IndEvaluation> indEvaluations) {
		this.indEvaluations = indEvaluations;
	}


	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author sram  on : 02 fev. 2015  11:02:27
	 * @return
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getEtablissement() {
		return etablissement;
	}


	public void setEtablissement(RefEtablissement etablissement) {
		this.etablissement = etablissement;
	}
	
	
	@OneToMany(orphanRemoval = true,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "projet")
	public List<ProjetPartenaire> getProjetPartenaires() {
		return projetPartenaires;
	}


	public void setProjetPartenaires(List<ProjetPartenaire> projetPartenaires) {
		this.projetPartenaires = projetPartenaires;
	}
	
	
	@OneToMany(orphanRemoval = false,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "projet")
	public List<EtapeValidation> getEtapeValidations() {
		return etapeValidations;
	}


	public void setEtapeValidations(List<EtapeValidation> etapeValidations) {
		this.etapeValidations = etapeValidations;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public Structure getStructure() {
		return structure;
	}


	public void setStructure(Structure structure) {
		this.structure = structure;
	}


	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	


	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author sram  on : 02 fev. 2015  11:02:27
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
