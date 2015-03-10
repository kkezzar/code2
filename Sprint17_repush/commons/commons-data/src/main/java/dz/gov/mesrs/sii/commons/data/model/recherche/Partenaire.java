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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPartenaire;


/**
 * @author rlaib  on : 15 d�c. 2014  15:33:40
 */
@Entity
@Table(name = "partenaire", schema = "recherche")
public class Partenaire implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Structure structure;
	private RefContrat refContrat;
	private RefPartenaire refPartenaire;
	private Date dateDebut;
	private Date dateFin;
	private String description;
	private List<ProjetPartenaire> projetPartenaires;
		
	public Partenaire() {
		
	}

	/**
	 * [Partenaire.Partenaire()] Constructor
	 * @author rlaib  on : 15 d�c. 2014  15:51:12
	 * @param structure
	 * @param refContrat
	 * @param refPartenaire
	 * @param dateDebut
	 * @param dateFin
	 * @param description	
	 */
	public Partenaire(Structure structure, RefContrat refContrat,
			RefPartenaire refPartenaire, Date dateDebut, Date dateFin,
			String description) {
		super();
		this.structure = structure;
		this.refContrat = refContrat;
		this.refPartenaire = refPartenaire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
	}
	

	/**
	 * [Partenaire.id] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="partenaire_id_seq_gen", sequenceName="recherche.partenaire_id_seq")
	@GeneratedValue(generator="partenaire_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Partenaire.id] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [Partenaire.structure] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @return the structure
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public Structure getStructure() {
		return structure;
	}

	/**
	 * [Partenaire.structure] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @param structure the structure to set
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	/**
	 * [Partenaire.refContrat] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @return the refContrat
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ref_contrat", nullable = false)
	public RefContrat getRefContrat() {
		return refContrat;
	}

	/**
	 * [Partenaire.refContrat] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @param refContrat the refContrat to set
	 */
	public void setRefContrat(RefContrat refContrat) {
		this.refContrat = refContrat;
	}

	/**
	 * [Partenaire.refPartenaire] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @return the refPartenaire
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ref_partenaire", nullable = false)
	public RefPartenaire getRefPartenaire() {
		return refPartenaire;
	}

	/**
	 * [Partenaire.refPartenaire] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @param refPartenaire the refPartenaire to set
	 */
	public void setRefPartenaire(RefPartenaire refPartenaire) {
		this.refPartenaire = refPartenaire;
	}

	/**
	 * [Partenaire.dateDebut] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @return the dateDebut
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", nullable=false)
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [Partenaire.dateDebut] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [Partenaire.dateFin] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @return the dateFin
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin")
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [Partenaire.dateFin] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [Partenaire.description] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @return the description
	 */
	@Column(name = "description",length=250)
	public String getDescription() {
		return description;
	}

	/**
	 * [Partenaire.description] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:47:38
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * @author Rafik  on : 17 déc. 2014  20:37:37
	 * @return
	 */
	
		
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partenaire")
	public List<ProjetPartenaire> getProjetPartenaires() {
		return projetPartenaires;
	}


	public void setProjetPartenaires(List<ProjetPartenaire> projetPartenaires) {
		this.projetPartenaires = projetPartenaires;
	}
	
	
	
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}
	

	

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * @author Rafik  on : 17 déc. 2014  20:37:37
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
}
