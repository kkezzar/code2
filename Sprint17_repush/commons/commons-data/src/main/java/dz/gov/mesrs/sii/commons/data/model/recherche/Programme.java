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
import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;


/**
 * @author rlaib  on : 15 d�c. 2014  15:33:48
 */
@Entity
@Table(name = "programme", schema = "recherche")
public class Programme implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private Date dateDebut;
	private Date dateFin;
	private Nomenclature typeProgramme;
	private Processus circuitValidation;
		
	public Programme() {
		
	}

	/**
	 * [Programme.id] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="programme_id_seq_gen", sequenceName="recherche.programme_id_seq")
	@GeneratedValue(generator="programme_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Programme.id] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [Programme.code] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the code
	 */
	@Column(name = "code",length=50, nullable=false)
	public String getCode() {
		return code;
	}

	/**
	 * [Programme.code] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [Programme.intituleFr] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the intituleFr
	 */
	@Column(name = "intitule_fr",length=250, nullable=false)
	public String getIntituleFr() {
		return intituleFr;
	}

	/**
	 * [Programme.intituleFr] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param intituleFr the intituleFr to set
	 */
	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	/**
	 * [Programme.intituleAr] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the intituleAr
	 */
	@Column(name = "intitule_ar",length=250)
	public String getIntituleAr() {
		return intituleAr;
	}

	/**
	 * [Programme.intituleAr] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param intituleAr the intituleAr to set
	 */
	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	/**
	 * [Programme.dateDebut] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the dateDebut
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", nullable=false)
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [Programme.dateDebut] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [Programme.dateFin] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the dateFin
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", nullable=false)
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [Programme.dateFin] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [Programme.typeProgramme] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the typeProgramme
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_programme", nullable = false)
	public Nomenclature getTypeProgramme() {
		return typeProgramme;
	}

	/**
	 * [Programme.typeProgramme] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param typeProgramme the typeProgramme to set
	 */
	public void setTypeProgramme(Nomenclature typeProgramme) {
		this.typeProgramme = typeProgramme;
	}

	/**
	 * [Programme.circuitValidation] Getter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @return the circuitValidation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_processus", nullable = false)
	public Processus getCircuitValidation() {
		return circuitValidation;
	}

	/**
	 * [Programme.circuitValidation] Setter 
	 * @author rlaib on : 25 janv. 2015  11:02:05
	 * @param circuitValidation the circuitValidation to set
	 */
	public void setCircuitValidation(Processus circuitValidation) {
		this.circuitValidation = circuitValidation;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  11:02:27
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  11:02:27
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
