package dz.gov.mesrs.sii.commons.data.model.recherche;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;


/**
 * @author rlaib  on : 15 dec. 2014  15:33:48
 */
@Entity
@Table(name = "theme", schema = "recherche")
public class Theme implements java.io.Serializable, Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Structure structure; 
	private Groupe groupe;
	private Nomenclature ncTheme;
	private Date dateDebut;
	private Date dateFin;
	private String description;
	public Theme() {
		
	}

	/**
	 * [Theme.Theme()] Constructor
	 * @author rlaib  on : 15 d�c. 2014  16:09:32
	 * @param structure
	 * @param groupe
	 * @param ncTheme
	 * @param dateDebut
	 * @param dateFin
	 * @param description	
	 */
	public Theme(Structure structure, Groupe groupe, Nomenclature ncTheme,
			Date dateDebut, Date dateFin, String description) {
		super();
		this.structure = structure;
		this.groupe = groupe;
		this.ncTheme = ncTheme;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
	}
	



	/**
	 * [Theme.id] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="theme_id_seq_gen", sequenceName="recherche.theme_id_seq")
	@GeneratedValue(generator="theme_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Theme.id] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * [Theme.structure] Getter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @return the structure
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public Structure getStructure() {
		return structure;
	}
	

	/**
	 * [Theme.structure] Setter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @param structure the structure to set
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	

	/**
	 * [Theme.groupe] Getter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @return the groupe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe", nullable = false)
	public Groupe getGroupe() {
		return groupe;
	}
	

	/**
	 * [Theme.groupe] Setter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @param groupe the groupe to set
	 */
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	

	/**
	 * [Theme.ncTheme] Getter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @return the ncTheme
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_theme", nullable = false)
	public Nomenclature getNcTheme() {
		return ncTheme;
	}
	

	/**
	 * [Theme.ncTheme] Setter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @param ncTheme the ncTheme to set
	 */
	public void setNcTheme(Nomenclature ncTheme) {
		this.ncTheme = ncTheme;
	}
	

	/**
	 * [Theme.dateDebut] Getter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @return the dateDebut
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", nullable=false)
	public Date getDateDebut() {
		return dateDebut;
	}
	

	/**
	 * [Theme.dateDebut] Setter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	

	/**
	 * [Theme.dateFin] Getter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @return the dateFin
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin")
	public Date getDateFin() {
		return dateFin;
	}
	

	/**
	 * [Theme.dateFin] Setter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	

	/**
	 * [Theme.description] Getter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
	 * @return the description
	 */
	@Column(name = "description",length=250)
	public String getDescription() {
		return description;
	}
	

	/**
	 * [Theme.description] Setter 
	 * @author rlaib on : 15 d�c. 2014  16:03:41
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
