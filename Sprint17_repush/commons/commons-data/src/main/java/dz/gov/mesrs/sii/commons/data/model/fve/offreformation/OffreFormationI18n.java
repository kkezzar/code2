package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

// Generated 7 janv. 2014 14:52:44 by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "offre_formation_i18n", schema = "lmd")

public class OffreFormationI18n implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 18 avr. 2014  14:45:50
	 */
	private static final long serialVersionUID = 8708777445374580892L;
	private int id;
	private OffreFormation offreFormation;
	private String langue;
	private String libelle;
	private String libelleCourt;
	private String specialisation;
	private String description;
	private String libelleDomaine;
	private String libelleFiliere;
	private String libelleSpecialite;
	private String libelleCycle;
	private String libelleVocation;
	private String libelleTypeFormation;
	
	public OffreFormationI18n() {
	}
	
	
	@SequenceGenerator(name="offre_formation_i18n_id_seq_gen", sequenceName="lmd.offre_formation_i18n_id_seq")
	@Id @GeneratedValue(generator="offre_formation_i18n_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [OffreFormationI18n.offreFormation] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @return the offreFormation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_offre_formation", nullable = false)
	public OffreFormation getOffreFormation() {
		return offreFormation;
	}

	/**
	 * [OffreFormationI18n.offreFormation] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @param offreFormation the offreFormation to set
	 */
	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}

	/**
	 * [OffreFormationI18n.langue] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}

	/**
	 * [OffreFormationI18n.langue] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		
		this.langue = langue;
	}

	/**
	 * [OffreFormationI18n.libelle] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @return the libelle
	 */
	public String getLibelle() {
		
		return (this.libelle == null) ?  ""  : libelle;
		
	}

	/**
	 * [OffreFormationI18n.libelle] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @param libelle the libelle to set
	 */
	@Column(name = "libelle", nullable = false,length=255)
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [OffreFormationI18n.libelleCourt] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @return the libelleCourt
	 */
	@Column(name = "libelle_court",length=100)
	public String getLibelleCourt() {
		
		return (this.libelleCourt == null) ?  ""  : libelleCourt;

	}

	/**
	 * [OffreFormationI18n.libelleCourt] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @param libelleCourt the libelleCourt to set
	 */
	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	/**
	 * [OffreFormationI18n.specialisation] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:50
	 * @return the specialisation
	 */
	@Column(name = "specialisation",length=150)
	public String getSpecialisation() {
		
		
		return (this.specialisation == null) ?  ""  : specialisation;

	}

	/**
	 * [OffreFormationI18n.specialisation] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param specialisation the specialisation to set
	 */
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	/**
	 * [OffreFormationI18n.description] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the description
	 */
	@Column(name = "description",length=250)
	public String getDescription() {
		return description;
	}

	/**
	 * [OffreFormationI18n.description] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [OffreFormationI18n.libelleDomaine] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the libelleDomaine
	 */
	@Column(name = "libelle_domaine",length=250)
	public String getLibelleDomaine() {
		
		return (this.libelleDomaine == null) ?  ""  : libelleDomaine;
	}

	/**
	 * [OffreFormationI18n.libelleDomaine] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param libelleDomaine the libelleDomaine to set
	 */
	public void setLibelleDomaine(String libelleDomaine) {
		this.libelleDomaine = libelleDomaine;
	}

	/**
	 * [OffreFormationI18n.libelleFiliere] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the libelleFiliere
	 */
	@Column(name = "libelle_filiere",length=250)
	public String getLibelleFiliere() {
		
		return (this.libelleFiliere == null) ?  ""  : libelleFiliere;

	}

	/**
	 * [OffreFormationI18n.libelleFiliere] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param libelleFiliere the libelleFiliere to set
	 */
	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}

	/**
	 * [OffreFormationI18n.libelleSpecialite] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the libelleSpecialite
	 */
	@Column(name = "libelle_specialite",length=250)
	public String getLibelleSpecialite() {
		
		return (this.libelleSpecialite == null) ?  ""  : libelleSpecialite;

	}

	/**
	 * [OffreFormationI18n.libelleSpecialite] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param libelleSpecialite the libelleSpecialite to set
	 */
	public void setLibelleSpecialite(String libelleSpecialite) {
		this.libelleSpecialite = libelleSpecialite;
	}

	/**
	 * [OffreFormationI18n.libelleCycle] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the libelleCycle
	 */
	@Column(name = "libelle_cycle",length=50)
	public String getLibelleCycle() {
		
		return (this.libelleCycle == null) ?  ""  : libelleCycle;

	}

	/**
	 * [OffreFormationI18n.libelleCycle] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param libelleCycle the libelleCycle to set
	 */
	public void setLibelleCycle(String libelleCycle) {
		this.libelleCycle = libelleCycle;
	}

	/**
	 * [OffreFormationI18n.libelleVocation] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the libelleVocation
	 */
	@Column(name = "libelle_vocation",length=50)
	public String getLibelleVocation() {
		
		return (this.libelleVocation == null) ?  ""  : libelleVocation;

	}

	/**
	 * [OffreFormationI18n.libelleVocation] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param libelleVocation the libelleVocation to set
	 */
	public void setLibelleVocation(String libelleVocation) {
		this.libelleVocation = libelleVocation;
	}

	/**
	 * [OffreFormationI18n.libelleTypeFormation] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @return the libelleTypeFormation
	 */
	@Column(name = "libelle_type_formation",length=50)
	public String getLibelleTypeFormation() {
		
		return (this.libelleTypeFormation == null) ?  ""  : libelleTypeFormation;

	}

	/**
	 * [OffreFormationI18n.libelleTypeFormation] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  14:35:51
	 * @param libelleTypeFormation the libelleTypeFormation to set
	 */
	public void setLibelleTypeFormation(String libelleTypeFormation) {
		this.libelleTypeFormation = libelleTypeFormation;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationI18n.hashCode] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 19 avr. 2014  16:05:32
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationI18n.equals] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 19 avr. 2014  16:05:32
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OffreFormationI18n other = (OffreFormationI18n) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	
	
}
