package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 12 févr. 2015 16:22:41 by Mounir MESSAOUDI Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * Mission generated by hbm2java
 */
@Entity
@Table(name = "mission", schema = "schema")
public class Mission implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numero;
	private Date dateDemande;
	private String objet;
	private Date dateDebut;
	private Date dateFin;

	private Nomenclature typeMission;
	private Nomenclature natureMission;

	private Nomenclature pays;
	private Nomenclature wilaya;
	private String ville;

	private RefEtablissement etablissementNational;
	private RefStructure structureNational;

	private String etablissement;
	private String structure;
	private String adresse;

	private String observation;

	private List<DossierMission> dossierMissions = new ArrayList<DossierMission>(0);
	private SituationEntite situation;

	public Mission() {
	}

	public Mission(int id, String numero, Date dateDemande, String objet, Date dateDebut, Date dateFin) {
		this.id = id;
		this.numero = numero;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	@SequenceGenerator(name = "mission_id_seq", sequenceName = "gfc.mission_id_seq")
	@Id
	@GeneratedValue(generator = "mission_id_seq")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "numero", nullable = false, length = 20)
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_demande", nullable = false, length = 29)
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Column(name = "objet", nullable = false, length = 200)
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_debut", nullable = false, length = 29)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_fin", nullable = false, length = 29)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@ManyToOne()
	@JoinColumn(name = "id_type_mission", nullable = false)
	public Nomenclature getTypeMission() {
		return this.typeMission;
	}

	public void setTypeMission(Nomenclature typeMission) {
		this.typeMission = typeMission;
	}

	@ManyToOne()
	@JoinColumn(name = "id_nature_mission", nullable = false)
	public Nomenclature getNatureMission() {
		return this.natureMission;
	}

	public void setNatureMission(Nomenclature natureMission) {
		this.natureMission = natureMission;
	}

	@ManyToOne()
	@JoinColumn(name = "id_pays", nullable = false)
	public Nomenclature getPays() {
		return this.pays;
	}

	public void setPays(Nomenclature pays) {
		this.pays = pays;
	}

	@ManyToOne()
	@JoinColumn(name = "id_wilaya", nullable = false)
	public Nomenclature getWilaya() {
		return this.wilaya;
	}

	public void setWilaya(Nomenclature wilaya) {
		this.wilaya = wilaya;
	}

	@Column(name = "ville", length = 100)
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@ManyToOne()
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getEtablissementNational() {
		return this.etablissementNational;
	}

	public void setEtablissementNational(RefEtablissement etablissementNational) {
		this.etablissementNational = etablissementNational;
	}

	@ManyToOne()
	@JoinColumn(name = "id_structure", nullable = false)
	public RefStructure getStructureNational() {
		return this.structureNational;
	}

	public void setStructureNational(RefStructure structureNational) {
		this.structureNational = structureNational;
	}

	@Column(name = "etablissement", length = 200)
	public String getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	@Column(name = "structure", length = 200)
	public String getStructure() {
		return this.structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	@Column(name = "adresse", length = 200)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mission")
	public List<DossierMission> getDossierMissions() {
		return this.dossierMissions;
	}

	public void setDossierMissions(List<DossierMission> dossierMissions) {
		this.dossierMissions = dossierMissions;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}