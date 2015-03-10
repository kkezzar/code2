/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationEquipe.java] 
 * @author rlaib on : 6 fevr. 2014  12:22:02
 */
package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

/**
 * @author rlaib  on : 25 mars 2014  09:51:17
 */
@Entity
@Table(name = "offre_formation_equipe", schema = "lmd")
public class OffreFormationEquipe  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private OffreFormation offreFormation;
	private Date dateCreation;
	private Date dateDerniereModif;
	private String libelleCourtFr;
	private String libelleCourtAr;
	private Set<OffreFormationEquipeMembre> offreFormationEquipeMembres = new HashSet<OffreFormationEquipeMembre>(0);

	public OffreFormationEquipe() {
	}

	public OffreFormationEquipe(int id, String code, String libelleFr, String libelleAr) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
	}

	/**
	 * [OffreFormationEquipe.id] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the id
	 */
	
	@SequenceGenerator(name="offre_formation_equipe_id_seq_gen", sequenceName="lmd.offre_formation_equipe_id_seq")
	@Id @GeneratedValue(generator="offre_formation_equipe_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [OffreFormationEquipe.id] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [OffreFormationEquipe.code] Getter 
	 * @author rlaib on : 25 mars 2014  09:52:49
	 * @return the code
	 */
	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return code;
	}

	/**
	 * [OffreFormationEquipe.code] Setter 
	 * @author rlaib on : 25 mars 2014  09:52:49
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [OffreFormationEquipe.libelleFr] Getter 
	 * @author rlaib on : 25 mars 2014  09:47:25
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr")
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [OffreFormationEquipe.libelleFr] Setter 
	 * @author rlaib on : 25 mars 2014  09:47:25
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [OffreFormationEquipe.libelleAr] Getter 
	 * @author rlaib on : 25 mars 2014  09:47:25
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar")
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [OffreFormationEquipe.libelleAr] Setter 
	 * @author rlaib on : 25 mars 2014  09:47:25
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [OffreFormationDetailContent.offreFormation] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:47:47
	 * @return the offreFormation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "  id_offre_formation")
	public OffreFormation getOffreFormation() {
		return offreFormation;
	}

	/**
	 * [OffreFormationDetailContent.offreFormation] Setter 
	 * @author rlaib on : 6 f�vr. 2014  12:47:47
	 * @param offreFormation the offreFormation to set
	 */
	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}

	/**
	 * [OffreFormationEquipe.getDateCreation] Method 
	 * @author rlaib  on : 25 mars 2014  09:50:15
	 * @return
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 29)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [OffreFormationEquipe.getDateDerniereModif] Method 
	 * @author rlaib  on : 25 mars 2014  09:51:14
	 * @return
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_derniere_modif", length = 29)
	public Date getDateDerniereModif() {
		return this.dateDerniereModif;
	}

	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}

	/**
	 * [OffreFormationEquipe.libelleCourtFr] Getter 
	 * @author rlaib on : 25 mars 2014  15:15:40
	 * @return the libelleCourtFr
	 */
	@Column(name = "libelle_court_fr",length=10)
	public String getLibelleCourtFr() {
		return libelleCourtFr;
	}

	/**
	 * [OffreFormationEquipe.libelleCourtFr] Setter 
	 * @author rlaib on : 25 mars 2014  15:15:40
	 * @param libelleCourtFr the libelleCourtFr to set
	 */
	public void setLibelleCourtFr(String libelleCourtFr) {
		this.libelleCourtFr = libelleCourtFr;
	}

	/**
	 * [OffreFormationEquipe.libelleCourtAr] Getter 
	 * @author rlaib on : 25 mars 2014  15:15:54
	 * @return the libelleCourtAr
	 */
	@Column(name = "libelle_court_ar")
	public String getLibelleCourtAr() {
		return libelleCourtAr;
	}

	/**
	 * [OffreFormationEquipe.libelleCourtAr] Setter 
	 * @author rlaib on : 25 mars 2014  15:15:54
	 * @param libelleCourtAr the libelleCourtAr to set
	 */
	public void setLibelleCourtAr(String libelleCourtAr) {
		this.libelleCourtAr = libelleCourtAr;
	}

	/**
	 * [OffreFormationEquipe.offreFormationEquipeMembres] Getter 
	 * @author rlaib on : 25 mars 2014  10:27:35
	 * @return the offreFormationEquipeMembres
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "offreFormationEquipe")
	public Set<OffreFormationEquipeMembre> getOffreFormationEquipeMembres() {
		return offreFormationEquipeMembres;
	}

	/**
	 * [OffreFormationEquipe.offreFormationEquipeMembres] Setter 
	 * @author rlaib on : 25 mars 2014  10:27:35
	 * @param offreFormationEquipeMembres the offreFormationEquipeMembres to set
	 */
	public void setOffreFormationEquipeMembres(
			Set<OffreFormationEquipeMembre> offreFormationEquipeMembres) {
		this.offreFormationEquipeMembres = offreFormationEquipeMembres;
	}

	/**
	 * [OffreFormationEquipe.toString] Method 
	 * @author rlaib  on : 1 avr. 2014  10:12:47
	 * @return
	 */
	@Override
	public String toString() {
	
		return this.libelleFr;
	}

	
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationEquipe.hashCode] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  20:46:08
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}


	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationEquipe.equals] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  20:46:08
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
		OffreFormationEquipe other = (OffreFormationEquipe) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	
}
