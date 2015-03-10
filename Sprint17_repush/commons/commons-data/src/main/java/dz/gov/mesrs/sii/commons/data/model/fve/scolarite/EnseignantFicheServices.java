/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantFicheServices.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */
@Entity
@Table(name = "enseignant_fiche_services", schema = "fve_scolarite")
public class EnseignantFicheServices  implements java.io.Serializable  {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:05:18
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private EnseignantFicheVoeux enseignantFicheVoeux;
	private SituationEntite situation;
	private Set<EnseignantChargePedagogique> enseignantChargePedagogiques = new HashSet<EnseignantChargePedagogique>(0);

	public EnseignantFicheServices() {
		
	}


	/**
	 * [EnseignantFicheServices.id] Getter 
	 * @author rlaib on : 22 oct. 2014  11:59:05
	 * @return the id
	 */
	@Id 
	@Column(name = "id")
	public int getId() {
		return id;
	}


	/**
	 * [EnseignantFicheServices.id] Setter 
	 * @author rlaib on : 22 oct. 2014  11:59:05
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [EnseignantFicheServices.enseignantFicheVoeux] Getter 
	 * @author rlaib on : 22 oct. 2014  11:59:05
	 * @return the enseignantFicheVoeux
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_fiche_voeux", nullable = false)
	public EnseignantFicheVoeux getEnseignantFicheVoeux() {
		return enseignantFicheVoeux;
	}


	/**
	 * [EnseignantFicheServices.enseignantFicheVoeux] Setter 
	 * @author rlaib on : 22 oct. 2014  11:59:05
	 * @param enseignantFicheVoeux the enseignantFicheVoeux to set
	 */
	public void setEnseignantFicheVoeux(EnseignantFicheVoeux enseignantFicheVoeux) {
		this.enseignantFicheVoeux = enseignantFicheVoeux;
	}


	/**
	 * [EnseignantFicheServices.situation] Getter 
	 * @author rlaib on : 22 oct. 2014  11:59:48
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}


	/**
	 * [EnseignantFicheServices.situation] Setter 
	 * @author rlaib on : 22 oct. 2014  11:59:48
	 * @param situation the situation to set
	 */
	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}


	/**
	 * [EnseignantFicheServices.enseignantChargePedagogiques] Getter 
	 * @author rlaib on : 24 oct. 2014  17:26:53
	 * @return the enseignantChargePedagogiques
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enseignantFicheServices", targetEntity= EnseignantChargePedagogique.class, cascade = CascadeType.ALL)
	public Set<EnseignantChargePedagogique> getEnseignantChargePedagogiques() {
		return enseignantChargePedagogiques;
	}


	/**
	 * [EnseignantFicheServices.enseignantChargePedagogiques] Setter 
	 * @author rlaib on : 24 oct. 2014  17:26:53
	 * @param enseignantChargePedagogiques the enseignantChargePedagogiques to set
	 */
	public void setEnseignantChargePedagogiques(
			Set<EnseignantChargePedagogique> enseignantChargePedagogiques) {
		this.enseignantChargePedagogiques = enseignantChargePedagogiques;
	}
	
	
	
}
